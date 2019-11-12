package com.enigma.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private Integer totalPrice= 0;
    private Integer manyCustomers;

    @OneToMany(mappedBy = "orderId",cascade = CascadeType.PERSIST)
    private List<OrderDetail> orderDetails=new ArrayList<>();

    @Transient
    private  String idTable;

    public OrderList(String picCustomer, Integer manyCustomers, List<OrderDetail> orderDetails, String idTable) {
        this.picCustomer = picCustomer;
        this.manyCustomers = manyCustomers;
        this.orderDetails = orderDetails;
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

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {

        this.totalPrice = this.totalPrice+totalPrice;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderList orderList = (OrderList) o;
        return Objects.equals(idOrder, orderList.idOrder) &&
                Objects.equals(table, orderList.table) &&
                Objects.equals(picCustomer, orderList.picCustomer) &&
                Objects.equals(totalPrice, orderList.totalPrice) &&
                Objects.equals(manyCustomers, orderList.manyCustomers) &&
                Objects.equals(orderDetails, orderList.orderDetails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, table, picCustomer, totalPrice, manyCustomers, orderDetails, idTable);
    }
}
