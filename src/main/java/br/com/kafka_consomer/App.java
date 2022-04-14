package br.com.kafka_consomer;

import java.util.concurrent.ExecutionException;

import br.com.kafka_consomer.services.KafkaService;

public class App 
{
    public static void main( String[] args ) throws InterruptedException, ExecutionException
    {
        System.out.println("Lendo mensagens ...");
        var grupoId = System.getenv("KAFKA_GROUP_ID_READER");
        KafkaService.readMessage(grupoId);
    }
}

