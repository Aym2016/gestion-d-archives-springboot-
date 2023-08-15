package com.sip.ams1.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sip.ams1.entities.LieuArchive;
import com.sip.ams1.repository.LieuArchiveRepository;
@Controller
@RequestMapping("/lieuArchive/")
public class LieuArchiveController {
	private final LieuArchiveRepository lieuArchiveRepository;
    @Autowired
    public LieuArchiveController(LieuArchiveRepository lieuArchiveRepository) {
        this.lieuArchiveRepository = lieuArchiveRepository;
    }
    
    @GetMapping("list")
    public String listlieuArchives(Model model) {
        model.addAttribute("lieuArchives", lieuArchiveRepository.findAll());
        return "lieuArchive/listlieuArchive";
    }
    
    @GetMapping("add")
    public String showAddlieuArchiveForm(LieuArchive lieuArchive) {
        return "lieuArchive/addlieuArchive";
    }
    
    @PostMapping("add")
    public String addlieuArchive(@Valid LieuArchive lieuArchive, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "lieuArchive/addlieuArchive";
        }
        lieuArchiveRepository.save(lieuArchive);
        return "redirect:list";
    }
    
    @GetMapping("delete/{id}")
public String deletelieuArchive(@PathVariable("id") long id, Model model) {
        LieuArchive lieuArchive = lieuArchiveRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid lieuArchive Id:" + id));
        lieuArchiveRepository.delete(lieuArchive);
        model.addAttribute("lieuArchives", lieuArchiveRepository.findAll());
        return "lieuArchive/listlieuArchive";
    }
    
    
    @GetMapping("edit/{id}")
    public String showlieuArchiveFormToUpdate(@PathVariable("id") long id, Model model) {
        LieuArchive lieuArchive = lieuArchiveRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid lieuArchive Id:" + id));
        model.addAttribute("lieuArchive", lieuArchive);
        return "lieuArchive/updatelieuArchive";
    }
    @PostMapping("update/{id}")
    public String updatelieuArchive(@PathVariable("id") long id, @Valid LieuArchive lieuArchive, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            lieuArchive.setCodeLieu(id);
            return "lieuArchive/updatelieuArchive";
        }
        lieuArchiveRepository.save(lieuArchive);
        model.addAttribute("lieuArchives", lieuArchiveRepository.findAll());
        return "lieuArchive/listlieuArchives";
    }
}
