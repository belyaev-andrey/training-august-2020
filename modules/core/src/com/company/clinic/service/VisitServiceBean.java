package com.company.clinic.service;

import com.company.clinic.entity.Consumable;
import com.company.clinic.entity.Pet;
import com.company.clinic.entity.Veterinarian;
import com.company.clinic.entity.Visit;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.security.entity.User;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service(VisitService.NAME)
public class VisitServiceBean implements VisitService {

    @Inject
    private DataManager dataManager;

    @Override
    public Visit scheduleVisit(Pet pet, LocalDateTime visitDate, User user) {
        Visit visit = dataManager.create(Visit.class);
        visit.setDate(visitDate);
        visit.setPet(pet);
        visit.setVeterinarian(findVeterinarianByUser(user));
        visit.setHoursSpent(1);
        return dataManager.commit(visit);
    }

    private Veterinarian findVeterinarianByUser(User user) {
        return dataManager.load(Veterinarian.class)
                .query("select v from clinic_Veterinarian v where v.user.id = :userId")
                .parameter("userId", user.getId())
                .optional()
                .orElseThrow(IllegalArgumentException::new);
    }


    @Override
    public BigDecimal calculateAmount(Visit visit) {
        BigDecimal amount = BigDecimal.ZERO;

        if (visit.getHoursSpent() != null) {
            amount = amount.add(BigDecimal.valueOf(visit.getHoursSpent()).multiply(visit.getVeterinarian().getHourlyRate()));
        }

        if (visit.getConsumables() != null) {
            for (Consumable consumable : visit.getConsumables()) {
                amount = amount.add(consumable.getPrice());
            }
        }

        return amount;
    }
}