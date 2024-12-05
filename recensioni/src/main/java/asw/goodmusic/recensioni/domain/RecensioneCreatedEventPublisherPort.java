package asw.goodmusic.recensioni.domain;

import org.springframework.stereotype.Service;

@Service
public interface RecensioneCreatedEventPublisherPort {

    public void publish(Recensione recensione);

}