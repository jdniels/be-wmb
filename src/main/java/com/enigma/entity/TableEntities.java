package com.enigma.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "mst_table")
public class TableEntities {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid",strategy = "uuid")
    private String idTable;
    private Integer numberTable;
    private String status;
    private Integer capacity;

    public TableEntities(Integer numberTable, String status, Integer capacity) {
        this.numberTable = numberTable;
        this.status = status;
        this.capacity = capacity;
    }

    public TableEntities() {
    }

    public String getIdTable() {
        return idTable;
    }

    public void setIdTable(String idTable) {
        this.idTable = idTable;
    }

    public Integer getNumberTable() {
        return numberTable;
    }

    public void setNumberTable(Integer numberTable) {
        this.numberTable = numberTable;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableEntities that = (TableEntities) o;
        return Objects.equals(idTable, that.idTable) &&
                Objects.equals(numberTable, that.numberTable) &&
                Objects.equals(status, that.status) &&
                Objects.equals(capacity, that.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTable, numberTable, status, capacity);
    }
}
