package com.enigma.controller;

import com.enigma.entity.TableEntities;
import com.enigma.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class TableController {
    @Autowired
    TableService tableService;

    @PostMapping("/table")
    public TableEntities saveTable(@RequestBody TableEntities newTable) {
        return tableService.saveTable(newTable);
    }

    @GetMapping("/table")
    public List<TableEntities> getAllTable() {
        return tableService.getAllTable();
    }

    @GetMapping("/table/{idTable}")
    public TableEntities getTableById(@PathVariable String idTable) {
        return tableService.getTableById(idTable);
    }
    @DeleteMapping("/table/{idTable}")
    public void deleteById(@PathVariable String idTable){
        tableService.deleteById(idTable);
    }
}
