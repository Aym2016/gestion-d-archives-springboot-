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

import com.sip.ams1.entities.CentrePreArchivage;
import com.sip.ams1.entities.LieuArchive;
import com.sip.ams1.entities.Nomenclature;
import com.sip.ams1.entities.SuiviDoc2emeAge;
import com.sip.ams1.repository.CentrePreArchivageRepository;
import com.sip.ams1.repository.NomenclatureRepository;
import com.sip.ams1.repository.SuiviDoc2emeAgeRepository;
@Controller
@RequestMapping("/suiviDoc2emeAge/")
public class SuiviDoc2emeAgeController {
	private final SuiviDoc2emeAgeRepository suiviDoc2emeAgeRepository;
	private final NomenclatureRepository nomenclatureRepository;
	private final CentrePreArchivageRepository centrePreArchivageRepository;
    @Autowired
    public SuiviDoc2emeAgeController(SuiviDoc2emeAgeRepository suiviDoc2emeAgeRepository,NomenclatureRepository nomenclatureRepository ,CentrePreArchivageRepository centrePreArchivageRepository) {
        this.suiviDoc2emeAgeRepository = suiviDoc2emeAgeRepository;
        this.nomenclatureRepository=nomenclatureRepository;
        this.centrePreArchivageRepository=centrePreArchivageRepository;
    }
    public SuiviDoc2emeAgeController() {
		this.suiviDoc2emeAgeRepository = null;
		this.nomenclatureRepository = null;
		this.centrePreArchivageRepository = null;
        
    }
    
    @GetMapping("list")
    public String listsuiviDoc2emeAges(Model model) {
        model.addAttribute("suiviDoc2emeAges", suiviDoc2emeAgeRepository.findAll());
        return "suiviDoc2emeAge/listsuiviDoc2emeAges";
    }
    
    @GetMapping("add")
    public String showAddsuiviDoc2emeAgeForm(SuiviDoc2emeAge suiviDoc2emeAge , Model model,Nomenclature nomenclature,CentrePreArchivage centrePreArchivage) {
    	model.addAttribute("nomenclatures",nomenclatureRepository.findAll());
    	model.addAttribute("centrePreArchivages",centrePreArchivageRepository.findAll());
        
    	return "suiviDoc2emeAge/addsuiviDoc2emeAge";
    }
    
    @PostMapping("add") 
    public String addsuiviDoc2emeAge(@Valid SuiviDoc2emeAge suiviDoc2emeAge, BindingResult result, Model model
    		,@RequestParam(name = "nomenclatureId", required = false) Long no , @RequestParam(name = "centrePreArchivageId", required = false) long ce) {
        if (result.hasErrors()) {
            return "suiviDoc2emeAge/addsuiviDoc2emeAge";
        }
        
        Nomenclature nomenclature = nomenclatureRepository.findById(no)
                .orElseThrow(()-> new IllegalArgumentException("Invalid nomenclature Id:" + no));
    	suiviDoc2emeAge.setNomenclature(nomenclature);
        CentrePreArchivage centrePreArchivage = centrePreArchivageRepository.findById(ce)
                .orElseThrow(()-> new IllegalArgumentException("Invalid centrePreArchivage Id:" + ce));
    	suiviDoc2emeAge.setCentrePreArchivage(centrePreArchivage);
        suiviDoc2emeAgeRepository.save(suiviDoc2emeAge);
        return "redirect:list";
    }
    
    @GetMapping("delete/{id}")
public String deletesuiviDoc2emeAge(@PathVariable("id") long id, Model model) {
        SuiviDoc2emeAge suiviDoc2emeAge = suiviDoc2emeAgeRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid suiviDoc2emeAge Id:" + id));
        suiviDoc2emeAgeRepository.delete(suiviDoc2emeAge);
        model.addAttribute("suiviDoc2emeAges", suiviDoc2emeAgeRepository.findAll());
        return "suiviDoc2emeAge/listsuiviDoc2emeAges";
    }
    
    
    @GetMapping("edit/{id}")
    public String showsuiviDoc2emeAgeFormToUpdate(@PathVariable("id") long id, Model model) {
        SuiviDoc2emeAge suiviDoc2emeAge = suiviDoc2emeAgeRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid suiviDoc2emeAge Id:" + id));
        model.addAttribute("suiviDoc2emeAge", suiviDoc2emeAge);
        return "suiviDoc2emeAge/updatesuiviDoc2emeAge";
    }
    @PostMapping("update/{id}")
    public String updatesuiviDoc2emeAge(@PathVariable("id") long id, @Valid SuiviDoc2emeAge suiviDoc2emeAge, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            suiviDoc2emeAge.setNumDoc(id);;
            return "suiviDoc2emeAge/updatesuiviDoc2emeAge";
        }
        suiviDoc2emeAgeRepository.save(suiviDoc2emeAge);
        model.addAttribute("suiviDoc2emeAges", suiviDoc2emeAgeRepository.findAll());
        return "suiviDoc2emeAge/listsuiviDoc2emeAges";
    }

}
