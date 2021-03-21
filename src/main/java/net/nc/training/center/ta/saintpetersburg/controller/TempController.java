package net.nc.training.center.ta.saintpetersburg.controller;

import net.nc.training.center.ta.saintpetersburg.model.TempModelMessage;
import net.nc.training.center.ta.saintpetersburg.service.TempService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


//@Controller
//@CrossOrigin(origins = "http://localhost:3000")
//@CrossOrigin(origins = "https://task-tracker-lc-2021.herokuapp.com")
@RestController
public class TempController {

    private final TempService tempService;

    @Autowired
    public TempController(TempService tempService) {
        this.tempService = tempService;
    }


//    @GetMapping("/tempModelsMessage")
//    public String findAll(Model model) {
//        List<TempModelMessage> tempModelsMessage = tempService.findAll();
//        model.addAttribute("tempModelsMessage", tempModelsMessage);
//        return "temp";
//    }


    @GetMapping("/tempModelsMessage")
    public List<TempModelMessage> findAll(Model model) {
        return this.tempService.findAll();

    }


}
