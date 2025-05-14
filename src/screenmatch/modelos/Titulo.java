package screenmatch.modelos;

import screenmatch.exceptions.ErroDeConversaoDeAnoException;


public class Titulo{
    private String nome;
    private int anoDeLancamento;
    private boolean incluidoNoPlano;
    private double avaliacao;
    private int qtdeAvaliacoes;
    private int duracaoEmMinutos;
    private String genero;
    private String diretor;

    // Cria um novo Título a partir de dados obtidos da API OMDb;
    // Realiza conversões e validações dos dados recebidos
    public Titulo(TituloOmdb meuTituloOmdb) {
        this.nome = meuTituloOmdb.title();

        if(meuTituloOmdb.year().length() > 4) {
            throw new ErroDeConversaoDeAnoException("Não consegui converter o ano pois tem mais de 04 caracteres.");
        }
        this.anoDeLancamento = Integer.valueOf(meuTituloOmdb.year());

        if(Integer.valueOf(meuTituloOmdb.year()) < 2024) {
            this.incluidoNoPlano = true;
        }

        // Converte a avaliação IMDB para double, tratando valores nulos ou não disponíveis
        if (meuTituloOmdb.imdbRating() != null && !meuTituloOmdb.imdbRating().equals("N/A")) {
            this.avaliacao = Double.valueOf(meuTituloOmdb.imdbRating());
        } else {
            this.avaliacao = 0;
        }

        // Processa a quantidade de votos, removendo caracteres não numéricos
        if (meuTituloOmdb.imdbVotes() != null && !meuTituloOmdb.imdbVotes().equals("N/A")) {
            String votosLimpos = meuTituloOmdb.imdbVotes().replaceAll("[^0-9]", "");
            this.qtdeAvaliacoes = votosLimpos.isEmpty() ? 0 : Integer.valueOf(votosLimpos);
        } else {
            this.qtdeAvaliacoes = 0;
        }

        // Extrai apenas os dígitos da duração (runtime)
        String duracaoLimpa = meuTituloOmdb.runtime().replaceAll("[^0-9]", "");
        this.duracaoEmMinutos = Integer.valueOf(duracaoLimpa);

        this.genero = meuTituloOmdb.genre();

        this.diretor = meuTituloOmdb.director();
    }


    @Override
    public String toString() {
        return nome + " (" + anoDeLancamento + ")" + " - " + duracaoEmMinutos + " min";
    }
}
