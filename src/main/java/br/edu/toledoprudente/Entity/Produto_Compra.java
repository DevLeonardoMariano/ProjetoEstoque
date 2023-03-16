package br.edu.toledoprudente.Entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto_compra")
public class Produto_Compra extends AbstractEntity<Integer> {

	@Column(name="quantidade", nullable = false)
	private Integer quantidade;
	
	@Column(name="valorTotal", nullable = true, columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
	private BigDecimal valorTotal;
	
	//-----------------------------------------------------------------------------	

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(BigDecimal valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	
	
	
}
