package br.edu.toledoprudente.Entity;

import java.math.BigDecimal;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto_venda")
public class Produto_Venda extends AbstractEntity<Integer> {

	
	@Column(name="quantidade", nullable = false)
	private Integer quantidade;
	
	@Column(name="valorTotal", nullable = true, columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
	private BigDecimal valorTotal;
	
	//------------------------------------ RELACIONAMENTO -----------------------------------------	
	
	@ManyToOne
	@JoinColumn(name ="idproduto")
	private Produto produto;
	
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	@ManyToOne
	@JoinColumn(name ="idvenda")
	private Venda venda;
	
	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
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
