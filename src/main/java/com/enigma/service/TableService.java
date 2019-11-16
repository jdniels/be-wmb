package com.enigma.service;

import com.enigma.entity.TableEntities;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TableService {
    TableEntities saveTable(TableEntities newTable);

    List<TableEntities> getAllTable();

    TableEntities getTableById(String idTable);

    void deleteById(String idTable);

    Page<TableEntities> getAllWithPagination(Pageable pageable);
    List<TableEntities>getTableAvailable(String status);
    TableEntities updateTable(TableEntities tableData);
}
