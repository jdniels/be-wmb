package com.enigma.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_list")
public class OrderList {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String idOrder;

    @ManyToOne
    @JoinColumn(name = "id_table")
    private TableEntities table;
    private String picCustomer;
    private BigDecimal totalPrice;
    private Integer manyCustomers;
    @OneToMany(mappedBy = "idOrder",cascade = CascadeType.PERSIST)
    private List<OrderDetail> orderDetails=new ArrayList<>();

    @Transient
    private  String idTable;

    public OrderList(String picCustomer, BigDecimal totalPrice, Integer manyCustomers, String idTable) {
        this.picCustomer = picCustomer;
        this.totalPrice = totalPrice;
        this.manyCustomers = manyCustomers;
        this.idTable = idTable;
    }

    public OrderList() {
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public TableEntities getTable() {
        return table;
    }

    public void setTable(TableEntities table) {
        this.table = table;
    }

    public String getPicCustomer() {
        return picCustomer;
    }

    public void setPicCustomer(String picCustomer) {
        this.picCustomer = picCustomer;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getManyCustomers() {
        return manyCustomers;
    }

    public void setManyCustomers(Integer manyCustomers) {
        this.manyCustomers = manyCustomers;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public String getIdTable() {
        return idTable;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }
}
