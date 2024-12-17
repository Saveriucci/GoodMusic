package asw.goodmusic.recensioniseguite.domain.connessione;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/* Connessione tra un utente e un seguito (con un ruolo). */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Connessione {

	/* id della connessione */
	@Id
	@GeneratedValue
	private Long id;
	/* utente che segue */
	private String utente;
	/*
	 * chi o cosa è seguito (un artista o uno che scrive recensioni oppure un genere
	 * musicale)
	 */
	private String seguito;
	/* ruolo del seguito: può essere ARTISTA oppure RECENSORE oppure GENERE */
	private String ruolo;

	@Override
	public int hashCode() {
		return Long.hashCode(this.id);
	}

	@Override
	public boolean equals(Object other1) {
		Connessione other = (Connessione) other1;
		return this.id == other.getId() && this.utente.equals(other.getUtente())
				&& this.seguito.equals(other.getSeguito()) && this.ruolo.equals(other.getRuolo());
	}

}
