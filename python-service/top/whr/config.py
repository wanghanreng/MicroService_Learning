from nacos import NacosClient
import pymysql
import time

server_addr = "127.0.0.1:8848"
namespace = "public"
username = "nacos"
password = "nacos"

client = NacosClient(server_addr, namespace=namespace, username=username, password=password)

def register_service():
    service_name = "python-service"  # 服务名称
    client.add_naming_instance(service_name, "127.0.0.1", "5050")

def send_heartbeat():
    service_name = "python-service"
    ip = "127.0.0.1"
    port = 5050
    while True:
        client.send_heartbeat(service_name, ip, port)
        print(f"发送心跳:  {time.strftime('%Y-%m-%d %H:%M:%S')}")
        time.sleep(5)  # 每隔5秒发送一次心跳

def get_db_connection():
    db_config = {
        "host": "127.0.0.1",
        "port": 3306,
        "user": "root",
        "password": "password",
        "database": "spring_boot",
        "charset": "utf8mb4"
    }
    return pymysql.connect(**db_config)