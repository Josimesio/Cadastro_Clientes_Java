package br.com.exemplo.cadastro;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ClienteRepository {
    private static final Path ARQUIVO = Path.of("data", "clientes.csv");
    private static final String CABECALHO = "id;nome;cpf;email;telefone";

    public List<Cliente> carregar() {
        List<Cliente> clientes = new ArrayList<>();

        try {
            criarArquivoSeNaoExistir();

            List<String> linhas = Files.readAllLines(ARQUIVO, StandardCharsets.UTF_8);

            for (int i = 1; i < linhas.size(); i++) {
                String linha = linhas.get(i).trim();

                if (!linha.isBlank()) {
                    clientes.add(Cliente.fromCsvLine(linha));
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar clientes: " + e.getMessage());
        }

        return clientes;
    }

    public void salvar(List<Cliente> clientes) {
        try {
            criarArquivoSeNaoExistir();

            List<String> linhas = new ArrayList<>();
            linhas.add(CABECALHO);

            for (Cliente cliente : clientes) {
                linhas.add(cliente.toCsvLine());
            }

            Files.write(ARQUIVO, linhas, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }

    private void criarArquivoSeNaoExistir() throws IOException {
        Path pasta = ARQUIVO.getParent();

        if (pasta != null && Files.notExists(pasta)) {
            Files.createDirectories(pasta);
        }

        if (Files.notExists(ARQUIVO)) {
            Files.writeString(ARQUIVO, CABECALHO + System.lineSeparator(), StandardCharsets.UTF_8);
        }
    }
}
