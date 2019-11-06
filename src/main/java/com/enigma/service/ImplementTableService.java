package com.enigma.service;

import com.enigma.repositories.TableRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImplementTableService implements TableService {
    @Autowired
    TableRepositories tableRepositories;
}
