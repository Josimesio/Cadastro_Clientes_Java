package br.com.exemplo.cadastro;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class ClienteService {
    private final ClienteRepository repository;
    private final List<Cliente> clientes;

    public ClienteService() {
        this.repository = new ClienteRepository();
        this.clientes = new ArrayList<>(repository.carregar());
    }

    public Cliente cadastrar(String nome, String cpf, String email, String telefone) {
        validarNome(nome);
        validarCpf(cpf);

        if (buscarPorCpf(cpf).isPresent()) {
            throw new IllegalArgumentException("Já existe cliente cadastrado com este CPF.");
        }

        int novoId = gerarProximoId();
        Cliente cliente = new Cliente(novoId, nome.trim(), cpf.trim(), email.trim(), telefone.trim());

        clientes.add(cliente);
        salvar();

        return cliente;
    }

    public List<Cliente> listar() {
        return clientes.stream()
                .sorted(Comparator.comparing(Cliente::getId))
                .toList();
    }

    public Optional<Cliente> buscarPorCpf(String cpf) {
        if (cpf == null) {
            return Optional.empty();
        }

        String cpfNormalizado = cpf.trim();

        return clientes.stream()
                .filter(cliente -> cliente.getCpf().equalsIgnoreCase(cpfNormalizado))
                .findFirst();
    }

    public boolean atualizar(String cpf, String novoNome, String novoEmail, String novoTelefone) {
        Optional<Cliente> clienteOptional = buscarPorCpf(cpf);

        if (clienteOptional.isEmpty()) {
            return false;
        }

        Cliente cliente = clienteOptional.get();

        validarNome(novoNome);

        cliente.setNome(novoNome.trim());
        cliente.setEmail(novoEmail.trim());
        cliente.setTelefone(novoTelefone.trim());

        salvar();
        return true;
    }

    public boolean remover(String cpf) {
        Optional<Cliente> clienteOptional = buscarPorCpf(cpf);

        if (clienteOptional.isEmpty()) {
            return false;
        }

        clientes.remove(clienteOptional.get());
        salvar();

        return true;
    }

    private int gerarProximoId() {
        return clientes.stream()
                .mapToInt(Cliente::getId)
                .max()
                .orElse(0) + 1;
    }

    private void salvar() {
        repository.salvar(clientes);
    }

    private void validarNome(String nome) {
        if (nome == null || nome.trim().length() < 3) {
            throw new IllegalArgumentException("O nome deve ter pelo menos 3 caracteres.");
        }
    }

    private void validarCpf(String cpf) {
        if (cpf == null || cpf.trim().length() < 11) {
            throw new IllegalArgumentException("O CPF deve ter pelo menos 11 caracteres.");
        }
    }
}
