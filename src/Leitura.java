import java.util.Scanner;

public class Leitura {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);

        System.out.println("Digite seu filme favorito: ");
        String filme = leitura.nextLine();
        System.out.println("E qual o ano de lançamento?");
        int anoDeLancamento = leitura.nextInt();
        System.out.println("Avaliação de 1-10: ");
        double avaliacao = leitura.nextDouble();

        System.out.println(filme + " - " + anoDeLancamento + " | " + avaliacao);
    }
}
