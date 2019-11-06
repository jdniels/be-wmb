package com.enigma.service;

import com.enigma.entity.TableEntities;

import java.util.List;

public interface TableService {
    TableEntities saveTable(TableEntities newTable);

    List<TableEntities> getAllTable();
}
