package br.edu.toledoprudente.Controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.edu.toledoprudente.Entity.AppAuthority;
import br.edu.toledoprudente.Entity.Funcionario;
import br.edu.toledoprudente.Entity.Users;
import br.edu.toledoprudente.Repository.FuncionarioRepository;
import br.edu.toledoprudente.Repository.UsersRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@Controller
@RequestMapping("/funcionario")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository repository;
	
	@Autowired
	private UsersRepository repositoryusers;
	
	@GetMapping("/novo")
	public String novo(ModelMap model) {
		Funcionario fun = new Funcionario();
		fun.setUsuario(new Users());
		model.addAttribute("funcionario", fun);
		return "/funcionario/index";
	}
	
	//-------------------------------------- metodo listar ------------------------------------------	
		@GetMapping("/listar")
		public String listar(ModelMap model) {
			model.addAttribute("lista",repository.findAll());
			
			return "/funcionario/listar";
		}
		
	//-------------------------------------- metodo pre alterar ------------------------------------------		
		@GetMapping("/prealterar")
		public String preAlterar(@RequestParam(name="id") int id,ModelMap model) {
			
			model.addAttribute("funcionario", repository.findById(id));
			
			return "/funcionario/index";
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
			return "/funcionario/listar";
		}
		
		//-------------------------------------- metodo salvar ------------------------------------------	
		@PostMapping("/salvar")
		public String salvar(@ModelAttribute("funcionario") Funcionario fun, ModelMap model) {
			try {
				
				
				Validator validator;
				ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
				validator = factory.getValidator();
				Set<ConstraintViolation<Funcionario>> constraintViolations =
				validator.validate( fun );
				String errors = "";

				for (ConstraintViolation<Funcionario> constraintViolation : constraintViolations) {
					errors = errors + constraintViolation.getMessage() + ". "; }
				
				if(errors!="")
				{
				//tem erros
				model.addAttribute("funcionario",fun);
				model.addAttribute("mensagem", errors);
				model.addAttribute("retorno", false);
				return "/funcionario/index";
				}
				else {
					
					String username = fun.getUsuario().getUsername();
		            Users existingUser = repositoryusers.findByUserName(username);

		            if (existingUser != null) {
		                model.addAttribute("cliente", fun);
		                model.addAttribute("mensagem", "Já existe um usuário com esse nome");
		                model.addAttribute("retorno", false);
		                return "/funcionario/index";
		            }
					
					Users usu = fun.getUsuario();
					String senha = "{bcrypt}" + new BCryptPasswordEncoder().encode(usu.getPassword());
					usu.setPassword(senha);
					usu.setEnabled(false);
					usu.setAdmin(true);
					
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
				
				
				if(fun.getId()== null)
					repository.save(fun);
				
				else
					repository.update(fun);
				model.addAttribute("mensagem", "Salvo com sucesso");
				model.addAttribute("retorno", true);
				
			} catch (Exception e) {
				model.addAttribute("mensagem", "Erro ao Salvar" + e.getMessage());
				model.addAttribute("retorno", false);
			}
			
			return "/funcionario/index";
		}
		

}
