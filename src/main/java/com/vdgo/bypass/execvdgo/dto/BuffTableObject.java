package com.vdgo.bypass.execvdgo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class BuffTableObject {
    private int idObject;
    private int idExecutor;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime dateAction;

    private int doneType;
    private int undoneReason;
    private boolean created;

    public BuffTableObject(int idObject, int idExecutor, LocalDateTime dateAction, int doneType, int undoneReason, boolean created) {
        this.idObject = idObject;
        this.idExecutor = idExecutor;
        this.dateAction = dateAction;
        this.doneType = doneType;
        this.undoneReason = undoneReason;
        this.created = created;
    }

    public int getDoneType() {
        return doneType;
    }

    public int getUndoneReason() {
        return undoneReason;
    }

    public int getIdObject() {
        return idObject;
    }

    public int getIdExecutor() {
        return idExecutor;
    }

    public LocalDateTime getDateAction() {
        return dateAction;
    }

    public boolean isCreated() {
        return created;
    }


    public BuffTableObject() {
    }
}
