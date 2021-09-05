package com.vineet;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;
import java.util.Random;

public class Producer {
    public static void main(String[] args){
        // For example 192.168.1.1:9092,192.168.1.2:9092
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.vineet.UserSerializer");

        KafkaProducer<String, UserModel> kafkaProducer = new KafkaProducer<>(properties);
        try{
            Random ob = new Random();
            for(int i = 1; i <= 15; i++){
               UserModel user= new UserModel(i,"Vineet ",ob.nextInt(15), "MCA");

                kafkaProducer.send(new ProducerRecord<String,UserModel>("first", Integer.toString(i),user));

            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            kafkaProducer.close();
        }
    }
}