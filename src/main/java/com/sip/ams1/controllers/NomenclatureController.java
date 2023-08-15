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
import org.springframework.web.bind.annotation.RequestParam;

import com.sip.ams1.entities.DirectionRegionale;
import com.sip.ams1.entities.Nomenclature;
import com.sip.ams1.repository.DirectionRegionaleRepository;
import com.sip.ams1.repository.NomenclatureRepository;

@Controller
@RequestMapping("/nomenclature/")
public class NomenclatureController {
	
	private final NomenclatureRepository nomenclatureRepository;
	private final DirectionRegionaleRepository directionRegionaleRepository;
    @Autowired
    public NomenclatureController(NomenclatureRepository nomenclatureRepository,DirectionRegionaleRepository 
    		directionRegionaleRepository) {
        this.nomenclatureRepository = nomenclatureRepository;
        this.directionRegionaleRepository=directionRegionaleRepository;
         
    }
    
    @GetMapping("list")
    public String listnomenclatures(Model model) {
        model.addAttribute("nomenclatures", nomenclatureRepository.findAll());
        return "nomenclature/listnomenclature";
    }
    
    @GetMapping("add")
    public String showAddnomenclatureForm(Nomenclature nomenclature, Model model, DirectionRegionale directionRegionale) {
    	model.addAttribute("directionRegionales", directionRegionaleRepository.findAll());
    	return "nomenclature/addnomenclature";
    }
    
    @PostMapping("add")
    public String addnomenclature(@Valid Nomenclature nomenclature, BindingResult result, Model model,
    		@RequestParam(name = "directionRegionaleId", required = false) String di) {
        if (result.hasErrors()) {
            return "nomenclature/addnomenclature";
        }
        DirectionRegionale directionRegionale = directionRegionaleRepository.findById(di)
                .orElseThrow(()-> new IllegalArgumentException("Invalid structureCnetrale Id:" + di));
    	nomenclature.setDirectionRegionale(directionRegionale);
        nomenclatureRepository.save(nomenclature);
        return "redirect:list";
    }
    
    @GetMapping("delete/{id}")
public String deletenomenclature(@PathVariable("id") long id, Model model) {
        Nomenclature nomenclature = nomenclatureRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid nomenclature Id:" + id));
        nomenclatureRepository.delete(nomenclature);
        model.addAttribute("nomenclatures", nomenclatureRepository.findAll());
        return "nomenclature/listnomenclatures";
    }
    
    
    @GetMapping("edit/{id}")
    public String shownomenclatureFormToUpdate(@PathVariable("id") long id, Model model) {
        Nomenclature nomenclature = nomenclatureRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid nomenclature Id:" + id));
        model.addAttribute("nomenclature", nomenclature);
        return "nomenclature/updatenomenclature";
    }
    @PostMapping("update/{id}")
    public String updatenomenclature(@PathVariable("id") long id, @Valid Nomenclature nomenclature, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            nomenclature.setCodeNomenclature(id);;
            return "nomenclature/updatenomenclature";
        }
        nomenclatureRepository.save(nomenclature);
        model.addAttribute("nomenclatures", nomenclatureRepository.findAll());
        return "nomenclature/listnomenclatures";
    }	

}
