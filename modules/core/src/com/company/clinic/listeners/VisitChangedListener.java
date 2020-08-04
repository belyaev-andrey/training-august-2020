package com.company.clinic.listeners;

import com.company.clinic.entity.Owner;
import com.company.clinic.entity.Visit;
import com.haulmont.cuba.core.app.EmailService;
import com.haulmont.cuba.core.app.events.AttributeChanges;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.FluentLoader;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;
import java.util.UUID;

@Component("clinic_VisitChangedListener")
public class VisitChangedListener {

    @Inject
    private Logger log;

    @Inject
    private DataManager dataManager;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(EntityChangedEvent<Visit, UUID> event) {
        AttributeChanges changes = event.getChanges();
        if (changes.isChanged("date")) {
            Visit visit = dataManager.load(event.getEntityId()).view("visit-owner-view").one();
            sendEmail(visit.getPet().getOwner());
            //sending email
        }
    }

    private void sendEmail(Owner owner){
        log.info("Sending email to "+owner.getEmail());
    }
}