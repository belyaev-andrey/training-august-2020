package com.company.clinic.web.screens.usedconsumables;

import com.company.clinic.entity.Consumable;
import com.company.clinic.entity.Drug;
import com.company.clinic.service.ConsumablesService;
import com.company.clinic.service.DrugsService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

@UiController("clinic_UsedConsumables")
@UiDescriptor("used-consumables.xml")
@LoadDataBeforeShow
public class UsedConsumables extends Screen {

    @Inject
    private ConsumablesService consumablesService;

    @Inject
    private DrugsService drugsService;

    @Inject
    private CollectionContainer<Consumable> consumablesDc;

    @Inject
    private DataContext dataContext;

    @Inject
    private CollectionLoader<Consumable> consumablesDl;

    @Install(to = "consumablesDl", target = Target.DATA_LOADER)
    private List<Consumable> consumablesDlLoadDelegate(LoadContext<Consumable> loadContext) {
        return consumablesService.getUsedConsumables();
    }

    @Install(to = "drugsDl", target = Target.DATA_LOADER)
    private List<Drug> drugsDlLoadDelegate(LoadContext<Drug> loadContext) {
        List<Drug> drugList = drugsService.getDrugList();
        return drugList;
    }

    @Subscribe("drugsTable.addDrag")
    public void onDrugsTableAddDrag(Action.ActionPerformedEvent event) {
        Drug drug = dataContext.create(Drug.class);
    }

}