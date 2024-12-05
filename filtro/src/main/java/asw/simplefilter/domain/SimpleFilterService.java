package asw.simplefilter.domain;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

@Service
public class SimpleFilterService {

	private final Logger logger = Logger.getLogger(this.getClass().toString());

	@Autowired
	private SimpleMessagePublisherPort simpleMessagePublisher;

	public void filter(String record) {
		try {
			Recensione recensione = convertToRecensione(record);
			RecensioneBreve recensioneBreve = createRecensioneBreve(recensione);

			publishRecensioneBreve(recensioneBreve);

			logger.info(String.format("Recensione elaborata e pubblicata con successo: %s", recensioneBreve.getId()));

		} catch (Exception e) {
			logger.severe(String.format("Errore durante il processamento del messaggio: %s", e.getMessage()));
			e.printStackTrace();
		}
	}

	private Recensione convertToRecensione(String recordValue) throws Exception {
		try {
			// deserializzazione JSON in un oggetto Recensione
			return new ObjectMapper().readValue(recordValue, Recensione.class);
		} catch (Exception e) {
			logger.severe(String.format("Errore nella conversione del record in Recensione: %s", e.getMessage()));
			throw new Exception("Errore nella conversione della Recensione", e);
		}
	}

	private RecensioneBreve createRecensioneBreve(Recensione recensione) {
		return new RecensioneBreve(recensione);
	}

	private void publishRecensioneBreve(RecensioneBreve recensioneBreve) {
		try {
			// Conversioen dell oggetto in stringa json
			String recensioneBreveJson = new ObjectMapper().writeValueAsString(recensioneBreve);

			simpleMessagePublisher.publish(recensioneBreveJson);

		} catch (Exception e) {
			logger.severe(String.format("Errore durante la pubblicazione della RecensioneBreve (ID: %s): %s",
					recensioneBreve.getId(), e.getMessage()));
			e.printStackTrace();
		}
	}
}
