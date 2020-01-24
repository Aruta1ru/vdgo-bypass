package com.vdgo.bypass.execvdgo.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BypassDoneTypeEnum {

    UNDONE( "не выполнено"),
    DONE("выполнено");

    private String name;

    BypassDoneTypeEnum(String name) {

        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
