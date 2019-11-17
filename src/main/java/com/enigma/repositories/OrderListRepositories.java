package com.enigma.repositories;

import com.enigma.entity.OrderList;
import com.enigma.entity.TableEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderListRepositories extends JpaRepository<OrderList,String> {
}
