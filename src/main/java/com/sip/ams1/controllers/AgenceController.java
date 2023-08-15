package com.sip.ams1.controllers;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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

import com.sip.ams1.entities.Agence;
import com.sip.ams1.entities.Provider;
import com.sip.ams1.entities.StructureCentrale;
import com.sip.ams1.repository.AgenceRepository;
//import com.sip.ams1.repository.ProviderRepository;
import com.sip.ams1.repository.StructureCentraleRepository;
@Controller
@RequestMapping("/agence/")
public class AgenceController {
	private final StructureCentraleRepository structureCentraleRepository;
	private final AgenceRepository agenceRepository;
    @Autowired
public AgenceController(AgenceRepository agenceRepository,StructureCentraleRepository structureCentraleRepository) {
        this.agenceRepository = agenceRepository;
        this.structureCentraleRepository = structureCentraleRepository;
    }
    
    @GetMapping("list")
    public String listagences(Model model) {
        model.addAttribute("agences", agenceRepository.findAll());
        return "agence/listagences";
    }
    
    
  /*  @GetMapping("list")
    public String listStructureCentrales(Model model) {
    	//model.addAttribute("articles", null);
        model.addAttribute("agences", agenceRepository.findAll());
        return "agence/listAgences";
    }*/
    @GetMapping("add")
    public String showAddAgenceForm(Agence agence, Model model,StructureCentrale structureCentrale) {
    	
    	model.addAttribute("structureCentrales", structureCentraleRepository.findAll());
    	//model.addAttribute("article", new Article());
        return "agence/addagence";
    }
    
   /* @GetMapping("add")
    public String showAddagenceForm(Agence agence) {
        return "agence/addagence";
    }*/
    
    @PostMapping("add")
    public String addagence(@Valid Agence agence, BindingResult result, Model model,@RequestParam(name = "structureCentraleId", required = false) Long p) {
        if (result.hasErrors()) {
            return "agence/addagence";
        }
        
        StructureCentrale structureCentrale = structureCentraleRepository.findById(p)
                .orElseThrow(()-> new IllegalArgumentException("Invalid structureCnetrale Id:" + p));
    	agence.setStructureCentrale(structureCentrale);
        agenceRepository.save(agence);
        return "redirect:list";
    }
    
    @GetMapping("delete/{id}")
public String deleteagence(@PathVariable("id") String id, Model model) {
        Agence agence = agenceRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid agence Id:" + id));
        agenceRepository.delete(agence);
        model.addAttribute("agences", agenceRepository.findAll());
        return "agence/listagences";
    }
    
    
    @GetMapping("edit/{id}")
    public String showagenceFormToUpdate(@PathVariable("id") String id, Model model) {
        Agence agence = agenceRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid agence Id:" + id));
        model.addAttribute("agence", agence);
        return "agence/updateagence";
    }
    @PostMapping("update/{id}")
    public String updateagence(@PathVariable("id") String id, @Valid Agence agence, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            agence.setCodeAgence(id);
            return "agence/updateagence";
        }
        agenceRepository.save(agence);
        model.addAttribute("agences", agenceRepository.findAll());
        return "agence/listagences";
    }


}
