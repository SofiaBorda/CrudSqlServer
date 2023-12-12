package com.alumnosvista2.alumnosvista2.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alumnosvista2.alumnosvista2.interfazService.IAlumnoInterfaz;
import com.alumnosvista2.alumnosvista2.model.Alumno;

@Controller
@RequestMapping
public class AlumnoController {
	@Autowired
	private IAlumnoInterfaz service;
	
	@GetMapping("/")
	public String inicio(Model model){
		List <Alumno> alumno = service.listar();
		model.addAttribute("alumnos",alumno);
		return "index";
	}
	
	@GetMapping("/listar")
	public String listar(Model model){
		List <Alumno> alumno = service.listar();
		model.addAttribute("alumnos",alumno);
		return "index";
	}
	@GetMapping("/agregar")
	public String add(Model model) {
		model.addAttribute("alumno",new Alumno());
		return "form";
	}
	@GetMapping("/del/{id}")
	public String del(Model model,@PathVariable int id) {
		service.delete(id);
		return "redirect:/listar";
	}
	
	@PostMapping("/save")
	public String save (@Validated Alumno p,Model model) {
		service.save(p);
		List<Alumno>alumnos=service.listar();
		model.addAttribute("alumnos",alumnos);
		return "form";
	}
	@GetMapping("/editar/{id}")
    public String edit(@PathVariable int id, Model model) {
        Optional<Alumno> alumno = service.listar(id);
        if (alumno.isPresent()) {
            model.addAttribute("alumno", alumno.get());
        }
        return "form";
    }
}

