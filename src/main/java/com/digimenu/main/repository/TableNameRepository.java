package com.digimenu.main.repository;

import com.digimenu.main.domain.entity.TableName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableNameRepository extends JpaRepository<TableName,Long> {
}
