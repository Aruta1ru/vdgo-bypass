package com.vdgo.bypass.execvdgo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;

import javax.persistence.*;

@Entity
@Table(name = "web_vdgo_files")
public class FileStorage {

    @Id
    @JsonView(Views.BypassView.class)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_obj")
    @JsonIgnore
    private Addr address;

    @Column(name = "name")
    @JsonView(Views.BypassView.class)
    private String name;

    @Column(name = "size")
    @JsonView(Views.BypassView.class)
    private long size;

    @Column(name = "file_type")
    @JsonView(Views.BypassView.class)
    private int fileType;

    public FileStorage() {
    }

    public FileStorage(Addr address, String name, long size, int fileType) {
        this.address = address;
        this.name = name;
        this.size = size;
        this.fileType = fileType;
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

    public int getFileType() {return fileType;}
}
