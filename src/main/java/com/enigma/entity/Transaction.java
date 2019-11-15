package com.enigma.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "trx_transaction")
public class Transaction {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String idTransaction;
    private Integer total;
    private Integer change;
    private Integer pay ;
    private String paymentStatus;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private OrderList orderList;
    @ManyToOne
    @JoinColumn(name = "id_table")
    private TableEntities tableEntities;

    public Transaction() {
    }

    public Transaction(Integer total, String paymentMethod, Integer change, Integer pay, String paymentStatus, OrderList orderList) {
        this.total = total;
        this.change = change;
        this.pay = pay;
        this.paymentStatus = paymentStatus;
        this.orderList = orderList;
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPay() {
        return pay;
    }

    public void setPay(Integer pay) {
        this.pay = pay;
    }

    public Integer getChange() {
        return change;
    }

    public void setChange(Integer total) {
        this.change =this.pay-total;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public OrderList getOrderList() {
        return orderList;
    }

    public void setOrderList(OrderList orderList) {
        this.orderList = orderList;
    }

    public TableEntities getTableEntities() {
        return tableEntities;
    }

    public void setTableEntities(TableEntities tableEntities) {
        this.tableEntities = tableEntities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(idTransaction, that.idTransaction) &&
                Objects.equals(total, that.total) &&
                Objects.equals(change, that.change) &&
                Objects.equals(pay, that.pay) &&
                Objects.equals(paymentStatus, that.paymentStatus) &&
                Objects.equals(orderList, that.orderList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTransaction, total, change, pay, paymentStatus, orderList);
    }
}
