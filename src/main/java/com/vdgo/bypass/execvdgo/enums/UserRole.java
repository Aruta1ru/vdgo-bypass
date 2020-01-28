package com.vdgo.bypass.execvdgo.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    USER;

    @Override
    public String getAuthority()
    {
        return this.name();
    }
}