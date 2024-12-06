package asw.goodmusic.recensioniseguite.messagelistener.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.stereotype.Component;

import asw.goodmusic.recensioniseguite.domain.services.RecensioniSeguiteService;

import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

@Component
public class ConnessioniDeletedListener {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Value("${spring.kafka.consumer.topics.ConnessioniDeleted.channel-in}")
    private String channelConnessioniDeleted;

    @Autowired
    private RecensioniSeguiteService recensioniSeguiteService;

    @KafkaListener(topics = "${spring.kafka.consumer.topics.ConnessioniDeleted.channel-in}", groupId = "${spring.kafka.consumer.groupid}")
    public void listen(ConsumerRecord<String, String> record) throws Exception {
        logger.info("CONNESSIONE DELETED EVENT RECEIVED ON CHANNEL: " + channelConnessioniDeleted);
        recensioniSeguiteService.deleteConnessione(record.value());
    }
}