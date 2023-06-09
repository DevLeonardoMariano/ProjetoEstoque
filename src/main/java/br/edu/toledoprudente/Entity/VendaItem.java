package br.edu.toledoprudente.Entity;

import java.util.List;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

public class VendaItem {
	
	
    public int idcliente;
    public int idfuncionario;
    @NumberFormat(style = Style.CURRENCY, pattern = "#,##0.00")
    public int valor;
    public List<ProdutoVendaItem> itens;
    
    
    
	public int getIdfuncionaario() {
		return idfuncionario;
	}
	public void setIdfuncionaario(int idfuncionaario) {
		this.idfuncionario = idfuncionaario;
	}
	public int getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
	public List<ProdutoVendaItem> getItens() {
		return itens;
	}
	public void setItens(List<ProdutoVendaItem> itens) {
		this.itens = itens;
	}
}
