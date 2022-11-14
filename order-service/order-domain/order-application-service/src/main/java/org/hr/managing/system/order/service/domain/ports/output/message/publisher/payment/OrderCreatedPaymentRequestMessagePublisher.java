package org.hr.managing.system.order.service.domain.ports.output.message.publisher.payment;

import org.hr.managing.system.domain.event.publisher.DomainEventPublisher;
import org.hr.managing.system.order.service.domain.event.OrderCreatedEvent;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
}
