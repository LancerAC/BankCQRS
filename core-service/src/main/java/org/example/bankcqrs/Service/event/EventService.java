package org.example.bankcqrs.Service.event;

import com.example.common.Events.AbstractEvent;

public interface EventService {

    void create(AbstractEvent event);
}
