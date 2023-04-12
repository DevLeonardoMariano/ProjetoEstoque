package br.edu.toledoprudente.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class PrincipalController {

	@GetMapping("/")
	public String principal() {

		return "principal";
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
