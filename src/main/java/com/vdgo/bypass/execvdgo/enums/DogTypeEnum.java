package com.vdgo.bypass.execvdgo.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum DogTypeEnum {

    ABSENT(0, "отсутствует"),
    INDIVIDUAL(1, "частное лицо"),
    LEGALLAWYER(2, "юридическое лицо"),
    SBK(3, "СБК");

    private byte id;
    private String name;

    DogTypeEnum(int id, String name) {
        this.id = (byte) id;
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
