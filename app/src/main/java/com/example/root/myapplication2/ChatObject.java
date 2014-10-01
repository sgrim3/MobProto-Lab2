package com.example.root.myapplication2;

/**
 * Created by root on 9/21/14.
 */
import java.io.Serializable;

/**
 * Created by clee2 on 5/26/2014.
 */
public class ChatObject implements Serializable {
    public String name;
    public String message;
    public String timestamp;

    public ChatObject(){}
    public ChatObject(String message, String name, String timestamp) {
        this.name = name;
        this.message = message;
        this.timestamp = timestamp;
    }
}
