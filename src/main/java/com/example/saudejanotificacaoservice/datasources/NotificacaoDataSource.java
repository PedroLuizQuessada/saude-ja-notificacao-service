package com.example.saudejanotificacaoservice.datasources;

import com.example.saudejanotificacaoservice.dtos.NotificacaoDto;

public interface NotificacaoDataSource {
    void enviarNotificacao(NotificacaoDto notificacaoDto);
}
