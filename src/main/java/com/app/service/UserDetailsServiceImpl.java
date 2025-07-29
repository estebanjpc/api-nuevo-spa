package com.app.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.dao.IUsuarioDao;
import com.app.entity.Usuario;


@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
	
	@Autowired
	IUsuarioDao usuarioDao;

	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Usuario usuario = usuarioDao.findByUsername(username);
		
		if(usuario == null) {
			logger.error("ERROR LOGIN :::::  usuario: " + username + " no existe en bd");
			throw new UsernameNotFoundException("usuario: " + username + " no existe en bd");
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, authorities);
		
	}
}
