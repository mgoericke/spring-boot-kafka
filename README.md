# Kafka

## when to use Consumer - Producer:

* If there are single consumer , consume message process but not spill to other topic.
* As point 1 if have just producer producing message the we don't need to Kafka Stream.
* If consumer message from one Kafka cluster but publish to different Kafka cluster topic. In that case even you can use Kafka Stream but you have to use separate Producer to publish message tp different cluster. Or simply use Kafka Consumer - Producer mechanism.
* Batch processing - if there is requirement to collect message or kind of batch processing its good to use normal traditional way.

## when to use Kafka Stream:

* If you consume message from one topic , transform and publish to other topic Kafka Stream is best suited.
* Realtime processing, realtime analytic and Machine learning.
* Stateful transformation such as aggregation, join, window etc.
* Planning to use local state store or mounted store such as Portworx etc.
* Achieve Exactly one processing semantic and auto defined fault tolerance.

# Examples

## Kafka Stack
Add `127.0.0.1 kafka` to /etc/hosts file. 

Start Kafka Stack
```
docker-compose -f docker-compose-ui.yml up -d
```

|  Service | Host:Port  | 
|---|---|
| Kafka  | localhost:9092  |
| Zookeeper | localhost:2181  |
| Schema-Registry  | localhost:8001  |
| Kafka-Topics UI  | localhost:8002  |
| Schema-Registry UI  | localhost:8003  |

## Producer

### Start the producer 

* spring profile "producer"
* server port 8080

```
java -jar target/*jar --spring.profiles.active="producer" --server.port=8080
```

### publish messages 


(example users httpie)
```
echo '{"id":1, "body": "just a message"}' | http POST :8080/publish
```

## Consumer

Start the consumer 

* spring profile "consumer"
* server port 8081

```
java -jar target/*jar --spring.profiles.active="consumer" --server.port=8081
```















