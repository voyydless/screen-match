import screenmatch.calculos.CalculadoraDeTempo;
import screenmatch.calculos.FiltroRecomendacao;
import screenmatch.modelos.Episodio;
import screenmatch.modelos.Filme;
import screenmatch.modelos.Serie;

public class Principal {
    public static void main(String[] args) {
        Filme gremlins = new Filme();
        gremlins.setNome("Gremlins");
        gremlins.setAnoDeLancamento(1984);
        gremlins.setDuracaoEmMinutos(106);

        gremlins.exibeFichaTecnica();

        gremlins.avalia(8.2);
        gremlins.avalia(6.5);
        gremlins.avalia(9.5);

        System.out.println(gremlins.pegaMedia());
        System.out.println("Total de avaliações: " + gremlins.getTotalDeAvaliacoes());

        Serie house = new Serie();
        house.setNome("House MD");
        house.setAnoDeLancamento(2004);
        house.setTemporadas(8);
        house.setEpisodiosPorTemporada(22);
        house.setMinutosPorEpisodio(45);
        System.out.println("Duração para maratonar: " + house.getDuracaoEmMinutos());

        Filme shortCircuit = new Filme();
        shortCircuit.setNome("Short Circuit");
        shortCircuit.setAnoDeLancamento(1986);
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
    }
}
