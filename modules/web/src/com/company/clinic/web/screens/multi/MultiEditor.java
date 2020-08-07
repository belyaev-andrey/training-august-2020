package com.company.clinic.web.screens.multi;

import com.company.clinic.entity.Owner;
import com.company.clinic.entity.PetType;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("clinic_MultiEditor")
@UiDescriptor("multi-editor.xml")
public class MultiEditor extends Screen {

    @Inject
    private DataContext dataContext;
    @Inject
    private InstanceContainer<PetType> petTypeDc;
    @Inject
    private DataManager dataManager;

    @Inject
    private InstanceContainer<Owner> ownerDc;

    @Subscribe
    public void onAfterInit(AfterInitEvent event) {
        initEntities();
    }

    @Subscribe("createNew")
    public void onCreateNew(Action.ActionPerformedEvent event) {
        initEntities();
    }

    @Subscribe("save")
    public void onSave(Action.ActionPerformedEvent event) {
        dataContext.commit();
        dataManager.commit(ownerDc.getItem());
        initEntities();
    }

    private void initEntities() {
        PetType petType = dataContext.create(PetType.class);
        petTypeDc.setItem(petType);

        Owner owner = dataManager.create(Owner.class);
        ownerDc.setItem(owner); //Not tracked, need to save it manually!
    }

}