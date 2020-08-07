package com.company.clinic.web.screens.visit;

import com.company.clinic.web.screens.usedconsumables.UsedConsumables;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.Screens;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.DateField;
import com.haulmont.cuba.gui.components.LayoutClickNotifier;
import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Visit;

import javax.inject.Inject;
import java.time.LocalDateTime;

@UiController("clinic_Visit.browse")
@UiDescriptor("visit-browse.xml")
@LookupComponent("visitsTable")
@LoadDataBeforeShow
public class VisitBrowse extends StandardLookup<Visit> {

    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private DateField<LocalDateTime> endDate;
    @Inject
    private DateField<LocalDateTime> startDate;

    @Subscribe("visitsTable.refresh")
    public void onVisitsTableRefresh(Action.ActionPerformedEvent event) {
        getScreenData().loadAll();
    }

    @Subscribe("visitsTable.showConsumables")
    public void onVisitsTableShowConsumables(Action.ActionPerformedEvent event) {
        UsedConsumables usedConsumables = screenBuilders.screen(this)
                .withScreenClass(UsedConsumables.class)
                .withOpenMode(OpenMode.DIALOG)
                .build();

        usedConsumables.setStartDate(startDate.getValue());
        usedConsumables.setEndDate(endDate.getValue());

        usedConsumables.show();
    }



}