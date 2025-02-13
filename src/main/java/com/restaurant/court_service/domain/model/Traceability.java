package com.restaurant.court_service.domain.model;

public class Traceability {
    private Long orderId;
    private String clientId;
    private String clientEmail;
    private Long employeeId;
    private String employeeEmail;

    public Traceability(Long orderId, String clientId, String clientEmail) {
        this.orderId = orderId;
        this.clientId = clientId;
        this.clientEmail = clientEmail;
    }

    public Traceability(Long orderId, Long employeeId, String employeeEmail) {
        this.orderId = orderId;
        this.employeeId = employeeId;
        this.employeeEmail = employeeEmail;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
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
