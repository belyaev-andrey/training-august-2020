package com.company.clinic.service;

import com.company.clinic.entity.Drug;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Profile("prod")
@Service(DrugsService.NAME)
public class DrugServiceRemoteBean implements DrugsService {

    @Override
    public List<Drug> getDrugList() {
        return null;
    }
}
