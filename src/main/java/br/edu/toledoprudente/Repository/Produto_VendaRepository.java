package br.edu.toledoprudente.Repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.edu.toledoprudente.Entity.Produto_Venda;

@Repository
public class Produto_VendaRepository extends AbstractRepository<Produto_Venda, Integer> {

	public List<Produto_Venda> findByName(String name){

        List<Produto_Venda> lista = this.createQuery("select * from Produto_Venda");

        return lista;

}
	
}
