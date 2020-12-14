package com.chaves.apicontatos.repository;

import com.chaves.apicontatos.entity.Contato;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContatoRepository extends JpaRepository<Contato, Long> {
}
