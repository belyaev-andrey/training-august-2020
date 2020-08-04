package com.company.clinic.web.screens.visitanalysis;

import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.company.clinic.entity.VisitAnalysis;

import javax.inject.Inject;

@UiController("clinic_VisitAnalysis.browse")
@UiDescriptor("visit-analysis-browse.xml")
@LookupComponent("visitAnalysesTable")
@LoadDataBeforeShow
public class VisitAnalysisBrowse extends StandardLookup<VisitAnalysis> {

}