package br.edu.toledoprudente.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.edu.toledoprudente.Entity.Categoria;
import br.edu.toledoprudente.Entity.Funcionario;
import br.edu.toledoprudente.Entity.Produto;
import br.edu.toledoprudente.Repository.CategoriaRepository;
import br.edu.toledoprudente.Repository.FuncionarioRepository;
import br.edu.toledoprudente.Repository.ProdutoRepository;

@Component
public class StringParaFuncionarioConverter implements Converter<String, Funcionario> {

	@Autowired
	private FuncionarioRepository repository;

	@Override
	public Funcionario convert(String idTexto) {

		if (idTexto.isEmpty())

			return null;

		return repository.findById(Integer.parseInt(idTexto));
	}
}
