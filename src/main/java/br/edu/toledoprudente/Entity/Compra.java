package br.edu.toledoprudente.Entity;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "compra")
public class Compra extends AbstractEntity<Integer> {

	@Column(name="dataCompra", nullable = false, columnDefinition = "DATE")
	private LocalDate datadataCompraVenda;

	// -------------------------------------------------------------------------------
	@OneToMany(mappedBy = "compra")
	private List<Produto_Compra> produto_compra;
	
	
	public List<Produto_Compra> getProduto_compra() {
		return produto_compra;
	}


	public void setProduto_compra(List<Produto_Compra> produto_compra) {
		this.produto_compra = produto_compra;
	}
	
	@ManyToOne
	@JoinColumn(name = "idfornecedor")
	private Fornecedor fornecedor;
	
	
	public Fornecedor getFornecedor() {
		return fornecedor;
	}


	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}


	// -------------------------------------------------------------------------------
	public LocalDate getDatadataCompraVenda() {
		return datadataCompraVenda;
	}
	

	public void setDatadataCompraVenda(LocalDate datadataCompraVenda) {
		this.datadataCompraVenda = datadataCompraVenda;
	}

	
}
