package org.sid;

import java.util.Date;

import org.sid.dao.EntrepriseRepository;
import org.sid.dao.TaxeRepository;
import org.sid.entities.Entreprise;
import org.sid.entities.IR;
import org.sid.entities.TVA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaxesApplication implements CommandLineRunner {
	@Autowired
	private EntrepriseRepository entrepriseRpository;
	@Autowired
	private TaxeRepository taxeRepository;
	public static void main(String[] args) {
		SpringApplication.run(TaxesApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Entreprise e1=entrepriseRpository.save(new Entreprise("R1","r1@gmail.com","SARL"));
		Entreprise e2=entrepriseRpository.save(new Entreprise("R2","r2@gmail.com","SARL"));
		
		taxeRepository.save(new TVA("TVA habitation",new Date(),900,e1));
		taxeRepository.save(new TVA("TVA voiture",new Date(),400,e1));
		taxeRepository.save(new IR("IR 2021",new Date(),4500,e1));
		taxeRepository.save(new TVA("TVA habitation",new Date(),400,e2));
		
	}

}
