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
import com.sip.ams1.entities.DemandeConsultation;
import com.sip.ams1.entities.StructureCentrale;
import com.sip.ams1.repository.DemandeConsultationRepository;
import com.sip.ams1.repository.AgenceRepository;
import com.sip.ams1.repository.StructureCentraleRepository;
@Controller
@RequestMapping("/demandeConsultation/")
public class DemandeConsultationController {

	private final DemandeConsultationRepository demandeConsultationRepository;// = null;
	private final AgenceRepository agenceRepository;
	private final StructureCentraleRepository structureCentraleRepository;
    @Autowired
    public DemandeConsultationController(DemandeConsultationRepository demandeConsultationRepository,AgenceRepository agenceRepository,StructureCentraleRepository structureCentraleRepository) {
    	
        this.demandeConsultationRepository = demandeConsultationRepository;
        this.agenceRepository=agenceRepository;
        this.structureCentraleRepository=structureCentraleRepository;
    
    }
    
    @GetMapping("list")
    public String listdemandeConsultations(Model model) {
        model.addAttribute("demandeConsultations", demandeConsultationRepository.findAll());
        return "demandeConsultation/listdemandeConsultation";
    }
    
   /* @GetMapping("add")
    public String showAdddemandeConsultationForm(DemandeConsultation demandeConsultation) {
        return "demandeConsultation/adddemandeConsultation";
    }*/
    
    @GetMapping("add")
    public String showAddDemandeConsultationForm(DemandeConsultation demandeConsultation , Model model,StructureCentrale structureCentrale, Agence agence) {
    	
    	model.addAttribute("agences", agenceRepository.findAll());
    	model.addAttribute("structureCentrales", structureCentraleRepository.findAll());
    	//model.addAttribute("article", new Article());
        return "demandeConsultation/adddemandeConsultation";
    }
    
    @PostMapping("add")
    public String adddemandeConsultation(@Valid DemandeConsultation demandeConsultation, BindingResult result, Model model, 
    		@RequestParam(name = "structureCentraleId", required = false) Long st ,@RequestParam(name = "agenceId", required = false) String ag) {
        if (result.hasErrors()) {
            return "demandeConsultation/adddemandeConsultation";
        }
        Agence agence = agenceRepository.findById(ag)
                .orElseThrow(()-> new IllegalArgumentException("Invalid agence Id:" + ag));
    	demandeConsultation.setAgence(agence);
    	StructureCentrale structureCentrale = structureCentraleRepository.findById(st)
                .orElseThrow(()-> new IllegalArgumentException("Invalid structureCentrale Id:" + st));
    	demandeConsultation.setStructureCentrale(structureCentrale);
        
    	
        
    	demandeConsultationRepository.save(demandeConsultation);
        return "redirect:list";
    }
    
    @GetMapping("delete/{id}")
public String deletedemandeConsultation(@PathVariable("id") long id, Model model) {
        DemandeConsultation demandeConsultation = demandeConsultationRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid demandeConsultation Id:" + id));
        demandeConsultationRepository.delete(demandeConsultation);
        model.addAttribute("demandeConsultations", demandeConsultationRepository.findAll());
        return "demandeConsultation/listdemandeConsultation";
    }
    
    
    @GetMapping("edit/{id}")
    public String showdemandeConsultationFormToUpdate(@PathVariable("id") long id, Model model) {
        DemandeConsultation demandeConsultation = demandeConsultationRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid demandeConsultation Id:" + id));
        model.addAttribute("demandeConsultation", demandeConsultation);
        return "demandeConsultation/updatedemandeConsultation";
    }
    @PostMapping("update/{id}")
    public String updatedemandeConsultation(@PathVariable("id") long id, @Valid DemandeConsultation demandeConsultation, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            demandeConsultation.setNumDem(id);
            return "demandeConsultation/updatedemandeConsultation";
        }
        demandeConsultationRepository.save(demandeConsultation);
        model.addAttribute("demandeConsultations", demandeConsultationRepository.findAll());
        return "demandeConsultation/listdemandeConsultation";
    }	

}
