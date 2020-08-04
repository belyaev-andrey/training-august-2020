package com.company.clinic.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "CLINIC_VISIT_ANALYSIS")
@Entity(name = "clinic_VisitAnalysis")
public class VisitAnalysis extends StandardEntity {
    private static final long serialVersionUID = -7914196688271394356L;

    @Column(name = "MONTH_")
    private String month;

    @Column(name = "ANALYSIS_COUNT")
    private Integer analysisCount;

    public Integer getAnalysisCount() {
        return analysisCount;
    }

    public void setAnalysisCount(Integer analysisCount) {
        this.analysisCount = analysisCount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}