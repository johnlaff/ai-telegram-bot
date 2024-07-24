package br.com.johnlaff.telegram.bot;

import br.com.johnlaff.telegram.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

/**
 * A classe TelegramBot é responsável por gerenciar a interação com o bot do Telegram.
 * Ela estende a classe TelegramLongPollingBot para receber atualizações e responder às mensagens.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TelegramBot extends TelegramLongPollingBot {

    // Serviço de chat que processa as mensagens recebidas
    private final ChatService chatService;

    // Nome de usuário do bot, injetado a partir das configurações
    @Value("${telegram.bot.username}")
    private String botUsername;

    // Token do bot, injetado a partir das configurações
    @Value("${telegram.bot.token}")
    private String botToken;

    // ID do usuário permitido a interagir com o bot, injetado a partir das configurações
    @Value("${telegram.bot.allowed-user-id}")
    private Long allowedUserId;

    /**
     * Método chamado quando o bot recebe uma atualização.
     * Verifica se a mensagem é de um usuário permitido e processa a mensagem.
     *
     * @param update A atualização recebida.
     */
    @Override
    public void onUpdateReceived(Update update) {
        log.info("Recebendo atualização: {}", update);
        if (update.hasMessage() && update.getMessage().hasText()) {
            long userId = update.getMessage().getFrom().getId();
            if (!allowedUserId.equals(userId)) {
                log.info("Usuário não autorizado: {}", userId);
                sendMessage(update.getMessage().getChatId(), "Você não tem permissão para usar este bot.");
                return;
            }
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            log.info("Mensagem recebida: {}", messageText);
            String response = chatService.chat(messageText);
            log.info("Resposta do ChatService: {}", response);
            sendMessage(chatId, response);
        }
    }

    /**
     * Retorna o nome de usuário do bot.
     *
     * @return O nome de usuário do bot.
     */
    @Override
    public String getBotUsername() {
        return botUsername;
    }

    /**
     * Retorna o token do bot.
     *
     * @return O token do bot.
     */
    @Override
    public String getBotToken() {
        return botToken;
    }

    /**
     * Envia uma mensagem para um determinado chat.
     *
     * @param chatId O ID do chat para o qual a mensagem deve ser enviada.
     * @param text   O texto da mensagem a ser enviada.
     */
    private void sendMessage(long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(chatId));
        message.setText(text);

        try {
            execute(message);
            log.info("Mensagem enviada: {}", text);
        } catch (TelegramApiException e) {
            log.error("Erro ao enviar mensagem", e);
        }
    }
}
