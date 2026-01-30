package com.example.saudejanotificacaoservice.controllers;

import com.example.saudejanotificacaoservice.datasources.NotificacaoDataSource;
import com.example.saudejanotificacaoservice.gateways.NotificacaoGateway;
import com.example.saudejanotificacaoservice.usecases.EnviarNotificacaoUseCase;
import dtos.requests.EnviarNotificacaoRequest;

public class NotificacaoController {

    private final NotificacaoDataSource notificacaoDataSource;

    public NotificacaoController(NotificacaoDataSource notificacaoDataSource) {
        this.notificacaoDataSource = notificacaoDataSource;
    }

    public void enviarNotificacao(EnviarNotificacaoRequest enviarNotificacaoRequest) {
        NotificacaoGateway notificacaoGateway = new NotificacaoGateway(notificacaoDataSource);
        EnviarNotificacaoUseCase useCase = new EnviarNotificacaoUseCase(notificacaoGateway);

        useCase.executar(enviarNotificacaoRequest);
    }
}
