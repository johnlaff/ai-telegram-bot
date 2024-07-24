package br.com.johnlaff.telegram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Spring Boot.
 * Esta classe inicializa a aplicação e configura o contexto do Spring.
 */
@SpringBootApplication
public class AITelegramBotApplication {

	/**
	 * Método principal que inicia a aplicação Spring Boot.
	 *
	 * @param args Argumentos de linha de comando.
	 */
	public static void main(String[] args) {
		SpringApplication.run(AITelegramBotApplication.class, args);
	}
}
