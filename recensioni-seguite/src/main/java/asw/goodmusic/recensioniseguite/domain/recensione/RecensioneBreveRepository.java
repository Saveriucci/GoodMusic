package asw.goodmusic.recensioniseguite.domain.recensione;

import org.springframework.data.repository.CrudRepository;

import java.util.*;

public interface RecensioneBreveRepository extends CrudRepository<RecensioneBreve, Long> {

	public Optional<RecensioneBreve> findById(Long id);

	public Collection<RecensioneBreve> findAll();

	public Collection<RecensioneBreve> findByRecensore(String recensore);

	public Collection<RecensioneBreve> findByRecensoreIn(Collection<String> recensori);

	public Collection<RecensioneBreve> findByAlbum(String album);

	public Collection<RecensioneBreve> findByArtista(String artista);

	public Collection<RecensioneBreve> findByArtistaIn(Collection<String> artisti);

	public Collection<RecensioneBreve> findByGenere(String genere);

	public Collection<RecensioneBreve> findByGenereIn(Collection<String> generi);

}
