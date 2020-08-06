package com.company.clinic.web.screens.mainscreen;

import com.company.clinic.entity.Pet;
import com.company.clinic.entity.Visit;
import com.company.clinic.service.VisitService;
import com.company.clinic.web.screens.visit.VisitEdit;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.web.app.main.MainScreen;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Date;


@UiController("extMainScreen")
@UiDescriptor("ext-main-screen.xml")
@LoadDataBeforeShow
public class ExtMainScreen extends MainScreen {

    @Inject
    private CollectionLoader<Pet> petsDl;
    @Inject
    private CollectionLoader<Visit> visitsDl;
    @Inject
    private DataManager dataManager;
    @Inject
    private CollectionContainer<Visit> visitsDc;
    @Inject
    private EditorScreenFacet<Visit, VisitEdit> visitEditor;
    @Inject
    private ScreenBuilders screenBuilders;
    @Inject
    private VisitService visitService;
    @Inject
    private LookupField<Pet> petSelector;
    @Inject
    private DateField<LocalDateTime> dateSelector;
    @Inject
    private UserSession userSession;


    @Subscribe("refresh")
    public void onRefresh(Action.ActionPerformedEvent event) {
        petsDl.load();
        visitsDl.load();
    }

    @Subscribe("scheduleCalendar")
    public void onScheduleCalendarCalendarEventMove(Calendar.CalendarEventMoveEvent<LocalDateTime> event) {
        Visit visit = (Visit) event.getEntity();
        LocalDateTime newStart = event.getNewStart();
        visit.setDate(newStart);
        Visit committedVisit = dataManager.commit(visit);
        visitsDc.replaceItem(committedVisit);
    }

    @Subscribe("scheduleCalendar")
    public void onScheduleCalendarCalendarEventClick(Calendar.CalendarEventClickEvent<LocalDateTime> event) {
        Visit visit = (Visit) event.getEntity();

        Screen screen = screenBuilders.editor(Visit.class, this)
                .editEntity(visit)
                .build();

        screen.addAfterCloseListener(afterCloseEvent -> {
            if (afterCloseEvent.getCloseAction() == WINDOW_COMMIT_AND_CLOSE_ACTION) {
                visitsDl.load();
            }
        });

        screen.show();
    }

    @Subscribe("schedule")
    public void onSchedule(Action.ActionPerformedEvent event) {
        Pet pet = petSelector.getValue();
        LocalDateTime date = dateSelector.getValue();
        User user = userSession.getCurrentOrSubstitutedUser();

        Visit visit = visitService.scheduleVisit(pet, date, user);
        visitsDc.replaceItem(visit);
    }

}