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
//for MSSQL connection
//@Table(name = "vdg_obj_work", catalog = "tmp_to_ch_dog", schema="dbo")
//for PostgreSQL connection
@Table(name = "vdg_obj_work")
@ToString(of = {"id", "address", "dogType", "executor", "bypassDate", "doneType"})
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

    @Column(name = "exec_vdgo")
    @JsonView(Views.BypassView.class)
    @Enumerated(EnumType.ORDINAL)
    private BypassDoneTypeEnum doneType;

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

    public DogTypeEnum getDogType() {
        return dogType;
    }

    public void setDogType(DogTypeEnum dogType) {
        this.dogType = dogType;
    }

    public BypassDoneTypeEnum getDoneType() {
        return doneType;
    }

    public void setDoneType(BypassDoneTypeEnum doneType) {
        this.doneType = doneType;
    }
}
