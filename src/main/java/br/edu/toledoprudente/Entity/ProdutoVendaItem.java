package br.edu.toledoprudente.Entity;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class ProdutoVendaItem {

	
    public int idproduto;
    public int quantidade;
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    public double valor_unitario;
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    public double valor_total;
    
	public int getIdproduto() {
		return idproduto;
	}
	public void setIdproduto(int idproduto) {
		this.idproduto = idproduto;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public double getValor_unitario() {
		return valor_unitario;
	}
	public void setValor_unitario(double valor_unitario) {
		this.valor_unitario = valor_unitario;
	}
	public double getValor_total() {
		return valor_total;
	}
	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}
}
