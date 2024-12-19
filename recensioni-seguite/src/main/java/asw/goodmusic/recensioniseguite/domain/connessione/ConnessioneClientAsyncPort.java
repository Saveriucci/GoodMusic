package asw.goodmusic.recensioniseguite.domain.connessione;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public interface ConnessioneClientAsyncPort {

	public CompletableFuture<Collection<Connessione>> getConnessioniByUtenteAsync(String utente);

	public CompletableFuture<Collection<Connessione>> getConnessioniByUtenteAndRuoloAsync(String utente, String ruolo);

}
