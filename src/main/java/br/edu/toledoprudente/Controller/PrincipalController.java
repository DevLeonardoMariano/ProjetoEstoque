package br.edu.toledoprudente.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.toledoprudente.Entity.Cliente;
import br.edu.toledoprudente.Repository.UsersRepository;

@Controller
@RequestMapping("/")
public class PrincipalController {

	@Autowired
	UsersRepository Repository;
	
	@GetMapping("/")
	public String principal(ModelMap model) {
		//model.addAttribute("nomeusuario", Repository.getUsuarioLogado().getNome());
		return "principal";
	}
	
	@GetMapping("/cadastro")
	public String cadastro(ModelMap model) {
		model.addAttribute("cliente", new Cliente());
		return "/cadastro";
	}

	@GetMapping("/login")
	public String login() {

		return "/login";
	}

	@GetMapping("/login-error")
	public String loginError(ModelMap model) {
		model.addAttribute("mensagem", "Dados inválidos");
		return "/login";
	}

}
