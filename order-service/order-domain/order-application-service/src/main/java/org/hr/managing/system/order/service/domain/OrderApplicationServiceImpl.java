package org.hr.managing.system.order.service.domain;

import lombok.extern.slf4j.Slf4j;
import org.hr.managing.system.order.service.domain.dto.create.CreateOrderCommand;
import org.hr.managing.system.order.service.domain.dto.create.CreateOrderResponse;
import org.hr.managing.system.order.service.domain.dto.track.TrackOrderQuery;
import org.hr.managing.system.order.service.domain.dto.track.TrackOrderResponse;
import org.hr.managing.system.order.service.domain.ports.input.service.OrderApplicationService;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Slf4j
@Service
class OrderApplicationServiceImpl implements OrderApplicationService {

    private final OrderTrackCommandHandler orderTrackCommandHandler;
    private final OrderCreateCommandHandler orderCreateCommandHandler;

    OrderApplicationServiceImpl(OrderTrackCommandHandler orderTrackCommandHandler, OrderCreateCommandHandler orderCreateCommandHandler) {
        this.orderTrackCommandHandler = orderTrackCommandHandler;
        this.orderCreateCommandHandler = orderCreateCommandHandler;
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        return orderCreateCommandHandler.createOrder(createOrderCommand);
    }

    @Override
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery) {
        return orderTrackCommandHandler.trackOrder(trackOrderQuery);
    }
}
