package br.edu.toledoprudente.Entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cliente")
public class Cliente extends AbstractEntity<Integer> {
	
	@NotBlank(message = "Informe um nome")
	@Size(min = 3, max = 150, message = "O nome deve conter 3 a 150 caracteres")
	@Column(name="nome", length = 150, nullable = false)
	private String nome;
	
	@DateTimeFormat(iso = ISO.DATE)
	@NotNull(message = "Informe Data de Nascimento")
	@Column(name="dataNascimento", nullable = false, columnDefinition = "DATE")
	private LocalDate dataNascimento;
	
	@NotBlank(message = "Informe o Email")
	@Size(min = 3, max = 150, message = "O email deve conter 3 a 150 caracteres")
	@Column(name="email", length = 150, nullable = false)
	private String email;
	
	@NotBlank(message = "Informe o Telefone")
	@Column(name="telefone", length = 15, nullable = false)
	private String telefone;
	
	@NotBlank(message = "Informe o CPF")
	@Column(name="cpf", length = 11, nullable = false)
	private String cpf;
	
	@NotBlank(message = "Informe a Rua")
	@Size(min = 3, max = 150, message = "A rua deve conter 3 a 150 caracteres")
	@Column(name="rua", length = 150, nullable = false)
	private String rua;
	
	@NotBlank(message = "Informe a Cidade")
	@Size(min = 3, max = 150, message = "A cidade deve conter 3 a 150 caracteres")
	@Column(name="cidade", length = 150, nullable = false)
	private String cidade;
	
	@NotBlank(message = "Informe o Estado")
	@Size(min = 2, max = 150, message = "O estado deve conter 3 a 150 caracteres")
	@Column(name="estado", length = 150, nullable = false)
	private String estado;
	
	
// -------------------------- RELACIONAMENTO -----------------------------------------------------	

	@JsonIgnore
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="idUsuario")
	private Users usuario;
	
	@JsonIgnore
	@OneToMany(mappedBy = "cliente")
	private List<Venda> venda;
	
	public List<Venda> getVenda() {
		return venda;
	}

	public void setVenda(List<Venda> venda) {
		this.venda = venda;
	}
// -------------------------------------------------------------------------------

	public Users getUsuario() {
		return usuario;
	}

	public void setUsuario(Users usuario) {
		this.usuario = usuario;
	}

	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
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