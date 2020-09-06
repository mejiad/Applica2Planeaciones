package com.evoltech.register;

import com.evoltech.register.model.jpa.*;
import com.evoltech.register.repository.*;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
// import org.junit.platform.commons.logging.Logger;
// import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RegisterApplicationTests {

	Logger log = LoggerFactory.getLogger(RegisterApplicationTests.class);

	@Autowired
	private EscuelaRepository escuelaRepository;

	@Autowired
	private MaestraRepository maestraRepository;

	@Autowired
	private GrupoRepository grupoRepository;

	@Autowired
	private LicenciaRepository licenciaRepository;

	@Autowired
	private ColeccionRepository coleccionRepository;

	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private PlaneacionRepository planeacionRepository;


	@Test
	@Order(1)
	void contextLoads() {
	}

	@Test
    @Order(2)
    @Transactional
	@Commit
	void addEscuela() {
		log.info("<<<<<<<<<<<<<<<<<<<<<  2   >>>>>>>>>>>>>>>>>>>>>>>>");
		Escuela escuela = new Escuela();
		escuela.setNombre("Escuela Test");

		escuelaRepository.save(escuela);

		Maestra maestra = new Maestra();
		maestra.setNombre("Maestra Test");
		maestra.setApellido("Apellido Test");
		maestra.setEscuela(escuela);

		escuelaRepository.save(escuela);

		maestraRepository.save(maestra);

		escuelaRepository.count() ;
		assert(escuelaRepository.count() > 8);
	}

	@Test
	@Order(3)
	@Transactional
	@Commit
	void addGrupo() {
		log.info("<<<<<<<<<<<<<<<<<<<<<  3   >>>>>>>>>>>>>>>>>>>>>>>>");
		Optional<Escuela> optional = escuelaRepository.findById(1L);

		if(optional.isPresent()) {
			Escuela escuela = optional.get();
			Grupo grupo = new Grupo();
			grupo.setNombre("Grupo Test");
			grupo.setEscuela(escuela);
			grupoRepository.save(grupo);
			assert (true);
		} else {
			assert (false);
		}
	}

	@Test
	@Order(4)
	@Transactional
	@Commit
	void findMaestra() {
		log.info("<<<<<<<<<<<<<<<<<<<<<  4   >>>>>>>>>>>>>>>>>>>>>>>>");
		List<Escuela> list = escuelaRepository.findByNombre("Escuela Test");

		Grupo grupo = new Grupo();

		grupo.setNombre("Grupo Test 2");

		Escuela escuela = list.get(0);
		grupo.setEscuela(escuela);
		grupoRepository.save(grupo);

		escuela.addGrupo(grupo);
		escuelaRepository.save(escuela);

		log.info("Escuela: " + escuela.getNombre());

		assert(list.size() > 0);
	}

	@Test
	@Order(5)
	@Transactional
	@Commit
    void findMaestraEscuela() {
		log.info("<<<<<<<<<<<<<<<<<<<<<  5   >>>>>>>>>>>>>>>>>>>>>>>>");
		List<Maestra> list = maestraRepository.findByNombre("Maestra Test");

		Maestra maestra = list.get(0);
		log.info("Valor de maestra.nombre: " + maestra.getNombre());

		Escuela escuela = maestra.getEscuela();
		log.info("Valor de escuela nombre: " + escuela.getNombre());

		assertEquals(true, list.size() > 0 );
	}

	@Test
	@Order(6)
	@Transactional
	@Commit
	void findMaestraEscuelaGrupo() {
		log.info("<<<<<<<<<<<<<<<<<<<<<  6   >>>>>>>>>>>>>>>>>>>>>>>>");
		List<Maestra> list = maestraRepository.findByNombre("Maestra Test");

		Maestra maestra = list.get(0);
		log.info("Valor de maestra.nombre: " + maestra.getNombre());

		Escuela escuela = maestra.getEscuela();
		log.info("Valor de escuela nombre: " + escuela.getNombre());

		List<Grupo> listGrupos = escuela.getGrupos();
		log.info("Lista grupos size: " + listGrupos.size());

		assertEquals(true, listGrupos.size() > 0 );
	}

	@Test
	@Order(7)
	@Transactional
	@Commit
	void addLicencia() {
		Optional<Escuela> optionalEscuela = escuelaRepository.findById(1L);
		if(optionalEscuela.isPresent()) {
			Escuela escuela = optionalEscuela.get();
			Licencia licencia = new Licencia();
			licencia.setNombre("Licencia Test");
			escuela.addLicencia(licencia);
			licenciaRepository.save(licencia);
			assertEquals(true, true);
		} else {
		    log.warn("No hay escuela");
			assertEquals(true, false);
		}

	}

	@Test
	@Order(8)
	@Transactional
	@Commit
	void addColeccion() {
	    List<Licencia> lista = licenciaRepository.findByNombre("Licencia Test");
	    if (lista.size()> 0){
	    	Licencia licencia = lista.get(0);
			Coleccion coleccion = new Coleccion();
			coleccion.setNombre("Coleccion Test");
			licencia.addColeccion(coleccion);
			coleccionRepository.save(coleccion);
			assertEquals(true, true);

		} else {
	    	assertEquals(true, false);
		}
	}

	@Test
	@Order(9)
	@Transactional
	@Commit
	void addLibro() {
		Libro libro = new Libro();
		libro.setTitulo("Libro Test");
		libroRepository.save(libro);
    }

	@Test
	@Order(10)
	@Transactional
	@Commit
	void addLibroToColeccion() {
		// buscar coleccion
		Coleccion coleccion = null;
		Libro libro = null;

		List<Coleccion> listaColeccion = coleccionRepository.findByNombre("Coleccion Test");
		if (listaColeccion.size() > 0){
			coleccion = listaColeccion.get(0);
		}

		List<Libro> listaLibro = libroRepository.findByTitulo("Libro Test");
		if (listaLibro.size() > 0){
			libro = listaLibro.get(0);
		}

		if (coleccion != null && libro != null){
			coleccion.addLibro(libro);
			coleccionRepository.save(coleccion);
			assertEquals(true, true);
		} else {
			assertEquals(true, false);
		}
	}

	@Test
	@Order(11)
	@Transactional
	@Commit
	void addPlaneacionToColeccion() {
		Coleccion coleccion = null;
		List<Coleccion> listaColeccion = coleccionRepository.findByNombre("Coleccion Test");
		if (listaColeccion.size() > 0){
			coleccion = listaColeccion.get(0);
		}

		Planeacion planeacion = new Planeacion();
		planeacion.setNombre("Planeacion Test");

		if (coleccion != null){
			coleccion.addPlaneacion(planeacion);
			coleccionRepository.save(coleccion);
		}
	}
}
