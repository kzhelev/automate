package com.linxs.automate.models.vin.data;

import java.time.LocalDate;

public class Inspection {

    private String conclusion;
    private int kmCovered;
    private LocalDate date;
    private LocalDate nextInspectionDate;

    public Inspection() {
    }

    public Inspection(String conclusion, int kmCovered, LocalDate date, LocalDate nextInspectionDate) {
        this.conclusion = conclusion;
        this.kmCovered = kmCovered;
        this.date = date;
        this.nextInspectionDate = nextInspectionDate;
    }

    public String getConclusion() {
        return conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public int getKmCovered() {
        return kmCovered;
    }

    public void setKmCovered(int kmCovered) {
        this.kmCovered = kmCovered;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getNextInspectionDate() {
        return nextInspectionDate;
    }

    public void setNextInspectionDate(LocalDate nextInspectionDate) {
        this.nextInspectionDate = nextInspectionDate;
    }
}
