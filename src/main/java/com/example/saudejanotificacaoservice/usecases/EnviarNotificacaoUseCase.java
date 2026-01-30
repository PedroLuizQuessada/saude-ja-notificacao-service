package com.example.saudejanotificacaoservice.usecases;

import com.example.saudejanotificacaoservice.entidades.Notificacao;
import com.example.saudejanotificacaoservice.gateways.NotificacaoGateway;
import com.example.saudejanotificacaoservice.mappers.NotificacaoMapper;
import dtos.requests.EnviarNotificacaoRequest;

public class EnviarNotificacaoUseCase {
    private final NotificacaoGateway notificacaoGateway;

    public EnviarNotificacaoUseCase(NotificacaoGateway notificacaoGateway) {
        this.notificacaoGateway = notificacaoGateway;
    }

    public void executar(EnviarNotificacaoRequest enviarNotificacaoRequest) {
        Notificacao notificacao = NotificacaoMapper.toEntidade(enviarNotificacaoRequest);

        notificacaoGateway.enviarNotificacao(NotificacaoMapper.toDto(notificacao));
    }
}
