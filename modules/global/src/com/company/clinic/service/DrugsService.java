package com.company.clinic.service;

import com.company.clinic.entity.Drug;

import java.util.List;

public interface DrugsService {
    String NAME = "clinic_DrugsService";

    List<Drug> getDrugList();

}