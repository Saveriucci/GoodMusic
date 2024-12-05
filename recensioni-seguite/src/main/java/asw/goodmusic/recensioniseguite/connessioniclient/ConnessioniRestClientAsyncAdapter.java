package asw.goodmusic.recensioniseguite.connessioniclient;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import asw.goodmusic.connessioni.api.rest.ConnessioneResponse;
import asw.goodmusic.recensioniseguite.domain.connessione.Connessione;
import asw.goodmusic.recensioniseguite.domain.connessione.ConnessioneClientAsyncPort;
import reactor.core.publisher.Flux;

@Service
@Primary
public class ConnessioniRestClientAsyncAdapter implements ConnessioneClientAsyncPort {

    @Autowired
    @Qualifier("loadBalancedWebClient")
    private WebClient loadBalancedWebClient;

    @Override
    public CompletableFuture<Collection<Connessione>> getConnessioniByUtenteAsync(String utente) {
        return CompletableFuture.supplyAsync(() -> {
            Flux<ConnessioneResponse> response = loadBalancedWebClient
                    .get()
                    .uri("http://connessioni/connessioni/{utente}", utente)
                    .retrieve()
                    .bodyToFlux(ConnessioneResponse.class);

            return toConnessioni(response.collectList().block()); // Blocco il risultato per completare l'elaborazione
                                                                  // in async
        });
    }

    @Override
    public CompletableFuture<Collection<Connessione>> getConnessioniByUtenteAndRuoloAsync(String utente, String ruolo) {
        return CompletableFuture.supplyAsync(() -> {
            Flux<ConnessioneResponse> response = loadBalancedWebClient
                    .get()
                    .uri("http://connessioni/connessioni/{utente}/{ruolo}", utente, ruolo)
                    .retrieve()
                    .bodyToFlux(ConnessioneResponse.class);

            return toConnessioni(response.collectList().block()); // Blocco il risultato per completare l'elaborazione
                                                                  // in async
        });
    }

    private Connessione toConnessione(ConnessioneResponse response) {
        return new Connessione(response.getId(), response.getUtente(), response.getSeguito(), response.getRuolo());
    }

    private Collection<Connessione> toConnessioni(Collection<ConnessioneResponse> response) {
        return response.stream()
                .map(this::toConnessione)
                .collect(Collectors.toList());
    }
}
