package asw.goodmusic.recensioniseguite.domain.recensione;

import jakarta.persistence.*;

import lombok.*;

/* Recensione (in formato breve) di un album scritta da un recensore. */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RecensioneBreve implements Comparable<RecensioneBreve> {

	/* id della recensione */
	@Id
	private Long id;
	/* chi ha scritto la recensione */
	private String recensore;
	/* album oggetto della recensione */
	private String album;
	/* artista autore dell'album */
	private String artista;
	/* genere dell'album */
	private String genere;
	/* sunto del testo della recensione */
	private String sunto;

	@Override
	public boolean equals(Object other1) {
		RecensioneBreve other = (RecensioneBreve) other1;
		return (this.id == other.id) && this.recensore.equals(other.getRecensore()) &&
				this.album.equals(other.getAlbum()) && this.artista.equals(other.getArtista()) &&
				this.genere.equals(other.getGenere()) && this.sunto.equals(other.getSunto());
	}

	@Override
	public int hashCode() {
		return Long.hashCode(this.id);
	}

	@Override
	public int compareTo(RecensioneBreve other) {
		return this.id.compareTo(other.id);
	}

}
