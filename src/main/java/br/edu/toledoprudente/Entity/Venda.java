package br.edu.toledoprudente.Entity;


import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat.Style;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "venda")
public class Venda extends AbstractEntity<Integer> {

	@DateTimeFormat(iso = ISO.DATE)
	@Column(name="dataVenda", nullable = false, columnDefinition = "DATE")
	private LocalDate dataVenda;
	
	@Column(name="valorTotal", nullable = true, columnDefinition = "DECIMAL(10,2) DEFAULT 0.00")
	@NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
	private double valorTotal;

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public LocalDate getDataVenda() {
		return dataVenda;
	}
	
	public void setDataVenda(LocalDate dataVenda) {
		this.dataVenda = dataVenda;
	}
	// -------------------------- RELACIONAMENTO -----------------------------------------------------
	@ManyToOne
	@JoinColumn(name ="idcliente")
	private Cliente cliente;
	
	
	public Cliente getCliente() {
			return cliente;
		}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@ManyToOne
	@JoinColumn(name ="idfuncionario")
	private Funcionario funcionario;
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
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
