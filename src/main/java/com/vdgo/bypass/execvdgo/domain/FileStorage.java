package com.vdgo.bypass.execvdgo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "web_vdgo_files", catalog = "to_ch_dog", schema = "dbo")
public class FileStorage {

    @Id
    @JsonView(Views.BypassView.class)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_obj")
    @JsonIgnore
    private Addr address;

    @Column(name = "name")
    @JsonView(Views.BypassView.class)
    private String name;

    @Column(name = "size")
    @JsonView(Views.BypassView.class)
    private long size;

    public FileStorage() {
    }

    public FileStorage(Addr address, String name, long size) {
        this.address = address;
        this.name = name;
        this.size = size;
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

    public long getSize() {
        return size;
    }
}
