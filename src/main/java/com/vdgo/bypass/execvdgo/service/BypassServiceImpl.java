package com.vdgo.bypass.execvdgo.service;

import com.vdgo.bypass.execvdgo.domain.Bypass;
import com.vdgo.bypass.execvdgo.repo.BypassRepo;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class BypassServiceImpl implements BypassService {

    BypassRepo bypassRepo;
    private final JdbcTemplate jdbcTemplate;

    public BypassServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Bypass setExecStatus(Bypass bypass, byte status) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        if (bypass.getDoneType() == 100) {
            jdbcTemplate.update(x -> {
                PreparedStatement preparedStatement = x.prepareStatement("INSERT INTO web_vdgo_buff_data (id_bypass, id_obj, id_exec, date_action, exec_vdgo, undone_reason) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, bypass.getId());
                preparedStatement.setInt(2, bypass.getAddress().getId());
                preparedStatement.setInt(3, bypass.getExecutor().getId());
                preparedStatement.setObject(4, bypass.getBypassDate());
                if (status == 0) {
                    preparedStatement.setByte(5, (byte) 1);
                    preparedStatement.setByte(6, (byte) 0);
                } else {
                    preparedStatement.setByte(5, (byte) 0);
                    preparedStatement.setByte(6, bypass.getUndoneReason().getId());
                }
                return preparedStatement;
            }, keyHolder);
        } else {
            jdbcTemplate.update(x -> {
                PreparedStatement preparedStatement = x.prepareStatement("UPDATE web_vdgo_buff_data SET exec_vdgo = ?, undone_reason = ? WHERE id_bypass = ?", Statement.RETURN_GENERATED_KEYS);
                if (status == 0) {
                    preparedStatement.setByte(1, (byte) 1);
                    preparedStatement.setByte(2, (byte) 0);
                } else {
                    preparedStatement.setByte(1, (byte) 0);
                    preparedStatement.setByte(2, bypass.getUndoneReason().getId());
                }
                preparedStatement.setInt(3, bypass.getId());
                return preparedStatement;
            }, keyHolder);
        }
        Bypass newBypass = bypassRepo.getOne(bypass.getId());
        return newBypass;
    }
}
