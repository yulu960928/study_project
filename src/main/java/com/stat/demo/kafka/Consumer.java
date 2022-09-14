//package com.stat.demo.kafka;
//
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.kafka.support.Acknowledgment;
//import org.springframework.stereotype.Component;
//
////@Component
//public class Consumer {
//   // @KafkaListener(topics = "my-kafka-test")
//    public void consumerMessage(ConsumerRecord<String,String> record, Acknowledgment ack){
//        String value = record.value();
//        System.out.println(value);
//        //手动提交offset
//        ack.acknowledge();
//    }
//}
