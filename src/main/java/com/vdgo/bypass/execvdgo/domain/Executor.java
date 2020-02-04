package com.vdgo.bypass.execvdgo.domain;

import com.fasterxml.jackson.annotation.JsonView;
import com.vdgo.bypass.execvdgo.enums.UserRole;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
//for MSSQL connection
@Table(name = "one_bso_user", catalog = "test_dog", schema = "dbo")
//for PostgreSQL connection
//@Table(name = "one_bso_user")
@ToString(of = {"id", "name", "post", "valid", "roles"})
@EqualsAndHashCode(of = {"id"})
public class Executor implements Serializable, UserDetails {

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

    @Column(name = "username")
    @JsonView(Views.BypassView.class)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "valid")
    @JsonView(Views.BypassView.class)
    private boolean valid;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "id_bso_user"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> authorities;

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isValid();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Set<UserRole> getAuthorities() {
        return this.authorities;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
