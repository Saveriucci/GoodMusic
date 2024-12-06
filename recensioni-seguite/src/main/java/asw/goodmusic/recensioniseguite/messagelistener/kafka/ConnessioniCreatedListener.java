package asw.goodmusic.recensioniseguite.messagelistener.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.stereotype.Component;

import asw.goodmusic.recensioniseguite.domain.services.RecensioniSeguiteService;

import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

@Component
public class ConnessioniCreatedListener {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Value("${spring.kafka.consumer.topics.ConnessioniCreated.channel-in}")
    private String channelConnessioniCreated;

    @Autowired
    private RecensioniSeguiteService recensioniSeguiteService;

    @KafkaListener(topics = "${spring.kafka.consumer.topics.ConnessioniCreated.channel-in}", groupId = "${spring.kafka.consumer.group-id}")
    public void listen(ConsumerRecord<String, String> record) throws Exception {
        logger.info("CONNESSIONE CREATED EVENT RECEIVED ON CHANNEL : " + channelConnessioniCreated);
        recensioniSeguiteService.saveConnessione(record.value());
    }
}