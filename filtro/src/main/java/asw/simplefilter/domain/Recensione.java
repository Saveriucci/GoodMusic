package asw.simplefilter.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Recensione {

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
	/*
	 * public Recensione(Long id, String recensore, String album, String artista,
	 * String genere, String testo,
	 * String sunto) {
	 * this.id = id;
	 * this.recensore = recensore;
	 * this.album = album;
	 * this.artista = artista;
	 * this.genere = genere;
	 * this.testo = testo;
	 * this.sunto = sunto;
	 * }
	 * 
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
	 * public String getTesto() {
	 * return testo;
	 * }
	 * 
	 * public void setTesto(String testo) {
	 * this.testo = testo;
	 * }
	 * 
	 * public String getSunto() {
	 * return sunto;
	 * }
	 * 
	 * public void setSunto(String sunto) {
	 * this.sunto = sunto;
	 * }
	 * 
	 */
}
