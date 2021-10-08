package com.route.bean;

import javax.persistence.criteria.CriteriaBuilder;

public class Distance {
    private Integer courierId;
    private Integer add1Id;
    private Integer add2Id;
    private Double distance;

    public Distance(Integer courierId, Integer add1Id, Integer add2Id, Double distance) {
        this.courierId = courierId;
        this.add1Id = add1Id;
        this.add2Id = add2Id;
        this.distance = distance;
    }

    public Distance() {
    }

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    public Integer getAdd1Id() {
        return add1Id;
    }

    public void setAdd1Id(Integer add1Id) {
        this.add1Id = add1Id;
    }

    public Integer getAdd2Id() {
        return add2Id;
    }

    public void setAdd2Id(Integer add2Id) {
        this.add2Id = add2Id;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
