package cv.igrp.simple.pedidos.domain.models;

import cv.igrp.simple.shared.domain.valueobject.Identificador;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class Avaliacao {

    private Integer idDb;
    private Identificador avaliacaoUuid;
    private Integer nota;
    private String comentario;
    private LocalDate dataAvaliacao;
    private Integer userId;
    private Pedido pedido;
    private Integer idPedidoDb;

    private Avaliacao(Integer idDb, Identificador avaliacaoUuid, Integer nota,
                      String comentario, LocalDate dataAvaliacao, Integer userId, Pedido pedido) {

         this.idDb = idDb;
         this.avaliacaoUuid = avaliacaoUuid;
         this.nota = nota;
         this.comentario = comentario;
         this.dataAvaliacao = dataAvaliacao;
         this.userId = userId;
         this.pedido = pedido;
    }

    private Avaliacao(Integer idDb, Identificador avaliacaoUuid, Integer nota,
                      String comentario, LocalDate dataAvaliacao, Integer userId, Integer idPedidoDb) {

        this.idDb = idDb;
        this.avaliacaoUuid = avaliacaoUuid;
        this.nota = nota;
        this.comentario = comentario;
        this.dataAvaliacao = dataAvaliacao;
        this.userId = userId;
        this.idPedidoDb = idPedidoDb;
    }

    public static Avaliacao criarNovo(String comentario,Integer nota, Pedido pedido) {
        var userId= 1; // todo resolve this later
        return new Avaliacao(null,Identificador.gerarNovo(),nota,
                comentario,LocalDate.now(),userId, pedido);
    }

    public static Avaliacao criarNovo(String comentario,Integer nota, Integer idPedidoDb) {
        var userId= 1; // todo resolve this later
        return new Avaliacao(null,Identificador.gerarNovo(),nota,
                comentario,LocalDate.now(),userId, idPedidoDb);
    }


    public static Avaliacao reconstruir(Integer idDb, Identificador avaliacaoUuid, Integer nota,
                                        String comentario, LocalDate dataAvaliacao, Integer userId, Pedido pedido) {

        return new Avaliacao(idDb, avaliacaoUuid, nota, comentario, dataAvaliacao, userId,pedido);
    }

    public static Avaliacao reconstruir(Integer idDb, Identificador avaliacaoUuid, Integer nota,
                                        String comentario, LocalDate dataAvaliacao, Integer userId, Integer idPedidoDb) {

        return new Avaliacao(idDb, avaliacaoUuid, nota, comentario, dataAvaliacao, userId,idPedidoDb);
    }

}
