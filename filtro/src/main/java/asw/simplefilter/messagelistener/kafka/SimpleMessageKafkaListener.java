package asw.simplefilter.messagelistener.kafka;

import asw.simplefilter.domain.SimpleFilterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

@Component
public class SimpleMessageKafkaListener {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private SimpleFilterService simpleFilterService;

    @Value("${spring.kafka.channel.in}")
    private String channel;

    @Value("${spring.kafka.groupid}")
    private String groupId;

    @KafkaListener(topics = "${spring.kafka.channel.in}", groupId = "${spring.kafka.groupid}")
    public void listen(ConsumerRecord<String, String> record) throws Exception {
        simpleFilterService.filter(record.value());
    }

}
