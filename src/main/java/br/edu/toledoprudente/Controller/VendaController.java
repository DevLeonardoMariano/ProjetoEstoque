package br.edu.toledoprudente.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.toledoprudente.Entity.Categoria;
import br.edu.toledoprudente.Entity.Cliente;
import br.edu.toledoprudente.Entity.Produto;
import br.edu.toledoprudente.Repository.ClienteRepository;
import br.edu.toledoprudente.Repository.ProdutoRepository;

@Controller
@RequestMapping("/venda")
public class VendaController {


	
	@Autowired
	ProdutoRepository repositoryproduto;
	
	@Autowired
	ClienteRepository repositorycliente;

	@GetMapping(path = "/getProduto/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getProduto(@PathVariable(value = "nome") String nome) {
		List<Produto> lista = repositoryproduto.findByName(nome);
		return new ResponseEntity<Object>(lista, HttpStatus.OK);
	}

	@GetMapping(path = "/novo")
	public String index() {

		return "/venda/index";
	}
	
	
	/* metodo usado para retornar dados para selection */
	@ModelAttribute(name = "listaproduto")
	public List<Produto> listaProduto() {

		return repositoryproduto.findAll();
	}
	
	/* metodo usado para retornar dados para selection */
	@ModelAttribute(name = "listacliente")
	public List<Cliente> listaCliente() {

		return repositorycliente.findAll();
	}

	@PostMapping(path = "/salvarProduto", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> salvar(@RequestBody Produto produto) {
		try {
			
			if (produto.getId() == null)
				repositoryproduto.save(produto);
			else
				repositoryproduto.update(produto);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return new ResponseEntity<Object>(produto, HttpStatus.OK);
	}

}
