package asw.simplefilter.domain;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

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

			logger.info(String.format("RECENSIONE ELABORATED AND PUBLISHED SUCCESSFULLY: %s", recensioneBreve.getId()));

		} catch (Exception e) {
			logger.severe(String.format("ERROR DURING PROCESSING A RECENSIONE OBJECT: %s", e.getMessage()));
			e.printStackTrace();
		}
	}

	private Recensione convertToRecensione(String recordValue) throws Exception {
		try {
			// deserializzazione JSON in un oggetto Recensione
			return new ObjectMapper().readValue(recordValue, Recensione.class);
		} catch (Exception e) {
			logger.severe(String.format("ERROR CONVERTING RECENSIONE OBJECT: %s", e.getMessage()));
			throw new Exception("ERROR CONVERTING RECENSIONE OBJECT", e);
		}
	}

	private RecensioneBreve createRecensioneBreve(Recensione recensione) {
		return new RecensioneBreve(recensione);
	}

	private void publishRecensioneBreve(RecensioneBreve recensioneBreve) {
		try {
			// Conversione dell oggetto in stringa json
			String recensioneBreveJson = new ObjectMapper().writeValueAsString(recensioneBreve);

			simpleMessagePublisher.publish(recensioneBreveJson);

		} catch (Exception e) {
			logger.severe(String.format("Errore durante la pubblicazione della RecensioneBreve (ID: %s): %s",
					recensioneBreve.getId(), e.getMessage()));
			e.printStackTrace();
		}
	}
}
