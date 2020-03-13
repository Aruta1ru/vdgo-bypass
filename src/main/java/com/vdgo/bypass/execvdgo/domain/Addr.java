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
@Table(name = "ch_adr_obj", catalog = "tmp_to_ch_dog", schema = "dbo")
//for PostgreSQL connection
//@Table(name = "ch_adr_obj")
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

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "address")
    private List<FileStorage> files = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "address")
    private Client client;

    public int getId() {
        return id;
    }

    public String getAddr() {
        return addr;
    }

    public List<FileStorage> getFiles() { return files; }

    public Client getClient() {
        return client;
    }
}
