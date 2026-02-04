package com.sergiomorell.tema4gradle;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.SystemMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.openai.OpenAiChatModel;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // El TOKEN no es necesario para interactuar con modelos locales
        final String TOKEN = "ollama";

        var model1 = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey(TOKEN)
                .modelName("gemma:2b")
                .build();

        var model2 = OpenAiChatModel.builder()
                .baseUrl("http://localhost:11434/v1")
                .apiKey(TOKEN)
                .modelName("llama3.1:8b")
                .build();

        List<ChatMessage> history = new ArrayList<>();
        // PREGUNTA
        history.add(new SystemMessage("Eres novato en física cuántica."));
        String pregunta = model1.chat("Haz una pregunta básica sobre física cuántica.");
        System.out.println("Gemma: " + pregunta);

        // RESPUESTA
        history.add(new SystemMessage("Eres un experto en física cuántica."));
        String respuesta = model2.chat(pregunta);
        System.out.println("Llama: " + respuesta);
    }
}
