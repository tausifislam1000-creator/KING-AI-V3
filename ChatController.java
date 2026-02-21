package com.kingai.controller;

import com.kingai.dto.ChatRequest;
import com.kingai.dto.ChatResponse;
import com.kingai.service.OpenRouterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ChatController {

    @Autowired
    private OpenRouterService openRouterService;

    @PostMapping("/chat")
    public ResponseEntity<?> chat(@RequestBody ChatRequest request) {
        try {
            ChatResponse response = openRouterService.callOpenRouter(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new ErrorResponse("Error: " + e.getMessage()));
        }
    }

    @GetMapping("/health")
    public ResponseEntity<?> health() {
        return ResponseEntity.ok(new HealthResponse("Kingdom is online and operational"));
    }

    // Inner classes for responses
    public static class HealthResponse {
        public String status;

        public HealthResponse(String status) {
            this.status = status;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }

    public static class ErrorResponse {
        public String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }
    }
}
