package com.vdgo.bypass.execvdgo.domain;

import com.fasterxml.jackson.annotation.JsonView;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
//for MSSQL connection
//@Table(name = "ch_adr_obj", catalog = "to_ch_dog", schema = "dbo")
//for PostgreSQL connection
@Table(name = "ch_adr_obj")
@ToString(of = {"id", "addr"})
@EqualsAndHashCode(of = {"id"})
@Immutable
public class Addr {

    @Id
    @Column(name = "id_obj")
    @JsonView(Views.BypassView.class)
    private int id;

    @Column(name = "addr")
    @JsonView(Views.BypassView.class)
    private String addr;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "address")
    private List<FileStorage> files = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "address")
    private List<Equipment> equipment = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "address")
    private List<Client> client = new ArrayList<>();

    public int getId() {
        return id;
    }

    public String getAddr() {
        return addr;
    }

    public List<FileStorage> getFiles() { return files; }

    public List<Client> getClient() {
        return client;
    }

    public List<Equipment> getEquipment() {
        return equipment;
    }
}
