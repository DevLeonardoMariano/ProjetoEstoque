package br.edu.toledoprudente.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.toledoprudente.Entity.Categoria;
import br.edu.toledoprudente.Repository.CategoriaRepository;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository repository;
	
	//tag novo utilizado na URL
	@GetMapping("/novo")
	public String novo(ModelMap model) {
		model.addAttribute("categoria", new Categoria());
		return "/categoria/index";
	}
	
//-------------------------------------- metodo salvar ------------------------------------------	
	@PostMapping("/salvar")
	public String salvar(@ModelAttribute("categoria") Categoria cat, ModelMap model) {
		try {
			
			if(cat.getId()== null)
				repository.save(cat);
			
			else
				repository.update(cat);
			model.addAttribute("mensagem", "Salvo com sucesso");
			model.addAttribute("retorno", true);
			
		} catch (Exception e) {
			model.addAttribute("mensagem", "Erro ao Salvar" + e.getMessage());
			model.addAttribute("retorno", false);
		}
		
		return "/categoria/index";
	}
}
