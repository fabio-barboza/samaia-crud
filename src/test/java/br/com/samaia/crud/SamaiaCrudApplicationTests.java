package br.com.samaia.crud;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.samaia.crud.usuario.Usuario;
import br.com.samaia.crud.usuario.UsuarioService;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class SamaiaCrudApplicationTests {

	@Autowired
	private UsuarioService service;	
	
	@Test
	public void contextLoads() {
	}
	
	@Test	
	public void testeListarTodosUsuarios() {
		List<Usuario> usuarios = service.listAll();
		
		assertEquals(usuarios.size(), 2);
	}
	
	@Test
	public void testeEncontrarUsuario() {
		Usuario usuario = service.find(1L);
		
		assertEquals(usuario.getEmail(), "barboza.oliveira@gmail.com");		
	}
	
	@Test	
	public void testeExcluirUsuario() {
		Usuario usuario = new Usuario();
		usuario.setId(1L);
		service.delete(usuario);
		
		List<Usuario> usuarios = service.listAll();
		
		assertEquals(usuarios.size(), 1);
	}	
	
	@Test
	public void testeCriarNovoUsuario() {
		Usuario usuario = service.save(new Usuario("Nome Usuario Teste", "testNewUsuario@test.com"));
		
		assertEquals(usuario.getId(), new Long(3L));		
	}
	
	@Test
	public void testeAtualizarUsuario() {
		Usuario usuario = service.find(1L);		

		usuario.setNome("Usuario Alterado");
		usuario.setEmail("alterado@teste.com");
		
		usuario = service.save(usuario);
		
		usuario = service.find(1L);	
		
		assertEquals(usuario.getNome(), "Usuario Alterado");	
		assertEquals(usuario.getEmail(), "alterado@teste.com");
	}	

}
