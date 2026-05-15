public class ArquivoInfo {

    private String nome;
    private double tamanhoMb;

    public ArquivoInfo(String nome, double tamanhoMb) {
        this.nome = nome;
        this.tamanhoMb = tamanhoMb;
    }

    public String getNome() {
        return nome;
    }

    public double getTamanhoMb() {
        return tamanhoMb;
    }

    @Override
    public String toString() {
        return String.format(
            "%s - %.2f MB",
            nome,
            tamanhoMb
        );
    }
}