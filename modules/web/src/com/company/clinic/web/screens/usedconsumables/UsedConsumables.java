package com.company.clinic.web.screens.usedconsumables;

import com.company.clinic.entity.Consumable;
import com.company.clinic.entity.Drug;
import com.company.clinic.service.ConsumablesService;
import com.company.clinic.service.DrugsService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.gui.components.Action;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.reports.entity.Report;
import com.haulmont.reports.gui.ReportGuiManager;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;

@UiController("clinic_UsedConsumables")
@UiDescriptor("used-consumables.xml")
@LoadDataBeforeShow
public class UsedConsumables extends Screen {

    @Inject
    private ConsumablesService consumablesService;

    @Inject
    private DrugsService drugsService;

    @Inject
    private DataContext dataContext;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
    @Inject
    private ReportGuiManager reportGuiManager;
    @Inject
    private DataManager dataManager;

    @Install(to = "consumablesDl", target = Target.DATA_LOADER)
    private List<Consumable> consumablesDlLoadDelegate(LoadContext<Consumable> loadContext) {
        LocalDateTime now = LocalDateTime.now();

        if (startDate == null) {
            startDate = now.minus(7, ChronoUnit.DAYS);
        }

        if (endDate == null) {
            endDate = now.plus(1, ChronoUnit.DAYS);
        }

        return consumablesService.getUsedConsumables(startDate, endDate);
    }

    @Install(to = "drugsDl", target = Target.DATA_LOADER)
    private List<Drug> drugsDlLoadDelegate(LoadContext<Drug> loadContext) {
        List<Drug> drugList = drugsService.getDrugList();
        return drugList;
    }

    @Subscribe("drugsTable.addDrag")
    public void onDrugsTableAddDrag(Action.ActionPerformedEvent event) {
        Drug drug = dataContext.create(Drug.class);
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    @Subscribe("consumablesTable.runPriceListReport")
    public void onConsumablesTableRunPriceListReport(Action.ActionPerformedEvent event) {
        Report report = dataManager.load(Report.class)
                .query("select r from report$Report r where r.code = :code")
                .parameter("code", "PRICES")
                .view("report-run")
                .optional().orElseThrow(IllegalArgumentException::new);
        reportGuiManager.runReport(report, this);
    }
}