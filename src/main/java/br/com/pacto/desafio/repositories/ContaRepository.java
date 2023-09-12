package br.com.pacto.desafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.pacto.desafio.entities.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long> {
}
