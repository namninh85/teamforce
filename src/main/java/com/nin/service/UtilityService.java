package com.nin.service;

import com.nin.model.Utility;
import com.nin.repository.UtilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UtilityService {
    @Autowired
    private UtilityRepository utilityRepository;

    @Autowired
    public UtilityService(UtilityRepository utilityRepository) {
        this.utilityRepository = utilityRepository;
    }

    public List<String> findByListId(String Id){
        List<Integer> listIds = new ArrayList<>();
        Arrays.asList(Id.split(",")).forEach(id -> {
            listIds.add(Integer.parseInt(id));
        });
        return utilityRepository.findByListId(listIds);
    }
}
