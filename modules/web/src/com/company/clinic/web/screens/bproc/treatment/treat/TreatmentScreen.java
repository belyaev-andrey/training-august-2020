package com.company.clinic.web.screens.bproc.treatment.treat;

import com.haulmont.addon.bproc.web.processform.*;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.TextArea;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("clinic_TreatmentScreen")
@UiDescriptor("treatment-screen.xml")
@ProcessForm(
        outcomes = {
                @Outcome(id = "complete"),
                @Outcome(id = "requestDetails",
                        outputVariables = {
                                @OutputVariable(name = "comment", type = String.class)
                        }
                )
        }
)
public class TreatmentScreen extends Screen {

    @Inject
    private ProcessFormContext processFormContext;

    @Inject
    @ProcessVariable(name = "comment")
    private TextArea<String> comment;

    @Inject
    @ProcessVariable(name = "description")
    private TextArea<String> description;

    @Subscribe("completeBtn")
    public void onCompleteBtnClick(Button.ClickEvent event) {
        processFormContext.taskCompletion()
                .withOutcome("complete")
                .complete();
        closeWithDefaultAction();
    }

    @Subscribe("needInfoBtn")
    public void onNeedInfoBtnClick(Button.ClickEvent event) {
        processFormContext.taskCompletion()
                .withOutcome("requestDetails")
                .addProcessVariable("comment", comment.getValue())
                .complete();
        closeWithDefaultAction();
    }


}