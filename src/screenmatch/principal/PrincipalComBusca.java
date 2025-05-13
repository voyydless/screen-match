package screenmatch.principal;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import screenmatch.exceptions.ErroDeConversaoDeAnoException;
import screenmatch.modelos.Titulo;
import screenmatch.modelos.TituloOmdb;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        String busca = "";
        List<Titulo> titulos = new ArrayList<>();
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        while (!busca.equalsIgnoreCase("sair")) {
            System.out.println("Digite um filme para buscar: ");
            busca = leitura.nextLine();

            if (busca.equalsIgnoreCase("sair")) {
                break;
            }

            String paraEndereco = "+";

            if (busca.contains(" ")) {
                busca = busca.replaceAll(" ", paraEndereco);
            }

            try {
                String endereco = "https://www.omdbapi.com/?t=" + busca + "&apikey=2e962e9a";

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();

                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();
                System.out.println(json);

                TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);

                Titulo meuTitulo = new Titulo(meuTituloOmdb);
                System.out.println("Título convertido:");
                System.out.println(meuTitulo);

                titulos.add(meuTitulo);

            } catch (NumberFormatException e) {
                System.out.println("\nOcorreu um erro:");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("\nOcorreu um erro no argumento na busca, verifique o endereço.");
            } catch (ErroDeConversaoDeAnoException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(titulos);

        FileWriter escrita = new FileWriter("filmes.json");
        escrita.write(gson.toJson(titulos));
        escrita.close();
        System.out.println("\nO programa finalizou corretamente!");
    }
}
