package com.kingai.dto;

import java.util.ArrayList;
import java.util.List;

public class ChatResponse {
    private List<Choice> choices;

    public static class Message {
        private String role;
        private String content;

        public Message() {}

        public Message(String role, String content) {
            this.role = role;
            this.content = content;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

    public static class Choice {
        private Message message;
        private Integer index;
        private String finish_reason;

        public Choice() {}

        public Choice(Message message) {
            this.message = message;
        }

        public Message getMessage() {
            return message;
        }

        public void setMessage(Message message) {
            this.message = message;
        }

        public Integer getIndex() {
            return index;
        }

        public void setIndex(Integer index) {
            this.index = index;
        }

        public String getFinish_reason() {
            return finish_reason;
        }

        public void setFinish_reason(String finish_reason) {
            this.finish_reason = finish_reason;
        }
    }

    public ChatResponse() {
        this.choices = new ArrayList<>();
    }

    public void addChoice(Message message) {
        Choice choice = new Choice(message);
        this.choices.add(choice);
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}
