package com.minsait.avaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minsait.avaliacao.entity.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {

}
