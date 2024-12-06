package asw.goodmusic.recensioniseguite.domain.connessione;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.logging.Logger;

@Service
public class ConnessioneServiceImp implements ConnessioneService {

    @Autowired
    ConnessioneRepository connessioneRepository;

    private final Logger logger = Logger.getLogger(this.getClass().toString());

    public Connessione saveConnessione(Connessione connessione) {
        try {
            Optional<Connessione> existingRecensione = connessioneRepository.findById(connessione.getId());

            if (existingRecensione.isPresent()) {
                logger.info("NOT SAVING THE CONNECTION ID:" + connessione.getId() + "CAUSE IS ALREADY EXISTING");
                return existingRecensione.get();
            } else {
                logger.info("CONNECTION WITH ID:" + connessione.getId() + "SAVED");
                return connessioneRepository.save(connessione);
            }
        } catch (Exception e) {
            logger.severe("ERROR SAVING THE CONNECTION");
            throw new RuntimeException("" + e);
        }
    }

    public void deleteConnessione(Long id) {
        try {
            Optional<Connessione> existingConnessione = connessioneRepository.findById(id);

            if (existingConnessione.isPresent()) {
                connessioneRepository.delete(existingConnessione.get());
                logger.info("THE CONNECTION WITH ID: " + id + " HAS BEEN PERMANENTLY DELETED");
            } else {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            logger.severe("CONNECTION NOT FOUND " + e.getMessage());
        } catch (Exception e) {
            logger.severe("ERROR DELETING THE CONNECTION: " + e.getMessage());
        }
    }

    public Collection<Connessione> getConnessioniByUtente(String utente) {
        try {
            Collection<Connessione> connessioniUtente = connessioneRepository.findByUtente(utente);
            if (connessioniUtente != null) {
                return connessioniUtente;
            } else {
                throw new NoSuchElementException();
            }
        } catch (NoSuchElementException e) {
            logger.severe("CONNECTIONS NOT FOUND " + e.getMessage());
            return null;
        } catch (Exception e) {
            logger.severe("ERROR FINDING USER'S CONNECTIONS");
            throw new RuntimeException("" + e);
        }
    }
}