package screenmatch.buscas;

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

// Responsável por realizar buscas de filmes na API OMDb,
// converter os resultados para objetos Java e salvar em arquivo JSON.
public class Busca {

    /**
     * Realiza buscas interativas de filmes na API OMDb;
     * Permite ao usuário digitar nomes de filmes para buscar,
     * converte os resultados em objetos Titulo e salva todos
     * em um arquivo JSON apenas com as informações que queremos ao finalizar.
     *
     * @throws IOException Se ocorrer um erro de entrada/saída durante a comunicação com a API ou ao escrever o arquivo
     * @throws InterruptedException Se a operação HTTP for interrompida
     */

    public static void fazerBusca() throws IOException, InterruptedException {
        Scanner leitura = new Scanner(System.in);
        List<Titulo> titulos = new ArrayList<>();
        String busca = "";

        // Configura o Gson para formatação de JSON
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        // Loop principal para buscar filmes até que o usuário digite 's'
        while (!busca.equalsIgnoreCase("s")) {
            System.out.println("\nDigite um filme para converter para JSON ou 's' para sair: ");
            busca = leitura.nextLine();

            if (busca.equalsIgnoreCase("s")) {
                break;
            }

            // Substitui espaços por '+' para formar a URL de busca
            String paraEndereco = "+";
            if (busca.contains(" ")) {
                busca = busca.replaceAll(" ", paraEndereco);
            }

            try {
                // Monta a URL da API com o termo de busca
                String endereco = "https://www.omdbapi.com/?t=" + busca + "&apikey=2e962e9a";

                // Configura e executa a requisição HTTP
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(endereco))
                        .build();

                // Envia a requisição e obtém a resposta
                HttpResponse<String> response = client
                        .send(request, HttpResponse.BodyHandlers.ofString());
                String json = response.body();

                // Converte o JSON para objeto Java
                TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);

                // Cria um objeto Titulo a partir dos dados da API
                Titulo meuTitulo = new Titulo(meuTituloOmdb);
                System.out.println("Título encontrado:");
                System.out.println(meuTitulo);

                // Adiciona à lista de títulos
                titulos.add(meuTitulo);

            } catch (NumberFormatException e) {
                // Erro ao converter valores numéricos
                System.out.println("\nOcorreu um erro:");
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                // Erro na formação da URI
                System.out.println("\nOcorreu um erro no argumento na busca, verifique o endereço.");
            } catch (ErroDeConversaoDeAnoException e) {
                // Erro específico ao converter o ano do filme
                System.out.println(e.getMessage());
            }
        }
        // Salva todos os títulos encontrados em um arquivo JSON,
        // apenas com as informações que queremos
        FileWriter escrita = new FileWriter("filmes.json");
        escrita.write(gson.toJson(titulos));
        escrita.close();

        System.out.println("Programa finalizado com sucesso!");
    }
}
