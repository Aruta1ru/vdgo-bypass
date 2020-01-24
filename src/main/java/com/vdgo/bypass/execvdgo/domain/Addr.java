package com.vdgo.bypass.execvdgo.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ch_adr_obj")
@ToString(of = {"id", "addr"})
@EqualsAndHashCode(of = {"id"})
@Immutable
public class Addr {

    @Id
    @Column(name = "id_obj")
    private int id;

    @Column(name = "addr")
  //  @JsonValue
    private String addr;

    public int getId() {
        return id;
    }

    public String getAddr() {
        return addr;
    }
}
