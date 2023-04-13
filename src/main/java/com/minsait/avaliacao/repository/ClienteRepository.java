package com.minsait.avaliacao.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.minsait.avaliacao.entity.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {



}
