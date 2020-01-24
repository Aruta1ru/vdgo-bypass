package com.vdgo.bypass.execvdgo.controller;

import com.vdgo.bypass.execvdgo.domain.Bypass;
import com.vdgo.bypass.execvdgo.repo.BypassRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("bypass")
public class BypassController {

    private final BypassRepo bypassRepo;

    @Autowired
    public BypassController(BypassRepo bypassRepo) {
        this.bypassRepo = bypassRepo;
    }

    @GetMapping
    public List<Bypass> list() {
        return bypassRepo.findAll();
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
