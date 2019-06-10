package com.nin.repository;

import com.nin.model.AppDataConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppDataConfigRepository extends JpaRepository<AppDataConfig,Long> {
    AppDataConfig findByIsActive(Boolean isActive);
    AppDataConfig findByAppDataConfigId(Long Id);
}
