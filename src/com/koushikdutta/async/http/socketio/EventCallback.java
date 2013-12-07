package com.koushikdutta.async.http.socketio;

public interface EventCallback {
    public void onEvent(String data, Acknowledge acknowledge);
}