package org.sid.web;

import java.util.ArrayList;

import javax.validation.Valid;

import org.sid.dao.EntrepriseRepository;
import org.sid.dao.TaxeRepository;
import org.sid.entities.Entreprise;
import org.sid.entities.Taxe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TaxeController {
	@Autowired
	private EntrepriseRepository entrepriseRepository;
	@Autowired
	private TaxeRepository taxeRepository;
	@RequestMapping(value="/entreprises",method = RequestMethod.GET)
	 
	public String index(Model model,
			  @RequestParam(name="page",defaultValue ="0")int page,
			  @RequestParam(name="size",defaultValue="5")int size,
			  @RequestParam(name="motCle",defaultValue="")String motCle ) {
		Page<Entreprise> pageEntreprises = entrepriseRepository
				.chercher( "%"+motCle+"%",PageRequest.of(page,size));
		model.addAttribute("listEntreprises", pageEntreprises.getContent());
		int[] pages=new int[pageEntreprises.getTotalPages()];
		model.addAttribute("pages", pages);
		model.addAttribute("currentPage",page);
		model.addAttribute("size",size);
		model.addAttribute("motCle",motCle);
		return "entreprises";
	}
	@RequestMapping(value="/formEntreprise")
	public String fromentreprise(Model model) {
		model.addAttribute("entreprise",new Entreprise());
		return "formEntreprise";
	}
	@RequestMapping(value="/saveEntreprise")
	public String save(Model model,	@Valid Entreprise e,
			BindingResult bindingResult) {
		if(bindingResult.hasErrors()) 
			  return "formEntreprise";
			
			   entrepriseRepository.save(e); 
			   return "redirect:/entreprises";	
	}

	@RequestMapping(value="/taxes")
	public String taxes(Model model,
			@RequestParam(name="code",defaultValue="-1")Long code) {

		model.addAttribute("entreprises", entrepriseRepository.findAll());
		if(code==-1) model.addAttribute("taxes",new ArrayList<Taxe>());
		else {
			Entreprise e = new Entreprise();
			e.setCode(code);
			model.addAttribute("taxes", taxeRepository.findByEntreprise(e));
			}
		
		return "taxes";
}
}
