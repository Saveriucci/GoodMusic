package asw.goodmusic.recensioniseguite.domain.services;

import org.springframework.stereotype.Service;

import asw.goodmusic.recensioniseguite.domain.recensione.RecensioneBreve;
import asw.goodmusic.recensioniseguite.domain.recensione.RecensioneClientAsyncPort;

import asw.goodmusic.recensioniseguite.domain.connessione.Connessione;
import asw.goodmusic.recensioniseguite.domain.connessione.ConnessioneClientAsyncPort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.*;

@Service
@Primary
public class RecensioniSeguiteServiceImplAsync implements RecensioniSeguiteServiceAsync {

    @Autowired
    private ConnessioneClientAsyncPort connessioniClient;

    @Autowired
    private RecensioneClientAsyncPort recensioniClient;

    /* Metodo principale: trova tutte le recensioni seguite da un utente */
    @Override
    public CompletableFuture<Collection<RecensioneBreve>> getRecensioniSeguite(String utente) {
        // Esegui asincrono la richiesta per le connessioni
        CompletableFuture<Collection<Connessione>> futureConnessioni = connessioniClient
                .getConnessioniByUtenteAsync(utente);

        // Quando la futura operazione sulle connessioni è completata, esegui il resto
        // in modo asincrono
        return futureConnessioni.thenCompose(connessioni -> {
            // Recupera le recensioni in parallelo per artisti, recensori e generi
            CompletableFuture<Collection<RecensioneBreve>> recensioniArtisti = getRecensioniPerArtisti(connessioni);
            CompletableFuture<Collection<RecensioneBreve>> recensioniRecensori = getRecensioniPerRecensori(connessioni);
            CompletableFuture<Collection<RecensioneBreve>> recensioniGeneri = getRecensioniPerGeneri(connessioni);

            // Combina i risultati quando tutte le future sono terminate
            return CompletableFuture.allOf(recensioniArtisti, recensioniRecensori, recensioniGeneri)
                    .thenApply(v -> {
                        // Combina i risultati delle recensioni
                        Collection<RecensioneBreve> recensioniSeguite = new TreeSet<>();
                        recensioniSeguite.addAll(recensioniArtisti.join());
                        recensioniSeguite.addAll(recensioniRecensori.join());
                        recensioniSeguite.addAll(recensioniGeneri.join());
                        return recensioniSeguite;
                    });
        });
    }

    /* Ottieni recensioni per gli artisti seguiti */
    @Async
    private CompletableFuture<Collection<RecensioneBreve>> getRecensioniPerArtisti(
            Collection<Connessione> connessioni) {
        Collection<String> artistiSeguiti = connessioni.stream()
                .filter(c -> c.getRuolo().equals("ARTISTA"))
                .map(Connessione::getSeguito)
                .collect(Collectors.toSet());

        if (artistiSeguiti.isEmpty()) {
            return CompletableFuture.completedFuture(Collections.emptyList());
        }

        // Chiamata asincrona per ottenere recensioni degli artisti seguiti
        return recensioniClient.getRecensioniByArtistiAsync(artistiSeguiti);
    }

    /* Ottieni recensioni per i recensori seguiti */
    @Async
    private CompletableFuture<Collection<RecensioneBreve>> getRecensioniPerRecensori(
            Collection<Connessione> connessioni) {
        Collection<String> recensoriSeguiti = connessioni.stream()
                .filter(c -> c.getRuolo().equals("RECENSORE"))
                .map(Connessione::getSeguito)
                .collect(Collectors.toSet());

        if (recensoriSeguiti.isEmpty()) {
            return CompletableFuture.completedFuture(Collections.emptyList());
        }

        // Chiamata asincrona per ottenere recensioni dei recensori seguiti
        return recensioniClient.getRecensioniByRecensoriAsync(recensoriSeguiti);
    }

    /* Ottieni recensioni per i generi seguiti */
    @Async
    private CompletableFuture<Collection<RecensioneBreve>> getRecensioniPerGeneri(Collection<Connessione> connessioni) {
        Collection<String> generiSeguiti = connessioni.stream()
                .filter(c -> c.getRuolo().equals("GENERE"))
                .map(Connessione::getSeguito)
                .collect(Collectors.toSet());

        if (generiSeguiti.isEmpty()) {
            return CompletableFuture.completedFuture(Collections.emptyList());
        }

        // Chiamata asincrona per ottenere recensioni dei generi seguiti
        return recensioniClient.getRecensioniByGeneriAsync(generiSeguiti);
    }
}