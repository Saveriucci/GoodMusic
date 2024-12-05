package asw.simplefilter.messagepublisher.kafka;

import asw.simplefilter.domain.SimpleMessagePublisherPort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

@Component
public class SimpleMessageKafkaPublisher implements SimpleMessagePublisherPort {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Value("${spring.kafka.channel.out}")
    private String channel;

    @Autowired
    private KafkaTemplate<String, String> template;

    @Override
    public void publish(String message) {
        template.send(channel, message);
    }

}
