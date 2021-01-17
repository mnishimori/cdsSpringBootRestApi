package br.com.digitalzyon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.digitalzyon.domain.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
