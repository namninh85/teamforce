package com.nin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nin.model.SpecialOffer;

@Repository
public interface SpecialOfferRepository extends JpaRepository<SpecialOffer, Long> {
	List<SpecialOffer> findByIsActive(boolean active);
}
