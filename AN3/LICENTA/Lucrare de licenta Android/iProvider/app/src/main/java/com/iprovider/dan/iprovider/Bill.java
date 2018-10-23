package com.iprovider.dan.iprovider;

public class Bill {
    private String id;
    private String idMeter;
    private String period;
    private String priorRead;
    private String currentRead;
    private String status; //0 - new, 1 - paid, 2 - unpaid
    private String debt;

    public Bill() {
    }

    public Bill(String id, String idMeter, String period, String priorRead, String currentRead, String status, String debt) {
        this.id = id;
        this.idMeter = idMeter;
        this.period = period;
        this.priorRead = priorRead;
        this.currentRead = currentRead;
        this.status = status;
        this.debt = debt;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getIdMeter() {
        return idMeter;
    }
    public void setIdMeter(String idMeter) {
        this.idMeter = idMeter;
    }
    public String getPeriod() {
        return period;
    }
    public void setPeriod(String period) {
        this.period = period;
    }
    public String getPriorRead() {
        return priorRead;
    }
    public void setPriorRead(String priorRead) {
        this.priorRead = priorRead;
    }
    public String getCurrentRead() {
        return currentRead;
    }
    public void setCurrentRead(String currentRead) {
        this.currentRead = currentRead;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDebt() {
        return debt;
    }
    public void setDebt(String debt) {
        this.debt = debt;
    }
}
