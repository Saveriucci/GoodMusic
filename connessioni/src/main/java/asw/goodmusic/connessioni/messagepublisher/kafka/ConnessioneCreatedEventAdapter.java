package asw.goodmusic.connessioni.messagepublisher.kafka;

import asw.goodmusic.connessioni.domain.Connessione;
import asw.goodmusic.connessioni.domain.ConnessioneCreatedEventPublisherPort;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.logging.Logger;

@Component
public class ConnessioneCreatedEventAdapter implements ConnessioneCreatedEventPublisherPort {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Value("${spring.kafka.producer.channel.out1}")
    private String channel;

    @Autowired
    private ObjectMapper objectMapperString;

    @Autowired
    private KafkaTemplate<String, String> template;

    @Override
    public void publish(Connessione connessione) {
        try {
            // Serializza l'oggetto Recensione in un JSON string
            String jsonMessage = objectMapperString.writeValueAsString(connessione);

            logger.info("PUBLISHING MESSAGE WITH ID: " + connessione.getId() + " ON CHANNEL: " + channel);

            // Invia il JSON al topic Kafka come stringa
            template.send(channel, jsonMessage);

            // Facoltativo: forza l'invio del messaggio (utile in alcuni casi)
            // template.flush();
        } catch (Exception e) {
            logger.severe("ERROR WHILE SERIALIZING CONNESSIONE OBJECT TO JSON: " + e.getMessage());
        }
    }
}