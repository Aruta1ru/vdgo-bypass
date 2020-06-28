package com.vdgo.bypass.execvdgo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.vdgo.bypass.execvdgo.domain.Bypass;
import com.vdgo.bypass.execvdgo.domain.Executor;
import com.vdgo.bypass.execvdgo.domain.Views;
import com.vdgo.bypass.execvdgo.dto.BuffTableObject;
import com.vdgo.bypass.execvdgo.repo.BypassRepo;
import com.vdgo.bypass.execvdgo.service.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.PreparedStatement;
import java.util.List;

@RestController
@RequestMapping("bypass")
public class BypassController {

    private final BypassRepo bypassRepo;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private ExecutorService executorService;

    @Autowired
    public BypassController(BypassRepo bypassRepo, JdbcTemplate jdbcTemplate) {
        this.bypassRepo = bypassRepo;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    @JsonView(Views.BypassView.class)
    public List<Bypass> list(Principal principal, Model model) {

        Executor executor = (Executor) executorService.loadUserByUsername(principal.getName());

        List<Bypass> bypasses = bypassRepo.findByExecutor(executor);

        return bypasses;
    }

    @GetMapping("{id}")
    public Bypass getOne(@PathVariable("id") Bypass bypass) {
        return bypass;
    }

    @PostMapping("{id}")
    public Bypass save(@PathVariable("id") int bypassId, @RequestBody BuffTableObject buffTableObject) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        if (buffTableObject.isCreated())
        {
            jdbcTemplate.update(x -> {
                PreparedStatement preparedStatement = x.prepareStatement("UPDATE web_vdgo_buff_data SET exec_vdgo=?, undone_reason=? WHERE id_bypass=?");
                preparedStatement.setInt(1, buffTableObject.getDoneType());
                preparedStatement.setInt(2, buffTableObject.getUndoneReason());
                preparedStatement.setInt(3, bypassId);
                return preparedStatement;
            }, keyHolder);}
        else {
            jdbcTemplate.update(x -> {
                PreparedStatement preparedStatement = x.prepareStatement("INSERT INTO web_vdgo_buff_data (id_bypass, id_obj, id_exec, date_action, exec_vdgo, undone_reason) VALUES (?, ?, ?, ?, ?, ?)");
                preparedStatement.setInt(1, bypassId);
                preparedStatement.setInt(2, buffTableObject.getIdObject());
                preparedStatement.setInt(3, buffTableObject.getIdExecutor());
                preparedStatement.setObject(4, buffTableObject.getDateAction());
                preparedStatement.setInt(5, buffTableObject.getDoneType());
                preparedStatement.setInt(6, buffTableObject.getUndoneReason());
                return preparedStatement;
            }, keyHolder);
        }

        Bypass changedBypass = bypassRepo.findById(bypassId);

        return changedBypass;
    }

}
