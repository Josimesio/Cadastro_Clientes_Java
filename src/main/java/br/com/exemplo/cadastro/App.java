package br.com.exemplo.cadastro;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ClienteService service = new ClienteService();

    public static void main(String[] args) {
        int opcao;

        do {
            mostrarMenu();
            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1 -> cadastrarCliente();
                case 2 -> listarClientes();
                case 3 -> buscarClientePorCpf();
                case 4 -> atualizarCliente();
                case 5 -> removerCliente();
                case 0 -> System.out.println("Sistema finalizado.");
                default -> System.out.println("Opção inválida.");
            }

            if (opcao != 0) {
                pausar();
            }

        } while (opcao != 0);
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("======================================");
        System.out.println("       CADASTRO DE CLIENTES");
        System.out.println("======================================");
        System.out.println("1 - Cadastrar cliente");
        System.out.println("2 - Listar clientes");
        System.out.println("3 - Buscar cliente por CPF");
        System.out.println("4 - Atualizar cliente");
        System.out.println("5 - Remover cliente");
        System.out.println("0 - Sair");
        System.out.println("======================================");
    }

    private static void cadastrarCliente() {
        try {
            System.out.println();
            System.out.println("Cadastro de Cliente");

            String nome = lerTexto("Nome: ");
            String cpf = lerTexto("CPF: ");
            String email = lerTexto("E-mail: ");
            String telefone = lerTexto("Telefone: ");

            Cliente cliente = service.cadastrar(nome, cpf, email, telefone);

            System.out.println();
            System.out.println("Cliente cadastrado com sucesso:");
            System.out.println(cliente);

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void listarClientes() {
        System.out.println();
        System.out.println("Lista de Clientes");

        List<Cliente> clientes = service.listar();

        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        clientes.forEach(System.out::println);
    }

    private static void buscarClientePorCpf() {
        System.out.println();
        System.out.println("Buscar Cliente por CPF");

        String cpf = lerTexto("CPF: ");

        Optional<Cliente> cliente = service.buscarPorCpf(cpf);

        if (cliente.isPresent()) {
            System.out.println("Cliente encontrado:");
            System.out.println(cliente.get());
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static void atualizarCliente() {
        try {
            System.out.println();
            System.out.println("Atualizar Cliente");

            String cpf = lerTexto("CPF do cliente: ");
            String novoNome = lerTexto("Novo nome: ");
            String novoEmail = lerTexto("Novo e-mail: ");
            String novoTelefone = lerTexto("Novo telefone: ");

            boolean atualizado = service.atualizar(cpf, novoNome, novoEmail, novoTelefone);

            if (atualizado) {
                System.out.println("Cliente atualizado com sucesso.");
            } else {
                System.out.println("Cliente não encontrado.");
            }

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void removerCliente() {
        System.out.println();
        System.out.println("Remover Cliente");

        String cpf = lerTexto("CPF do cliente: ");

        boolean removido = service.remover(cpf);

        if (removido) {
            System.out.println("Cliente removido com sucesso.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Digite um número válido.");
            }
        }
    }

    private static void pausar() {
        System.out.println();
        System.out.print("Pressione ENTER para continuar...");
        scanner.nextLine();
    }
}
