package com.cornell.air.a10ants.Model;

/**
 * Created by massami on 30/05/2017.
 */

import java.util.Date;

public class ChatMessage {
    private String messageText;
    private String messageUser;
    private long messageTime;

    public ChatMessage(String messageText, String messageUser) {
        this.messageText = messageText;
        this.messageUser = messageUser;

        messageTime = new Date().getTime();
    }

    /**
     * Return the value of the message text
     * @return return value
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     * Return the value of the message user
     * @return return value
     */
    public String getMessageUser() {
        return messageUser;
    }

    /**
     * Return the value of the message time
     * @return return value
     */
    public long getMessageTime() {
        return messageTime;
    }
}
