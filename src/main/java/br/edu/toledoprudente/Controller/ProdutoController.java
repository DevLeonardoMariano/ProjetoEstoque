package br.edu.toledoprudente.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import br.edu.toledoprudente.Entity.Categoria;
import br.edu.toledoprudente.Entity.Produto;
import br.edu.toledoprudente.Repository.CategoriaRepository;
import br.edu.toledoprudente.Repository.ProdutoRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;

	@Autowired
	private CategoriaRepository repositorycategoria;

	// tag novo utilizado na URL
	@GetMapping("/novo")
	public String novo(ModelMap model) {
		model.addAttribute("produto", new Produto());
		return "/produto/index";
	}

//-------------------------------------- metodo listar ------------------------------------------	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("lista", repository.findAll());

		return "/produto/listar";
	}

//-------------------------------------- metodo pre alterar ------------------------------------------		
	@GetMapping("/prealterar")
	public String preAlterar(@RequestParam(name = "id") int id, ModelMap model) {

		model.addAttribute("produto", repository.findById(id));
		

		return "/produto/index";
	}

//-------------------------------------- metodo excluir ------------------------------------------		
	@GetMapping("/excluir")
	public String Excluir(@RequestParam int id, ModelMap model) {

		try {
			repository.delete(id);
			model.addAttribute("mensagem", "Exclusão efetuada");
			model.addAttribute("retorno", true);
		} catch (Exception e) {
			model.addAttribute("mensagem", "Exclusão não pode ser efetuada");
			model.addAttribute("retorno", false);

		}
		model.addAttribute("lista", repository.findAll());
		return "/produto/listar";
	}

//-------------------------------------- metodo salvar ------------------------------------------	
	@PostMapping("/salvar")
	public String salvar(@ModelAttribute("produto") Produto pro, ModelMap model,
			@RequestParam("file") MultipartFile file) {
		try {

			Validator validator;
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			validator = factory.getValidator();
			Set<ConstraintViolation<Produto>> constraintViolations = validator.validate(pro);
			String errors = "";

			for (ConstraintViolation<Produto> constraintViolation : constraintViolations) {
				errors = errors + constraintViolation.getMessage() + ". ";
			}

			// validar imagem
			if (file.isEmpty()) {
				errors = errors + "Selecionar uma imagem";
			}

			if (errors != "") {
				// tem erros
				model.addAttribute("produto", pro);
				model.addAttribute("mensagem", errors);
				model.addAttribute("retorno", false);
				return "/produto/index";
			} else {

				Random random = new Random();
				String nomeArquivo = random.nextInt() + file.getOriginalFilename();
				pro.setImagem(nomeArquivo);

				if (pro.getId() == null) {
					repository.save(pro);
				} else {
					repository.update(pro);
				}
				model.addAttribute("mensagem", "Salvo com sucesso");
				model.addAttribute("retorno", true);

				try {
					byte[] bytes = file.getBytes();
					Path path = Paths.get(
							System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image\\" + nomeArquivo);
					Files.write(path, bytes);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (Exception e) {
			model.addAttribute("mensagem", "Erro ao Salvar" + e.getMessage());
			model.addAttribute("retorno", false);
		}

		return "/produto/index";
	}

	/* metodo usado para retornar dados para selection */
	@ModelAttribute(name = "listacategoria")
	public List<Categoria> listaCategoria() {

		return repositorycategoria.findAll();
	}

	// -------------------------------------- metodo baixar------------------------------------------

	@ResponseBody
	@RequestMapping(value = "/getimagem/{nome}", method = RequestMethod.GET)
	public HttpEntity<byte[]> download(@PathVariable(value = "nome") String nome) throws IOException {
		byte[] arquivo = Files.readAllBytes(
				Paths.get(System.getProperty("user.dir") + "\\src\\main\\resources\\static\\image\\" + nome));
		HttpHeaders httpHeaders = new HttpHeaders();
		switch (nome.substring(nome.lastIndexOf(".") + 1).toUpperCase()) {
		case "JPG":
			httpHeaders.setContentType(MediaType.IMAGE_JPEG);
			break;
		case "GIF":
			httpHeaders.setContentType(MediaType.IMAGE_GIF);
			break;
		case "PNG":
			httpHeaders.setContentType(MediaType.IMAGE_PNG);
			break;
		default:
			break;
		}
		httpHeaders.setContentLength(arquivo.length);
		HttpEntity<byte[]> entity = new HttpEntity<byte[]>(arquivo, httpHeaders);
		return entity;
	}

}
