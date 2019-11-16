package com.enigma.service.serviceImpl;

import com.enigma.entity.TableEntities;
import com.enigma.exeption.TableCapacityException;
import com.enigma.repositories.TableRepositories;
import com.enigma.service.TableService;
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
        if (newTable.getCapacity() <=1 || newTable.getCapacity() == null){
            throw new TableCapacityException();
        }else{
            newTable.setStatus("AVAILABLE");
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
        return tableRepositories.findAllByOrderByNumberTableAsc(pageable);
    }

    @Override
    public List<TableEntities> getTableAvailable(String status) {
        return tableRepositories.findTableEntitiesByStatus(status);
    }

    @Override
    public TableEntities updateTable(TableEntities tableData) {
        TableEntities table =getTableById(tableData.getIdTable());
        table.setCapacity(tableData.getCapacity());
        table.setNumberTable(tableData.getNumberTable());
        return saveTable(table);
    }
}
