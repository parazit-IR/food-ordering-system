package org.hr.managing.system.order.service.domain.dto.create;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hr.managing.system.domain.valueobject.OrderStatus;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderResponse {
    @NotNull
    private final UUID trackingId;
    @NotNull
    private final OrderStatus orderStatus;
    @NotNull
    private final String message;
}
