## Nacos

shell:

- Interactive Mode

```bash
sh nacos/nacos-config-interactive.sh
```

This command will generate interactive configuration mode, eg:

```
Please enter the host of nacos.
请输入nacos的host [localhost]:
>>>
Please enter the port of nacos.
请输入nacos的port [8861]:
>>>
Please enter the group of nacos.
请输入nacos的group [pangu]:
>>>
Please enter the tenant of nacos.
请输入nacos的tenant:
>>>
Please enter the username of nacos.
请输入nacos的username:
>>>
Please enter the password of nacos.
请输入nacos的password:
>>>
Are you sure to continue? [y/n]
```

- Non-Interactive Mode

```bash
sh nacos/nacos-config.sh -h localhost -p 8861 -g pangu -t seata -u nacos -w nacos
```

Parameter Description:

-h: host, the default value is localhost.

-p: port, the default value is 8861.

-g: Configure grouping, the default value is 'pangu'.

-t: Tenant information, corresponding to the namespace ID field of Nacos, the default value is ''.

-u: username, nacos 1.2.0+ on permission control, the default value is ''.

-w: password, nacos 1.2.0+ on permission control, the default value is ''.

python:

- Interactive Mode


```bash
python ${SEATAPATH}/script/config-center/nacos/nacos-config-interactive.py
```

This command will generate interactive configuration mode like nacos-config-interactive.sh.

- Non-Interactive Mode


```bash
python ${SEATAPATH}/script/config-center/nacos/nacos-config.py localhost:8861
```