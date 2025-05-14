package screenmatch.modelos;

import com.google.gson.annotations.SerializedName;

// Classe de registro que mapeia os dados recebidos da API OMDb;
// Usa anotações SerializedName para mapear corretamente os campos JSON.
public record TituloOmdb(
        @SerializedName("Title") String title,
        @SerializedName("Year") String year,
        @SerializedName("Runtime") String runtime,
        @SerializedName("Genre") String genre,
        @SerializedName("Director") String director,
        @SerializedName("imdbRating") String imdbRating,
        @SerializedName("imdbVotes") String imdbVotes
) {}
