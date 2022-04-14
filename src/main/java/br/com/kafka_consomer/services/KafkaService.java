package br.com.kafka_consomer.services;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import br.com.kafka_consomer.models.Aluno;

public class KafkaService {
    public static void readMessage(String groupId) throws InterruptedException, ExecutionException{
        var consumer = new KafkaConsumer<String, String>(properties(groupId));
        consumer.subscribe(Collections.singletonList(System.getenv("KAFKA_TOPIC")));

        while (true) {
            var records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> registro : records) {

                var jsonString = registro.value();

                Aluno aluno = new Gson().fromJson(jsonString, Aluno.class);

                System.out.println("------------------------------------------");
                System.out.println("Aluno/Nota");
                System.out.println("TIPO Objeto: " + registro.key());
                System.out.println("Nome: " + aluno.getNome());
                System.out.println("Matricula: " + aluno.getMatricula());

                String notas = aluno.getNotas().stream().map(String::valueOf).collect(Collectors.joining(", "));

                System.out.println("Notas: " + notas);

                if (aluno.aprovado()) {
                    System.out.println("Passou de ano");
                } else {
                    System.out.println("Foi reprovado");
                }

                System.out.println("Aluno/Nota processada.");
                System.out.println("------------------------------------------");
            }
        }
    }

    private static Properties properties(String groupId) {
        var properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, System.getenv("KAFKA_HOST"));
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId); // item que identifica qual consumidor irá ler a mensagem
        properties.setProperty(ConsumerConfig.CLIENT_ID_CONFIG, UUID.randomUUID().toString()); // ID identificador do consumidor
        return properties;
    }
}