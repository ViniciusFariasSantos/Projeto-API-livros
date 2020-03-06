package com.br.gft.socialBookApi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.gft.socialBookApi.domain.Autor;
import com.br.gft.socialBookApi.repository.AutoresRepository;
import com.br.gft.socialBookApi.services.exceptions.AutorExistenteException;
import com.br.gft.socialBookApi.services.exceptions.AutorNaoEncontradoException;

@Service
public class AutoresService {
	
	@Autowired
	private AutoresRepository autoresRepository; 
	
	public List<Autor>listar (){
		return autoresRepository.findAll(); 
	}
	
	public Autor salvar (Autor autor) {
		
		if(autor.getId() != null) {
			
			Autor a = autoresRepository.findById(autor.getId()).orElse(null);
			
			if (a != null) {
				
				throw new AutorExistenteException("O Autor já existe");
			}
			
		}
		return autoresRepository.save(autor);
	}
	
	public Optional<Autor> buscar(Long id) {
		
		Optional<Autor> autor = autoresRepository.findById(id);
		
		if(autor.isEmpty()  ) {
			throw new AutorNaoEncontradoException("O Autor não existe");
		}
		return autor; 
	}
	
	
}
