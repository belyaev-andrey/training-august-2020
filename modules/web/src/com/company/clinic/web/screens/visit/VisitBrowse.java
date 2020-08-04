package com.company.clinic.web.screens.visit;

import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.LayoutClickNotifier;
import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Visit;

@UiController("clinic_Visit.browse")
@UiDescriptor("visit-browse.xml")
@LookupComponent("visitsTable")
@LoadDataBeforeShow
public class VisitBrowse extends StandardLookup<Visit> {

    @Subscribe("visitsTable.refresh")
    public void onVisitsTableRefresh(Action.ActionPerformedEvent event) {
        getScreenData().loadAll();
    }



}