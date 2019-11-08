package com.enigma.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
@Entity
@Table(name = "order_detail")
public class OrderDetail {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String idOrderDetail;

    @ManyToOne
    @JoinColumn(name = "id_order")
    @JsonIgnore
    private OrderList orderId;

    @ManyToOne
    @JoinColumn(name = "id_food")
    private FoodEntities food;
    private Integer quantity;
    private BigDecimal subTotal;

    @Transient
    private String foodId;

    public OrderDetail(Integer quantity, BigDecimal subTotal, String foodId) {
        this.quantity = quantity;
        this.subTotal = subTotal;
        this.foodId = foodId;
    }

    public OrderDetail() {
    }

    public String getIdOrderDetail() {
        return idOrderDetail;
    }

    public void setIdOrderDetail(String idOrderDetail) {
        this.idOrderDetail = idOrderDetail;
    }

    public OrderList getOrderId() {
        return orderId;
    }

    public void setOrderId(OrderList orderId) {
        this.orderId = orderId;
    }

    public FoodEntities getFood() {
        return food;
    }

    public void setFood(FoodEntities food) {
        this.food = food;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal.multiply(new BigDecimal(this.quantity));
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }
}
