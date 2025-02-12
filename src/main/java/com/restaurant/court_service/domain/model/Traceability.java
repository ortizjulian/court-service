package com.restaurant.court_service.domain.model;

public class Traceability {
    private Long orderId;
    private String clientId;
    private String clientEmail;

    public Traceability(Long orderId, String clientId, String clientEmail) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.clientEmail = clientEmail;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }
}
