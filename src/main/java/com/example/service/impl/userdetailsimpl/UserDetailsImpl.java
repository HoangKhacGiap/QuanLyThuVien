package com.example.service.impl.userdetailsimpl;


import com.example.data.entity.PhanQuyen;
import com.example.data.entity.TaiKhoan;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class UserDetailsImpl implements UserDetails {

	private String email;
	@JsonIgnore
	private String password;
	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String email) {
		this.email = email;
		this.password = ""; // Set a default or placeholder password value
		// Initialize other properties as needed
	}

	public static UserDetailsImpl build(TaiKhoan user) {
		List<PhanQuyen> roles = new ArrayList<>();
		roles.add(user.getPhanQuyen());
		List<GrantedAuthority> authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getTenPhanQuyen().toString()))
				.collect(Collectors.toList());
		return new UserDetailsImpl(user.getEmail(), user.getMatKhau(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
