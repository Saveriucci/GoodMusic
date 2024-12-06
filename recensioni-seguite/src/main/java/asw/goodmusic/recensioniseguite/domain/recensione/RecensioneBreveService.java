package asw.goodmusic.recensioniseguite.domain.recensione;

import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public interface RecensioneBreveService {

    public RecensioneBreve saveRecensioneBreve(RecensioneBreve recensioneBreve);

    public Collection<RecensioneBreve> findByRecensoreIn(Collection<String> recensori);

    public Collection<RecensioneBreve> findByGenereIn(Collection<String> generi);

    public Collection<RecensioneBreve> findByArtistaIn(Collection<String> artisti);

}
