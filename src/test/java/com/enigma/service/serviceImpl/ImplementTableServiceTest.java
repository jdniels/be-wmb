package com.enigma.service.serviceImpl;

import com.enigma.entity.TableEntities;
import com.enigma.repositories.TableRepositories;
import com.enigma.service.TableService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ImplementTableServiceTest {
    @Autowired
    TableRepositories tableRepositories;
    @Autowired
    TableService tableService;

    @Before
    public void setup() {
        tableRepositories.deleteAll();
    }

    @After
    public void tearDown() {
        tableRepositories.deleteAll();
    }

    @Test
    public void should_created_id_database_when_createTable() {
        TableEntities newTable = new TableEntities(1, "Avaliable", 4);
        newTable = tableService.saveTable(newTable);
        assertEquals(newTable, tableRepositories.findById(newTable.getIdTable()).get());
    }

    @Test
    public void should_return_all_data_when_getAllTable() {
        TableEntities newTable1 = new TableEntities(1, "Avaliable", 4);
        TableEntities newTable2 = new TableEntities(2, "Avaliable", 6);
        newTable1 = tableRepositories.save(newTable1);
        newTable2 = tableRepositories.save(newTable2);
        List<TableEntities> entitiesList = new ArrayList<>();
        entitiesList.add(newTable1);
        entitiesList.add(newTable2);
        assertEquals(entitiesList, tableService.getAllTable());
    }

    @Test
    public void shold_return_same_data_as_id_when_getTableById() {
        TableEntities newTable = new TableEntities(1, "Avaliable", 4);
        newTable = tableRepositories.save(newTable);
        assertEquals(newTable, tableService.getTableById(newTable.getIdTable()));
    }

    @Test
    public void should_0_data_when_deleteById() {
        TableEntities newTable = new TableEntities(1, "Avaliable", 4);
        newTable = tableRepositories.save(newTable);
        tableService.deleteById(newTable.getIdTable());
        assertEquals(0, tableRepositories.findAll().size());
    }

    @Test
    public void should_get_data_table_with_Pagination() {
        TableEntities newTable1 = new TableEntities(1, "Avaliable", 4);
        TableEntities newTable2 = new TableEntities(2, "Avaliable", 6);
        tableRepositories.save(newTable1);
        tableRepositories.save(newTable2);
        Pageable pageable = PageRequest.of(0, 2);
        assertEquals(2, tableService.getAllWithPagination(pageable).getTotalElements());
    }

    @Test
    public void getTableAvailable() {
        TableEntities newTable1 = new TableEntities(1, "Avaliable", 4);
        TableEntities newTable2 = new TableEntities(2, "Dining", 6);
        tableRepositories.save(newTable1);
        tableRepositories.save(newTable2);
        assertEquals(1, tableService.getTableAvailable("Avaliable").size());
    }

    @Test
    public void updateTable() {
        TableEntities newTable1 = new TableEntities(1, "Avaliable", 4);
        TableEntities newTable2 = new TableEntities(2, "Avaliable", 6);
        newTable1 = tableRepositories.save(newTable1);
        newTable2.setIdTable(newTable1.getIdTable());
        tableService.updateTable(newTable2);
        assertEquals(tableRepositories.findById(newTable1.getIdTable()).get(), newTable2);

    }
}