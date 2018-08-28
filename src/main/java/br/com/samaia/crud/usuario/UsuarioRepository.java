package br.com.samaia.crud.usuario;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositorio do Usuario.
 * 
 * @author fabio
 *
 */
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long>  {

}
