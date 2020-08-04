package com.company.clinic.service;

import com.company.clinic.entity.Drug;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service(DrugsService.NAME)
public class DrugsServiceBean implements DrugsService {

    @Inject
    protected DataManager dataManager;

    @Override
    public List<Drug> getDrugList() {

        //Fetching external data

        List<Drug> result = new ArrayList<>();

        Drug drug = dataManager.create(Drug.class);
        drug.setId(1);
        drug.setName("Vitamin C");
        drug.setPrice(new BigDecimal("10.1"));

        result.add(drug);

        drug = dataManager.create(Drug.class);
        drug.setId(2);
        drug.setName("Vitamin A");
        drug.setPrice(new BigDecimal("1.1"));

        result.add(drug);

        return result;
    }
}