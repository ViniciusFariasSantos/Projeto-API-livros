package com.br.gft.socialBookApi.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.gft.socialBookApi.domain.Livro;
import com.br.gft.socialBookApi.repository.LivrosRepository;

@RestController
public class LivrosResources {
	
	@Autowired
	private LivrosRepository livrosRepository;
	
	
	@RequestMapping(value="/livros", method = RequestMethod.GET)
	public List<Livro> livro() {
		
			
		return livrosRepository.findAll();
	}
	
}
