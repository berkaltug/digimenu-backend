package com.digimenu.main.domain.entity;

import com.digimenu.main.security.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
public class Siparis {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @CreationTimestamp
    @Basic
    private Timestamp siparisTarihi;

    @ManyToOne(fetch = FetchType.LAZY)
    private Restaurant restaurant;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "siparis")
    private List<Table_Orders> tableOrders;

    private Boolean isVoted;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getSiparisTarihi() {
        return siparisTarihi;
    }

    public void setSiparisTarihi(Timestamp siparisTarihi) {
        this.siparisTarihi = siparisTarihi;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Table_Orders> getTableOrders() {
        return tableOrders;
    }

    public void setTableOrders(List<Table_Orders> tableOrders) {
        this.tableOrders = tableOrders;
    }

    public Boolean getVoted() {
        return isVoted;
    }

    public void setVoted(Boolean voted) {
        isVoted = voted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Siparis siparis = (Siparis) o;
        return Objects.equals(orderId, siparis.orderId) &&
                Objects.equals(user, siparis.user) &&
                Objects.equals(siparisTarihi, siparis.siparisTarihi) &&
                Objects.equals(restaurant, siparis.restaurant) &&
                Objects.equals(tableOrders, siparis.tableOrders) &&
                Objects.equals(isVoted, siparis.isVoted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, user, siparisTarihi, restaurant, tableOrders, isVoted);
    }

    @Override
    public String toString() {
        return "Siparis{" +
                "orderId=" + orderId +
                ", user=" + user +
                ", siparisTarihi=" + siparisTarihi +
                ", restaurant=" + restaurant +
                ", tableOrders=" + tableOrders +
                ", isVoted=" + isVoted +
                '}';
    }
}
