package asw.goodmusic.recensioni.domain;

import org.springframework.stereotype.Service;

@Service
public interface RecensioneEventPublisherService {

    public void publish(Recensione recensione);
}
