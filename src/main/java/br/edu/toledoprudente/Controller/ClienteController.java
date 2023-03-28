package br.edu.toledoprudente.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.toledoprudente.Entity.Cliente;
import br.edu.toledoprudente.Repository.ClienteRepository;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	
	@Autowired
	private ClienteRepository repository;
	
	//tag novo utilizado na URL
	@GetMapping("/novo")
	public String novo(ModelMap model) {
		model.addAttribute("cliente", new Cliente());
		return "/cliente/index";
	}
	
//-------------------------------------- metodo listar ------------------------------------------	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("lista",repository.findAll());
		
		return "/cliente/listar";
	}
	
//-------------------------------------- metodo pre alterar ------------------------------------------		
	@GetMapping("/prealterar")
	public String preAlterar(@RequestParam(name="id") int id,ModelMap model) {
		
		model.addAttribute("cliente", repository.findById(id));
		
		return "/cliente/index";
	}

	
//-------------------------------------- metodo excluir ------------------------------------------		
	@GetMapping("/excluir")
	public String Excluir(@RequestParam int id, ModelMap model) {
		
		try {
			repository.delete(id);
			model.addAttribute("mensagem", "Exlusão efetuada");
			model.addAttribute("retorno", true);
		}
		catch (Exception e){
			model.addAttribute("mensagem", "Exlusão não pode ser efetuada");
			model.addAttribute("retorno", false);
			
		}
		model.addAttribute("lista", repository.findAll());
		return "/cliente/listar";
	}
	
	
//-------------------------------------- metodo salvar ------------------------------------------	
	@PostMapping("/salvar")
	public String salvar(@ModelAttribute("cliente") Cliente cat, ModelMap model) {
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
		
		return "/cliente/index";
	}
	
}
