package br.edu.toledoprudente.Entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "fornecedor")
public class Fornecedor extends AbstractEntity<Integer> {
	
	@NotBlank(message = "Informe a Razão")
	@Size(min = 3, max = 150, message = "A Razão deve conter 3 a 150 caracteres")
	@Column(name="razao", length = 150, nullable = false)
	private String razao;
	
	@NotBlank(message = "Informe o CNPJ")
	@Column(name="cnpj", length = 14, nullable = false)
	private String cnpj;
	
	@NotBlank(message = "Informe o Email")
	@Size(min = 3, max = 150, message = "O email deve conter 3 a 150 caracteres")
	@Column(name="email", length = 150, nullable = false)
	private String email;
	
	@NotBlank(message = "Informe o Telefone")
	@Column(name="telefone", length = 15, nullable = false)
	private String telefone;
	
	@NotBlank(message = "Informe a Rua")
	@Size(min = 3, max = 150, message = "A rua deve conter 3 a 150 caracteres")
	@Column(name="rua", length = 150, nullable = false)
	private String rua;
	
	@NotBlank(message = "Informe a Cidade")
	@Size(min = 3, max = 150, message = "A cidade deve conter 3 a 150 caracteres")
	@Column(name="cidade", length = 150, nullable = false)
	private String cidade ;
	
	@NotBlank(message = "Informe o Estado")
	@Size(min = 2, max = 150, message = "O estado deve conter 3 a 150 caracteres")
	@Column(name="estado", length = 150, nullable = false)
	private String estado ;
	// -------------------------- RELACIONAMENTO --------------------------
	@OneToMany(mappedBy = "fornecedor")
	private List<Compra> compra;
	
	public List<Compra> getCompra() {
		return compra;
	}

	public void setCompra(List<Compra> compra) {
		this.compra = compra;
	}
	
	
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
