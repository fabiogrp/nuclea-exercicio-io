import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class GeradorRelatorio {

    public List<ArquivoInfo> buscarArquivos(Path pasta) throws IOException {

        List<ArquivoInfo> arquivos = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(pasta)) {

            paths
                .filter(Files::isRegularFile)
                .forEach(path -> {

                    try {

                        long bytes = Files.size(path);

                        double mb = bytes / (1024.0 * 1024.0);

                        arquivos.add(
                            new ArquivoInfo(
                                path.toString(),
                                mb
                            )
                        );

                    } catch (IOException e) {
                        System.out.println(
                            "Erro ao ler arquivo: " + path
                        );
                    }

                });
        }

        arquivos.sort(
            Comparator.comparing(ArquivoInfo::getTamanhoMb)
                      .reversed()
        );

        return arquivos;
    }

    public void gerarArquivoTexto(
            List<ArquivoInfo> arquivos,
            Path destino
    ) throws IOException {

        try (BufferedWriter writer =
                     Files.newBufferedWriter(destino)) {

            for (ArquivoInfo arquivo : arquivos) {

                writer.write(arquivo.toString());
                writer.newLine();

            }
        }
    }
}