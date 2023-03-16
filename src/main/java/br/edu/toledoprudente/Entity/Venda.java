package br.edu.toledoprudente.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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


	// -------------------------------------------------------------------------------

	
	
}
