package br.com.samaia.crud;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.samaia.crud.usuario.Usuario;
import br.com.samaia.crud.usuario.UsuarioRepository;

@SpringBootApplication
public class SamaiaCrudApplication implements CommandLineRunner {
	
	@Autowired
	private UsuarioRepository repository;		

	public static void main(String[] args) {
		SpringApplication.run(SamaiaCrudApplication.class, args);
	}
	
	/**
	 * Method executed everytime that the applications run at first time.
	 */
	@Override
	public void run(String... args) throws Exception {

		Usuario u1 = new Usuario("Fabio Barboza de Oliveira", "barboza.oliveira@gmail.com");
		Usuario u2 = new Usuario("João da Silva", "joao.silva@gmail.com");				

		repository.saveAll(Arrays.asList(u1, u2));
	}		
}
