package asw.goodmusic.recensioniseguite.domain.connessione;

import org.springframework.data.repository.CrudRepository;

import java.util.*;

public interface ConnessioneRepository extends CrudRepository<Connessione, Long> {

	public Collection<Connessione> findAll();

	public List<Connessione> findByUtente(String utente);

	public List<Connessione> findByRuolo(String ruolo);
}