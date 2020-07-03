package com.vdgo.bypass.execvdgo.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//@Table(name = "web_vdgo_undone_spr", catalog = "to_ch_dog", schema = "dbo")
@Table(name = "web_vdgo_undone_spr")
@ToString(of = {"id", "shortName"})
public class UndoneReason {

    @Id
    @Column(name = "id")
    @JsonView({Views.BypassView.class, Views.UndoneReasonsView.class})
    private int id;

    @Column(name = "name_full")
    private String fullName;

    @Column(name = "name_short")
    @JsonView({Views.BypassView.class, Views.UndoneReasonsView.class})
    private String shortName;

    public UndoneReason(int id, String fullName, String shortName) {
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public UndoneReason() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
