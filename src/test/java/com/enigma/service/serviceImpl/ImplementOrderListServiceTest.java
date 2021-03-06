package com.enigma.service.serviceImpl;
import com.enigma.entity.FoodEntities;
import com.enigma.entity.OrderDetail;
import com.enigma.entity.OrderList;
import com.enigma.entity.TableEntities;
import com.enigma.repositories.FoodRepositories;
import com.enigma.repositories.OrderListRepositories;
import com.enigma.repositories.TableRepositories;
import com.enigma.repositories.TransactionRepositories;
import com.enigma.service.OrderListService;
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
public class ImplementOrderListServiceTest {
    @Autowired
    FoodRepositories foodRepositories;
    @Autowired
    TableRepositories tableRepositories;
    @Autowired
    TransactionRepositories transactionRepositories;
    @Autowired
    OrderListRepositories orderListRepositories;
    @Autowired
    OrderListService orderListService;


    @Before
    public void setUp() {
        transactionRepositories.deleteAll();
        orderListRepositories.deleteAll();
        foodRepositories.deleteAll();
        tableRepositories.deleteAll();
    }
    @After
    public void tearDown() {
        transactionRepositories.deleteAll();
        orderListRepositories.deleteAll();
        foodRepositories.deleteAll();
        tableRepositories.deleteAll();
    }
    @Test
    public void should_exist_In_database_when_saveOrder() {
        TableEntities newTable=new TableEntities(3,"AVAILABLE",5);
        newTable=tableRepositories.save(newTable);
        FoodEntities newFood1 = new FoodEntities("pecel","MAKANAN",10000,50);
        FoodEntities newFood2 = new FoodEntities("sup","MAKANAN",10000,50);
        newFood1=foodRepositories.save(newFood1);
        newFood2=foodRepositories.save(newFood2);
        List<OrderDetail> orderDetails=new ArrayList<>();
        orderDetails.add(new OrderDetail(2,newFood1.getIdFood()));
        orderDetails.add(new OrderDetail(2,newFood2.getIdFood()));
        OrderList newOrderList = new OrderList("RIfqi",4,orderDetails,newTable.getIdTable());
        newOrderList=orderListService.saveOrder(newOrderList);
        OrderList actual=orderListRepositories.getOne(newOrderList.getIdOrder());
        assertEquals(newOrderList.getIdOrder(),actual.getIdOrder());

    }

    @Test
    public void should_return_id_order_when_getOrderListById() {
        TableEntities newTable=new TableEntities(3,"AVAILABLE",5);
        newTable=tableRepositories.save(newTable);
        FoodEntities newFood1 = new FoodEntities("pecel","MAKANAN",10000,50);
        FoodEntities newFood2 = new FoodEntities("sup","MAKANAN",10000,50);
        newFood1=foodRepositories.save(newFood1);
        newFood2=foodRepositories.save(newFood2);
        List<OrderDetail> orderDetails=new ArrayList<>();
        orderDetails.add(new OrderDetail(2,newFood1.getIdFood()));
        orderDetails.add(new OrderDetail(2,newFood2.getIdFood()));
        OrderList newOrderList = new OrderList("RIfqi",4,orderDetails,newTable.getIdTable());
        newOrderList=orderListService.saveOrder(newOrderList);
        OrderList actual=orderListService.getOrderListById(newOrderList.getIdOrder());
        assertEquals(newOrderList.getIdOrder(),actual.getIdOrder());
    }

    @Test
    public void should_return_data_size_when_getAllOrderList() {
        TableEntities newTable=new TableEntities(3,"AVAILABLE",5);
        newTable=tableRepositories.save(newTable);
        FoodEntities newFood1 = new FoodEntities("pecel","MAKANAN",10000,50);
        FoodEntities newFood2 = new FoodEntities("sup","MAKANAN",10000,50);
        newFood1=foodRepositories.save(newFood1);
        newFood2=foodRepositories.save(newFood2);
        List<OrderDetail> orderDetails=new ArrayList<>();
        orderDetails.add(new OrderDetail(2,newFood1.getIdFood()));
        orderDetails.add(new OrderDetail(2,newFood2.getIdFood()));
        OrderList newOrderList = new OrderList("RIfqi",4,orderDetails,newTable.getIdTable());
        orderListService.saveOrder(newOrderList);
        Integer actual=orderListService.getAllOrderList().size();
        Integer expectedSize=1;
        assertEquals(expectedSize,actual);
    }

    @Test
    public void getOrderListPagination() {
        TableEntities newTable=new TableEntities(3,"AVAILABLE",5);
        newTable=tableRepositories.save(newTable);
        FoodEntities newFood1 = new FoodEntities("pecel","MAKANAN",10000,50);
        FoodEntities newFood2 = new FoodEntities("sup","MAKANAN",10000,50);
        newFood1=foodRepositories.save(newFood1);
        newFood2=foodRepositories.save(newFood2);
        List<OrderDetail> orderDetails=new ArrayList<>();
        orderDetails.add(new OrderDetail(2,newFood1.getIdFood()));
        orderDetails.add(new OrderDetail(2,newFood2.getIdFood()));
        OrderList newOrderList = new OrderList("RIfqi",4,orderDetails,newTable.getIdTable());
        orderListService.saveOrder(newOrderList);
        Integer expectedSize=1;
        Pageable pageable = PageRequest.of(0,1);
        assertEquals(1,orderListService.getOrderListPagination(pageable).getTotalElements());
    }

}