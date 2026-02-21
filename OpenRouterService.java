package com.kingai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kingai.dto.ChatRequest;
import com.kingai.dto.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class OpenRouterService {

    @Value("${openrouter.api.key}")
    private String apiKey;

    private static final String OPENROUTER_API_URL = "https://openrouter.ai/api/v1/chat/completions";
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final HttpClient httpClient = HttpClient.newHttpClient();

    public ChatResponse callOpenRouter(ChatRequest request) throws Exception {
        // Prepare the request body
        String requestBody = objectMapper.writeValueAsString(request);

        // Build HTTP request
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(new URI(OPENROUTER_API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        // Send request
        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // Parse response
        if (response.statusCode() == 200) {
            JsonNode jsonResponse = objectMapper.readTree(response.body());
            ChatResponse chatResponse = new ChatResponse();
            
            // Extract choices array
            JsonNode choicesNode = jsonResponse.get("choices");
            if (choicesNode != null && choicesNode.isArray() && choicesNode.size() > 0) {
                JsonNode messageNode = choicesNode.get(0).get("message");
                if (messageNode != null) {
                    ChatResponse.Message message = new ChatResponse.Message();
                    message.setRole(messageNode.get("role").asText());
                    message.setContent(messageNode.get("content").asText());
                    
                    chatResponse.addChoice(message);
                    return chatResponse;
                }
            }
            throw new Exception("Invalid response structure from OpenRouter");
        } else {
            String errorBody = response.body();
            throw new Exception("OpenRouter API error: " + response.statusCode() + " - " + errorBody);
        }
    }
}
