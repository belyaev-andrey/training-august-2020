package com.company.clinic.web.screens.owner;

import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Owner;
import org.slf4j.Logger;

import javax.inject.Inject;

@UiController("clinic_Owner.browse")
@UiDescriptor("owner-browse.xml")
@LookupComponent("ownersTable")
@LoadDataBeforeShow
public class OwnerBrowse extends StandardLookup<Owner> {

    @Inject
    private CollectionLoader<Owner> ownersDl;

    @Inject
    private Logger log;

    @Install(to = "ownersTable.edit", subject = "afterCloseHandler")
    private void ownersTableEditAfterCloseHandler(AfterCloseEvent afterCloseEvent) {
        ownersDl.load();
        log.info("Owners data was reloaded");
    }



}