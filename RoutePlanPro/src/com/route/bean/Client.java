package com.route.bean;

public class Client {
    private Integer id;
    private String clientName;
    private Integer number;
    private String address;
    private String attention;
    private Integer courierId;

    public Client() {
    }

    public Client(Integer id, String clientName, Integer number, String address, String attention, Integer courierId) {
        this.id = id;
        this.clientName = clientName;
        this.number = number;
        this.address = address;
        this.attention = attention;
        this.courierId = courierId;
    }

    public Integer getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public Integer getNumber() {
        return number;
    }

    public String getAddress() {
        return address;
    }

    public String getAttention() {
        return attention;
    }

    public Integer getCourierId() {
        return courierId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAttention(String attention) {
        this.attention = attention;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    @Override
    public String toString() {
        return "client{" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", number=" + number +
                ", address='" + address + '\'' +
                ", attention='" + attention + '\'' +
                ", courierId=" + courierId +
                '}';
    }
}
