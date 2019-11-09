package com.enigma.service;

import com.enigma.entity.FoodEntities;
import com.enigma.entity.OrderDetail;
import com.enigma.entity.OrderList;
import com.enigma.entity.TableEntities;
import com.enigma.repositories.FoodRepositories;
import com.enigma.repositories.OrderListRepositories;
import com.enigma.repositories.TableRepositories;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ImplementOrderListServiceTest {
    @Autowired
    OrderListService orderListService;
    @Autowired
    OrderListRepositories orderListRepositories;
    @Autowired
    FoodRepositories foodRepositories;
    @Autowired
    TableRepositories tableRepositories;
    @Before
    public void setUp() throws Exception {
        orderListRepositories.deleteAll();
        foodRepositories.deleteAll();
        tableRepositories.deleteAll();
    }
//    @Test
//    public void should_exist_in_database_save_order(){
//        TableEntities newTable=new TableEntities(3,"AVAILABLE",5);
//        newTable=tableRepositories.save(newTable);
//        FoodEntities newFood1 = new FoodEntities("pecel","MAKANAN",new BigDecimal(10000),50);
//        FoodEntities newFood2 = new FoodEntities("sup","MAKANAN",new BigDecimal(10000),50);
//        newFood1=foodRepositories.save(newFood1);
//        newFood2=foodRepositories.save(newFood2);
//        OrderDetail order1= new OrderDetail(2,newFood1.getIdFood());
//        OrderDetail order2= new OrderDetail(2,newFood2.getIdFood());
//        List<OrderDetail>orderDetailList=new ArrayList<>();
//        orderDetailList.add(order1);
//        orderDetailList.add(order2);
//        OrderList newOrderList = new OrderList("RIfqi",4,orderDetailList,newTable.getIdTable());
//        newOrderList=orderListService.saveOrder(newOrderList);
//        assertEquals(newOrderList,orderListRepositories.findById(newOrderList.getIdOrder()).get());
//    }
}