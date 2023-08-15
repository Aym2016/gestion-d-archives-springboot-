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

import com.sip.ams1.entities.DemandeVersement;
import com.sip.ams1.entities.DirectionRegionale;
import com.sip.ams1.entities.LieuArchive;
import com.sip.ams1.entities.StructureCentrale;
import com.sip.ams1.repository.StructureCentraleRepository;
import com.sip.ams1.repository.DemandeVersementRepository;
import com.sip.ams1.repository.DirectionRegionaleRepository;
import com.sip.ams1.repository.LieuArchiveRepository;
@Controller
@RequestMapping("/structureCentrale/")
public class StructureCentraleController {
	private final StructureCentraleRepository structureCentraleRepository;

	private final DirectionRegionaleRepository directionRegionaleRepository;
    @Autowired
    public StructureCentraleController(StructureCentraleRepository structureCentraleRepository
    ,DirectionRegionaleRepository directionRegionaleRepository) {
        this.structureCentraleRepository = structureCentraleRepository;
        //this.lieuArchiveRepository = lieuArchiveRepository;
        this.directionRegionaleRepository=directionRegionaleRepository;
    }
    
    @GetMapping("list")
    public String liststructureCentrales(Model model) {
        model.addAttribute("structureCentrales", structureCentraleRepository.findAll());
        return "structureCentrale/liststructureCentrale";
    }
    
    /*@GetMapping("add")
    public String showAddstructureCentraleForm(StructureCentrale structureCentrale) {
        return "structureCentrale/addstructureCentrale";
    }*/
    @GetMapping("add")
    public String showAddStructureCentraleForm(StructureCentrale structureCentrale, Model model,DirectionRegionale directionRegionale) {
    	
    	//model.addAttribute("lieuArchives", lieuArchiveRepository.findAll());
    	model.addAttribute("directionRegionales", directionRegionaleRepository.findAll());
    	//model.addAttribute("article", new Article());
        return "structureCentrale/addStructureCentrale";
    }
    
    @PostMapping("add")
    public String addstructureCentrale(@Valid StructureCentrale structureCentrale, BindingResult result, Model model,
    		@RequestParam(name = "directionRegionaleId", required = false) String di
    		) {
        if (result.hasErrors()) {
            return "structureCentrale/addstructureCentrale";
        }
        DirectionRegionale directionRegionale = directionRegionaleRepository.findById(di)
                .orElseThrow(()-> new IllegalArgumentException("Invalid directionRegionale Id:" + di));
        structureCentrale.setDirectionRegionale(directionRegionale);
        structureCentraleRepository.save(structureCentrale);
        return "redirect:list";
    }
    
    @GetMapping("delete/{id}")
public String deletestructureCentrale(@PathVariable("id") long id, Model model) {
        StructureCentrale structureCentrale = structureCentraleRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid structureCentrale Id:" + id));
        structureCentraleRepository.delete(structureCentrale);
        model.addAttribute("structureCentrales", structureCentraleRepository.findAll());
        return "structureCentrale/liststructureCentrale";
    }
    
    
    @GetMapping("edit/{id}")
    public String showstructureCentraleFormToUpdate(@PathVariable("id") long id, Model model) {
        StructureCentrale structureCentrale = structureCentraleRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid structureCentrale Id:" + id));
        model.addAttribute("structureCentrale", structureCentrale);
        return "structureCentrale/updatestructureCentrale";
    }
    @PostMapping("update/{id}")
    public String updatestructureCentrale(@PathVariable("id") long id, @Valid StructureCentrale structureCentrale, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            structureCentrale.setCodeStructure(id);;
            return "structureCentrale/updatestructureCentrale";
        }
        structureCentraleRepository.save(structureCentrale);
        model.addAttribute("structureCentrales", structureCentraleRepository.findAll());
        return "structureCentrale/liststructureCentrales";
    }
	
	
}
