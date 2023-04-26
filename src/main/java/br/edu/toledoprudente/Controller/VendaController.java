package br.edu.toledoprudente.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.edu.toledoprudente.Entity.Cliente;
import br.edu.toledoprudente.Repository.ClienteRepository;

@Controller
@RequestMapping("/venda")
public class VendaController {

	@Autowired
	ClienteRepository repositorycliente;

	@GetMapping(path = "/getCliente/{nome}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getCliente(@PathVariable(value = "nome") String nome) {
		List<Cliente> lista = repositorycliente.findByName(nome);
		return new ResponseEntity<Object>(lista, HttpStatus.OK);
	}

	@GetMapping(path = "/index")
	public String index() {

		return "/venda/index";
	}
	

	@PostMapping(path = "/salvarCliente", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> salvar(@RequestBody Cliente cliente) {
		try {
			
			if (cliente.getId() == null)
				repositorycliente.save(cliente);
			else
				repositorycliente.update(cliente);
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		return new ResponseEntity<Object>(cliente, HttpStatus.OK);
	}

}
