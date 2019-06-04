package com.nin.repository;

import com.nin.model.LoyaltyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoyaltyProgramRepository extends JpaRepository<LoyaltyProgram,Long> {
    @Query("SELECT  p FROM LoyaltyProgram p WHERE p.isActive=true " +
            "AND CAST(to_char(now(),'YYYYMMDD') AS integer) BETWEEN p.startDate AND p.endDate")
    List<LoyaltyProgram> findLoyaltyProgramByDate();
}
