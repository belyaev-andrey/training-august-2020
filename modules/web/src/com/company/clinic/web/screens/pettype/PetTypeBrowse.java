package com.company.clinic.web.screens.pettype;

import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.PetType;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.Collection;

@UiController("clinic_PetType.browse")
@UiDescriptor("pet-type-browse.xml")
@LookupComponent("petTypesTable")
@LoadDataBeforeShow
public class PetTypeBrowse extends StandardLookup<PetType> {

    @Inject
    private Logger log;
    @Inject
    private GroupTable<PetType> petTypesTable;

    @Install(subject = "selectHandler")
    private void selectHandler(Collection<PetType> collection) {
        collection.forEach(pt -> log.info("Pet Type: {} was selected", pt.getName()));
    }

    @Subscribe("petTypesTable.greet")
    public void onPetTypesTableGreet(Action.ActionPerformedEvent event) {
        log.info("Greet!");
    }

    @Install(to = "petTypesTable.greet", subject = "enabledRule")
    private boolean petTypesTableGreetEnabledRule() {
        return !petTypesTable.getSelected().isEmpty();
    }

}