package br.edu.toledoprudente.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.edu.toledoprudente.Entity.Categoria;
import br.edu.toledoprudente.Entity.Cliente;
import br.edu.toledoprudente.Entity.Produto;
import br.edu.toledoprudente.Repository.CategoriaRepository;
import br.edu.toledoprudente.Repository.ClienteRepository;
import br.edu.toledoprudente.Repository.ProdutoRepository;

@Component
public class StringParaClienteConverter implements Converter<String, Cliente> {

	@Autowired
	private ClienteRepository repository;

	@Override
	public Cliente convert(String idTexto) {

		if (idTexto.isEmpty())

			return null;

		return repository.findById(Integer.parseInt(idTexto));
	}
}
