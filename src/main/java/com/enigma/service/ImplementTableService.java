package com.enigma.service;

import com.enigma.entity.TableEntities;
import com.enigma.exeption.TableCapacityException;
import com.enigma.repositories.TableRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImplementTableService implements TableService {
    @Autowired
    TableRepositories tableRepositories;

    @Override
    public TableEntities saveTable(TableEntities newTable) {
        if (newTable.getCapacity() < 1 || newTable.getCapacity() == null){
            throw new TableCapacityException();
        }else{
            newTable =tableRepositories.save(newTable);
        }
        return newTable;
    }

    @Override
    public List<TableEntities> getAllTable() {
        return tableRepositories.findAll();
    }

    @Override
    public TableEntities getTableById(String idTable) {
        return tableRepositories.findById(idTable).get();
    }

    @Override
    public void deleteById(String idTable) {
        tableRepositories.deleteById(idTable);
    }

    @Override
    public Page<TableEntities> getAllWithPagination(Pageable pageable) {
        return tableRepositories.findAll(pageable);
    }
}
