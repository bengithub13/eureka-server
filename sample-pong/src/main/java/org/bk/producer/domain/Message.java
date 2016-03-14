package org.bk.producer.domain;

import java.util.concurrent.atomic.AtomicInteger;

public class Message {
    private String id;
    private String payload;

    public Message() {

    }
    
    public static final AtomicInteger index = new AtomicInteger();

    public Message(String id, String payload) {
        this.id = String.valueOf(index.incrementAndGet());
        this.payload = payload;
    }

    public Message(String message) {
        this.id = String.valueOf(index.incrementAndGet());
        this.payload = message;
    }

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

}
