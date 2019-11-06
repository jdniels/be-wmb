package com.enigma.service;

import com.enigma.entity.TableEntities;
import com.enigma.repositories.TableRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplementTableService implements TableService {
    @Autowired
    TableRepositories tableRepositories;

    @Override
    public TableEntities saveTable(TableEntities newTable) {
        return tableRepositories.save(newTable);
    }

    @Override
    public List<TableEntities> getAllTable() {
        return tableRepositories.findAll();
    }
}
