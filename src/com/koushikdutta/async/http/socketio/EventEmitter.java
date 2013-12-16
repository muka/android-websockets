package com.koushikdutta.async.http.socketio;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by koush on 7/1/13.
 */
public class EventEmitter {
    interface OnceCallback extends EventCallback {
    }

    HashSet<EventCallback> callbacks = new HashSet<EventCallback>();
    
    void onEvent(String data, Acknowledge acknowledge) {
        Iterator<EventCallback> iter = callbacks.iterator();
        while (iter.hasNext()) {
            EventCallback cb = iter.next();
            cb.onEvent(data, acknowledge);
            if (cb instanceof OnceCallback)
                iter.remove();
        }
    }

    public void addListener(EventCallback callback) {
        on(callback);
    }

    public void once(final String event, final EventCallback callback) {
        on(new OnceCallback() {
            @Override
            public void onEvent(String data, Acknowledge acknowledge) {
                callback.onEvent(data, acknowledge);
            }
        });
    }

    public void on(EventCallback callback) {
        callbacks.add(callback);
    }

    public void removeListener(EventCallback callback) {
        callbacks.remove(callback);
    }
}
