package com.kafka.kafkaExampleApp;
import java.util.Properties;
import java.util.Random;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.model.User;

public class BatchProducerApp {

    public static void main(String[] args) throws JsonProcessingException{

        // Create the Properties class to instantiate the Consumer with the desired settings:
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092, localhost:9093");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks", "1");
        props.put("buffer.memory", 33554432);
        props.put("compression.type", "none");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("client.id", "");
        props.put("linger.ms", 0);
        props.put("max.block.ms", 60000);
        props.put("max.request.size", 1048576);
        props.put("partitioner.class", "org.apache.kafka.clients.producer.internals.DefaultPartitioner");
        props.put("request.timeout.ms", 30000);
        props.put("timeout.ms", 30000);
        props.put("max.in.flight.requests.per.connection", 5);
        props.put("retry.backoff.ms", 5);

        KafkaProducer<String, String> myProducer = new KafkaProducer<String, String>(props);
        ObjectMapper mapper=new ObjectMapper();
        String topic = "my-topic";
        User user = null;
        try {
            int batchNumber = 1;
            int counter = 0;
            while (batchNumber<11) {
                do {
                	user=new User("Abc","Cognome",counter,getMessage());
                	myProducer.send(new ProducerRecord<String, String>(topic,mapper.writeValueAsString(user)));
                    counter++; // Increase record counter...
                    // Thread.sleep(500); // use if you want to add latency between record sends
                    // Thread.sleep(new Random(1000).nextLong()); // use if you want to add random latency between record sends
                } while (counter < 10); // Number of records sent in a batch...
                counter = 0; // Reset the record counter...
                Thread.sleep(500); // Set how long before a new batch is sent...
                // Thread.sleep(new Random(5000).nextLong()); // use if you want to randomize the time between batch record sends
                batchNumber++; // Increase the batch number...
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myProducer.close();
        }

    }
    public static String getMessage()
    {

        String message="";
    	final String []noun = {"face", "eyes", "tender", "lips", "ears", "roses"};
        Random random = new Random();
        int rand1 = random.nextInt(noun.length);


        final String []verb = {"enchant", "dazzle", "cuddle" , "lure", "desire", "dream" };
        Random random2 = new Random();
        int rand2 = random2.nextInt(verb.length);


        final String []adjective = { "Alluring", "Angelic", "Adoring", "Appealing", "Attractive", "beautiful"};
        Random random3 = new Random();
        int rand3 = random3.nextInt(adjective.length);
        message="I get butterflies when I see your " + noun[rand1] +" ,you make me " + verb[rand2] +", in your " + adjective[rand3] + " world!";
        return message;
    }
}
