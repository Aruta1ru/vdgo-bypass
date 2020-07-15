package com.vdgo.bypass.execvdgo.domain;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import javax.persistence.*;

@Entity
//for MSSQL connection
@Table(name = "one_bso_user", catalog = "dog", schema = "dbo")
//for PostgreSQL connection
//@Table(name = "one_bso_user")
@ToString(of = {"id", "name", "post", "valid"})
@EqualsAndHashCode(of = {"id"})
public class Executor {

    @Id
    @Column(name = "id_bso_user")
    @JsonView(Views.BypassView.class)
    private int id;

    @Column(name = "fio")
    @JsonView(Views.BypassView.class)
    private String name;

    @Column(name = "dolgn")
    @JsonView(Views.BypassView.class)
    private String post;

    @Column(name = "valid")
    @JsonView(Views.BypassView.class)
    private boolean valid;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "executor")
    private User user;

    public void setId(int id) {
        this.id = id;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public int getId() {
        return id;
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
