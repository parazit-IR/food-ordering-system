package org.hr.managing.system.order.service.domain;


import lombok.extern.slf4j.Slf4j;
import org.hr.managing.system.order.service.domain.dto.track.TrackOrderQuery;
import org.hr.managing.system.order.service.domain.dto.track.TrackOrderResponse;
import org.hr.managing.system.order.service.domain.entity.Order;
import org.hr.managing.system.order.service.domain.exception.OrderDomainException;
import org.hr.managing.system.order.service.domain.exception.OrderNotFoundException;
import org.hr.managing.system.order.service.domain.mapper.OrderDataMapper;
import org.hr.managing.system.order.service.domain.ports.output.repository.OrderRepository;
import org.hr.managing.system.order.service.domain.valueobject.TrackingId;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Component
@Slf4j
public class OrderTrackCommandHandler {

    private final OrderDataMapper orderDataMapper;
    private final OrderRepository orderRepository;

    public OrderTrackCommandHandler(OrderDataMapper orderDataMapper, OrderRepository orderRepository) {
        this.orderDataMapper = orderDataMapper;
        this.orderRepository = orderRepository;
    }

    @Transactional(readOnly = true)
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery){
        Optional<Order> orderResult = orderRepository.findByTrackingId(new TrackingId(trackOrderQuery.getOrderTrackingId()));
        if (orderResult.isEmpty()){
            log.warn("Could not found find order with tracking id : {}", trackOrderQuery.getOrderTrackingId());
            throw new OrderNotFoundException("Could not found find order with tracking id : " + trackOrderQuery.getOrderTrackingId());
        }
        return orderDataMapper.orderToTrackOrderResponse(orderResult.get());
    }
}
