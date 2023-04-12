package br.edu.toledoprudente.Entity;

import java.io.Serializable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "authorities")
public class AppAuthority extends AbstractEntity<Integer> implements GrantedAuthority, Serializable {
	
//~ Instance fields ================================================================================================

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "authority", nullable = false)
	private String authority;
// Here comes the buggy attribute. It is supposed to repesent the
// association username<->username, but I just don't know how to
// implement it
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "appuser_fk")
	private Users appUser;
//~ Constructors ===================================================================================================
	public AppAuthority(String username, String authority) {
	Assert.hasText(authority,
			"A granted authority textual representation is required");
	this.username = username;
	this.authority = authority;
	}
	public AppAuthority() {
		}
	@Override
	public String getAuthority() {
		//TODO Auto-generated method stub
		return null;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Users getAppUser() {
		return appUser;
	}
	public void setAppUser(Users appUser) {
		this.appUser = appUser;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	// Getters 'n setters 'n other stuff
}
