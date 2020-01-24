package com.vdgo.bypass.execvdgo.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DogTypeEnum {

    ABSENT((byte) 0, "отсутствует"),
    INDIVIDUAL((byte) 1, "частное лицо"),
    LEGALLAWYER((byte) 2, "юридическое лицо"),
    SBK((byte) 3, "СБК");

    private byte id;
    private String name;

    DogTypeEnum(byte id, String name) {
        this.id = id;
        this.name = name;
    }

    public byte getId() {
        return id;
    }

    public void setId(byte id) {
        this.id = id;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
