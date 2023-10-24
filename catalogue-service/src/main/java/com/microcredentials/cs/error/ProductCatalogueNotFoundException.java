package com.microcredentials.cs.error;

public class ProductCatalogueNotFoundException extends Exception {
    public ProductCatalogueNotFoundException() {
        super();
    }

    public ProductCatalogueNotFoundException(String message) {
        super(message);
    }

    public ProductCatalogueNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductCatalogueNotFoundException(Throwable cause) {
        super(cause);
    }

    protected ProductCatalogueNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
