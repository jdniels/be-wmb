package com.enigma.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "trx_transaction")
public class Transaction {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String idTransaction;
    private BigDecimal total;
    private BigDecimal change=new BigDecimal(0);
    private BigDecimal pay = new BigDecimal(0);
    private String paymentStatus;

    @ManyToOne
    @JoinColumn(name = "id_order")
    private OrderList orderList;

    public Transaction() {
    }

    public Transaction(BigDecimal total, BigDecimal change, BigDecimal pay, String paymentStatus, OrderList orderList) {
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

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getPay() {
        return pay;
    }

    public void setPay(BigDecimal pay) {
        this.pay = pay;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
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
