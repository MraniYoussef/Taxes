package org.sid.web;

import java.util.List;

import org.sid.dao.EntrepriseRepository;
import org.sid.entities.Entreprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxeRestController {
	
	@Autowired
	private EntrepriseRepository entrepriseRpository;
	
	@RequestMapping("/listEntreprises")
	public Page<Entreprise> pageEntreprises(
			@RequestParam(name="motCle",defaultValue="")String motCle,
			@RequestParam(name="page",defaultValue="0")int page, 
			@RequestParam(name="size",defaultValue="5")int size){
		return entrepriseRpository.chercher("%"+motCle+"%", PageRequest.of(page, size));
	}
//	public List<Entreprise> list(){
//		return entrepriseRpository.findAll();
//	}
}
