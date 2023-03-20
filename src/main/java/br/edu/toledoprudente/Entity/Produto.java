package br.edu.toledoprudente.Entity;

import java.math.BigDecimal;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto extends AbstractEntity<Integer> {
	
	@Column(name="nome", length = 150, nullable = false)
	private String nome;
	
	@Column(name="valor", nullable = true, columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
	private BigDecimal valor;
	
	@Column(name="estoque", length = 150, nullable = false)
	private Integer estoque;
	
	//-----------------------------------------------------------------------------	
	
		// RELACIONAMENTO COM A CLASSE CATEGORIA > UM PRODUTO PODE TER UMA CATEGORIA
		@ManyToOne
		@JoinColumn(name= "idcategoria")
		private Categoria categoria;
		
		public Categoria getCategoria() {
			return categoria;
		}

		public void setCategoria(Categoria categoria) {
			this.categoria = categoria;
			
		}
		
		@OneToMany(mappedBy = "produto")
		private List<Produto_Venda> produto_venda;
		
		public List<Produto_Venda> getProduto_venda() {
			return produto_venda;
		}

		public void setProduto_venda(List<Produto_Venda> produto_venda) {
			this.produto_venda = produto_venda;
		}
		
		@OneToMany(mappedBy = "produto")
		private List<Produto_Compra> produto_compra;
		
		public List<Produto_Compra> getProduto_compra() {
			return produto_compra;
		}

		public void setProduto_compra(List<Produto_Compra> produto_compra) {
			this.produto_compra = produto_compra;
		}

		
		
	 //-----------------------------------------------------------------------------	
		
	

		

		
		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public BigDecimal getValor() {
			return valor;
		}

		public void setValor(BigDecimal valor) {
			this.valor = valor;
		}

		public Integer getEstoque() {
			return estoque;
		}

		public void setEstoque(Integer estoque) {
			this.estoque = estoque;
		}
		

		
		
		
}
