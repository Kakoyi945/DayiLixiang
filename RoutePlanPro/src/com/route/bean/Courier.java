package com.route.bean;


public class Courier {
    private Integer id;
    private String status;
    private Integer courierId;
    private String courierName;
    private Integer age;
    private String gender;

    public Courier(Integer id, String status, Integer courierId, String courierName, Integer age, String gender) {
        this.id = id;
        this.status = status;
        this.courierId = courierId;
        this.courierName = courierName;
        this.age = age;
        this.gender = gender;
    }

    public Courier() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCourierId() {
        return courierId;
    }

    public void setCourierId(Integer courierId) {
        this.courierId = courierId;
    }

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Courier{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", courierId=" + courierId +
                ", courierName='" + courierName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                '}';
    }
}
