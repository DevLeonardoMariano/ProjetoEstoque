package br.edu.toledoprudente.Entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "compra")
public class Compra extends AbstractEntity<Integer> {

	@Column(name="dataCompra", nullable = false, columnDefinition = "DATE")
	private LocalDate datadataCompraVenda;

	// -------------------------------------------------------------------------------
	
	public LocalDate getDatadataCompraVenda() {
		return datadataCompraVenda;
	}
	

	public void setDatadataCompraVenda(LocalDate datadataCompraVenda) {
		this.datadataCompraVenda = datadataCompraVenda;
	}

	
}
