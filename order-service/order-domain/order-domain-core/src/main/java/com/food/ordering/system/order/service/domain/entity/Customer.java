package com.food.ordering.system.order.service.domain.entity;

import com.food.ordering.system.domain.entity.AggregateRoot;
import com.food.ordering.system.domain.valueobject.CustomerId;

import java.util.UUID;

public class Customer extends AggregateRoot<CustomerId> {
    private CustomerId customerId;

    public Customer() {
    }

    public Customer(UUID customerId) {
        this.customerId = new CustomerId(customerId) ;
    }
}
