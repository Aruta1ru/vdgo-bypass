package com.vdgo.bypass.execvdgo.controller;

import com.vdgo.bypass.execvdgo.exception.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("bypass")
public class BypassController {

    private List<Map<String, String>> bypasses = new ArrayList<Map<String, String>>() {{
        add(new HashMap<String, String>() {{ put("id", "1"); put("executor", "Иванов И.И."); put("address", "г.Нижний Новгород, ул.Пушкина, д.18"); put("dog_type", "ч.л.");
            put("bypass_date", "22 января 2020г."); put("done_type", "не выполнено"); }});
        add(new HashMap<String, String>() {{ put("id", "2"); put("executor", "Петров П.П."); put("address", "г.Нижний Новгород, пр-кт Гагарина, д.23 корп.2"); put("dog_type", "ю.л.");
            put("bypass_date", "21 января 2020г."); put("done_type", "не выполнено"); }});
        add(new HashMap<String, String>() {{ put("id", "3"); put("executor", "Сидоров В.А."); put("address", "г.Нижний Новгород, ул.Народная, д.79 кв.15"); put("dog_type", "сбк");
            put("bypass_date", "23 января 2020г."); put("done_type", "не выполнено"); }});
    }};

    @GetMapping
    public List<Map<String, String>> list() {
        return bypasses;
    }

    @GetMapping("{id}")
    public Map<String, String> getOne(@PathVariable String id) {
        return getBypass(id);
    }

    private Map<String, String> getBypass(@PathVariable String id) {
        return bypasses.stream().filter(bypasses -> bypasses.get("id").equals(id)).findFirst().orElseThrow(NotFoundException::new);
    }

    @PutMapping("{id}")
    public Map<String, String> update(@PathVariable String id, @RequestBody Map<String, String> bypass) {
        Map<String, String> bypassFromDb = getBypass(id);
        bypassFromDb.putAll(bypass);
        bypassFromDb.put("id", id);
        return bypassFromDb;
    }
}
