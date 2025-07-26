package cv.igrp.simple.pedidos.domain.valueobject;

import jakarta.validation.constraints.NotBlank;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CodigoAcompanhamento {

    private static final String PREFIXO = "PED";
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("yyMMdd");
    private static final String CARACTERES = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int TAMANHO_ALEATORIO = 4;

    private final String valor;

    private CodigoAcompanhamento(String valor) {
        this.valor = valor;
    }

    public static CodigoAcompanhamento gerar() {
        String data = LocalDate.now().format(FORMATO_DATA);
        String aleatorio = gerarAleatorio(TAMANHO_ALEATORIO);
        String codigo = String.format("%s%s-%s", PREFIXO, data, aleatorio);
        return new CodigoAcompanhamento(codigo);
    }

    private static String gerarAleatorio(int tamanho) {
        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tamanho; i++) {
            sb.append(CARACTERES.charAt(random.nextInt(CARACTERES.length())));
        }
        return sb.toString();
    }

    public static CodigoAcompanhamento of( String codigoAcompanhamento) {
        return new CodigoAcompanhamento(codigoAcompanhamento);
    }

    public String getValor() {
        return valor;
    }
}
