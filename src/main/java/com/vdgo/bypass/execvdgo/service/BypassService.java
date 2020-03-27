package com.vdgo.bypass.execvdgo.service;

import com.vdgo.bypass.execvdgo.domain.Bypass;

public interface BypassService {
    Bypass setExecStatus(Bypass bypass, byte status);
}
