package cv.igrp.simple.utente.domain.models;

import lombok.Getter;

import java.util.Objects;

@Getter
public class Utente {

    private final Integer id;
    private final String nome;
    private final String numero;

    private Utente(Integer id, String nome, String numero) {
        validar(nome, numero);
        this.id = id;
        this.nome = nome;
        this.numero = numero;
    }

    public static Utente criarNovo(String nome, String numero) {
        return new Utente(null, nome, numero);
    }

    public static Utente reconstruir(Integer id, String nome, String numero) {
        return new Utente(id, nome, numero);
    }

    private void validar(String nome, String numero) {
        Objects.requireNonNull(nome, "Nome do utente não pode ser nulo.");
        Objects.requireNonNull(numero, "Número do utente não pode ser nulo.");
        if (nome.isBlank()) {
            throw new IllegalArgumentException("Nome do utente não pode estar em branco.");
        }
        if (numero.isBlank()) {
            throw new IllegalArgumentException("Número do utente não pode estar em branco.");
        }
    }

}
