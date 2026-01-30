package com.example.saudejanotificacaoservice.mappers;

import com.example.saudejanotificacaoservice.dtos.NotificacaoDto;
import com.example.saudejanotificacaoservice.entidades.Notificacao;
import dtos.requests.EnviarNotificacaoRequest;

public class NotificacaoMapper {
    private NotificacaoMapper() {}

    public static Notificacao toEntidade(EnviarNotificacaoRequest enviarNotificacaoRequest) {
        return new Notificacao(enviarNotificacaoRequest.destinatario(), enviarNotificacaoRequest.assunto(), enviarNotificacaoRequest.mensagem());
    }

    public static NotificacaoDto toDto(Notificacao notificacao) {
        return new NotificacaoDto(notificacao.getDestinatario(), notificacao.getAssunto(), notificacao.getMensagem());
    }
}
