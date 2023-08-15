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
import com.sip.ams1.entities.DemandeVersement;
import com.sip.ams1.entities.DirectionRegionale;
import com.sip.ams1.entities.LieuArchive;
import com.sip.ams1.entities.Nomenclature;
import com.sip.ams1.entities.StructureCentrale;
import com.sip.ams1.repository.DemandeVersementRepository;
import com.sip.ams1.repository.DirectionRegionaleRepository;
import com.sip.ams1.repository.NomenclatureRepository;
import com.sip.ams1.repository.LieuArchiveRepository;

@Controller
@RequestMapping("/demandeVersement/")
public class DemandeVersementController {
	private final DemandeVersementRepository demandeVersementRepository;
    private final NomenclatureRepository nomenclatureRepository;
    private final DirectionRegionaleRepository directionRegionaleRepository;
    private final LieuArchiveRepository lieuArchiveRepository;
  
   
   
	@Autowired
    public DemandeVersementController(DemandeVersementRepository demandeVersementRepository,NomenclatureRepository nomenclatureRepository
    	,DirectionRegionaleRepository directionRegionaleRepository,LieuArchiveRepository lieuArchiveRepository) {
        this.demandeVersementRepository = demandeVersementRepository;
        this.nomenclatureRepository = nomenclatureRepository;
        this.directionRegionaleRepository=directionRegionaleRepository;
        this.lieuArchiveRepository=lieuArchiveRepository;
        
    }
    
    @GetMapping("list")
    public String listdemandeVersements(Model model) {
        model.addAttribute("demandeVersements", demandeVersementRepository.findAll());
        return "demandeVersement/listdemandeVersement";
    }
    
    @GetMapping("add")
    public String showAddDemandeVersementForm(DemandeVersement demandeVersement ,DirectionRegionale directionRegionale, Model model,LieuArchive lieuArchive,
    		Nomenclature nomenclature) {
    	
    	model.addAttribute("nomenclatures", nomenclatureRepository.findAll());
    	model.addAttribute("directionRegionales", directionRegionaleRepository.findAll());
    	model.addAttribute("lieuArchives", lieuArchiveRepository.findAll());
    	
    	//model.addAttribute("article", new Article());
        return "demandeVersement/adddemandeVersement";
    }
    
    @PostMapping("add")
    public String adddemandeVersement(@Valid DemandeVersement demandeVersement, BindingResult result, Model model
    		,@RequestParam(name = "nomenclatureId", required = false) Long no,
    		@RequestParam(name = "directionRegionaleId", required = false) String di
    		,@RequestParam(name = "lieuArchiveId", required = false) Long li) {
        if (result.hasErrors()) {
            return "demandeVersement/adddemandeVersement";
        }
        Nomenclature nomenclature = nomenclatureRepository.findById(no)
                .orElseThrow(()-> new IllegalArgumentException("Invalid nomenclature Id:" + no));
    	demandeVersement.setNomenclature(nomenclature);
    	DirectionRegionale directionRegionale = directionRegionaleRepository.findById(di)
                .orElseThrow(()-> new IllegalArgumentException("Invalid structureCnetrale Id:" + di));
    	demandeVersement.setDirectionRegionale(directionRegionale);
    	LieuArchive  lieuArchive = lieuArchiveRepository.findById(li)
                .orElseThrow(()-> new IllegalArgumentException("Invalid structureCnetrale Id:" + li));
    	demandeVersement.setLieuArchive(lieuArchive);
        demandeVersementRepository.save(demandeVersement);
        return "redirect:list";
    }
    
    @GetMapping("delete/{id}")
public String deletedemandeVersement(@PathVariable("id") long id, Model model) {
        DemandeVersement demandeVersement = demandeVersementRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid demandeVersement Id:" + id));
        demandeVersementRepository.delete(demandeVersement);
        model.addAttribute("demandeVersements", demandeVersementRepository.findAll());
        return "demandeVersement/listdemandeVersements";
    }
    
    
    @GetMapping("edit/{id}")
    public String showdemandeVersementFormToUpdate(@PathVariable("id") long id, Model model) {
        DemandeVersement demandeVersement = demandeVersementRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid demandeVersement Id:" + id));
        model.addAttribute("demandeVersement", demandeVersement);
        return "demandeVersement/updatedemandeVersement";
    }
    @PostMapping("update/{id}")
    public String updatedemandeVersement(@PathVariable("id") long id, @Valid DemandeVersement demandeVersement, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            demandeVersement.setNumDem(id);;
            return "demandeVersement/updatedemandeVersement";
        }
        demandeVersementRepository.save(demandeVersement);
        model.addAttribute("demandeVersements", demandeVersementRepository.findAll());
        return "demandeVersement/listdemandeVersements";
    }
	
}
