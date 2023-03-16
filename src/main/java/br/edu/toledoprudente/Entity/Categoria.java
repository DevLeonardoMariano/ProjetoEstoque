package br.edu.toledoprudente.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Categoria")
public class Categoria extends AbstractEntity<Integer> {

		@Column(length = 150, nullable = false)
		private String descricao;
		
		//RELACIONAMENTO COM A CLASSE CLIENTE > UMA CATEGORIA PODE TER VARIOS CLIENTES
		@OneToMany(mappedBy = "categoria")
		private List<Produto> produto;
		
		
		public List<Produto> getProduto() {
			return produto;
		}

		public void setProduto(List<Produto> produto) {
			this.produto = produto;
		}
	
		
		//-----------------------------------------------------------------



		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
		
		
		
}
