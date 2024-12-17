package asw.goodmusic.recensioni.domain;

import jakarta.persistence.*;

import lombok.*;

/* Recensione di un album scritta da un recensore. */
@Entity
@Data
@NoArgsConstructor
public class Recensione implements Comparable<Recensione> {

	/* id della recensione */
	@Id
	@GeneratedValue
	private Long id;

	/* chi ha scritto la recensione */
	private String recensore;
	/* album oggetto della recensione */
	private String album;
	/* artista autore dell'album */
	private String artista;
	/* genere dell'album */
	private String genere;
	/* testo della recensione */
	private String testo;
	/* sunto del testo della recensione */
	private String sunto;

	public Recensione(String recensore, String album, String artista, String genere, String testo, String sunto) {
		this();
		this.recensore = recensore;
		this.album = album;
		this.artista = artista;
		this.genere = genere;
		this.testo = testo;
		this.sunto = sunto;
	}

	@Override
	public boolean equals(Object other1) {
		Recensione other = (Recensione) other1;
		return (this.id == other.id) && this.recensore.equals(other.getRecensore()) &&
				this.album.equals(other.getAlbum()) && this.artista.equals(other.getArtista()) &&
				this.genere.equals(other.getGenere()) && this.testo.equals(other.getTesto()) &&
				this.sunto.equals(other.getSunto());
	}

	@Override
	public int hashCode() {
		return Long.hashCode(this.id);
	}

	@Override
	public int compareTo(Recensione other) {
		return this.id.compareTo(other.id);
	}

}
