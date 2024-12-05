package asw.goodmusic.recensioniseguite.domain.recensione;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public interface RecensioneClientAsyncPort {

	// public Collection<RecensioneBreve> getRecensioniByArtisti(Collection<String>
	// artisti);
	// public Collection<RecensioneBreve>
	// getRecensioniByRecensori(Collection<String> recensori);
	// public Collection<RecensioneBreve> getRecensioniByGeneri(Collection<String>
	// generi);

	public CompletableFuture<Collection<RecensioneBreve>> getRecensioniByArtistiAsync(Collection<String> artisti);

	public CompletableFuture<Collection<RecensioneBreve>> getRecensioniByRecensoriAsync(Collection<String> recensori);

	public CompletableFuture<Collection<RecensioneBreve>> getRecensioniByGeneriAsync(Collection<String> generi);

}
