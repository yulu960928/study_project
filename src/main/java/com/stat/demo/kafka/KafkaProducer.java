//package com.stat.demo.kafka;
//
//import org.apache.kafka.common.protocol.types.Field;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.SendResult;
//import org.springframework.stereotype.Component;
//import org.springframework.util.concurrent.ListenableFuture;
//
//import javax.annotation.Resource;
//
////@Component
//public class KafkaProducer {
//
//    private final static String TOPIC = "my-kafka-test";
//
//    @Resource
//    private KafkaTemplate<String, String> kafkaTemplate;
//
////    public ListenableFuture<SendResult<String, String>> send(String topic,Integer partition, Long timestamp, String key, String data){
////        ListenableFuture<SendResult<String, String>> sendResult = kafkaTemplate.send(topic, partition, timestamp, key, data);
////        return sendResult;
////
////    }
//
//
//}
