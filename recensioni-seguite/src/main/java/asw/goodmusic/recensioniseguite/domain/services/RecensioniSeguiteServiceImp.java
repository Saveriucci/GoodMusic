package asw.goodmusic.recensioniseguite.domain.services;

import asw.goodmusic.recensioniseguite.domain.recensione.RecensioneBreve;
import asw.goodmusic.recensioniseguite.domain.recensione.RecensioneBreveServiceImp;

import asw.goodmusic.recensioniseguite.domain.connessione.Connessione;
import asw.goodmusic.recensioniseguite.domain.connessione.ConnessioneServiceImp;

import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.logging.Logger;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Primary
public class RecensioniSeguiteServiceImp implements RecensioniSeguiteService {

    @Autowired
    RecensioneBreveServiceImp recensioneBreveService;

    @Autowired
    ConnessioneServiceImp connessioneService;

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    @Override
    public Collection<RecensioneBreve> getRecensioniSeguite(String utente) {
        Collection<Connessione> connessioniUtente = connessioneService.getConnessioniByUtente(utente);
        Collection<RecensioneBreve> recensioni = Stream.concat(
                getRecensioniPerRecensori(connessioniUtente).stream(),
                Stream.concat(
                        getRecensioniPerArtisti(connessioniUtente).stream(),
                        getRecensioniPerGeneri(connessioniUtente).stream()))
                .collect(Collectors.toList());
        return recensioni;
    }

    @Override
    public void saveRecensioneBreve(String recensioneBreveJson) {
        try {
            // Deserializza la stringa JSON in un oggetto RecensioneBreve
            RecensioneBreve recensioneBreve = new ObjectMapper().readValue(recensioneBreveJson, RecensioneBreve.class);

            // salvo
            recensioneBreveService.saveRecensioneBreve(recensioneBreve);

            logger.info("Salvata RecensioneBreve: " + recensioneBreve);
        } catch (Exception e) {
            logger.severe("Errore nella deserializzazione della recensione: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void saveConnessione(String connessioneJson) {
        try {
            // Deserializza la stringa JSON in un oggetto RecensioneBreve
            Connessione connessione = new ObjectMapper().readValue(connessioneJson, Connessione.class);

            // bisogna fare il salvataggio
            connessioneService.saveConnessione(connessione);

            logger.info("NEW CONNECTION SAVED: " + connessione);
        } catch (Exception e) {
            logger.severe("ERROR SAVING THE NEW CONNECTION: " + e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void deleteConnessione(String connessioneIdJson) {
        try {
            // Deserializza la stringa JSON in un oggetto RecensioneBreve
            Long connessioneId = new ObjectMapper().readValue(connessioneIdJson, Long.class);

            // bisogna fare il salvataggio
            connessioneService.deleteConnessione(connessioneId);

            logger.info("CONNECTION DELETED: ");
        } catch (Exception e) {
            logger.severe("ERROR DELETING THE CONNECTION: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private Collection<RecensioneBreve> getRecensioniPerRecensori(Collection<Connessione> connessioni) {
        // Filtriamo le connessioni per ottenere solo quelle con ruolo "RECENSORE"
        Collection<String> recensoriSeguiti = connessioni.stream()
                .filter(c -> c.getRuolo().equals("RECENSORE"))
                .map(Connessione::getSeguito)
                .collect(Collectors.toSet());

        if (recensoriSeguiti.isEmpty()) {
            return Collections.emptyList();
        }

        return recensioneBreveService.findByRecensoreIn(recensoriSeguiti);
    }

    private Collection<RecensioneBreve> getRecensioniPerArtisti(Collection<Connessione> connessioni) {
        Collection<String> artistiSeguiti = connessioni.stream()
                .filter(c -> c.getRuolo().equals("ARTISTA"))
                .map(Connessione::getSeguito)
                .collect(Collectors.toSet());

        if (artistiSeguiti.isEmpty()) {
            return Collections.emptyList();
        }

        return recensioneBreveService.findByArtistaIn(artistiSeguiti);
    }

    private Collection<RecensioneBreve> getRecensioniPerGeneri(Collection<Connessione> connessioni) {
        Collection<String> generiSeguiti = connessioni.stream()
                .filter(c -> c.getRuolo().equals("GENERE"))
                .map(Connessione::getSeguito)
                .collect(Collectors.toSet());

        if (generiSeguiti.isEmpty()) {
            return Collections.emptyList();
        }

        return recensioneBreveService.findByGenereIn(generiSeguiti);
    }

}