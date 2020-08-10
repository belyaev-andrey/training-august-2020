package com.company.clinic.web.screens.bproc.treatment.start;

import com.haulmont.addon.bproc.web.processform.OutputVariable;
import com.haulmont.addon.bproc.web.processform.ProcessForm;
import com.haulmont.addon.bproc.web.processform.ProcessFormContext;
import com.haulmont.addon.bproc.web.processform.ProcessVariable;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.TextArea;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;

import javax.inject.Inject;

@UiController("clinic_TreatmentStartScreen")
@UiDescriptor("treatment-start-screen.xml")
@LoadDataBeforeShow
@ProcessForm (
        outputVariables = {
                @OutputVariable(name = "description", type = String.class),
                @OutputVariable(name = "nurse", type = User.class),
                @OutputVariable(name = "doctor", type = User.class)
        }
)
public class TreatmentStartScreen extends Screen {

    @Inject
    private ProcessFormContext processFormContext;

    @Inject
    @ProcessVariable(name = "description")
    private TextArea<String> descriptionField;

    @Inject
    @ProcessVariable(name = "nurse")
    private LookupField<User> nurseField;

    @ProcessVariable(name = "doctor")
    private User doctor;

    @Inject
    private UserSession userSession;

    @Subscribe("startProcessBtn")
    public void onStartProcessBtnClick(Button.ClickEvent event) {
        doctor = userSession.getCurrentOrSubstitutedUser();
        processFormContext
                .processStarting()
                .saveInjectedProcessVariables()
                .start();
        closeWithDefaultAction();
    }



}