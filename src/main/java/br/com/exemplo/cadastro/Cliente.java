package br.com.exemplo.cadastro;

import java.util.Objects;

public class Cliente {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;

    public Cliente(int id, String nome, String cpf, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String toCsvLine() {
        return id + ";" + limparCampo(nome) + ";" + limparCampo(cpf) + ";" + limparCampo(email) + ";" + limparCampo(telefone);
    }

    public static Cliente fromCsvLine(String linha) {
        String[] partes = linha.split(";", -1);

        if (partes.length < 5) {
            throw new IllegalArgumentException("Linha CSV inválida: " + linha);
        }

        return new Cliente(
                Integer.parseInt(partes[0]),
                partes[1],
                partes[2],
                partes[3],
                partes[4]
        );
    }

    private String limparCampo(String valor) {
        if (valor == null) {
            return "";
        }
        return valor.replace(";", ",").trim();
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | Nome: %s | CPF: %s | E-mail: %s | Telefone: %s",
                id, nome, cpf, email, telefone
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cliente cliente)) return false;
        return id == cliente.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
