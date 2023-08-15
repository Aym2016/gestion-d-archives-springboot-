package com.sip.ams1.controllers;

import java.util.Date;

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
import com.sip.ams1.entities.LieuArchive;
import com.sip.ams1.entities.Nomenclature;
import com.sip.ams1.entities.StructureCentrale;
import com.sip.ams1.entities.SuiviDoc1ereAge;
import com.sip.ams1.entities.SuiviDoc2emeAge;
import com.sip.ams1.repository.DirectionRegionaleRepository;
import com.sip.ams1.repository.NomenclatureRepository;
import com.sip.ams1.repository.SuiviDoc1ereAgeRepository;
import com.sip.ams1.repository.SuiviDoc2emeAgeRepository;
import com.sip.ams1.repository.TriRepository;
@Controller
@RequestMapping("/suiviDoc1ereAge/")
public class SuiviDoc1ereAgeController {
	private final SuiviDoc1ereAgeRepository suiviDoc1ereAgeRepository;
	private final SuiviDoc2emeAgeRepository suiviDoc2emeAgeRepository;
	private final DirectionRegionaleRepository directionRegionaleRepository;
	private final NomenclatureRepository nomenclatureRepository;
	
    @Autowired
    public SuiviDoc1ereAgeController(SuiviDoc1ereAgeRepository suiviDoc1ereAgeRepository,DirectionRegionaleRepository directionRegionaleRepository
    		,NomenclatureRepository nomenclatureRepository,SuiviDoc2emeAgeRepository suiviDoc2emeAgeRepository) {
        this.suiviDoc1ereAgeRepository = suiviDoc1ereAgeRepository;
        this.nomenclatureRepository=nomenclatureRepository;
        this.directionRegionaleRepository=directionRegionaleRepository;
        this.suiviDoc2emeAgeRepository=suiviDoc2emeAgeRepository;
    }
    
    /*@GetMapping("list")
    public String listsuiviDoc1ereAges(Model model) {
        model.addAttribute("suiviDoc1ereAges", suiviDoc1ereAgeRepository.findAll());
        return "suiviDoc1ereAge/listsuiviDoc1ereAges";
    }*/
    
    
    
    @GetMapping("list")
    public String listsuiviDoc1ereAges1(Model model) {
        model.addAttribute("suiviDoc1ereAges", TriRepository.queryAll());
        return "suiviDoc1ereAge/listsuiviDoc1ereAges";
    }
    
    
   /* @GetMapping("add")
    public String showAddsuiviDoc1ereAgeForm(SuiviDoc1ereAge suiviDoc1ereAge) {
        return "suiviDoc1ereAge/addsuiviDoc1ereAge";
    }*/
    
    @GetMapping("add")
    public String showAddsuiviDoc1ereAgeForm(SuiviDoc1ereAge suiviDoc1ereAge,Model model,NomenclatureRepository nomenclature,DirectionRegionale directionRegionale) {
    	
    	//model.addAttribute("lieuArchives", lieuArchiveRepository.findAll());
    	model.addAttribute("nomenclatures", nomenclatureRepository.findAll());
    	model.addAttribute("directionRegionales", directionRegionaleRepository.findAll());
    	
    	//model.addAttribute("article", new Article());
        return "suiviDoc1ereAge/addsuiviDoc1ereAge";
    }
    
    @PostMapping("add")
    public String addsuiviDoc1ereAge(@Valid SuiviDoc1ereAge suiviDoc1ereAge, BindingResult result, Model model,
    		@RequestParam(name = "directionRegionaleId", required = false) String di
    		,@RequestParam(name = "nomenclatureId", required = false) long no) {
        if (result.hasErrors()) {
            return "suiviDoc1ereAge/addsuiviDoc1ereAge";
        }
        Nomenclature nomenclature = nomenclatureRepository.findById(no)
                .orElseThrow(()-> new IllegalArgumentException("Invalid nomenclature Id:" + no));
        suiviDoc1ereAge.setNomenclature(nomenclature);
        
        DirectionRegionale directionRegionale = directionRegionaleRepository.findById(di)
                .orElseThrow(()-> new IllegalArgumentException("Invalid directionRegionale Id:" + di));
        suiviDoc1ereAge.setDirectionRegionale(directionRegionale);
        suiviDoc1ereAgeRepository.save(suiviDoc1ereAge);
        return "redirect:list";
    }
    
    @PostMapping("add/{id}") @GetMapping("delete/{id}")
    public String traiterDocument(@PathVariable("id") long id ,@Valid SuiviDoc1ereAge Doc1, BindingResult result, Model model,
    		@RequestParam(name = "directionRegionaleId", required = false) String di
    		,@RequestParam(name = "nomenclatureId", required = false) long no) {
    	SuiviDoc2emeAge doc2=new SuiviDoc2emeAge();  
    	doc2.setChapitreComptable(Doc1.getChapitreComptable());
    	doc2.getCentrePreArchivage().setCodeCentre(7);
    	//doc2.setCentrePreArchivage(doc2.getCentrePreArchivage());
    	doc2.setDateCreation(Doc1.getDateCreation()); 
    	doc2.setDateEntree(Doc1.getDateEntree());
    	doc2.setNbrPages(Doc1.getNbrPages());
    	doc2.setNomenclature(Doc1.getNomenclature());
    	doc2.setNumDoc(Doc1.getNumDoc()); 
    	SuiviDoc2emeAgeController s2= new SuiviDoc2emeAgeController();
    	s2.addsuiviDoc2emeAge(doc2, result, model,no,7 );
    	Doc1= suiviDoc1ereAgeRepository.findById(id)
    	            .orElseThrow(()-> new IllegalArgumentException("Invalid suiviDoc1ereAge Id:" + id));
    	        suiviDoc1ereAgeRepository.delete(Doc1);
    	
    	
    	
    	   
        return "redirect:list";
    }
    
    
    @GetMapping("delete/{id}")
public String deletesuiviDoc1ereAge(@PathVariable("id") long id, Model model) {
        SuiviDoc1ereAge suiviDoc1ereAge = suiviDoc1ereAgeRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid suiviDoc1ereAge Id:" + id));
        suiviDoc1ereAgeRepository.delete(suiviDoc1ereAge);
        model.addAttribute("suiviDoc1ereAges", suiviDoc1ereAgeRepository.findAll());
        return "suiviDoc1ereAge/listsuiviDoc1ereAges";
    }
    
    
    @GetMapping("edit/{id}")
    public String showsuiviDoc1ereAgeFormToUpdate(@PathVariable("id") long id, Model model) {
        SuiviDoc1ereAge suiviDoc1ereAge = suiviDoc1ereAgeRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid suiviDoc1ereAge Id:" + id));
        model.addAttribute("suiviDoc1ereAge", suiviDoc1ereAge);
        return "suiviDoc1ereAge/updatesuiviDoc1ereAge";
    }
    @PostMapping("update/{id}")
    public String updatesuiviDoc1ereAge(@PathVariable("id") long id, @Valid SuiviDoc1ereAge suiviDoc1ereAge, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            suiviDoc1ereAge.setNumDoc(id);
            return "suiviDoc1ereAge/updatesuiviDoc1ereAge";
        }
        suiviDoc1ereAgeRepository.save(suiviDoc1ereAge);
        model.addAttribute("suiviDoc1ereAges", suiviDoc1ereAgeRepository.findAll());
        return "suiviDoc1ereAge/listsuiviDoc1ereAge";
    }
}
