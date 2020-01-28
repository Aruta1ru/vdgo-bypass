package com.vdgo.bypass.execvdgo.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.vdgo.bypass.execvdgo.domain.Bypass;
import com.vdgo.bypass.execvdgo.domain.Executor;
import com.vdgo.bypass.execvdgo.domain.Views;
import com.vdgo.bypass.execvdgo.repo.BypassRepo;
import com.vdgo.bypass.execvdgo.service.ExecutorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("bypass")
public class BypassController {

    private final BypassRepo bypassRepo;

    @Autowired
    private ExecutorService executorService;

    @Autowired
    public BypassController(BypassRepo bypassRepo) {
        this.bypassRepo = bypassRepo;
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

    @PutMapping("{id}")
    public Bypass update(@PathVariable("id") Bypass bypassFromDb, @RequestBody Bypass bypass) {
        BeanUtils.copyProperties(bypass, bypassFromDb, "id");
        return bypassRepo.save(bypassFromDb);
    }
}
