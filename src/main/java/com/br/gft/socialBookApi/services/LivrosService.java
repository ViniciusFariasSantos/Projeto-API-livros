package com.br.gft.socialBookApi.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.br.gft.socialBookApi.domain.Comentarios;
import com.br.gft.socialBookApi.domain.Livro;
import com.br.gft.socialBookApi.repository.ComentariosRepository;
import com.br.gft.socialBookApi.repository.LivrosRepository;
import com.br.gft.socialBookApi.services.exceptions.LivroNaoEncontradoException;

@Service
public class LivrosService {
	
	@Autowired
	private LivrosRepository livrosRepository;
	
	@Autowired
	ComentariosRepository comentariosRepository;
	
	
	
	//metodo listar 
	public List<Livro> listar(){
		return livrosRepository.findAll();
	}
	
	
	
	
	
	public Optional<Livro> buscar(Long id ) {
		
		Optional<Livro> livro  = livrosRepository.findById(id);
				if(livro.isEmpty() ){
				
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
			
			throw new  LivroNaoEncontradoException("O livro não pode ser encontrado .");
		}
		
	}
	
	
	
	
	public void atualizar (Livro livro) {
		verificarExistencia(livro);
		livrosRepository.save(livro);
		
		
	}
	
	
	private void verificarExistencia(Livro livro) {
		
		buscar (livro.getId());
		
	}
	
	
	public Comentarios salvarComentarios(Long livroId, Comentarios comentario) {
		
		Livro livro = buscar(livroId).get();
		
		comentario.setLivro(livro);
		comentario.setData(new Date());
		
		return comentariosRepository.save(comentario);
		
	}
	
	
	public List <Comentarios> ListarComentarios(Long livroID){
		Livro livro = buscar(livroID).get();
		return livro.getComentarios();
	}
	
	
	
	
	
	
}
