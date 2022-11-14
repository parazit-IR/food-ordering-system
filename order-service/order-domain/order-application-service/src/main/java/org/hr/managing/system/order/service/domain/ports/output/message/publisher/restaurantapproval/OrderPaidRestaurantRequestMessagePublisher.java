package org.hr.managing.system.order.service.domain.ports.output.message.publisher.restaurantapproval;

import org.hr.managing.system.domain.event.publisher.DomainEventPublisher;
import org.hr.managing.system.order.service.domain.event.OrderPaidEvent;

public interface OrderPaidRestaurantRequestMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
