package org.hr.managing.system.order.service.domain.mapper;


import org.hr.managing.system.domain.valueobject.CustomerId;
import org.hr.managing.system.domain.valueobject.Money;
import org.hr.managing.system.domain.valueobject.ProductId;
import org.hr.managing.system.domain.valueobject.RestaurantId;
import org.hr.managing.system.order.service.domain.dto.create.CreateOrderCommand;
import org.hr.managing.system.order.service.domain.dto.create.CreateOrderResponse;
import org.hr.managing.system.order.service.domain.dto.create.OrderAddress;
import org.hr.managing.system.order.service.domain.dto.track.TrackOrderResponse;
import org.hr.managing.system.order.service.domain.entity.Order;
import org.hr.managing.system.order.service.domain.entity.OrderItem;
import org.hr.managing.system.order.service.domain.entity.Product;
import org.hr.managing.system.order.service.domain.entity.Restaurant;
import org.hr.managing.system.order.service.domain.valueobject.StreetAddress;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class OrderDataMapper {

    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand){
        return Restaurant.builder()
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .products(createOrderCommand.getItems().stream().map(orderItem ->
                        new Product(new ProductId(orderItem.getProductId()))).collect(Collectors.toList()))
                .build();
    }

    public TrackOrderResponse orderToTrackOrderResponse(Order order){
        return TrackOrderResponse.builder()
                .orderStatus(order.getOrderStatus())
                .orderTrackingId(order.getTrackingId().getValue())
                .failureMessages(order.getFailureMessages())
                .build();
    }

    public CreateOrderResponse orderToCreateOrderResponse(Order order, String message){
        return  CreateOrderResponse.builder()
                .message(message)
                .orderStatus(order.getOrderStatus())
                .trackingId(order.getTrackingId().getValue())
                .build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand){
        return Order.builder()
                .customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .deliveryAddress(orderAddressToStreetAddress(createOrderCommand.getAddress()))
                .price(new Money(createOrderCommand.getPrice()))
                .items(orderItemsToOrderItemEntity(createOrderCommand.getItems()))
                .build();
    }

    public List<OrderItem> orderItemsToOrderItemEntity(List<org.hr.managing.system.order.service.domain.dto.create.OrderItem> orderItems) {
        return orderItems.stream().map(orderItem -> OrderItem.builder()
                .product(new Product(new ProductId(orderItem.getProductId())))
                .price(new Money(orderItem.getPrice()))
                .quantity(orderItem.getQuantity())
                .subTotal(new Money(orderItem.getSubTotal()))
                .build()).collect(Collectors.toList());
    }

    private StreetAddress orderAddressToStreetAddress(OrderAddress orderAddress) {
        return new StreetAddress(UUID.randomUUID(), orderAddress.getStreet(), orderAddress.getPostalCode(), orderAddress.getCity());
    }
}
