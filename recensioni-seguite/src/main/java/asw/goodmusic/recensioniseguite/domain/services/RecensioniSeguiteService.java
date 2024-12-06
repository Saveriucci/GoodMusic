package asw.goodmusic.recensioniseguite.domain.services;

import org.springframework.stereotype.Service;

import asw.goodmusic.recensioniseguite.domain.recensione.RecensioneBreve;

import java.util.*;

@Service
public interface RecensioniSeguiteService {

	/*
	 * Trova le recensioni seguite da un utente,
	 * ovvero le recensioni degli album degli artisti, dei recensori e dei generi
	 * musicali seguiti da quell'utente.
	 */
	public Collection<RecensioneBreve> getRecensioniSeguite(String utente);

	public void saveRecensioneBreve(String recensioneBreve);

	public void saveConnessione(String connessione);

	public void deleteConnessione(String connessioneId);
}
