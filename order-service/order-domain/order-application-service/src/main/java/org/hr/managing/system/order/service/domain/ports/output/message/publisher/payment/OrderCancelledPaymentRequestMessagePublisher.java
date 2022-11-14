package org.hr.managing.system.order.service.domain.ports.output.message.publisher.payment;

import org.hr.managing.system.domain.event.publisher.DomainEventPublisher;
import org.hr.managing.system.order.service.domain.event.OrderCancelledEvent;

public interface OrderCancelledPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCancelledEvent> {
}
