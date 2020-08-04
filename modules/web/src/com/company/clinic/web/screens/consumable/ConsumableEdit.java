package com.company.clinic.web.screens.consumable;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.Consumable;

import javax.inject.Inject;
import java.util.Set;

@UiController("clinic_Consumable.edit")
@UiDescriptor("consumable-edit.xml")
@EditedEntityContainer("consumableDc")
@LoadDataBeforeShow
public class ConsumableEdit extends StandardEditor<Consumable> {
}