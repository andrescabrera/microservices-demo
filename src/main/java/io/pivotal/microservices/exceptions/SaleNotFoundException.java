package io.pivotal.microservices.exceptions;

import java.util.UUID;

public class SaleNotFoundException extends Throwable {

    private static final long serialVersionUID = 1L;

    public SaleNotFoundException(String saleNumber)  {
        super("No such account: " + saleNumber);
    }
}
