package br.edu.toledoprudente.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "fornecedor")
public class Fornecedor extends AbstractEntity<Integer> {
	
	@Column(name="razao", length = 150, nullable = false)
	private String razao;
	
	@Column(name="cnpj", length = 14, nullable = false)
	private String cnpj;
	
	@Column(name="email", length = 150, nullable = false)
	private String email;
	
	@Column(name="telefone", length = 15, nullable = false)
	private String telefone;
	
	@Column(name="rua", length = 150, nullable = false)
	private String rua;
	
	@Column(name="cidade", length = 150, nullable = false)
	private String cidade ;
	
	@Column(name="estado", length = 150, nullable = false)
	private String estado ;
	
	//---------------------------------------------------------------------

	public String getRazao() {
		return razao;
	}

	public void setRazao(String razao) {
		this.razao = razao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	

	
	
}
