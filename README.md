# criando projeto
mvn archetype:generate -DgroupId=br.com.kafka_producer -DartifactId=kafka_producer -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false

# doc mvn - gerar com manifest
https://www.sohamkamani.com/java/cli-app-with-maven/


# como rodar
- configurar o seu .bash_profile ou .bashrc
```shell
code ~/.bash_profile

export KAFKA_HOST="192.168.0.19:9092"
export KAFKA_TOPIC="TOPICO_1"
export KAFKA_GROUP_ID_READER="consomer_1"

source ~/.bash_profile
```

# rodar o comando
```shell
./buld.sh
./start.sh
```


# enviando e consumindo mensagem via terminal
```shell
# lista topicos
~/kafka_2.13-3.1.0/bin/kafka-topics.sh --list --bootstrap-server=192.168.0.19:9092

# cria um topico
~/kafka_2.13-3.1.0/bin/kafka-topics.sh --create --bootstrap-server=192.168.0.19:9092 --replication-factor=1 --partitions=1 --topic="UM_TOPICO"

# enviando mensagem
~/kafka_2.13-3.1.0/bin/kafka-console-producer.sh --broker-list=192.168.0.19:9092 --topic="UM_TOPICO"
# irá abrir um console para você irá digitar vários valores, exemplo:
# > valor
# > valor

# consumingo mensagems
~/kafka_2.13-3.1.0/bin/kafka-console-consumer.sh --bootstrap-server=192.168.0.19:9092 --topic="UM_TOPICO" # consome somente as mesagens novas
~/kafka_2.13-3.1.0/bin/kafka-console-consumer.sh --bootstrap-server=192.168.0.19:9092 --topic="UM_TOPICO" --from-beginning # lê mensagens deste o inicio

# deletar um topico
~/kafka_2.13-3.1.0/bin/kafka-topics.sh --topic="EXEMPLO_TOPICO" --delete --bootstrap-server=192.168.0.19:9092

```

# para criar um balanciamento de consumidores alterar o arquivo server.properties
```shell
vim ~/kafka_2.13-3.1.0/config/server.properties 
# alterar o valor: 
# num.partitions=3

# agora é alterar o tópico
~/kafka_2.13-3.1.0/bin/kafka-topics.sh --alter --bootstrap-server=192.168.0.19:9092 --topic=TOPICO_1 --partitions=3

# para verificar as partições de um tópido
~/kafka_2.13-3.1.0/bin/kafka-topics.sh --bootstrap-server=192.168.0.19:9092 --describe

# alterar o KafkaService.java
vim src/main/java/br/com/kafka_consumer/services/KafkaService.java
# acrescentar no metodo properties:
# properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString()); // para enviar dados em consumidores diferentes
# properties.setProperty(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "1"); // para evitar conflito de partições e rebalanciamento

# agora 2 consumidores passam a trabalhar em paralelo
# para testar, rodar 2 consumidores:
# ./start.sh # terminal 1
# ./start.sh # terminal 2
```

