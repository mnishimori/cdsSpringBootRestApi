package br.com.digitalzyon.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.digitalzyon.domain.exception.EntidadeJaCadastradaException;
import br.com.digitalzyon.domain.model.Usuario;
import br.com.digitalzyon.domain.repository.UsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Usuario usuario = usuarioRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado!"));
		
		return User.builder()
				.username(usuario.getUsername())
				.password(usuario.getPassword())
				.roles("USER")
				.build();
	}
	
	@Transactional
	public void salvar(Usuario usuario) {
		
		this.validarUsuarioCadastrado(usuario);
		
		usuarioRepository.save(usuario);
	}

	public List<Usuario> listar(){
		return usuarioRepository.findAll(Sort.by(Sort.Direction.ASC,"username"));
	}

	private void validarUsuarioCadastrado(Usuario usuario) {
		Optional<Usuario> usuarioDb = usuarioRepository.findByUsername(usuario.getUsername());
		if (usuarioDb.isPresent()) {
			throw new EntidadeJaCadastradaException(
					String.format("Já existe usuário cadastrado com o username %s", usuario.getUsername()));
		}
	}
	
}
