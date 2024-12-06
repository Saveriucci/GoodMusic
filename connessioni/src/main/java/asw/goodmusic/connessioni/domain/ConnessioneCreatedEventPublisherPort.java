package asw.goodmusic.connessioni.domain;

import org.springframework.stereotype.Service;

@Service
public interface ConnessioneCreatedEventPublisherPort {

    public void publish(Connessione connessione);
}
