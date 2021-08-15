package ru.murt.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {
    double res;
    @GetMapping("/first/calculator")
    public String webCalculator(@RequestParam(value = "a", required = false) int a,
                                @RequestParam(value = "b", required = false) int b,
                                @RequestParam(value = "action", required = false) String action,
                                Model model){

        switch (action) {
            case "multiplication":
                res = a * b;
                break;
            case "addition":
                res = a + b;
                break;
            case "subtraction":
                res = a - b;
                break;
            case "division":
                res = a / (double)b;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + action);
        }
        model.addAttribute("messageCal", "Result: " + res);
        return "first/calculator";
    }
}
