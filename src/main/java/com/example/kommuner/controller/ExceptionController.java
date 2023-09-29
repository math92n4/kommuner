package com.example.kommuner.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "exp/")
public class ExceptionController {

    @GetMapping("div/{num}")
    public int getDivNum(@PathVariable int num) {
        return 100 / num;
    }

}
