package com.enigma.controller;

import com.enigma.entity.TableEntities;
import com.enigma.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TableController {
    @Autowired
    TableService tableService;

    @PostMapping("/table")
    public TableEntities saveTable(@RequestBody TableEntities newTable){
        return tableService.saveTable(newTable);
    }
}
