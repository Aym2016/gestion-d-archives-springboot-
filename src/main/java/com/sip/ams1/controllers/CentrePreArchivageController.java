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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.sip.ams1.entities.CentrePreArchivage;
import com.sip.ams1.repository.CentrePreArchivageRepository;
@Controller
@RequestMapping("/centrePreArchivage/")
public class CentrePreArchivageController {
	private final CentrePreArchivageRepository CentrePreArchivageRepository;
    @Autowired
    public CentrePreArchivageController(CentrePreArchivageRepository CentrePreArchivageRepository) {
        this.CentrePreArchivageRepository = CentrePreArchivageRepository;
    }
    
    @GetMapping("list")
    public String listCentrePreArchivages(Model model) {
        model.addAttribute("centrePreArchivages", CentrePreArchivageRepository.findAll());
        return "centrePreArchivage/listcentrePreArchivage";
    }
    
    @GetMapping("add")
    public String showAddCentrePreArchivageForm(CentrePreArchivage CentrePreArchivage) {
        return "centrePreArchivage/addCentrePreArchivage";
    }
    
    @PostMapping("add")
    public String addCentrePreArchivage(@Valid CentrePreArchivage CentrePreArchivage, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "centrePreArchivage/addCentrePreArchivage";
        }
        CentrePreArchivageRepository.save(CentrePreArchivage);
        return "redirect:list";
    }
    
    @GetMapping("delete/{id}")
public String deleteCentrePreArchivage(@PathVariable("id") long id, Model model) {
        CentrePreArchivage CentrePreArchivage = CentrePreArchivageRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid CentrePreArchivage Id:" + id));
        CentrePreArchivageRepository.delete(CentrePreArchivage);
        model.addAttribute("CentrePreArchivages", CentrePreArchivageRepository.findAll());
        return "centrePreArchivage/listCentrePreArchivages";
    }
    
    
    @GetMapping("edit/{id}")
    public String showCentrePreArchivageFormToUpdate(@PathVariable("id") long id, Model model) {
        CentrePreArchivage CentrePreArchivage = CentrePreArchivageRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid CentrePreArchivage Id:" + id));
        model.addAttribute("CentrePreArchivage", CentrePreArchivage);
        return "CentrePreArchivage/updateCentrePreArchivage";
    }
    @PostMapping("update/{id}")
    public String updateCentrePreArchivage(@PathVariable("id") long id, @Valid CentrePreArchivage CentrePreArchivage, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            CentrePreArchivage.setCodeCentre(id);
            return "CentrePreArchivage/updateCentrePreArchivage";
        }
        CentrePreArchivageRepository.save(CentrePreArchivage);
        model.addAttribute("CentrePreArchivages", CentrePreArchivageRepository.findAll());
        return "CentrePreArchivage/listCentrePreArchivages";
    }
	
}
