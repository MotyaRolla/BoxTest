package ru.makotomc.boundingbox;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;

public interface PlayerEnterBoxCallback {
    Event<PlayerEnterBoxCallback> EVENT = EventFactory.createArrayBacked(PlayerEnterBoxCallback.class,
            (listeners) -> (event) -> {
                for (PlayerEnterBoxCallback listener : listeners)
                    if (!listener.enterBox(event))
                        return false;
                return true;
            });

    boolean enterBox(PlayerEnterBoxEvent event);
}
