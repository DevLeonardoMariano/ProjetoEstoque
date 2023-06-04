package br.edu.toledoprudente.Entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@EntityScan
@Table(name = "Users")
public class Users extends AbstractEntity<Integer> implements UserDetails {
	
	@Column(length = 150, nullable = false)
	private String username;
	
	
	@Column(length = 350, nullable = false)
	private String password;
	
	
	@Column(nullable = false)
	private Boolean enabled;
	
	@Column(name = "isAdmin")
	private boolean isAdmin;

	public boolean isAdmin() {
		return isAdmin;
	}
	
	@OneToMany(mappedBy = "usuario", cascade=CascadeType.PERSIST)
	private List<Cliente> cliente;
	

	public List<Cliente> getCliente() {
		return cliente;
	}
	
	

	public void setCliente(List<Cliente> cliente) {
		this.cliente = cliente;
	}

	@OneToMany(mappedBy = "usuario", cascade=CascadeType.PERSIST)
	private List<Funcionario> funcionario;


	public List<Funcionario> getFuncionario() {
		return funcionario;
	}



	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}



	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	public Set<AppAuthority> getAppAuthorities() {
		return appAuthorities;
	}

	public void setAppAuthorities(Set<AppAuthority> appAuthorities) {
		this.appAuthorities = appAuthorities;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "appUser")
	private Set<AppAuthority> appAuthorities;

	public Users(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends AppAuthority> authorities// ,
	// PersonalInformation personalInformation
	) {
		if (((username == null) || "".equals(username)) || (password == null)) {
			throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
		}
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		// this.accountNonExpired = accountNonExpired;
		// this.credentialsNonExpired = credentialsNonExpired;
		// this.accountNonLocked = accountNonLocked;
		// this.appAuthorities =
		// Collections.unmodifiableSet(sortAuthorities(authorities));
		// this.personalInformation = personalInformation;
	}

	public Users() {
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		 List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
	        if(isAdmin)
	        list.add(new SimpleGrantedAuthority("ADM"));
	        else
	        	list.add(new SimpleGrantedAuthority("USER"));

	        return list;
		
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
}
