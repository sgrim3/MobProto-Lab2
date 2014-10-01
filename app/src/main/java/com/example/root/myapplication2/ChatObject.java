package com.example.root.myapplication2;

/**
 * Created by root on 9/21/14.
 */
import java.io.Serializable;

/**
 * Created by clee2 on 5/26/2014.
 */
public class ChatObject implements Serializable {
    String sender, message, timestamp;

    public ChatObject (String sender, String message, String timestamp) {
        this.sender = sender;
        this.message = message;
        this.timestamp = timestamp;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
