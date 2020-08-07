package com.company.clinic.service;

import com.company.clinic.entity.Consumable;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.View;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

@Service(ConsumablesService.NAME)
public class ConsumablesServiceBean implements ConsumablesService {

    @Inject
    private DataManager dataManager;

    @Override
    public List<Consumable> getUsedConsumables(LocalDateTime startDate, LocalDateTime endDate) {

        LoadContext<Consumable> loadContext = new LoadContext<>(Consumable.class)
                .setQuery(new LoadContext.Query(
                        "select distinct c from clinic_Visit v join v.consumables c " +
                                "where v.date >= :startDate and v.date <= :endDate")
                        .setParameter("startDate", startDate)
                        .setParameter("endDate", endDate)
                )
                .setView(View.LOCAL);

        return dataManager.loadList(loadContext);


/*
        return dataManager.load(Consumable.class)
                .query("select distinct c from clinic_Visit v join v.consumables c " +
                "where @between(c.createTs, now-7, now+1, day)")
                .view(View.LOCAL).list();
*/
    }
}