package com.company.clinic.web.screens.visitanalysis;

import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.VisitAnalysis;

@UiController("clinic_VisitAnalysis.edit")
@UiDescriptor("visit-analysis-edit.xml")
@EditedEntityContainer("visitAnalysisDc")
@LoadDataBeforeShow
public class VisitAnalysisEdit extends StandardEditor<VisitAnalysis> {
}