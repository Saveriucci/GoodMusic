package asw.goodmusic.recensioniseguite.domain.connessione;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public interface ConnessioneService {

    public Connessione saveConnessione(Connessione connessione);

    public void deleteConnessione(Long id);

    public Collection<Connessione> getConnessioniByUtente(String utente);

}