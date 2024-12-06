package asw.goodmusic.recensioni.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

@Service
public class RecensioneEventPublisherServiceImp implements RecensioneEventPublisherService {

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Autowired
    private RecensioneCreatedEventPublisherPort recensioneCreatedEvent;

    @Override
    public void publish(Recensione recensione) {
        try {
            if (recensione == null) {
                throw new IllegalArgumentException();
            }
            recensioneCreatedEvent.publish(recensione);
        } catch (IllegalArgumentException e) {
            logger.severe("There's nothing to send: " + e.getMessage());
        } catch (Exception e) {
            logger.severe("Error sending RECENSIONE CREATED EVENT: " + e.getMessage());
        }
    }
}
