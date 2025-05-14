package screenmatch.modelos;

import screenmatch.exceptions.ErroDeConversaoDeAnoException;

public class Titulo implements Comparable<Titulo>{
    private String nome;
    private int anoDeLancamento;
    private boolean incluidoNoPlano;
    private double avaliacao;
    private int qtdeAvaliacoes;
    private int duracaoEmMinutos;

    public Titulo(String nome, int anoDeLancamento){
        this.setNome(nome);
        this.setAnoDeLancamento(anoDeLancamento);
    }

    public Titulo(TituloOmdb meuTituloOmdb) {
        this.nome = meuTituloOmdb.title();

        if(meuTituloOmdb.year().length() > 4) {
            throw new ErroDeConversaoDeAnoException("Não consegui converter o ano pois tem mais de 04 caracteres.");
        }
        this.anoDeLancamento = Integer.valueOf(meuTituloOmdb.year());

        if(Integer.valueOf(meuTituloOmdb.year()) < 2024) {
            this.incluidoNoPlano = true;
        }

        this.duracaoEmMinutos = Integer.valueOf(meuTituloOmdb.runtime().substring(0,3).trim());
    }

    public int getAnoDeLancamento() { return anoDeLancamento; }

    public String getNome() { return nome; }

    public boolean isIncluidoNoPlano() { return incluidoNoPlano; }

    public int getDuracaoEmMinutos() { return duracaoEmMinutos; }

    public int getQtdeAvaliacoes(){ return qtdeAvaliacoes; }

    public void setNome(String nome) { this.nome = nome; }

    public void setAnoDeLancamento(int anoDeLancamento) { this.anoDeLancamento = anoDeLancamento; }

    public void setIncluidoNoPlano(boolean incluidoNoPlano) { this.incluidoNoPlano = incluidoNoPlano; }

    public void setDuracaoEmMinutos(int duracaoEmMinutos) { this.duracaoEmMinutos = duracaoEmMinutos; }

    @Override
    public String toString() {
        return getNome() + " (" + getAnoDeLancamento() + ")" + " - " + getDuracaoEmMinutos() + " min";
    }

    @Override
    public int compareTo(Titulo outroTitulo) {
        return this.getNome().compareTo(outroTitulo.getNome());
    }
}
