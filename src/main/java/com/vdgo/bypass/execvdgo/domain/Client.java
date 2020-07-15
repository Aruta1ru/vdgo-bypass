package com.vdgo.bypass.execvdgo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.ToString;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;

@Entity
@Immutable
@Table(name = "to_ch_klient")
@ToString(of = {"id", "name", "phone"})
public class Client {

    @Id
    @Column(name = "id_klient")
    @JsonView(Views.BypassView.class)
    private int id;

    @Column(name = "FIO")
    @JsonView(Views.BypassView.class)
    private String name;

    @Column(name = "telefon")
    @JsonView(Views.BypassView.class)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_object")
    @JsonIgnore
    private Addr address;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return (phone == null || phone.isEmpty())
                ? "не указан"
                : phone;
    }

    public Addr getAddress() {
        return address;
    }
}
