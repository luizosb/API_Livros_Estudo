package com.gft.livros.resources;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.gft.livros.domain.Comentario;
import com.gft.livros.domain.Livro;
import com.gft.livros.services.LivrosService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(tags="Livro")
@RestController
@RequestMapping("/livros")
public class LivrosResources {
	
	@Autowired
	private LivrosService livrosServ;
	
	
	//Response Entity serve para criar uma resposta!

	//Puxar a lista de objetos toda
	@ApiOperation(value="Lista dos livros")
	@RequestMapping(method=RequestMethod.GET )
	public ResponseEntity<List<Livro>> listar() {
		return ResponseEntity.status(HttpStatus.OK).body(livrosServ.listar());

	}
	
	//Salvar um objeto
	@ApiOperation(value="Salva um livro")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> salvar(@ApiParam(name="Add um livro", value="Adicionar um livro a lista")@Valid @RequestBody Livro livro) {
		livro = livrosServ.salvar(livro);
		
		//Criou uma URI para reprentar o objeto salvo
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(livro.getId()).toUri();
		
		//Criar a resposta da forma correta
		return ResponseEntity.created(uri).build();
	}
	
	//Buscar um objeto pelo ID
	@ApiOperation(value="Buscar um livro")
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<?> buscar(@ApiParam(value = "Buscar um livro", example = "1") @PathVariable("id") Long id) {
		Livro livro = livrosServ.buscar(id);
		
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);
		
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(livro);
	}
	
	//Deletar um objeto
	// No content = sem conteudo
	@ApiOperation(value="Deleta um livro")
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> deletar(@ApiParam(name="Deletar um livro", value="Deletar um livro da lista")@PathVariable("id") Long id) {
		livrosServ.deletar(id);			
		return ResponseEntity.noContent().build();
	}

	//Editar um dado do objeto
	@ApiOperation(value="Atualizar um dado do livro")
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@ApiParam(name="Atualizar um livro", 
	value="Atualizar dados de um livro.",
	example ="1") @RequestBody Livro livro, @PathVariable("id") Long id) {
		livro.setId(id);
		livrosServ.atualizar(livro);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation(value="Adicionar um comentário a um livro")
	@RequestMapping(value="/{id}/comentarios", method = RequestMethod.POST)
	public ResponseEntity<Void> addComentario(@ApiParam(name="Add um comentário a um livro", value="Adicionar um comentário a um livro a lista")@PathVariable("id") Long livroId, 
			@RequestBody Comentario comentario) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		comentario.setUsuario(auth.getName());
		
		livrosServ.salvarComentario(livroId, comentario);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@ApiOperation(value="Buscar um comentário sobre um livro específico")
	@RequestMapping(value="/{id}/comentarios", method = RequestMethod.GET)
	public ResponseEntity<List<Comentario>> listarComentarios(@ApiParam(name="Buscar um comentário do livro", value="Buscar comentário de um livro a lista")@PathVariable("id") Long livroId){
		List<Comentario> comentarios = livrosServ.listarComentario(livroId);
		return ResponseEntity.status(HttpStatus.OK).body(comentarios);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
