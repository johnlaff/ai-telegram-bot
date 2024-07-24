package br.com.johnlaff.telegram.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço responsável por interagir com o cliente de chat da IA.
 */
@Service
public class ChatService {

    private final ChatClient chatClient;

    /**
     * Construtor da classe ChatService.
     * Utiliza o ChatClient.Builder para construir uma instância do ChatClient.
     *
     * @param chatClientBuilder Builder do ChatClient, injetado pelo Spring.
     */
    @Autowired
    public ChatService(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }

    /**
     * Método que envia uma mensagem para o cliente de chat da IA e retorna a resposta.
     *
     * @param message Mensagem enviada pelo usuário.
     * @return Resposta da IA.
     */
    public String chat(String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }
}
