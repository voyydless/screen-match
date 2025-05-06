package screenmatch.principal;

import screenmatch.modelos.Filme;
import screenmatch.modelos.Serie;
import screenmatch.modelos.Titulo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PrincipalComListas {
    public static void main(String[] args) {
        var gremlins = new Filme("Gremlins", 1984);
        gremlins.avalia(8);
        var shortCircuit = new Filme("Short Circuit", 1986);
        shortCircuit.avalia(7.5);
        var theThing = new Filme("The Thing", 1982);
        theThing.avalia(8.9);
        var house = new Serie("House MD", 2004);

        List<Titulo> lista = new ArrayList<>();
        lista.add(theThing);
        lista.add(shortCircuit);
        lista.add(gremlins);
        lista.add(house);

        for (Titulo item: lista) {
            System.out.println(item.getNome());
            if (item instanceof Filme filme && filme.getClassificacao() > 2) {
                System.out.println("Classificação: " + filme.getClassificacao());
            }
        }

        List<String> buscaPorArtista = new ArrayList<>();
        buscaPorArtista.add("Doug Jones");
        buscaPorArtista.add("Sean Bean");
        buscaPorArtista.add("Adam Sandler");
        System.out.println(buscaPorArtista);

        Collections.sort(buscaPorArtista);
        System.out.println("Depois da ordenação: ");
        System.out.println(buscaPorArtista);

        System.out.println("Lista de títulos ordenados: ");
        Collections.sort(lista);
        System.out.println(lista);

        System.out.println("Ordenando por ano: ");
        lista.sort(Comparator.comparing(Titulo::getAnoDeLancamento));
        System.out.println(lista);
    }
}
