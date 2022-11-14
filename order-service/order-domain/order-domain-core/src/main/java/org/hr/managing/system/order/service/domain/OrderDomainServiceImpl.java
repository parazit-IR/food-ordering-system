package org.hr.managing.system.order.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.hr.managing.system.order.service.domain.entity.Order;
import org.hr.managing.system.order.service.domain.entity.Product;
import org.hr.managing.system.order.service.domain.entity.Restaurant;
import org.hr.managing.system.order.service.domain.event.OrderCancelledEvent;
import org.hr.managing.system.order.service.domain.event.OrderCreatedEvent;
import org.hr.managing.system.order.service.domain.event.OrderPaidEvent;
import org.hr.managing.system.order.service.domain.exception.OrderDomainException;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService{

    private static final String UTC = "UTC";

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        validateRestaurant(restaurant);
        setOrderProductInformation(order, restaurant);
        order.validateOrder();
        order.initializeOrder();
        log.info("Order with id :{} is initialized.", order.getId().getValue() );
        return new OrderCreatedEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        order.pay();
        log.info("Order with id : {} is paid.", order.getId().getValue());
        return new OrderPaidEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void approveOrder(Order order) {
        order.approve();
        log.info("Order with id : {} is approved.", order.getId().getValue());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        order.initCancel(failureMessages);
        log.info("Order payment is cancelling for order order id : {}" , order.getId().getValue());
        return new OrderCancelledEvent(order, ZonedDateTime.now(ZoneId.of(UTC)));
    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {
        order.cancel(failureMessages);
        log.info("Order is cancelled with id : {}" , order.getId().getValue());
    }

    private void validateRestaurant(Restaurant restaurant) {
        if (!restaurant.isActive())
            throw new OrderDomainException("Restaurant with id : " + restaurant.getId().getValue() +
                    "is currently not activated.");
    }

    private void setOrderProductInformation(Order order, Restaurant restaurant) {
        order.getItems().forEach(orderItem -> restaurant.getProducts().forEach(restaurantProduct -> {
            Product currentProduct = orderItem.getProduct();
            if (currentProduct.equals(restaurantProduct)){
                currentProduct.updateWithConfirmedNAmeAndPrice(restaurantProduct.getName(), restaurantProduct.getPrice());
            }
        }));
    }

}
