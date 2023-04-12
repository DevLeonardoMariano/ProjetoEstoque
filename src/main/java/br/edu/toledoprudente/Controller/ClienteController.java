package br.edu.toledoprudente.Controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.toledoprudente.Entity.AppAuthority;
import br.edu.toledoprudente.Entity.Categoria;
import br.edu.toledoprudente.Entity.Cliente;
import br.edu.toledoprudente.Entity.Users;
import br.edu.toledoprudente.Repository.ClienteRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	
	@Autowired
	private ClienteRepository repository;
	
	//tag novo utilizado na URL
	@GetMapping("/novo")
	public String novo(ModelMap model) {
		Cliente cli = new Cliente();
		cli.setUsuario(new Users());
		model.addAttribute("cliente", cli);
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
			model.addAttribute("mensagem", "Exclusão efetuada");
			model.addAttribute("retorno", true);
		}
		catch (Exception e){
			model.addAttribute("mensagem", "Exclusão não pode ser efetuada");
			model.addAttribute("retorno", false);
			
		}
		model.addAttribute("lista", repository.findAll());
		return "/cliente/listar";
	}
	
	
//-------------------------------------- metodo salvar ------------------------------------------	
	@PostMapping("/salvar")
	public String salvar(@ModelAttribute("cliente") Cliente cli, ModelMap model) {
		try {
			
			
			Validator validator;
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			validator = factory.getValidator();
			Set<ConstraintViolation<Cliente>> constraintViolations =
			validator.validate( cli );
			String errors = "";

			for (ConstraintViolation<Cliente> constraintViolation : constraintViolations) {
				errors = errors + constraintViolation.getMessage() + ". "; }
			
			if(errors!="")
			{
			//tem erros
			model.addAttribute("cliente",cli);
			model.addAttribute("mensagem", errors);
			model.addAttribute("retorno", false);
			return "/cliente/index";
			}
			else {
				
				Users usu = cli.getUsuario();
				usu.setPassword(new BCryptPasswordEncoder().encode(usu.getPassword()));
				usu.setEnabled(true);
				usu.setAdmin(false);
				
				Set<AppAuthority> appAuthorities = new HashSet<AppAuthority>();
				AppAuthority app = new AppAuthority();
				if (usu.isAdmin())
					app.setAuthority("ADM");
				else
					app.setAuthority("USER");
				app.setUsername(usu.getUsername());
				appAuthorities.add(app);
				usu.setAppAuthorities(appAuthorities);

			}
			
			
			if(cli.getId()== null)
				repository.save(cli);
			
			else
				repository.update(cli);
			model.addAttribute("mensagem", "Salvo com sucesso");
			model.addAttribute("retorno", true);
			
		} catch (Exception e) {
			model.addAttribute("mensagem", "Erro ao Salvar" + e.getMessage());
			model.addAttribute("retorno", false);
		}
		
		return "/cliente/index";
	}
	
}
