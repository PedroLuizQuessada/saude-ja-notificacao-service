package com.example.saudejanotificacaoservice.infrastructure.notificacoes.javamailsender.services;

import com.example.saudejanotificacaoservice.datasources.NotificacaoDataSource;
import com.example.saudejanotificacaoservice.dtos.NotificacaoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Profile("javamailsender")
public class NotificacaoJavaMailSenderImpl implements NotificacaoDataSource {

    private final JavaMailSender javaMailSender;

    @Value("${email.remetente}")
    private String sender;

    public NotificacaoJavaMailSenderImpl(@Autowired(required = false) JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void enviarNotificacao(NotificacaoDto notificacaoDto) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(notificacaoDto.destinatario());
        mailMessage.setSubject(notificacaoDto.assunto());
        mailMessage.setText(notificacaoDto.mensagem());
        javaMailSender.send(mailMessage);
    }
}
