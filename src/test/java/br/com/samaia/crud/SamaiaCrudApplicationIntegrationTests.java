package br.com.samaia.crud;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.samaia.crud.usuario.Usuario;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class SamaiaCrudApplicationIntegrationTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testeListarTodosUsuarios() {

		ResponseEntity<List> responseEntity = restTemplate.getForEntity("/api/usuarios", List.class);
		List<Usuario> usuarios = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(usuarios.size(), 2);
	}

	@Test
	public void testeBuscarUmUsuario() {

		ResponseEntity<Usuario> responseEntity = restTemplate.getForEntity("/api/usuarios/1", Usuario.class);
		Usuario usuario = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(usuario.getEmail(), "barboza.oliveira@gmail.com");
	}

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testeExcluirUmUsuario() {

		restTemplate.delete("/api/usuarios/1");

		ResponseEntity<List> responseEntity = restTemplate.getForEntity("/api/usuarios", List.class);
		List<Usuario> usuarios = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(usuarios.size(), 1);
	}

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testeCriarNovoUsuario() {

		ResponseEntity<Usuario> responseEntity = restTemplate.postForEntity("/api/usuarios",
				new Usuario("Usuario Teste", "integrated@test.com"), Usuario.class);
		Usuario usuario = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(usuario.getEmail(), "integrated@test.com");

		ResponseEntity<List> responseEntityList = restTemplate.getForEntity("/api/usuarios", List.class);
		List<Usuario> usuarios = responseEntityList.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(usuarios.size(), 3);
	}

	@Test
	public void testeAtualizarUmUsuario() {

		ResponseEntity<Usuario> responseEntity = restTemplate.getForEntity("/api/usuarios/1", Usuario.class);
		Usuario usuario = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(usuario.getEmail(), "barboza.oliveira@gmail.com");

		usuario.setNome("Nome Alterado");
		restTemplate.put("/api/usuarios/1", usuario, Usuario.class);

		responseEntity = restTemplate.getForEntity("/api//usuarios/1", Usuario.class);
		usuario = responseEntity.getBody();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(usuario.getNome(), "Nome Alterado");
	}

}
