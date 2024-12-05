package asw.goodmusic.recensioniseguite.recensioniclient;

import asw.goodmusic.recensioniseguite.domain.recensione.RecensioneBreve;
import asw.goodmusic.recensioniseguite.domain.recensione.RecensioneClientAsyncPort;
import asw.goodmusic.recensioni.api.rest.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Primary
public class RecensioniRestClientAsyncAdapter implements RecensioneClientAsyncPort {

    @Autowired
    @Qualifier("loadBalancedWebClient")
    private WebClient loadBalancedWebClient;

    @Override
    public CompletableFuture<Collection<RecensioneBreve>> getRecensioniByArtistiAsync(Collection<String> artisti) {
        return CompletableFuture.supplyAsync(() -> {
            Flux<RecensioneBreveResponse> response = loadBalancedWebClient
                    .get()
                    .uri("http://recensioni/cercarecensioni/artisti/{artisti}", toString(artisti))
                    .retrieve()
                    .bodyToFlux(RecensioneBreveResponse.class);
            return toRecensioni(response.collectList().block());
        });
    }

    @Override
    public CompletableFuture<Collection<RecensioneBreve>> getRecensioniByRecensoriAsync(Collection<String> recensori) {
        return CompletableFuture.supplyAsync(() -> {
            Flux<RecensioneBreveResponse> response = loadBalancedWebClient
                    .get()
                    .uri("http://recensioni/cercarecensioni/recensori/{recensori}", toString(recensori))
                    .retrieve()
                    .bodyToFlux(RecensioneBreveResponse.class);
            return toRecensioni(response.collectList().block());
        });
    }

    @Override
    public CompletableFuture<Collection<RecensioneBreve>> getRecensioniByGeneriAsync(Collection<String> generi) {
        return CompletableFuture.supplyAsync(() -> {
            Flux<RecensioneBreveResponse> response = loadBalancedWebClient
                    .get()
                    .uri("http://recensioni/cercarecensioni/generi/{generi}", toString(generi))
                    .retrieve()
                    .bodyToFlux(RecensioneBreveResponse.class);
            return toRecensioni(response.collectList().block());
        });
    }

    private RecensioneBreve toRecensione(RecensioneBreveResponse response) {
        return new RecensioneBreve(response.getId(), response.getRecensore(), response.getAlbum(),
                response.getArtista(), response.getGenere(), response.getSunto());
    }

    private Collection<RecensioneBreve> toRecensioni(Collection<RecensioneBreveResponse> response) {
        return response.stream()
                .map(this::toRecensione)
                .collect(Collectors.toList());
    }

    private static String toString(Collection<String> c) {
        return c.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(",", "", ""));
    }
}
