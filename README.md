# Telegram Artificial Intelligence Bot

Um bot do Telegram integrado com IA Generativa, feito com Java e Spring AI + OpenAI API. Este projeto serve como uma casca para a construção de novos bots integrados com IA, permitindo que desenvolvedores rapidamente criem e personalizem seus próprios bots do Telegram com funcionalidades avançadas de IA.

## Funcionalidades

- **Integração com a API do OpenAI**: Utiliza modelos generativos da OpenAI para processar e responder mensagens.
- **Filtragem de usuários**: Permite o uso apenas por usuários autorizados.
- **Logging estruturado**: Utiliza o sistema de logging do Spring para monitorar e depurar a aplicação.

## Pré-requisitos

- Java 21
- Maven
- Conta e token de bot do Telegram
- API key da OpenAI

## Configuração

1. **Clone o repositório**:
```bash
git clone https://github.com/johnlaff/ai-telegram-bot
cd telegram-ai-bot
```

2. **Configure as variáveis de ambiente**:

Configure as seguintes variáveis de ambiente no seu sistema:

- `OPENAI_API_KEY`: Sua chave de API da OpenAI.
- `TELEGRAM_BOT_TOKEN`: O token do seu bot do Telegram.
- `TELEGRAM_BOT_USERNAME`: O nome de usuário do seu bot do Telegram.
- `TELEGRAM_ALLOWED_USER_ID`: O ID do usuário permitido a interagir com o bot.

3. **Configuração do `application.yml`**:

No arquivo `src/main/resources/application.yml`, você pode selecionar o modelo de IA desejado. Todos os modelos disponíveis podem ser encontrados na documentação da OpenAI:

```yaml
spring:
  application:
    name: Telegram Artificial Intelligence Bot
  ai:
    openai:
      api-key: ${OPENAI_API_KEY}
      chat:
        model: gpt-4o-mini
    retry:
      max-attempts: 2
      backoff:
        period: 1000

telegram:
  bot:
    token: ${TELEGRAM_BOT_TOKEN}
    username: ${TELEGRAM_BOT_USERNAME}
    allowed-user-id: ${TELEGRAM_ALLOWED_USER_ID}
logging:
  level:
    root: INFO
    br.com.johnlaff.telegram: DEBUG
  file:
    name: logs/app.log
```

## Compilação e Execução

1. **Compile o projeto**:
```bash
mvn clean install
```
2. **Execute a aplicação**:
```bash
mvn spring-boot:run
```
## Estrutura do Projeto

- **src/main/java/br/com/johnlaff/telegram**: Contém as classes principais da aplicação.
  - **AITelegramBotApplication**: Classe principal que inicia a aplicação Spring Boot.
  - **service/ChatService**: Classe de serviço responsável pela interação com o cliente de chat da IA.
  - **bot/TelegramBot**: Classe responsável por gerenciar a interação com o bot do Telegram.
  - **bot/BotInitializer**: Classe responsável por inicializar e registrar o bot do Telegram.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.
