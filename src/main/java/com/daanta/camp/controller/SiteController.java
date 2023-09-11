package com.daanta.camp.controller;

import com.daanta.camp.domain.Site;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/site")
public class SiteController {

    @PostMapping("/add")
    public Map<String, Object> add(
        Site site
    ) {

        // Prepare
        Map<String, Object> result = new HashMap<>();

        try {

            Map<String, String> body = new HashMap<>();

            result.put("result", "success");
            result.put("body", body);
        } catch(Exception e) {
            result.put("result", "fail");
        }

        return result;
    }

}
