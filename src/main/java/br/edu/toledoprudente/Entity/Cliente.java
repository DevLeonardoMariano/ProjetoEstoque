package br.edu.toledoprudente.Entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class Cliente extends AbstractEntity<Integer> {
	
	@Column(name="nome", length = 150, nullable = false)
	private String nome;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name="dataNascimento", nullable = false, columnDefinition = "DATE")
	private LocalDate dataNascimento;
	
	@Column(name="email", length = 150, nullable = false)
	private String email;
	
	@Column(name="telefone", length = 15, nullable = false)
	private String telefone;
	
	@Column(name="cpf", length = 11, nullable = false)
	private String cpf;
	
	@Column(name="rua", length = 150, nullable = false)
	private String rua;
	
	@Column(name="cidade", length = 150, nullable = false)
	private String cidade;
	
	@Column(name="estado", length = 150, nullable = false)
	private String estado;

// -------------------------- RELACIONAMENTO -----------------------------------------------------	

	@OneToMany(mappedBy = "cliente")
	private List<Venda> venda;
	
	public List<Venda> getVenda() {
		return venda;
	}

	public void setVenda(List<Venda> venda) {
		this.venda = venda;
	}
// -------------------------------------------------------------------------------



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