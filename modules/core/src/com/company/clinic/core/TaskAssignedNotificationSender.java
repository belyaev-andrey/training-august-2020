package com.company.clinic.core;

import com.haulmont.addon.bproc.entity.TaskData;
import com.haulmont.addon.bproc.events.UserTaskAssignedEvent;
import com.haulmont.addon.bproc.events.UserTaskCompletedEvent;
import com.haulmont.cuba.security.entity.User;
import org.slf4j.Logger;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

@Component(TaskAssignedNotificationSender.NAME)
public class TaskAssignedNotificationSender {
    public static final String NAME = "clinic_TaskAssignedNotificationSender";

    @Inject
    private Logger log;

    @EventListener
    protected void onTaskAssigned(UserTaskAssignedEvent event) {
        User user = event.getUser();
        TaskData taskData = event.getTaskData();
        String emailTitle = "New process task " + taskData.getName() + " for user "+user.getLogin();
        log.info(emailTitle);
    }

    @EventListener
    protected void onTreatmentTaskCompleted(UserTaskCompletedEvent event) {
        TaskData taskData = event.getTaskData();
        String dataName = taskData.getName();
        log.info(dataName +" is completed");
    }

}