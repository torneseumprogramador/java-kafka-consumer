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
~/kafka_2.13-3.1.0/bin/kafka-topics.sh --list --bootstrap-server 192.168.0.19:9092

# cria um topico
~/kafka_2.13-3.1.0/bin/kafka-topics.sh --create --bootstrap-server 192.168.0.19:9092 --replication-factor=1 --partitions=1 --topic="UM_TOPICO"

# enviando mensagem
~/kafka_2.13-3.1.0/bin/kafka-console-producer.sh --broker-list  192.168.0.19:9092 --topic="UM_TOPICO"
# irá abrir um console para você irá digitar vários valores, exemplo:
# > valor
# > valor

# consumingo mensagems
~/kafka_2.13-3.1.0/bin/kafka-console-consumer.sh --bootstrap-server  192.168.0.19:9092 --topic="UM_TOPICO" # consome somente as mesagens novas
~/kafka_2.13-3.1.0/bin/kafka-console-consumer.sh --bootstrap-server  192.168.0.19:9092 --topic="UM_TOPICO" --from-beginning # lê mensagens deste o inicio
```
