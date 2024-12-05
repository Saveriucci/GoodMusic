package asw.goodmusic.recensioni.domain;

import lombok.*; 

/* Recensione di un album scritta da un recensore. */  
@Data @NoArgsConstructor
public class RecensioneCreatedEvent {

	/* id della recensione */
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

	public RecensioneCreatedEvent(Recensione recensione) {
        this.id = recensione.getId();
		this.recensore = recensione.getRecensore(); 
        this.album = recensione.getAlbum();
        this.artista = recensione.getArtista();
        this.genere = recensione.getGenere();
        this.testo = recensione.getTesto();
        this.sunto = recensione.getSunto();
	}
}
