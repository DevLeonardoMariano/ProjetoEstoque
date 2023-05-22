package br.edu.toledoprudente.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;


import br.edu.toledoprudente.Entity.Produto;

@Repository
public class ProdutoRepository extends AbstractRepository<Produto, Integer> {
	
	public List<Produto> findByName(String name){
		
		List<Produto> lista = this.createQuery("select c from Produto " + "c where c.nome like ?1",  name + "%");
		
		return lista;
	}
	
}
