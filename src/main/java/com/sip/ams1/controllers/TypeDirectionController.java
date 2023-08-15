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

import com.sip.ams1.entities.DirectionRegionale;
import com.sip.ams1.entities.TypeDirection;
import com.sip.ams1.repository.DirectionRegionaleRepository;
import com.sip.ams1.repository.RoleRepository;
import com.sip.ams1.repository.TypeDirectionRepository;

@Controller
@RequestMapping("/typeDirection/")
public class TypeDirectionController {
	private final TypeDirectionRepository typeDirectionRepository;
	private final DirectionRegionaleRepository directionRegionaleRepository;
	private final RoleRepository roleRepository;
    @Autowired
    public TypeDirectionController(TypeDirectionRepository typeDirectionRepository,DirectionRegionaleRepository directionRegionaleRepository
    		, RoleRepository roleRepository ) {
        this.typeDirectionRepository = typeDirectionRepository;
        this.directionRegionaleRepository=directionRegionaleRepository;
        this.roleRepository=roleRepository;
    }
    
    @GetMapping("list")
    public String listtypeDirections(Model model) {
        model.addAttribute("typeDirections", typeDirectionRepository.findAll());
        return "typeDirection/listtypeDirections";
    }
    
    @GetMapping("add")
    public String showAddtypeDirectionForm(TypeDirection typeDirection,Model model,DirectionRegionale directionRegionale) {
       model.addAttribute("directionRegionales", directionRegionale);
    	return "typeDirection/addtypeDirection";
    }
    
    @PostMapping("add")
    public String addtypeDirection(@Valid TypeDirection typeDirection, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "typeDirection/addtypeDirection";
        }
        typeDirectionRepository.save(typeDirection);
        return "redirect:list";
    }
    
    @GetMapping("delete/{id}")
public String deletetypeDirection(@PathVariable("id") long id, Model model) {
        TypeDirection typeDirection = typeDirectionRepository.findById(id)
            .orElseThrow(()-> new IllegalArgumentException("Invalid typeDirection Id:" + id));
        typeDirectionRepository.delete(typeDirection);
        model.addAttribute("typeDirections", typeDirectionRepository.findAll());
        return "typeDirection/listtypeDirections";
    }
    
    
    @GetMapping("edit/{id}")
    public String showtypeDirectionFormToUpdate(@PathVariable("id") long id, Model model) {
        TypeDirection typeDirection = typeDirectionRepository.findById(id)
            .orElseThrow(()->new IllegalArgumentException("Invalid typeDirection Id:" + id));
        model.addAttribute("typeDirection", typeDirection);
        return "typeDirection/updatetypeDirection";
    }
    @PostMapping("update/{id}")
    public String updatetypeDirection(@PathVariable("id") String id, @Valid TypeDirection typeDirection, BindingResult result,
        Model model) {
        if (result.hasErrors()) {
            typeDirection.setCodeTypeDir(id);
            return "typeDirection/updatetypeDirection";
        }
        typeDirectionRepository.save(typeDirection);
        model.addAttribute("typeDirections", typeDirectionRepository.findAll());
        return "typeDirection/listtypeDirections";
    }
	
}
