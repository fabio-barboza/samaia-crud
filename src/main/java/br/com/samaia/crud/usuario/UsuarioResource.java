package br.com.samaia.crud.usuario;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

/**
 * Classe resource responsável por receber as requisições referente a Usuarios
 * 
 * @author fabio
 *
 */
@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioResource {

	/**
	 * Injeção do serviço
	 */
	@Autowired
	private UsuarioService service;

	/**
	 * Busca um usuário pelo ID
	 * 
	 * @param id O ID do usuário
	 * 
	 * @return O Usuário. @see Usuario.class
	 */
	@ApiOperation(value = "Retorna o Usuário.", response = Usuario.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.find(id));
	}

	/**
	 * Lista todos os usuários em ordem alfabética.
	 * 
	 * @return A listagem de Usuários @see Usuario.class
	 */
	@ApiOperation(value = "Retorna uma lista de Usuários", response = Usuario.class)
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> list() {
		return ResponseEntity.ok().body(service.listAll());
	}

	/**
	 * Insere um novo Usuário
	 * 
	 * @param usuario O Usuário a ser inserido
	 * 
	 * @return O Usuário persistido.
	 */
	@ApiOperation(value = "Insere um novo Usuário", response = Usuario.class)
	@PostMapping
	public ResponseEntity<?> insert(@Valid @RequestBody Usuario usuario) {
		return ResponseEntity.ok().body(service.save(usuario));
	}

	/**
	 * Atualiza um usuário
	 * 
	 * @param id O ID do Usuário
	 * 
	 * @param usuario O Usuário editado.
	 * 
	 * @return O Usuário persistido.
	 */
	@ApiOperation(value = "Atualiza um Usuário", response = Usuario.class)
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Usuario usuario) {
		Usuario usuarioEditado = service.find(id);

		usuarioEditado.setNome(usuario.getNome());
		usuarioEditado.setEmail(usuario.getEmail());
		usuarioEditado.setAtivo(usuario.isAtivo());		

		return ResponseEntity.ok().body(service.save(usuarioEditado));
	}

	/**
	 * Exclui um Usuário
	 * 
	 * @param id O ID do Usuário a ser excluido.
	 */
	@ApiOperation(value = "Exclui um usuário.")
	@DeleteMapping(value = "/{id}")
	public void delete(@PathVariable Long id) {
		Usuario usuario = new Usuario();
		usuario.setId(id);
		service.delete(usuario);
	}

}
