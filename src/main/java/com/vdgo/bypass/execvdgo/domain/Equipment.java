package com.vdgo.bypass.execvdgo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Immutable
@Table(name = "web_vdgo_ob")
@ToString(of = {"id", "address", "name", "quantity", "part", "installDate", "shutdownDate"})
public class Equipment {

    @Id
    @Column(name = "id_st")
    @JsonView(Views.BypassView.class)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_obj")
    @JsonIgnore
    private Addr address;

    @Column(name = "name_ob")
    @JsonView(Views.BypassView.class)
    private String name;

    @Column(name = "kol_oborud")
    @JsonView(Views.BypassView.class)
    private double quantity;

    @Column(name = "dol_ob")
    @JsonView(Views.BypassView.class)
    private double part;

    @Column(name = "du")
    @JsonView(Views.BypassView.class)
    private LocalDateTime installDate;

    @Column(name = "do")
    @JsonView(Views.BypassView.class)
    private LocalDateTime shutdownDate;

    public Equipment(int id, Addr address, String name, double quantity, double part, LocalDateTime installDate, LocalDateTime shutdownDate) {
        this.id = id;
        this.address = address;
        this.name = name;
        this.quantity = quantity;
        this.part = part;
        this.installDate = installDate;
        this.shutdownDate = shutdownDate;
    }

    public Equipment() {
    }

    public int getId() {
        return id;
    }

    public Addr getAddress() {
        return address;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPart() {
        return part;
    }

    public LocalDateTime getInstallDate() {
        return installDate;
    }

    public LocalDateTime getShutdownDate() {
        return shutdownDate;
    }
}
