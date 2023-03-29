package br.edu.toledoprudente.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.edu.toledoprudente.Entity.Categoria;
import br.edu.toledoprudente.Repository.CategoriaRepository;

@Component
public class StringParaCategoriaConverter implements Converter<String, Categoria> {

	@Autowired
	private CategoriaRepository repository;

	@Override
	public Categoria convert(String idTexto) {

		if (idTexto.isEmpty())

			return null;

		return repository.findById(Integer.parseInt(idTexto));
	}
}
