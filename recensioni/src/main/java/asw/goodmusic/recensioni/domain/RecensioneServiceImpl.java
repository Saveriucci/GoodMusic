package asw.goodmusic.recensioni.domain;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@Service
public class RecensioneServiceImpl implements RecensioneService {

	@Autowired
	private RecensioneRepository recensioniRepository;

	/* Crea una nuova recensione, a partire dai suoi dati. */
	@Override
	public Recensione createRecensione(String recensore, String album, String artista, String genere, String testo,
			String sunto) {
		Recensione recensione = new Recensione(recensore, album, artista, genere, testo, sunto);
		recensione = recensioniRepository.save(recensione);
		return recensione;
	}

	/* Trova una recensione, dato l'id. */
	@Override
	public Recensione getRecensione(Long id) {
		Recensione recensione = recensioniRepository.findById(id).orElse(null);
		return recensione;
	}

	/* Trova tutte le recensioni. */
	@Override
	public Collection<Recensione> getRecensioni() {
		Collection<Recensione> recensioni = recensioniRepository.findAll();
		return recensioni;
	}

	/* Trova tutte le recensioni di un album. */
	@Override
	public Collection<Recensione> getRecensioniByAlbum(String album) {
		Collection<Recensione> recensioni = recensioniRepository.findByAlbum(album);
		return recensioni;
	}

	/* Trova tutte le recensioni scritte da un recensore. */
	@Override
	public Collection<Recensione> getRecensioniByRecensore(String recensore) {
		Collection<Recensione> recensioni = recensioniRepository.findByRecensore(recensore);
		return recensioni;
	}

	/* Trova tutte le recensioni scritte da un insieme di recensori. */
	@Override
	public Collection<Recensione> getRecensioniByRecensori(Collection<String> recensori) {
		Collection<Recensione> recensioni = recensioniRepository.findByRecensoreIn(recensori);
		return recensioni;
	}

	/* Trova tutte le recensioni degli album di un artista. */
	@Override
	public Collection<Recensione> getRecensioniByArtista(String artista) {
		Collection<Recensione> recensioni = recensioniRepository.findByArtista(artista);
		return recensioni;
	}

	/* Trova tutte le recensioni degli album di un insieme di artisti. */
	@Override
	public Collection<Recensione> getRecensioniByArtisti(Collection<String> artisti) {
		Collection<Recensione> recensioni = recensioniRepository.findByArtistaIn(artisti);
		return recensioni;
	}

	/* Trova tutte le recensioni degli album di un certo genere. */
	@Override
	public Collection<Recensione> getRecensioniByGenere(String genere) {
		Collection<Recensione> recensioni = recensioniRepository.findByGenere(genere);
		return recensioni;
	}

	/* Trova tutte le recensioni degli album di un insieme di generi. */
	@Override
	public Collection<Recensione> getRecensioniByGeneri(Collection<String> generi) {
		Collection<Recensione> recensioni = recensioniRepository.findByGenereIn(generi);
		return recensioni;
	}

}
