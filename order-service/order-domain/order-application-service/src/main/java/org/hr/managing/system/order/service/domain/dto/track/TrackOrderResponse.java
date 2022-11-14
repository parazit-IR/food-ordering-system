package org.hr.managing.system.order.service.domain.dto.track;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.hr.managing.system.domain.valueobject.OrderStatus;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class TrackOrderResponse {
    @NotNull
    private final UUID orderTrackingId;
    @NotNull
    private final OrderStatus orderStatus;
    private final List<String> failureMessages;
}
