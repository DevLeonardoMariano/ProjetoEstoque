package br.edu.toledoprudente.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;


import br.edu.toledoprudente.Entity.Funcionario;

@Repository
public class FuncionarioRepository extends AbstractRepository<Funcionario, Integer> {

public List<Funcionario> findByName(String name){
		
		List<Funcionario> lista = this.createQuery("select c from Funcionario " + "c where c.nome like ?1",  name + "%");
		
		return lista;
	}
	
}
