package screenmatch.exceptions;

/**
 * Exceção personalizada que é lançada quando ocorre um erro na conversão
 * do ano de lançamento. Esta exceção é utilizada principalmente quando
 * o formato do ano recebido da API não está no formato esperado (4 dígitos)
 * ou não pode ser convertido para um valor numérico válido.
 */
public class ErroDeConversaoDeAnoException extends RuntimeException  {
    private String mensagem;

    //Constrói uma nova exceção com a mensagem de erro especificada.
    public ErroDeConversaoDeAnoException(String mensagem) {
        this.mensagem = mensagem;
    }

    // Retorna a mensagem de erro desta exceção.
    // sobrescrevendo o método getMessage() da classe RuntimeException
    @Override
    public String getMessage() {
        return this.mensagem;
    }
}
