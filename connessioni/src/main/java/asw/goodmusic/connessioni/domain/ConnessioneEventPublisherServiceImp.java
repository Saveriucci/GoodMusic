package asw.goodmusic.connessioni.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

@Service
public class ConnessioneEventPublisherServiceImp implements ConnessioneEventPublisherService {

    @Autowired
    private ConnessioneCreatedEventPublisherPort connessioneCreatedPublisher;
    @Autowired
    private ConnessioneDeletedEventPublisherPort connessioneDeletedPublisher;

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    public void publish(Connessione connessione) {
        try {
            if (connessioneCreatedPublisher == null) {
                throw new IllegalArgumentException();
            }
            connessioneCreatedPublisher.publish(connessione);
        } catch (IllegalArgumentException e) {
            logger.severe("There's nothing to send: " + e.getMessage());
        } catch (Exception e) {
            logger.severe("Error sending CONNESSIONE CREATED EVENT: " + e.getMessage());
        }
    }

    public void publish(Long connessioneId) {
        try {
            if (connessioneDeletedPublisher == null) {
                throw new IllegalArgumentException();
            }
            connessioneDeletedPublisher.publish(connessioneId);
        } catch (IllegalArgumentException e) {
            logger.severe("There's nothing to send: " + e.getMessage());
        } catch (Exception e) {
            logger.severe("Error sending CONNESSIONE DELETED EVENT: " + e.getMessage());
        }
    }
}
