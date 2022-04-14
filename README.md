# criando projeto
mvn archetype:generate -DgroupId=br.com.kafka_producer -DartifactId=kafka_producer -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false

# doc mvn - gerar com manifest
https://www.sohamkamani.com/java/cli-app-with-maven/

# Um blog com exemplo de implementação
https://ivanqueiroz.dev/2020/09/2020-09-26-consumer-producer-kafka.html


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
