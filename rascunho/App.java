import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o caminho da pasta:");

        String caminho = scanner.nextLine();

        Path pasta = Path.of(caminho);

        GeradorRelatorio gerador =
                new GeradorRelatorio();

        try {

            List<ArquivoInfo> arquivos =
                    gerador.buscarArquivos(pasta);

            Path relatorio =
                    Path.of("relatorio.txt");

            gerador.gerarArquivoTexto(
                    arquivos,
                    relatorio
            );

            System.out.println(
                    "Relatório gerado com sucesso!"
            );

            System.out.println(
                    "Arquivo: " + relatorio.toAbsolutePath()
            );

        } catch (IOException e) {

            System.out.println(
                    "Erro: " + e.getMessage()
            );

        }

        scanner.close();
    }
}