package asw.goodmusic.connessioni.domain;

import org.springframework.stereotype.Service;

@Service
public interface ConnessioneEventPublisherService {

    public void publish(Connessione connessione);

    public void publish(Long connessioneId);

}
