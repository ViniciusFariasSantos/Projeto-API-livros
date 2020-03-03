package com.br.gft.socialBookApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.gft.socialBookApi.domain.Livro;

public interface LivrosRepository extends JpaRepository<Livro, Long>{

}
