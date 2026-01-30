package com.example.saudejanotificacaoservice.infrastructure.input.api.controllers.notificacao;

import com.example.saudejanotificacaoservice.controllers.NotificacaoController;
import com.example.saudejanotificacaoservice.datasources.NotificacaoDataSource;
import com.example.saudejanotificacaoservice.infrastructure.exceptions.TipoTokenException;
import dtos.requests.EnviarNotificacaoRequest;
import enums.TipoTokenEnum;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/notificacoes")
@Tag(name = "Notificações API V1", description = "Versão 1 do controlador referente a notificações")
@Profile("api")
public class NotificacaoControllerV1 {

    private final NotificacaoController notificacaoController;

    public NotificacaoControllerV1(NotificacaoDataSource notificacaoDataSource) {
        this.notificacaoController = new NotificacaoController(notificacaoDataSource);
    }

    @Operation(summary = "Envia notificação",
            description = "Endpoint restrito a serviços",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponses({
            @ApiResponse(responseCode = "204",
                    description = "Notificação enviada com sucesso"),
            @ApiResponse(responseCode = "400",
                    description = "Valores inválidos para a notificação a ser enviada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401",
                    description = "Credenciais de acesso inválidas",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "403",
                    description = "Token autenticado não é de serviço",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProblemDetail.class))),
    })
    @PostMapping("/enviar")
    public ResponseEntity<Void> apagarFichaPaciente(@AuthenticationPrincipal Jwt jwt,
                                                    @RequestBody @Valid EnviarNotificacaoRequest enviarNotificacaoRequest) {
        if (!Objects.equals(TipoTokenEnum.valueOf((String) jwt.getClaims().get("tipo_token")), TipoTokenEnum.SERVICO))
            throw new TipoTokenException();
        log.info("Serviço {} enviando notificação para {}", jwt.getSubject(), enviarNotificacaoRequest.destinatario());
        notificacaoController.enviarNotificacao(enviarNotificacaoRequest);
        log.info("Serviço {} enviou notificação para {}", jwt.getSubject(), enviarNotificacaoRequest.destinatario());

        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
