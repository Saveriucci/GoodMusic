package asw.goodmusic.recensioni.messagepublisher.kafka;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import asw.goodmusic.recensioni.domain.Recensione;
import asw.goodmusic.recensioni.domain.RecensioneCreatedEventPublisherPort;

@Component
public class RecensioneCreatedEventAdapter implements RecensioneCreatedEventPublisherPort {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Value("${spring.kafka.producer.channel.out}")
    private String channel;

    @Autowired
    private ObjectMapper objectMapperString;

    @Autowired
    private KafkaTemplate<String, String> template;

    @Override
    public void publish(Recensione recensione) {
        try {
            // Serializza l'oggetto Recensione in un JSON string
            String jsonMessage = objectMapperString.writeValueAsString(recensione);

            logger.info("PUBLISHING MESSAGE WITH ID: " + recensione.getId() + " ON CHANNEL: " + channel);

            // Invia il JSON al topic Kafka come stringa
            template.send(channel, jsonMessage);

            // Facoltativo: forza l'invio del messaggio (utile in alcuni casi)
            // template.flush();
        } catch (Exception e) {
            logger.severe("ERROR WHILE SERIALIZING RECENSIONE OBJECT TO JSON: " + e.getMessage());
        }
    }
}