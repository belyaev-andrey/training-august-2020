package com.company.clinic.service;

import com.company.clinic.entity.Consumable;

import java.time.LocalDateTime;
import java.util.List;

public interface ConsumablesService {
    String NAME = "clinic_ConsumablesService";

    List<Consumable> getUsedConsumables(LocalDateTime startDate, LocalDateTime endDate);
}