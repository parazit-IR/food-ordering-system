package org.hr.managing.system.order.service.domain.entity;

import org.hr.managing.system.domain.entity.AggregateRoot;
import org.hr.managing.system.domain.valueobject.CustomerId;
import org.hr.managing.system.domain.valueobject.OrderId;

import java.util.UUID;

public class Customer extends AggregateRoot<CustomerId> {
    private CustomerId customerId;

    public Customer() {
    }

    public Customer(UUID customerId) {
        this.customerId = new CustomerId(customerId) ;
    }
}
