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
import com.sip.ams1.entities.LieuArchive;
import com.sip.ams1.repository.LieuArchiveRepository;
import com.sip.ams1.entities.StructureCentrale;
import com.sip.ams1.repository.DirectionRegionaleRepository;
@Controller
@RequestMapping("/directionRegionale/")
public class DirectionRegionaleController {

private final DirectionRegionaleRepository directionRegionaleRepository;
private final LieuArchiveRepository lieuArchiveRepository;
//private final LieuArchiveRepository lieuArchiveRepository1;
@Autowired
public DirectionRegionaleController(DirectionRegionaleRepository directionRegionaleRepository,
		LieuArchiveRepository lieuArchiveRepository, LieuArchiveRepository lieuArchiveRepository1 ) {
    this.directionRegionaleRepository = directionRegionaleRepository;
    this.lieuArchiveRepository = lieuArchiveRepository;
    //this.lieuArchiveRepository1 = lieuArchiveRepository1;
    
    
}

@GetMapping("list")
public String listdirectionRegionales(Model model) {
    model.addAttribute("directionRegionales", directionRegionaleRepository.findAll());
    return "directionRegionale/listdirectionRegionale";
}

/*@GetMapping("add")
public String showAdddirectionRegionaleForm(DirectionRegionale directionRegionale) {
    return "directionRegionale/adddirectionRegionale";
}*/

@GetMapping("add")
public String showAddStructureCentraleForm(DirectionRegionale directionRegionale, LieuArchive lieuArchive ,Model model) {
	
	model.addAttribute("lieuArchives", lieuArchiveRepository.findAll());
	//model.addAttribute("demandeVersements", demandeVersementRepository.findAll());
	//model.addAttribute("article", new Article());
    return "directionRegionale/adddirectionRegionale";
}

@PostMapping("add")
public String adddirectionRegionale(@Valid DirectionRegionale directionRegionale, BindingResult result, Model model
		,@RequestParam(name = "lieuArchiveId", required = false) Long li,@RequestParam(name = "lieuArchiveId", required = false) Long li1) {
    if (result.hasErrors()) {
        return "directionRegionale/adddirectionRegionale";
    }
    LieuArchive  lieuArchive = lieuArchiveRepository.findById(li)
            .orElseThrow(()-> new IllegalArgumentException("Invalid structureCnetrale Id:" + li));
	directionRegionale.setLieuArchive(lieuArchive);
	 LieuArchive  lieuArchive1 = lieuArchiveRepository.findById(li1)
	            .orElseThrow(()-> new IllegalArgumentException("Invalid structureCnetrale Id:" + li1));
	 
		directionRegionale.setLieuArchive(lieuArchive);  
		directionRegionale.setLieuArchive(lieuArchive1); 
    directionRegionaleRepository.save(directionRegionale);
    return "redirect:list";
}

@GetMapping("delete/{id}")
public String deletedirectionRegionale(@PathVariable("id") String id, Model model) {
    DirectionRegionale directionRegionale = directionRegionaleRepository.findById(id)
        .orElseThrow(()-> new IllegalArgumentException("Invalid directionRegionale Id:" + id));
    directionRegionaleRepository.delete(directionRegionale);
    model.addAttribute("directionRegionales", directionRegionaleRepository.findAll());
    return "directionRegionale/listdirectionRegionales";
}


@GetMapping("edit/{id}")
public String showdirectionRegionaleFormToUpdate(@PathVariable("id") String id, Model model) {
    DirectionRegionale directionRegionale = directionRegionaleRepository.findById(id)
        .orElseThrow(()->new IllegalArgumentException("Invalid directionRegionale Id:" + id));
    model.addAttribute("directionRegionale", directionRegionale);
    return "directionRegionale/updatedirectionRegionale";
}
@PostMapping("update/{id}")
public String updatedirectionRegionale(@PathVariable("id") String id, @Valid DirectionRegionale directionRegionale, BindingResult result,
    Model model) {
    if (result.hasErrors()) {
        directionRegionale.setCodeDirection(id);;
        return "directionRegionale/updatedirectionRegionale";
    }
    directionRegionaleRepository.save(directionRegionale);
    model.addAttribute("directionRegionales", directionRegionaleRepository.findAll());
    return "directionRegionale/listdirectionRegionales";
}
}
