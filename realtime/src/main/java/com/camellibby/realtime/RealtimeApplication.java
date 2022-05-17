package com.camellibby.realtime;

import io.confluent.connect.jdbc.JdbcSinkConnector;
import io.confluent.connect.jdbc.sink.JdbcSinkTask;
import io.debezium.config.Configuration;
import io.debezium.data.Envelope;
import io.debezium.embedded.Connect;
import io.debezium.engine.ChangeEvent;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.format.Json;
import io.debezium.transforms.ExtractNewRecordState;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.connect.connector.ConnectorContext;
import org.apache.kafka.connect.data.Schema;
import org.apache.kafka.connect.data.SchemaBuilder;
import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.json.JsonConverter;
import org.apache.kafka.connect.sink.SinkRecord;
import org.apache.kafka.connect.sink.SinkTask;
import org.apache.kafka.connect.sink.SinkTaskContext;
import org.apache.kafka.connect.source.SourceRecord;
import org.apache.kafka.connect.transforms.ReplaceField;
import org.apache.kafka.connect.transforms.Transformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author luoxinliang
 */

public class RealtimeApplication {
    Logger log = LoggerFactory.getLogger(RealtimeApplication.class);
    Queue<SourceRecord> sourceRecords = new LinkedList<>();

    public static void main(String[] args) {
        RealtimeApplication app = new RealtimeApplication();
        app.pipeline();
    }

