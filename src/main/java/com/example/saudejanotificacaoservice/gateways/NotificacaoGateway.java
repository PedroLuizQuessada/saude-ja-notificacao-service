package com.example.saudejanotificacaoservice.gateways;

import com.example.saudejanotificacaoservice.datasources.NotificacaoDataSource;
import com.example.saudejanotificacaoservice.dtos.NotificacaoDto;

public class NotificacaoGateway {
    private final NotificacaoDataSource notificacaoDataSource;

    public NotificacaoGateway(NotificacaoDataSource notificacaoDataSource) {
        this.notificacaoDataSource = notificacaoDataSource;
    }

    public void enviarNotificacao(NotificacaoDto notificacaoDto) {
        notificacaoDataSource.enviarNotificacao(notificacaoDto);
    }
}
