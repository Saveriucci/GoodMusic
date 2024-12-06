package asw.goodmusic.recensioniseguite.domain.recensione;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;
import java.util.Collection;
import java.util.logging.Logger;

@Service
public class RecensioneBreveServiceImp implements RecensioneBreveService {

    @Autowired
    RecensioneBreveRepository recensioneBreveRepository;

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Override
    public RecensioneBreve saveRecensioneBreve(RecensioneBreve recensioneBreve) {
        try {
            Optional<RecensioneBreve> existingRecensione = recensioneBreveRepository.findById(recensioneBreve.getId());

            if (existingRecensione.isPresent()) {
                logger.info("La recensione con ID " + recensioneBreve.getId() + " esiste già. Non verrà salvata.");
                return existingRecensione.get();
            } else {
                // Se la recensione non esiste, salvala nel database
                return recensioneBreveRepository.save(recensioneBreve);
            }
        } catch (Exception e) {
            logger.severe("Errore durante il salvataggio della recensione: " + e.getMessage());
            throw new RuntimeException("Errore durante il salvataggio della recensione", e);
        }
    }

    @Override
    public Collection<RecensioneBreve> findByRecensoreIn(Collection<String> recensori) {
        return recensioneBreveRepository.findByRecensoreIn(recensori);
    }

    @Override
    public Collection<RecensioneBreve> findByGenereIn(Collection<String> generi) {
        return recensioneBreveRepository.findByGenereIn(generi);
    }

    @Override
    public Collection<RecensioneBreve> findByArtistaIn(Collection<String> artisti) {
        return recensioneBreveRepository.findByArtistaIn(artisti);
    }

}