    private void pipeline() {
        Properties propsSource = new Properties();
        Properties propsSink = new Properties();
        try {
            propsSource.load(this.getClass().getResourceAsStream("/source.properties"));
            propsSink.load(this.getClass().getResourceAsStream("/sink.properties"));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        RealtimeApplication app = new RealtimeApplication();
        try {
            // Run the engine asynchronously ...
            ExecutorService executor = Executors.newScheduledThreadPool(2);
            executor.execute(app.getSourceThread(propsSource));
            executor.execute(app.getSinkThread(propsSink));
            executor.awaitTermination(300, TimeUnit.MINUTES);

            // Do something else or wait for a signal or an event
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

    private Runnable getSourceThread(Properties props) {
        JsonConverter keyConverter = new JsonConverter();
        keyConverter.configure(Collections.emptyMap(), true);
        JsonConverter valueConverter = new JsonConverter();
        valueConverter.configure(Collections.emptyMap(), false);
        // Create the engine with this configuration ...
        DebeziumEngine<ChangeEvent<SourceRecord, SourceRecord>> engine = DebeziumEngine.create(Connect.class)
                .using(props)
                .notifying((x) -> {
                    if (x.value().valueSchema().field("op") == null) {
                        return;
                    }
                    sourceRecords.add(x.value());
                }).build();
        return engine;
    }

    private Runnable getSinkThread(Properties props) {
        return new ConfluentEngine(props);
    }

    private class ConfluentEngine implements Runnable {
        private JdbcSinkConnector connector;
        private SinkTask sinkTask;
        private Transformation<SinkRecord> transformation;
        private Configuration config;

        public ConfluentEngine(Properties props) {
            this.config = Configuration.from(props);
            this.connector = new JdbcSinkConnector();
            this.sinkTask = new JdbcSinkTask();
            this.transformation = new ExtractNewRecordState<>();
            initialize();
        }

        private void initialize() {
            this.connector.initialize(new ConnectorContext() {
                @Override
                public void requestTaskReconfiguration() {

                }

                @Override
                public void raiseError(Exception e) {
                    log.error(e.getMessage(), e);
                }
            });
            this.sinkTask.initialize(new SinkTaskContext() {
                @Override
                public Map<String, String> configs() {
                    return null;
                }

                @Override
                public void offset(Map<TopicPartition, Long> offsets) {

                }

                @Override
                public void offset(TopicPartition tp, long offset) {

                }

                @Override
                public void timeout(long timeoutMs) {

                }

                @Override
                public Set<TopicPartition> assignment() {
                    return null;
                }

                @Override
                public void pause(TopicPartition... partitions) {

                }

                @Override
                public void resume(TopicPartition... partitions) {

                }

                @Override
                public void requestCommit() {

                }
            });
            Map<String, String> params = new HashMap<>(1);
            params.put("transforms.unwrap.type", "io.debezium.transforms.ExtractNewRecordState");
            this.transformation.configure(params);
        }

        @Override
        public void run() {
            connector.start(config.asMap());
            List<Map<String, String>> taskConfigs = connector.taskConfigs(1);
            sinkTask.start(taskConfigs.get(0));
            while (true) {
                SourceRecord sourceRecord = sourceRecords.poll();

                if (sourceRecord == null) {
                    try {
                        Thread.sleep(1000);
                        continue;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                SinkRecord sinkRecord = new SinkRecord("topic", 0,
                        sourceRecord.keySchema(), sourceRecord.key(),
                        sourceRecord.valueSchema(), sourceRecord.value(), 0);
                sinkRecord = transformation.apply(sinkRecord);
                sinkTask.put(Collections.singletonList(sinkRecord));
            }
        }
    }

    public void debeziumExecutorStart() throws IOException {
        Properties props = new Properties();
        props.load(this.getClass().getResourceAsStream("/source.properties"));
        // Create the engine with this configuration ...
        try (DebeziumEngine<ChangeEvent<String, String>> engine = DebeziumEngine.create(Json.class)
                .using(props)
                .notifying((x) -> {
                    System.out.println(x.key());
                    System.out.println(x.value());
                }).build()
        ) {
            // Run the engine asynchronously ...
            ExecutorService executor = Executors.newSingleThreadExecutor();
            executor.execute(engine);
            executor.awaitTermination(60, TimeUnit.MINUTES);
            // Do something else or wait for a signal or an event
        } catch (IOException | InterruptedException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void transformTest() {
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

            final SourceRecord createRecord0 = createSourceRecord();
            SourceRecord unwrapped0 = transform.apply(createRecord0);
            unwrapped0 = transform2.apply(unwrapped0);
            Object ts_ms0 = unwrapped0.value();


            final Map<String, String> props1 = new HashMap<>();
            props1.put("add.fields", "ts_ms");
            transform.configure(props1);
            final SourceRecord createRecord1 = createSourceRecord();
            final SourceRecord unwrapped1 = transform.apply(createRecord1);
            Object ts_ms1 = ((Struct) unwrapped1.value()).get("__ts_ms");

            final Map<String, String> props2 = new HashMap<>();
            props2.put("add.fields", "source.ts_ms");
            transform.configure(props2);
            final SourceRecord createRecord2 = createSourceRecord();
            final SourceRecord unwrapped2 = transform.apply(createRecord2);
            Object ts_ms2 = ((Struct) unwrapped2.value()).get("__source_ts_ms");
        }
    }

    private SourceRecord createSourceRecord() {
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
        return new SourceRecord(Collections.emptyMap(), Collections.emptyMap(), "dummy", envelope.schema(), payload);
    }

    private SinkRecord createSinkRecord() {
        Schema keySchema = SchemaBuilder.struct().name("my_app_connector1.abc.person.Key").field("id", Schema.INT32_SCHEMA).build();
        Struct key = new Struct(keySchema).put("id", 1);
        Envelope keyEnvelope = Envelope.defineSchema()
                .withName("key")
                .withSource(Schema.OPTIONAL_STRING_SCHEMA)
                .withRecord(keySchema)
                .build();
        Struct keyPayload = keyEnvelope.create(key, null, Instant.now());

        Map<String, String> decimalParams = new HashMap<>(2);
        decimalParams.put("scale", "2");
        decimalParams.put("connect.decimal.precision", "10");
        Schema recordSchema = SchemaBuilder.struct().name("my_app_connector1.abc.person.Value").optional()
                .field("id", Schema.OPTIONAL_INT32_SCHEMA)
                .field("name", Schema.OPTIONAL_STRING_SCHEMA)
                .field("create_time", SchemaBuilder.int64().optional().name("io.debezium.time.Timestamp").build())
                .field("remark", Schema.OPTIONAL_STRING_SCHEMA)
                .field("desc", Schema.OPTIONAL_STRING_SCHEMA)
                .field("num", SchemaBuilder.bytes().optional().name("org.apache.kafka.connect.data.Decimal").parameters(decimalParams).build())
                .build();
        Schema sourceSchema = SchemaBuilder.struct()
                .name("io.debezium.connector.mysql.Source")
                .field("version", Schema.STRING_SCHEMA)
                .field("connector", Schema.STRING_SCHEMA)
                .field("name", Schema.STRING_SCHEMA)
                .field("ts_ms", Schema.INT64_SCHEMA)
                .field("snapshot", Schema.OPTIONAL_STRING_SCHEMA)
                .field("db", Schema.STRING_SCHEMA)
                .field("sequence", Schema.OPTIONAL_STRING_SCHEMA)
                .field("table", Schema.OPTIONAL_STRING_SCHEMA)
                .field("server_id", Schema.INT64_SCHEMA)
                .field("gtid", Schema.OPTIONAL_STRING_SCHEMA)
                .field("file", Schema.STRING_SCHEMA)
                .field("pos", Schema.INT64_SCHEMA)
                .field("row", Schema.INT32_SCHEMA)
                .field("thread", Schema.OPTIONAL_INT64_SCHEMA)
                .field("query", Schema.OPTIONAL_STRING_SCHEMA)
                .build();
        Struct before = new Struct(recordSchema)
                .put("id", 30)
                .put("name", "Jeffrey")
                .put("create_time", null)
                .put("remark", "123")
                .put("desc", null)
                .put("num", null);
        Struct after = new Struct(recordSchema)
                .put("id", 30)
                .put("name", "Jeffrey Luo")
                .put("create_time", null)
                .put("remark", "111")
                .put("desc", "good man")
                .put("num", null);
        Struct source = new Struct(sourceSchema)
                .put("version", "1.8.1.Final")
                .put("connector", "mysql")
                .put("name", "my-app-connector1")
                .put("ts_ms", 1647938049404L)
                .put("snapshot", "false")
                .put("db", "abc")
                .put("sequence", null)
                .put("table", "person")
                .put("server_id", 2L)
                .put("gtid", null)
                .put("file", "mysql-bin.000009")
                .put("pos", 737708284L)
                .put("row", 0)
                .put("thread", null)
                .put("query", null);
        Envelope valueEnvelope = Envelope.defineSchema()
                .withName("my_app_connector1.abc.person.Envelope")
                .withRecord(recordSchema)
                .withSource(sourceSchema)
                .build();
        final Struct valuePayload = valueEnvelope.update(before, after, source, Instant.now());
        return new SinkRecord("test", 0, keySchema, key, valueEnvelope.schema(), valuePayload, 0);
    }
}
