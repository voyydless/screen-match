import screenmatch.calculos.CalculadoraDeTempo;
import screenmatch.calculos.FiltroRecomendacao;
import screenmatch.modelos.Episodio;
import screenmatch.modelos.Filme;
import screenmatch.modelos.Serie;

import java.util.ArrayList;

public class Principal {
    public static void main(String[] args) {
        Filme gremlins = new Filme("Gremlins", 1984);
        gremlins.setDuracaoEmMinutos(106);

        gremlins.exibeFichaTecnica();

        gremlins.avalia(8.2);
        gremlins.avalia(6.5);
        gremlins.avalia(9.5);

        System.out.println(gremlins.pegaMedia());
        System.out.println("Total de avaliações: " + gremlins.getTotalDeAvaliacoes());

        Serie house = new Serie("House MD", 2004);
        house.setTemporadas(8);
        house.setEpisodiosPorTemporada(22);
        house.setMinutosPorEpisodio(45);
        System.out.println("Duração para maratonar: " + house.getDuracaoEmMinutos());

        Filme shortCircuit = new Filme("Short Circuit", 1986);
        shortCircuit.setDuracaoEmMinutos(98);

        CalculadoraDeTempo calculadora = new CalculadoraDeTempo();
        calculadora.inclui(gremlins);
        calculadora.inclui(shortCircuit);
        calculadora.inclui(house);
        System.out.println(calculadora.getTempoTotal());

        FiltroRecomendacao filtro = new FiltroRecomendacao();
        filtro.filtra(gremlins);

        Episodio episodio = new Episodio();
        episodio.setNumero(1);
        episodio.setSerie(house);
        episodio.setTotalVisualizacoes(100);
        filtro.filtra(episodio);

        var theThing = new Filme("The Thing", 1982);
        theThing.setDuracaoEmMinutos(109);
        theThing.avalia(8.5);

        ArrayList<Filme> listaDeFilmes = new ArrayList<>();
        listaDeFilmes.add(theThing);
        listaDeFilmes.add(shortCircuit);
        listaDeFilmes.add(gremlins);
        System.out.println("Tamanho da lista: " + listaDeFilmes.size());
        System.out.println("Primeiro filme: " + listaDeFilmes.getFirst().getNome());
        System.out.println(listaDeFilmes);
        System.out.println("toString do filme: " + listaDeFilmes.getFirst().toString());
    }
}
