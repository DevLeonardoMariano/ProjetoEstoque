package br.edu.toledoprudente.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.toledoprudente.Entity.Cliente;

//CLASSE FILHA DE CLASSE ABSTRACT REPOSITORY
@Repository
public class ClienteRepository extends AbstractRepository<Cliente, Integer> {
	
	public List<Cliente> findByName(String name){
		
		List<Cliente> lista = this.createQuery("select c from Cliente " + "c where c.nome like ?1",  name + "%");
		
		return lista;
	}
	
	
}
