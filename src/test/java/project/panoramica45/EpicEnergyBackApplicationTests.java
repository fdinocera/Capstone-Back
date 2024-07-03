package project.panoramica45;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
//@Transactional
class EpicEnergyBackApplicationTests {

/*	@Autowired
	private EntityManager entityManager;

	@Autowired
	private ProvinciaRepository provinciaRepository;*/

	/*@Test
	public void testFindByNomeProvincia() {
		// given
		Provincia savedProvincia = Provincia.builder()
				.siglaProvincia("AG")
				.regione("Sicilia")
				.nomeProvincia("Agrigento")
				.build();
		entityManager.persist(savedProvincia);
		entityManager.flush();

		// when
		Provincia foundProvincia = provinciaRepository.findByNomeProvincia(savedProvincia.getNomeProvincia());

		// then
		if (foundProvincia != null) {
			assertThat(foundProvincia.getNomeProvincia()).isEqualTo(savedProvincia.getNomeProvincia());
			System.out.println("Saved Provincia: " + savedProvincia);
			System.out.println("Found Provincia: " + foundProvincia);
		} else {
			System.out.println("Provincia with nomeProvincia " + savedProvincia.getNomeProvincia() + " does not exist.");
		}
	}*/
/*	@Test
	public void testFindByNomeProvinciad() {
		// given
		String nomeProvincia = "Sicilia"; // Use a known nomeProvincia

		// when
		Provincia foundProvincia = provinciaRepository.findByNomeProvincia(nomeProvincia);

		// then
		if (foundProvincia != null) {
			System.out.println("Found Provincia: " + foundProvincia);
		} else {
			System.out.println("Provincia with nomeProvincia " + nomeProvincia + " does not exist.");
		}
	}*/
}