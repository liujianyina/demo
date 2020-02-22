package com.rx.demo.service.impl;

import com.rx.demo.domain.Properties;
import com.rx.demo.repositpry.PropertiesRepository;
import com.rx.demo.service.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PropertiesServiceImpl extends BaseServiceImpl<Properties, PropertiesRepository> implements PropertiesService {

    private PropertiesRepository propertiesRepository;

    @Autowired
    @Override
    public void setRepository(PropertiesRepository repository) {
        this.propertiesRepository = repository;
        this.baseRepository = repository;
    }
}
