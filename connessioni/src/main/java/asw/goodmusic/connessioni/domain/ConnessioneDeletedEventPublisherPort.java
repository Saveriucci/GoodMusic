package asw.goodmusic.connessioni.domain;

import org.springframework.stereotype.Service;

@Service
public interface ConnessioneDeletedEventPublisherPort {

    public void publish(Long connessioneId);
}
