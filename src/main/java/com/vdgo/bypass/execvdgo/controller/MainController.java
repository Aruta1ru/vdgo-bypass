package com.vdgo.bypass.execvdgo.controller;

import com.vdgo.bypass.execvdgo.domain.Executor;
import com.vdgo.bypass.execvdgo.repo.BypassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

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
    public String main(Model model, @AuthenticationPrincipal Executor exec) {
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", exec);
        data.put("bypasses", bypassRepo.findByExecutor(exec));

        model.addAttribute("frontendData", data);

        return "index";
    }

}
