package com.company.clinic.web.screens.pet;

import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Pet;

import javax.inject.Inject;

@UiController("clinic_Pet.edit")
@UiDescriptor("pet-edit.xml")
@EditedEntityContainer("petDc")
@LoadDataBeforeShow
@DialogMode(width = "auto", height = "auto", forceDialog = true)
public class PetEdit extends StandardEditor<Pet> {

}