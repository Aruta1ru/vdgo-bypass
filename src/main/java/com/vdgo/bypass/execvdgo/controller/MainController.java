package com.vdgo.bypass.execvdgo.controller;

import com.vdgo.bypass.execvdgo.domain.Executor;
import com.vdgo.bypass.execvdgo.repo.BypassRepo;
import com.vdgo.bypass.execvdgo.repo.UndoneReasonRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

@Controller
@RequestMapping("/")
public class MainController
{
    private final BypassRepo bypassRepo;
    private final UndoneReasonRepo undoneReasonRepo;

    @Value("${spring.profiles.active:prod}")
    private String profile;

    @Autowired
    public MainController(BypassRepo bypassRepo, UndoneReasonRepo undoneReasonRepo) {
        this.bypassRepo = bypassRepo;
        this.undoneReasonRepo = undoneReasonRepo;
    }

    @GetMapping
    public String main(Model model, @AuthenticationPrincipal Executor exec) {
        HashMap<Object, Object> data = new HashMap<>();

        data.put("profile", exec);
        data.put("reasons", undoneReasonRepo.findAll());
        data.put("bypasses", bypassRepo.findByExecutor(exec));
        LocalDateTime currentDate =  LocalDate.now().atTime(0, 0, 0);
        //data.put("bypasses", bypassRepo.findByExecutorAndBypassDate(exec, currentDate));

        model.addAttribute("frontendData", data);
        model.addAttribute("isDevMode", "dev".equals(profile));

        return "index";
    }

}
