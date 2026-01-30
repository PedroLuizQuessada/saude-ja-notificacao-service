package com.example.saudejanotificacaoservice.entidades;

import com.example.saudejanotificacaoservice.exceptions.BadArgumentException;
import lombok.Getter;

import java.util.Objects;

@Getter
public class Notificacao {
    private final String destinatario;
    private final String assunto;
    private final String mensagem;

    public Notificacao(String destinatario, String assunto, String mensagem) {
        String mensagemErro = "";

        try {
            validarDestinatario(destinatario);
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }

        try {
            validarAssunto(assunto);
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }

        try {
            validarMensagem(mensagem);
        }
        catch (RuntimeException e) {
            mensagemErro = mensagemErro + " " + e.getMessage();
        }

        if (!mensagemErro.isEmpty())
            throw new BadArgumentException(mensagemErro);

        this.destinatario = destinatario;
        this.assunto = assunto;
        this.mensagem = mensagem;
    }

    private void validarDestinatario(String destinatario) {
        if (Objects.isNull(destinatario)) {
            throw new BadArgumentException("A notificação deve possuir um destinatário.");
        }
    }

    private void validarAssunto(String assunto) {
        if (Objects.isNull(assunto)) {
            throw new BadArgumentException("A notificação deve possuir um assunto.");
        }
    }

    private void validarMensagem(String mensagem) {
        if (Objects.isNull(mensagem)) {
            throw new BadArgumentException("A notificação deve possuir uma mensagem.");
        }
    }
}
