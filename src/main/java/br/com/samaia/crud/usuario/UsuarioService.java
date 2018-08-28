package br.com.samaia.crud.usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.samaia.crud.exception.ObjectNotFoundException;

/**
 * O Serviço de Usuários.
 * 
 * @author fabio
 *
 */
@Service
public class UsuarioService {

	/**
	 * The entity manager
	 */
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * A injeção do repositório.
	 */
	@Autowired
	private UsuarioRepository repository;

	/**
	 * Lista dodos os Usuários
	 * 
	 * @return Uma lista com todos os Usuários em ordem alfabetica.
	 */
	public List<Usuario> listAll() {
		List<Usuario> result = new ArrayList<Usuario>();
		repository.findAll().forEach(result::add);
		return result.stream().sorted((u1, u2) -> u1.getNome().compareTo(u2.getNome())).collect(Collectors.toList());
	}
	
	/**
	 * Busca um Usuário pelo ID.
	 * 
	 * @param id O ID do Usuário
	 * @return A entidade Usuário
	 */
	public Usuario find(Long id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Registro não encontrado"));
	}
	
	/**
	 * Grava o Usuário
	 * 
	 * @param usuario O Usuário a ser persistido
	 * @return
	 */
	public Usuario save(Usuario usuario) {
		return repository.save(usuario);
	}
	
	/**
	 * Exclui um Usuário
	 * 
	 * @param usuario O usuário a ser excluído.
	 */
	public void delete(Usuario usuario) {
		repository.delete(usuario);
	}

}
