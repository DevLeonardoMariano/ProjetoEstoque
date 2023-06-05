package br.edu.toledoprudente.Controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import br.edu.toledoprudente.Entity.Cliente;
import br.edu.toledoprudente.Entity.Funcionario;
import br.edu.toledoprudente.Entity.Produto;
import br.edu.toledoprudente.Entity.ProdutoVendaItem;
import br.edu.toledoprudente.Entity.Produto_Venda;
import br.edu.toledoprudente.Entity.Venda;
import br.edu.toledoprudente.Entity.VendaItem;
import br.edu.toledoprudente.Repository.ClienteRepository;
import br.edu.toledoprudente.Repository.FuncionarioRepository;
import br.edu.toledoprudente.Repository.ProdutoRepository;
import br.edu.toledoprudente.Repository.Produto_VendaRepository;
import br.edu.toledoprudente.Repository.VendaRepository;

@Controller
@RequestMapping("/venda")
public class VendaController {

	@Autowired
	ProdutoRepository repositoryproduto;

	@Autowired
	ClienteRepository repositorycliente;

	@Autowired
	FuncionarioRepository repositoryfuncionario;

	@Autowired
	VendaRepository repositoryvenda;

	@Autowired
	Produto_VendaRepository repositoryprodutovenda;


	@GetMapping(path = "/novo")
	public String index() {

		return "/venda/index";
	}

	@GetMapping(path = "/getProduto/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getProduto(@PathVariable(value = "nome") String nome) {
		List<Produto> lista = repositoryproduto.findByName(nome);
		return new ResponseEntity<Object>(lista, HttpStatus.OK);
	}

	
	@ModelAttribute(name = "listaproduto")
	public List<Produto> listaProduto() {

		return repositoryproduto.findAll();
	}

	
	@ModelAttribute(name = "listacliente")
	public List<Cliente> listaCliente() {

		return repositorycliente.findAll();
	}

	
	@ModelAttribute(name = "listafuncionario")
	public List<Funcionario> listaFuncionario() {

		return repositoryfuncionario.findAll();
	}
	
	@GetMapping("/listardetalhe")
    public String listadetalhe(ModelMap model) {
        model.addAttribute("listadetalhe", repositoryprodutovenda.findAll());
        return "/venda/listardetalhe";
    }
	
	@GetMapping("/listar")
    public String listar(ModelMap model) {
        model.addAttribute("lista", repositoryvenda.findAll());
        return "/venda/listar";
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

	
	
	@PostMapping(path = "/salvar", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> salvar(@RequestBody VendaItem vendaItem) {
		try {
			Venda venda = new Venda();

			Cliente cliente = repositorycliente.findById(vendaItem.getIdcliente());
			venda.setCliente(cliente);

			venda.setDataVenda(LocalDate.now());

			Funcionario funcionario = repositoryfuncionario.findById(vendaItem.getIdfuncionaario());
			venda.setFuncionario(funcionario);

			List<ProdutoVendaItem> itemsVenda = vendaItem.getItens();

			double valor_total = 0;

			for (ProdutoVendaItem itemVenda : itemsVenda) {
				valor_total += itemVenda.getValor_unitario() * itemVenda.quantidade;
			}

			venda.setValorTotal(valor_total);

			repositoryvenda.save(venda);
			

			int id_venda = venda.getId();

			for (ProdutoVendaItem itemVenda : itemsVenda) {
				Produto_Venda itemproduto = new Produto_Venda();

				Produto produtoVenda = repositoryproduto.findById(itemVenda.getIdproduto());
				itemproduto.setProduto(produtoVenda);

				itemproduto.setQuantidade(itemVenda.getQuantidade());

				itemproduto.setValorTotal(itemVenda.getValor_total());

				itemproduto.setValorUnitario(itemVenda.getValor_unitario());

				itemproduto.setVenda(venda);

				repositoryprodutovenda.save(itemproduto);

				produtoVenda.setEstoque(produtoVenda.getEstoque() - itemVenda.quantidade);

				repositoryproduto.save(produtoVenda);
			}

		} catch (Exception e) {
			
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			
		}

		return new ResponseEntity<Object>(vendaItem, HttpStatus.OK);

	}
	
	

	

}
