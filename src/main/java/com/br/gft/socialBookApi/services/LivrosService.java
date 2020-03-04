package com.br.gft.socialBookApi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;

import org.springframework.stereotype.Service;

import com.br.gft.socialBookApi.domain.Livro;
import com.br.gft.socialBookApi.repository.LivrosRepository;
import com.br.gft.socialBookApi.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {
	
	@Autowired
	private LivrosRepository livrosRepository;
	
	
	
	//metodo listar 
	public List<Livro> listar(){
		return livrosRepository.findAll();
	}
	
	
	
	
	
	public Optional<Livro> buscar(Long id ) {
		
		Optional<Livro> livro  = livrosRepository.findById(id);
				if(livro.isEmpty() ){
					System.out.println("Teste");
			throw new  LivroNaoEncontradoException("O livro não pode ser encontrado.");
		}
			return livro;
	}
	
	
	
	
	public Livro salvar (Livro livro) {
		livro.setId(null);
		return livrosRepository.save(livro);
		
	}
	
	
	
	
	public void deletar(Long id) {
		
		try {
			this.livrosRepository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			
			throw new  LivroNaoEncontradoException("O livro não pode ser encontrado encontrado.");
		}
		
	}
	
	
	
	
	public void atualizar (Livro livro) {
		verificarExistencia(livro);
		livrosRepository.save(livro);
		
		
	}
	
	
	private void verificarExistencia(Livro livro) {
		
		buscar (livro.getId());
		
	}





	
	
	
	
	
	
}
