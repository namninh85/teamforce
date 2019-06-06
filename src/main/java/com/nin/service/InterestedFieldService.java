package com.nin.service;

import com.nin.model.InterestedField;
import com.nin.repository.InterestedFieldRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class InterestedFieldService {
    @Autowired
    private InterestedFieldRepository interestedFieldRepository;

    @Autowired
    public InterestedFieldService(InterestedFieldRepository interestedFieldRepository) {
        this.interestedFieldRepository = interestedFieldRepository;
    }

    public List<Object[]> findByListId(String Id){
        List<Integer> listIds = new ArrayList<>();
        Arrays.asList(Id.split(",")).forEach(id -> {
            listIds.add(Integer.parseInt(id));
        });
        return interestedFieldRepository.findByListId(listIds);
    }
}
