package asw.goodmusic.recensioniseguite.rest;

import asw.goodmusic.recensioniseguite.domain.services.RecensioniSeguiteServiceAsync;
import asw.goodmusic.recensioniseguite.domain.services.RecensioniSeguiteService;
import asw.goodmusic.recensioniseguite.domain.recensione.RecensioneBreve;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.Duration;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

@RestController
public class RecensioniSeguiteController {

	private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired
	private RecensioniSeguiteServiceAsync recensioniSeguiteServiceAsync;

	@Autowired
	private RecensioniSeguiteService recensioniSeguite;

	/* Trova le recensioni degli utenti seguiti da utente. */
	@GetMapping("/recensioniseguite/{utente}")
	public Collection<RecensioneBreve> getRecensioniSeguite(@PathVariable String utente) {
		Instant start = Instant.now();
		logger.info("REST CALL: getRecensioniSeguite " + utente);
		CompletableFuture<Collection<RecensioneBreve>> recensioniFuture = recensioniSeguiteServiceAsync
				.getRecensioniSeguite(utente);
		Collection<RecensioneBreve> recensioni = recensioniFuture.join();

		// Collection<RecensioneBreve> recensioni =
		// recensioniSeguiteService.getRecensioniSeguite(utente);

		Duration duration = Duration.between(start, Instant.now());
		logger.info("getRecensioniSeguite " + utente
				+ " --> trovate " + recensioni.size() + " recensioni in " + duration.toMillis() + " ms"
				+ " --> " + recensioni);
		return recensioni;
	}

}
