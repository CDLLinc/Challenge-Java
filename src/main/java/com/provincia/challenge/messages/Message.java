package com.provincia.challenge.messages;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Message {

    private String message;

    public String getContent() {
        return message;
    }

}
