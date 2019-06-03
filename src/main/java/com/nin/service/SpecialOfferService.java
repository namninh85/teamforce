package com.nin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nin.model.SpecialOffer;
import com.nin.repository.SpecialOfferRepository;

@Service
public class SpecialOfferService {

    private SpecialOfferRepository specialOfferRepository;

    @Autowired
    public SpecialOfferService(SpecialOfferRepository specialOfferRepository) {
        this.specialOfferRepository = specialOfferRepository;       
    }

    public List<SpecialOffer> findAllActive() {
    	return specialOfferRepository.findByIsActive(true);
    }
}
