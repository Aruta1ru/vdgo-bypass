package com.vdgo.bypass.execvdgo.domain;


import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Immutable
@Table(name = "web_vdgo_undone_spr", catalog = "to_ch_dog", schema="dbo")
public class UndoneReason {

    @Id
    private byte id;

    @Column(name = "name_full")
    private String fullName;

    @Column(name = "name_short")
    private String shortName;

    public byte getId() {
        return id;
    }

    public String getFullName() {
        return id == 0
                ? ""
                : fullName;
    }

    public String getShortName() {
        return id == 0
                ? ""
                : shortName;
    }
}
