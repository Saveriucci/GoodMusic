package asw.goodmusic.recensioniseguite.domain.services;

import asw.goodmusic.recensioniseguite.domain.recensione.RecensioneBreve;
import asw.goodmusic.recensioniseguite.domain.recensione.RecensioneBreveService;

import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.*;
import java.util.logging.Logger;

@Service
@Primary
public class RecensioniSeguiteServiceImp implements RecensioniSeguiteService {

    @Autowired
    RecensioneBreveService recensioneBreveService;

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    public Collection<RecensioneBreve> getRecensioniSeguite(String utente) {
        return new ArrayList<RecensioneBreve>();
    }

    public void saveRecensioneBreve(String recensioneBreveJson) {
        try {
            // Deserializza la stringa JSON in un oggetto RecensioneBreve
            RecensioneBreve recensioneBreve = new ObjectMapper().readValue(recensioneBreveJson, RecensioneBreve.class);

            // bisogna fare il salvataggio
            recensioneBreveService.saveRecensioneBreve(recensioneBreve);

            logger.info("Salvata RecensioneBreve: " + recensioneBreve);
        } catch (Exception e) {
            logger.severe("Errore nella deserializzazione della recensione: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveConnessione(String connessione) {
    }

}