package com.gft.livros.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gft.livros.domain.Comentario;
import com.gft.livros.domain.Livro;
import com.gft.livros.services.LivrosService;

@RestController
@RequestMapping("/livros")
public class LivrosResources {
	
	@Autowired
	private LivrosService livrosServ;
	
	
	//Response Entity serve para criar uma resposta!

	//Puxar a lista de objetos toda
	@RequestMapping(method=RequestMethod.GET )
	public ResponseEntity<List<Livro>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(livrosServ.listar());

	}
	
	//Salvar um objeto
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@Valid @RequestBody	Livro livro) {
		livro = livrosServ.salvar(livro);
		
		//Criou uma URI para reprentar o objeto salvo
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(livro.getId()).toUri();
		
		//Criar a resposta da forma correta
		return ResponseEntity.created(uri).build();
	}
	
	//Buscar um objeto pelo ID
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		Livro livro = livrosServ.buscar(id);
		return ResponseEntity.status(HttpStatus.OK).body(livro);
	}
	
	//Deletar um objeto
	// No content = sem conteudo
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		livrosServ.deletar(id);			
		return ResponseEntity.noContent().build();
	}

	//Editar um dado do objeto
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Livro livro, @PathVariable("id") Long id) {
		livro.setId(id);
		livrosServ.atualizar(livro);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}/comentarios", method = RequestMethod.POST)
	public ResponseEntity<Void> addComentario(@PathVariable("id") Long livroId, 
			@RequestBody Comentario comentario) {
		
		livrosServ.salvarComentario(livroId, comentario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}/comentarios", method = RequestMethod.GET)
	public ResponseEntity<List<Comentario>> listarComentarios(@PathVariable("id") Long livroId){
		List<Comentario> comentarios = livrosServ.listarComentario(livroId);
		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
