package com.enigma.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

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
    private Integer subTotal;

    @Transient
    private String foodId;

    public OrderDetail(Integer quantity, String foodId) {
        this.quantity = quantity;
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

    public Integer getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Integer subTotal) {
        this.subTotal = subTotal*this.quantity;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetail that = (OrderDetail) o;
        return Objects.equals(idOrderDetail, that.idOrderDetail) &&
                Objects.equals(orderId, that.orderId) &&
                Objects.equals(food, that.food) &&
                Objects.equals(quantity, that.quantity) &&
                Objects.equals(subTotal, that.subTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrderDetail, orderId, food, quantity, subTotal, foodId);
    }
}
