package com.provincia.challenge.dtos;

import com.provincia.challenge.messages.Message;
import org.junit.Test;
import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void testMessageConstructorAndGetters() {
        // Definimos un mensaje
        String expectedMessage = "Test Message";

        // Instanciamos un Message
        Message message = new Message("Test Message");
        message.setMessage(expectedMessage);

        // Verificamos que coincidan
        assertEquals(expectedMessage, message.getMessage());
    }
}

