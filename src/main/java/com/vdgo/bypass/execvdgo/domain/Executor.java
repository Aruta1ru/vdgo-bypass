package com.vdgo.bypass.execvdgo.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
//for MSSQL connection
@Table(name = "one_bso_user", catalog = "dog", schema = "dbo")
//for PostgreSQL connection
//@Table(name = "one_bso_user")
@ToString(of = {"id", "name", "post"})
@EqualsAndHashCode(of = {"id"})
public class Executor {

    @Id
    @Column(name = "id_bso_user")
    private int id;

    @Column(name = "fio")
   // @JsonValue
    private String name;

    @Column(name = "dolgn")
    private String post;

    public int getId() {
        return id;
    }

    public void setId(short id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }
}
