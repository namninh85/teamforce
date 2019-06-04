package com.nin.service;

import com.nin.model.LoyaltyProgram;
import com.nin.repository.LoyaltyProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoyaltyProgramService {
    @Autowired
    private LoyaltyProgramRepository loyaltyProgramRepository;

    @Autowired
    public LoyaltyProgramService(LoyaltyProgramRepository loyaltyProgramRepository) {
        this.loyaltyProgramRepository = loyaltyProgramRepository;
    }
    public List<LoyaltyProgram> findLoyaltyProgramByDate(){
      return  loyaltyProgramRepository.findLoyaltyProgramByDate();
    }


}
