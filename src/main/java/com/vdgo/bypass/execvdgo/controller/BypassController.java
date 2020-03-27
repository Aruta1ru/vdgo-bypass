package com.vdgo.bypass.execvdgo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.vdgo.bypass.execvdgo.domain.Bypass;
import com.vdgo.bypass.execvdgo.domain.Executor;
import com.vdgo.bypass.execvdgo.domain.Views;
import com.vdgo.bypass.execvdgo.repo.BypassRepo;
import com.vdgo.bypass.execvdgo.service.BypassService;
import com.vdgo.bypass.execvdgo.service.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("bypass")
public class BypassController {

    private final BypassRepo bypassRepo;
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    private ExecutorService executorService;

    @Autowired
    private BypassService bypassService;

    @Autowired
    public BypassController(BypassRepo bypassRepo, JdbcTemplate jdbcTemplate) {
        this.bypassRepo = bypassRepo;
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping
    @JsonView(Views.BypassView.class)
    public List<Bypass> list(Principal principal, Model model) {
        Executor executor = (Executor) executorService.loadUserByUsername(principal.getName());
        //List<Bypass> bypasses = bypassRepo.findByExecutor(executor);
        List<Bypass> bypasses = bypassRepo.findByExecutorAndBypassDate(executor, LocalDateTime.now());
        return bypasses;
    }

    @GetMapping("{id}")
    public Bypass getOne(@PathVariable("id") Bypass bypass) {
        return bypass;
    }

    @PostMapping("/done")
    public Bypass done(@RequestBody Bypass bypass) {
        return bypassService.setExecStatus(bypass, (byte) 0);
    }

    @PostMapping("/undone")
    public Bypass undone( @RequestBody Bypass bypass) {
        return bypassService.setExecStatus(bypass, (byte) 1);
    }
}
