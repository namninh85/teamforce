package com.nin.service;

import com.nin.model.AppDataConfig;
import com.nin.repository.AppDataConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppDataConfigService {
    @Autowired
    private AppDataConfigRepository appDataConfigRepository;

    @Autowired
    public AppDataConfigService(AppDataConfigRepository appDataConfigRepository) {
        this.appDataConfigRepository = appDataConfigRepository;
    }

    public AppDataConfig findByIsActive(){
        return appDataConfigRepository.findByIsActive(true);
    }
}
