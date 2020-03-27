package com.vdgo.bypass.execvdgo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.Immutable;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Immutable
//for MSSQL connection
@Table(name = "web_vdgo_bypass", catalog = "to_ch_dog", schema="dbo")
//for PostgreSQL connection
//@Table(name = "vdg_obj_work")
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

    @Column(name = "dog_type")
    @JsonView(Views.BypassView.class)
    private String dogType;

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
    private byte doneTypeReal;

    @Column(name = "web_exec")
    @JsonView(Views.BypassView.class)
    private byte doneType;

    @Column(name = "fixed")
    @JsonView(Views.BypassView.class)
    private boolean fixed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "undone_reason")
    @JsonView(Views.BypassView.class)
    private UndoneReason undoneReason;

    public int getId() {
        return id;
    }

    public Addr getAddress() {
        return address;
    }

    public Executor getExecutor() {
        return executor;
    }

    public LocalDateTime getBypassDate() {
        return bypassDate;
    }

    public String getDogType() {
        return dogType;
    }

    public byte getDoneType() {
        return doneType;
    }

    public UndoneReason getUndoneReason() {
        return undoneReason;
    }

    public byte getDoneTypeReal() {
        return doneTypeReal;
    }

    public boolean isFixed() {
        return fixed;
    }
}
