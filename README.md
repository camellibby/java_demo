# spring_demo

# IT network basic knowledge
## how to establish a socket connection to send a http request
```bash
cd /proc/$$/fd
ll
exec 8<> /dev/tcp/www.baidu.com/80
ll
echo -e 'GET / HTTP/1.0\n' >& 8
cat 0<& 8
exec 8<& -
```

## how to find route and arp address
```bash
route -n
traceroute ip_address
arp -an
```