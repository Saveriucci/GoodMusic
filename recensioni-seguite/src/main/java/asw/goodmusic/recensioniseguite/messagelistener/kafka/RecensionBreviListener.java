package asw.goodmusic.recensioniseguite.messagelistener.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;

import org.springframework.stereotype.Component;

import asw.goodmusic.recensioniseguite.domain.services.RecensioniSeguiteService;

import org.springframework.beans.factory.annotation.Value;

import java.util.logging.Logger;

@Component
public class RecensionBreviListener {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Value("${spring.kafka.consumer.topics.RecensioniBrevi.channel-in}")
    private String channelRecensioniBrevi;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupIdRecensioniBrevi;

    @Autowired
    private RecensioniSeguiteService recensioniSeguiteService;

    @KafkaListener(topics = "${spring.kafka.consumer.topics.RecensioniBrevi.channel-in}", groupId = "${spring.kafka.consumer.groupid}")
    public void listen(ConsumerRecord<String, String> record) throws Exception {
        // logger.info("EVENT RECIEVED: ");
        recensioniSeguiteService.saveRecensioneBreve(record.value());
    }

    /*
     * @KafkaListener(topics = "${spring.kafka.consumer.kafka.channel.in}", groupId
     * = "${spring.kafka.consumer.kafka.groupid}")
     * public void listenConnessioni(ConsumerRecord<String, String> record) throws
     * Exception {
     * // logger.info("MESSAGE LISTENER: " + record.toString());
     * String message = record.value();
     * }
     * 
     */
}