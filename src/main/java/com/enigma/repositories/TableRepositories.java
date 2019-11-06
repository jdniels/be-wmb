package com.enigma.repositories;

import com.enigma.entity.TableEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TableRepositories extends JpaRepository<TableEntities,String> {
}
