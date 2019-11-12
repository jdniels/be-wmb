package com.enigma.repositories;

import com.enigma.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepositories extends JpaRepository<OrderDetail ,String> {
}
