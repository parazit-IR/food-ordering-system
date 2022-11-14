package org.hr.managing.system.order.service.domain.ports.input.service;

import org.hr.managing.system.order.service.domain.dto.create.CreateOrderCommand;
import org.hr.managing.system.order.service.domain.dto.create.CreateOrderResponse;
import org.hr.managing.system.order.service.domain.dto.track.TrackOrderQuery;
import org.hr.managing.system.order.service.domain.dto.track.TrackOrderResponse;

import javax.validation.Valid;

public interface OrderApplicationService {

    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);

    TrackOrderResponse trackOrder(@Valid TrackOrderQuery trackOrderQuery);
}
