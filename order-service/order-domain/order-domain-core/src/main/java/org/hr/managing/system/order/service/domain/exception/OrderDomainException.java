package org.hr.managing.system.order.service.domain.exception;

import org.hr.managing.system.domain.exception.DomainException;

public class OrderDomainException extends DomainException {


    public OrderDomainException(String message) {
        super(message);
    }

    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
