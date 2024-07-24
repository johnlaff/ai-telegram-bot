package br.com.johnlaff.telegram.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * A classe BotInitializer é responsável por inicializar e registrar o bot do Telegram
 * quando a aplicação está pronta. Ela utiliza o TelegramBotsApi para registrar o bot.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class BotInitializer {

    // Injeção da instância do TelegramBot
    private final TelegramBot telegramBot;

    /**
     * Método que é executado quando a aplicação está pronta para uso.
     * Ele registra o bot do Telegram utilizando a API do TelegramBotsApi.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        try {
            // Cria uma instância do TelegramBotsApi com a sessão padrão do bot
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);

            // Registra o bot utilizando a instância do TelegramBot
            botsApi.registerBot(telegramBot);

            // Loga uma mensagem informando que o bot foi registrado com sucesso
            log.info("Bot do Telegram registrado com sucesso");
        } catch (TelegramApiException e) {
            // Loga uma mensagem de erro caso o registro do bot falhe
            log.error("Falha ao registrar o bot do Telegram", e);
        }
    }
}
