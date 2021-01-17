package br.com.digitalzyon.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.digitalzyon.domain.model.ServicoPrestado;

public interface ServicoPrestadoRepository extends JpaRepository<ServicoPrestado, Integer> {
	
	List<ServicoPrestado> findByClienteId(Integer id);
	
	List<ServicoPrestado> findByClienteNomeIgnoreCaseContaining(String nome);

}
