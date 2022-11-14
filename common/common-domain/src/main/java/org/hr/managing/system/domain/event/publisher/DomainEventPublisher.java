package org.hr.managing.system.domain.event.publisher;

import org.hr.managing.system.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent>{
    void publish(T domainEvent);
}
