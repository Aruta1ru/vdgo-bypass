package com.vdgo.bypass.execvdgo.controller;

import com.vdgo.bypass.execvdgo.repo.BypassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController
{
    private final BypassRepo bypassRepo;

    @Autowired
    public MainController(BypassRepo bypassRepo) {
        this.bypassRepo = bypassRepo;
    }

    @GetMapping
    public String index(Model model)
    {
        return "index";
    }

}
