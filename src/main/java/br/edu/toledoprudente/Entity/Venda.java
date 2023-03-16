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
@Table(name = "venda")
public class Venda extends AbstractEntity<Integer> {

	@Column(name="dataVenda", nullable = false, columnDefinition = "DATE")
	private LocalDate dataVenda;

	public LocalDate getDataVenda() {
		return dataVenda;
	}
	
	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}
	// -------------------------- RELACIONAMENTO -----------------------------------------------------
	@ManyToOne
	@JoinColumn(name ="idcliente")
	private Clientes cliente;
	
	
	public Clientes getCliente() {
			return cliente;
		}


	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}
	
	@OneToMany(mappedBy = "venda")
	private List<Produto_Venda> produto_venda;

	
	public List<Produto_Venda> getProduto_venda() {
		return produto_venda;
	}

	public void setProduto_venda(List<Produto_Venda> produto_venda) {
		this.produto_venda = produto_venda;
	}
	
	


	// -------------------------------------------------------------------------------

	
	
}
