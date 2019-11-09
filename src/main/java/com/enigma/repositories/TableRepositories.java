package com.enigma.repositories;

import com.enigma.entity.TableEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepositories extends JpaRepository<TableEntities,String> {
    List<TableEntities>findTableEntitiesByStatus(String Status);
}
