# Cadastro de Clientes - Java 17

Projeto simples de cadastro de clientes via console, feito em Java 17.

## Funcionalidades

- Cadastrar cliente
- Listar clientes
- Buscar cliente por CPF
- Atualizar cliente
- Remover cliente
- Salvar dados em arquivo CSV
- Carregar dados automaticamente ao iniciar

## Requisitos

- Java 17 ou superior
- Terminal, VS Code, IntelliJ IDEA ou Eclipse

## Estrutura

```text
cadastro-clientes-java17/
├── data/
│   └── clientes.csv
├── src/
│   └── main/
│       └── java/
│           └── br/
│               └── com/
│                   └── exemplo/
│                       └── cadastro/
│                           ├── App.java
│                           ├── Cliente.java
│                           ├── ClienteRepository.java
│                           └── ClienteService.java
└── README.md
```

## Como executar pelo terminal

Entre na pasta do projeto:

```bash
cd cadastro-clientes-java17
```

Compile:

```bash
javac -d out src/main/java/br/com/exemplo/cadastro/*.java
```

Execute:

```bash
java -cp out br.com.exemplo.cadastro.App
```

## Observação

Os clientes são salvos no arquivo:

```text
data/clientes.csv
```

Este projeto é propositalmente simples, sem banco de dados e sem framework, ideal para estudo, base de evolução ou demonstração.
