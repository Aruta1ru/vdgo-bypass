package com.vdgo.bypass.execvdgo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import com.vdgo.bypass.execvdgo.enums.BypassDoneTypeEnum;
import com.vdgo.bypass.execvdgo.enums.DogTypeEnum;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "web_vdgo_bypass")
@ToString(of = {"id", "address", "dogType", "executor", "bypassDate", "doneType", "undoneReason", "fixed", "created"})
@EqualsAndHashCode(of = {"id"})
public class Bypass {

    @Id
    @JsonView(Views.BypassView.class)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_object")
    @JsonView(Views.BypassView.class)
    private Addr address;

    @Column(name = "type_dog")
    @Enumerated(EnumType.ORDINAL)
    @JsonView(Views.BypassView.class)
    private DogTypeEnum dogType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_exec")
    @JsonView(Views.BypassView.class)
    private Executor executor;

    @Column(name = "date_action")
    @JsonView(Views.BypassView.class)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime bypassDate;

    @Column(name = "web_exec")
    @JsonView(Views.BypassView.class)
    @Enumerated(EnumType.ORDINAL)
    private BypassDoneTypeEnum doneType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "undone_reason")
    @JsonView(Views.BypassView.class)
    private UndoneReason undoneReason;

    @Column(name = "fixed")
    @JsonView(Views.BypassView.class)
    private boolean fixed;

    @Column(name = "is_created")
    @JsonView(Views.BypassView.class)
    private int created;

    public Bypass(int id, Addr address, DogTypeEnum dogType, Executor executor, LocalDateTime bypassDate, BypassDoneTypeEnum doneType, UndoneReason undoneReason, boolean fixed, int created) {
        this.id = id;
        this.address = address;
        this.dogType = dogType;
        this.executor = executor;
        this.bypassDate = bypassDate;
        this.doneType = doneType;
        this.undoneReason = undoneReason;
        this.fixed = fixed;
        this.created = created;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Addr getAddress() {
        return address;
    }

    public void setAddress(Addr address) {
        this.address = address;
    }

    public DogTypeEnum getDogType() {
        return dogType;
    }

    public void setDogType(DogTypeEnum dogType) {
        this.dogType = dogType;
    }

    public Executor getExecutor() {
        return executor;
    }

    public void setExecutor(Executor executor) {
        this.executor = executor;
    }

    public LocalDateTime getBypassDate() {
        return bypassDate;
    }

    public void setBypassDate(LocalDateTime bypassDate) {
        this.bypassDate = bypassDate;
    }

    public BypassDoneTypeEnum getDoneType() {
        return doneType;
    }

    public int getDoneTypeNum() {
        int returnValue = 0;
        switch (this.doneType) {
            case INWORK: break;
            case DONE: returnValue = 1; break;
            case UNDONE: returnValue = 2; break;
        }
        return returnValue;
    }

    public void setDoneType(BypassDoneTypeEnum doneType) {
        this.doneType = doneType;
    }

    public UndoneReason getUndoneReason() {
        return undoneReason;
    }

    public void setUndoneReason(UndoneReason undoneReason) {
        this.undoneReason = undoneReason;
    }

    public boolean isFixed() {
        return fixed;
    }

    public void setFixed(boolean fixed) {
        this.fixed = fixed;
    }

    public int getCreated() {
        return created;
    }

    public void setCreated(int created) {
        this.created = created;
    }

    public Bypass() {
    }
}
