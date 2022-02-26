package me.vlouboos.neteasecheatplus.events;

import com.darkmagician6.eventapi.Event;

public class EventChatSend extends Event {
    private String message;

    public EventChatSend(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
