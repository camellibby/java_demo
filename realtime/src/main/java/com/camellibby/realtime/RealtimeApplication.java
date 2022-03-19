package com.camellibby.realtime;

import io.confluent.connect.jdbc.sink.JdbcSinkTask;
import io.debezium.data.Envelope;
import io.debezium.engine.ChangeEvent;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.format.Json;
import io.debezium.transforms.ExtractNewRecordState;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.data.Timestamp;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.transforms.ReplaceField;

import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author luoxinliang
 */
public class RealtimeApplication {
    public static void main(String[] args) {
        RealtimeApplication app = new RealtimeApplication();
        new Thread(app::source).start();
    }

    public void source() {
        Properties props = new Properties();
        try {
            props.load(RealtimeApplication.class.getResourceAsStream("/app.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create the engine with this configuration ...
        try (DebeziumEngine<ChangeEvent<String, String>> engine = DebeziumEngine.create(Json.class)
                .using(props)
                .notifying(System.out::println).build()
        ) {
            // Run the engine asynchronously ...
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(engine);

            // Do something else or wait for a signal or an event
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void sink() {
        final Map<String, String> props = new HashMap<>();
        props.put("name", "engine");
        props.put("connector.class", "io.confluent.connect.jdbc.JdbcSinkConnector");
        props.put("connection.url", "jdbc:mysql://192.168.10.111:3306/abc");
        props.put("connection.user", "root");
        props.put("connection.password", "oracle");
        props.put("table.name.format", "abc.demo3_copy1");
        props.put("tasks.max", "1");
        props.put("confluent.topic.bootstrap.servers", "localhost:9092");
        props.put("topics", "mysql71.abc.person");
        props.put("transforms", "ExtractNewRecordState,ReplaceKeyField,ReplaceValueField");
        props.put("transforms.unwrap.type", "io.debezium.transforms.ExtractNewRecordState");
        props.put("transforms.unwrap.drop.tombstones", "false");
        props.put("transforms.ReplaceKeyField.type", "org.apache.kafka.connect.transforms.ReplaceField$Key");
        props.put("transforms.ReplaceKeyField.include", "a,b,c,d");
        props.put("transforms.ReplaceKeyField.renames", "a:a,b:b,c:c,d:d");
        props.put("transforms.ReplaceValueField.type", "org.apache.kafka.connect.transforms.ReplaceField$Value");
        props.put("transforms.ReplaceValueField.include", "a,b,c,d");
        props.put("transforms.ReplaceValueField.renames", "a:a,b:b,c:c,d:d");
        props.put("auto.create", "true");
        props.put("insert.mode", "upsert");
        props.put("delete.enabled", "true");
        props.put("pk.fields", "id");
        props.put("pk.mode", "record_key");

        JdbcSinkTask task = new JdbcSinkTask();
        task.start(props);

        final Schema SCHEMA = SchemaBuilder.struct().name("com.example.Person")
                .field("firstName", Schema.STRING_SCHEMA)
                .field("lastName", Schema.STRING_SCHEMA)
                .field("age", Schema.OPTIONAL_INT32_SCHEMA)
                .field("bool", Schema.OPTIONAL_BOOLEAN_SCHEMA)
                .field("short", Schema.OPTIONAL_INT16_SCHEMA)
                .field("byte", Schema.OPTIONAL_INT8_SCHEMA)
                .field("long", Schema.OPTIONAL_INT64_SCHEMA)
                .field("float", Schema.OPTIONAL_FLOAT32_SCHEMA)
                .field("double", Schema.OPTIONAL_FLOAT64_SCHEMA)
                .field("modified", Timestamp.SCHEMA)
                .build();

        final Struct struct = new Struct(SCHEMA)
                .put("firstName", "Alex")
                .put("lastName", "Smith")
                .put("bool", true)
                .put("short", (short) 1234)
                .put("byte", (byte) -32)
                .put("long", 12425436L)
                .put("float", (float) 2356.3)
                .put("double", -2436546.56457)
                .put("age", 21)
                .put("modified", new Date(1474661402123L));
        task.put(Collections.singleton(new SinkRecord("test_topic", 1, null, null, SCHEMA, struct, 42)));
    }

    public void sinkConfigTest() {
        try (final ExtractNewRecordState<SourceRecord> transform = new ExtractNewRecordState<>();
            final ReplaceField.Value<SourceRecord> transform2 = new ReplaceField.Value<>()) {
            final Map<String, String> props00 = new HashMap<>();
            props00.put("add.fields", "before.id,before.name,after.id,after.name");
            props00.put("add.headers", "op");
            transform.configure(props00);

            final Map<String, String> props01 = new HashMap<>();
            props01.put("renames", "id:pid,name:pname");
            props01.put("include", "id,name");
            transform2.configure(props01);

            final SourceRecord createRecord0 = createCreateRecord();
            SourceRecord unwrapped0 = transform.apply(createRecord0);
            unwrapped0 = transform2.apply(unwrapped0);
            Object ts_ms0 = unwrapped0.value();


            final Map<String, String> props1 = new HashMap<>();
            props1.put("add.fields", "ts_ms");
            transform.configure(props1);
            final SourceRecord createRecord1 = createCreateRecord();
            final SourceRecord unwrapped1 = transform.apply(createRecord1);
            Object ts_ms1 = ((Struct) unwrapped1.value()).get("__ts_ms");

            final Map<String, String> props2 = new HashMap<>();
            props2.put("add.fields", "source.ts_ms");
            transform.configure(props2);
            final SourceRecord createRecord2 = createCreateRecord();
            final SourceRecord unwrapped2 = transform.apply(createRecord2);
            Object ts_ms2 = ((Struct) unwrapped2.value()).get("__source_ts_ms");
        }
    }

    private SourceRecord createCreateRecord() {
        final Schema recordSchema = SchemaBuilder.struct()
                .field("id", Schema.INT32_SCHEMA)
                .field("name", Schema.STRING_SCHEMA)
                .field("age", Schema.INT32_SCHEMA)
                .field("mark", Schema.STRING_SCHEMA)
                .build();
        final Schema sourceSchema = SchemaBuilder.struct()
                .field("lsn", Schema.INT32_SCHEMA)
                .field("ts_ms", Schema.OPTIONAL_INT32_SCHEMA)
                .build();
        final Envelope envelope = Envelope.defineSchema()
                .withName("dummy.Envelope")
                .withRecord(recordSchema)
                .withSource(sourceSchema)
                .build();

        final Struct before = new Struct(recordSchema);
        final Struct after = new Struct(recordSchema);
        final Struct record = new Struct(recordSchema);
        final Struct source = new Struct(sourceSchema);

        before.put("id", 1);
        before.put("name", "Jeffrey");
        before.put("age", 19);
        before.put("mark", "He is a good man");
        after.put("id", 1);
        after.put("name", "Libby");
        after.put("age", 18);
        after.put("mark", "She is a god");
        source.put("lsn", 1234);
        source.put("ts_ms", 12836);
        final Struct payload = envelope.update(before, after, source, Instant.now());
        return new SourceRecord(new HashMap<>(), new HashMap<>(), "dummy", envelope.schema(), payload);
    }
}
