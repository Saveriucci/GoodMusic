package asw.goodmusic.recensioniseguite.domain.services;

import org.springframework.stereotype.Service;

import asw.goodmusic.recensioniseguite.domain.recensione.RecensioneBreve;

import java.util.*;
import java.util.concurrent.CompletableFuture;

@Service
public interface RecensioniSeguiteServiceAsync {

	/*
	 * Trova le recensioni seguite da un utente,
	 * ovvero le recensioni degli album degli artisti, dei recensori e dei generi
	 * musicali seguiti da quell'utente.
	 */
	public CompletableFuture<Collection<RecensioneBreve>> getRecensioniSeguite(String utente);

}
