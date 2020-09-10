package com.evoltech.register;

import com.evoltech.register.model.jpa.*;
import com.evoltech.register.repository.*;
import com.evoltech.register.util.ColeccionNivel;
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
		Escuela escuela = new Escuela("Escuela Test");

		Maestra maestra = new Maestra("email test", "Maestra Test");
		escuela.addMaestra(maestra);

		escuelaRepository.save(escuela);

		maestraRepository.save(maestra);

		escuelaRepository.count() ;
		assert(escuela.getMaestras().size() > 0);
	}

	@Test
	@Order(3)
	@Transactional
	@Commit
	void addGrupo() {
		log.info("<<<<<<<<<<<<<<<<<<<<<  3   >>>>>>>>>>>>>>>>>>>>>>>>");
		Optional<Escuela> optional = escuelaRepository.findById(1L);

		Escuela escuela = null;
		if(optional.isPresent()) {
			escuela = optional.get();
			Grupo grupo = new Grupo("Grupo Test");
			grupoRepository.save(grupo);
			escuela.addGrupo(grupo);;
			escuelaRepository.save(escuela);
		}
		assertEquals(true, escuela != null);
	}

	@Test
	@Order(4)
	@Transactional
	@Commit
	void findEscuelaAddGrupo() {
		log.info("<<<<<<<<<<<<<<<<<<<<<  4   >>>>>>>>>>>>>>>>>>>>>>>>");
		List<Escuela> list = escuelaRepository.findByNombre("Escuela Test");
		Escuela escuela = list.get(0);

		Grupo grupo = new Grupo("Grupo Test 2");
		grupoRepository.save(grupo);

		escuela.addGrupo(grupo);
		escuelaRepository.save(escuela);

		assertEquals("Grupo Test 2", escuela.getGrupos().get(0).getNombre());
	}

	@Test
	@Order(5)
    void findEscuela() {
		log.info("<<<<<<<<<<<<<<<<<<<<<  5   >>>>>>>>>>>>>>>>>>>>>>>>");
		Optional<Escuela> optionalList = escuelaRepository.findById(1L);
		Escuela escuela = null;
		if (optionalList.isPresent()) {
			escuela = optionalList.get();
			log.info("Valor de escuela nombre: " + escuela.getNombre());
		}

		assertEquals("Uno", escuela.getNombre());
	}


	@Test
	@Order(6)
	@Transactional
	void findEscuelaGrupo() {
		log.info("<<<<<<<<<<<<<<<<<<<<<  6   >>>>>>>>>>>>>>>>>>>>>>>>");

		List<Escuela> escuelaList = escuelaRepository.findByNombre("Uno");
		Escuela escuela = null;
		if (escuelaList.size() > 0){
		    escuela = escuelaList.get(0);
			log.info("Valor de escuela nombre: " + escuela.getNombre());
        }

		List<Grupo> listGrupos = escuela.getGrupos();
		log.info("Lista grupos size: " + listGrupos.size());

		assertEquals(true, listGrupos.size() > 0 );
	}

	@Test
	@Order(7)
	@Transactional
	@Commit
	void addColeccion() {
		log.info("<<<<<<<<<<<<<<<<<<<<<  7   >>>>>>>>>>>>>>>>>>>>>>>>");
	    List<Coleccion> listInicial = coleccionRepository.findAll();

		Coleccion coleccion = new Coleccion();
		coleccion.setNombre("Coleccion Test");
		coleccion.setNivel("Nivel 1");
		coleccionRepository.save(coleccion);

		List<Coleccion> listFinal = coleccionRepository.findAll();
		assertEquals(true, listFinal.size() > listInicial.size());
	}

	@Test
	@Order(8)
	@Transactional
	@Commit
	void addLicencia() {
		log.info("<<<<<<<<<<<<<<<<<<<<<  8   >>>>>>>>>>>>>>>>>>>>>>>>");
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
	@Order(9)
	@Transactional
	@Commit
	void addLibro() {
		log.info("<<<<<<<<<<<<<<<<<<<<<  9   >>>>>>>>>>>>>>>>>>>>>>>>");
		Libro libro = new Libro();
		libro.setTitulo("Libro Test");
		libroRepository.save(libro);
    }

	@Test
	@Order(10)
	@Transactional
	@Commit
	void addLibroToColeccion() {
		log.info("<<<<<<<<<<<<<<<<<<<<<  10   >>>>>>>>>>>>>>>>>>>>>>>>");
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
	void addPlaneacionToLibro() {
		log.info("<<<<<<<<<<<<<<<<<<<<<  11   >>>>>>>>>>>>>>>>>>>>>>>>");
		Libro libro = null;
		List<Libro> listaLibro = libroRepository.findByTitulo("Libro Test");
		if (listaLibro.size() > 0){
			libro = listaLibro.get(0);
			Planeacion planeacion = new Planeacion();
			planeacion.setNombre("Planeacion Test");
			libro.addPlaneacion(planeacion);
			libroRepository.save(libro);
		}
	}

	@Test
	@Order(12)
	@Transactional
	@Commit
	void addColeccionToLicencia() {
		log.info("<<<<<<<<<<<<<<<<<<<<<  12   >>>>>>>>>>>>>>>>>>>>>>>>");
		List<Licencia> listLicencia = licenciaRepository.findByNombre("Licencia Test");
		List<Coleccion> listColeccion = coleccionRepository.findByNombre("Coleccion Test");

		if (listLicencia.size() > 0 && listColeccion.size() > 0){
			Licencia licencia = listLicencia.get(0);
			Coleccion coleccion = listColeccion.get(0);
			coleccion.addLicencia(licencia);
			coleccionRepository.save(coleccion);
		} else {
			assertEquals(true, false);
		}
	}

	@Test
	@Order(13)
	void displayColeccionNivel() {

		log.info("<<<<<<<<<<<<<<<<<<<<<  13   >>>>>>>>>>>>>>>>>>>>>>>>");
		List<Coleccion> list = coleccionRepository.findAll();

		for (Coleccion var: list ) {
		    log.warn("Valor de coleccion.nivelStr: " + var.getNivelStr());
		}
		assertEquals(true, list.size() > 0);
	}

	@Test
	@Order(14)
	void findColeccionNivelStr() {
		log.info("<<<<<<<<<<<<<<<<<<<<<  14   >>>>>>>>>>>>>>>>>>>>>>>>");

		List<Coleccion> list = coleccionRepository.findByNivelStr(ColeccionNivel.NIVEL_1);

		for (Coleccion var: list ) {
			log.warn("Valor de coleccion.nivelStr: " + var.getNivelStr());
		}
		assertEquals(true, list.size() > 0);
	}

}
