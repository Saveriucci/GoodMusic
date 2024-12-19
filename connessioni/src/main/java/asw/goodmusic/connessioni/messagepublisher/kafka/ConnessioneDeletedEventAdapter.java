package asw.goodmusic.connessioni.messagepublisher.kafka;

import asw.goodmusic.connessioni.domain.ConnessioneDeletedEventPublisherPort;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConnessioneDeletedEventAdapter implements ConnessioneDeletedEventPublisherPort {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Value("${spring.kafka.producer.channel.out2}")
    private String channel;

    @Autowired
    private ObjectMapper objectMapperString;

    @Autowired
    private KafkaTemplate<String, String> template;

    @Override
    public void publish(Long connessioneId) {
        try {
            logger.info("PUBLISHING MESSAGE WITH ID: " + connessioneId + " ON CHANNEL: " + channel);

            // Invia il JSON al topic Kafka come stringa
            template.send(channel, connessioneId.toString());

            // Facoltativo: forza l'invio del messaggio (utile in alcuni casi)
            // template.flush();
        } catch (Exception e) {
            logger.severe("ERROR SENDING THE MESSAGE: " + e.getMessage());
        }
    }
}
