package asw.simplefilter.domain;

import lombok.*;

@Data
@NoArgsConstructor
public class RecensioneBreve {

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
	/* sunto del testo della recensione */
	private String sunto;

	public RecensioneBreve(Recensione recensione) {
		this.id = recensione.getId();
		this.recensore = recensione.getRecensore();
		this.album = recensione.getAlbum();
		this.artista = recensione.getArtista();
		this.genere = recensione.getGenere();
		this.sunto = recensione.getSunto();
	}
	/*
	 * public Long getId() {
	 * return id;
	 * }
	 * 
	 * public void setId(Long id) {
	 * this.id = id;
	 * }
	 * 
	 * public String getRecensore() {
	 * return recensore;
	 * }
	 * 
	 * public void setRecensore(String recensore) {
	 * this.recensore = recensore;
	 * }
	 * 
	 * public String getAlbum() {
	 * return album;
	 * }
	 * 
	 * public void setAlbum(String album) {
	 * this.album = album;
	 * }
	 * 
	 * public String getArtista() {
	 * return artista;
	 * }
	 * 
	 * public void setArtista(String artista) {
	 * this.artista = artista;
	 * }
	 * 
	 * public String getGenere() {
	 * return genere;
	 * }
	 * 
	 * public void setGenere(String genere) {
	 * this.genere = genere;
	 * }
	 * 
	 * public String getSunto() {
	 * return sunto;
	 * }
	 * 
	 * public void setSunto(String sunto) {
	 * this.sunto = sunto;
	 * }
	 */
}