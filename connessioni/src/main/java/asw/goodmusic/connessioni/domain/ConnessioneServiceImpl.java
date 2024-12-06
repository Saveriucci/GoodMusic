package asw.goodmusic.connessioni.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class ConnessioneServiceImpl implements ConnessioneService {

	@Autowired
	private ConnessioneRepository connessioniRepository;

	/* Crea una nuova connessione, dati utente, seguito e ruolo. */
	@Override
	public Connessione createConnessione(String utente, String seguito, String ruolo) {
		Connessione connessione = new Connessione(utente, seguito, ruolo);
		try {
			connessione = connessioniRepository.save(connessione);
			return connessione;
		} catch (Exception e) {
			/*
			 * si potrebbe verificare un'eccezione se è violato il vincolo di unicità della
			 * connessione
			 */
			return null;
		}
	}

	/* Trova una connessione, dato l'id. */
	@Override
	public Connessione getConnessione(Long id) {
		Connessione connessione = connessioniRepository.findById(id).orElse(null);
		return connessione;
	}

	/* Trova una connessione, dati utente, seguito e ruolo. */
	@Override
	public Connessione getConnessione(String utente, String seguito, String ruolo) {
		Connessione connessione = connessioniRepository.findByUtenteAndSeguitoAndRuolo(utente, seguito, ruolo);
		return connessione;
	}

	/* Trova tutte le connessioni. */
	@Override
	public Collection<Connessione> getConnessioni() {
		Collection<Connessione> connessioni = connessioniRepository.findAll();
		return connessioni;
	}

	/* Trova tutte le connessioni di un utente. */
	@Override
	public Collection<Connessione> getConnessioniByUtente(String utente) {
		Collection<Connessione> connessioni = connessioniRepository.findByUtente(utente);
		return connessioni;
	}

	/* Trova tutte le connessioni con un certo ruolo. */
	@Override
	public Collection<Connessione> getConnessioniByRuolo(String ruolo) {
		Collection<Connessione> connessioni = connessioniRepository.findByRuolo(ruolo);
		return connessioni;
	}

	/* Trova tutte le connessioni di un utente con un certo ruolo. */
	@Override
	public Collection<Connessione> getConnessioniByUtenteAndRuolo(String utente, String ruolo) {
		Collection<Connessione> connessioni = connessioniRepository.findByUtenteAndRuolo(utente, ruolo);
		return connessioni;
	}

	/* Cancella una connessione, dati utente, seguito e ruolo. */
	@Override
	public Connessione deleteConnessione(String utente, String seguito, String ruolo) {
		Connessione connessione = getConnessione(utente, seguito, ruolo);
		if (connessione != null) {
			Long id = connessione.getId();
			connessioniRepository.delete(connessione);
		}
		return connessione;
	}

}