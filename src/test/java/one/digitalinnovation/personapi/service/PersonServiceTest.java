package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeDTO;
import static one.digitalinnovation.personapi.utils.PersonUtils.createFakeEntity;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Mock
	private PersonRepository personRepo;

	@InjectMocks
	private PersonService personService;

	@Test
	void testGivenPersonDTOThenReturnSavedMessage() {

		PersonDTO personDTO = createFakeDTO();
		Person expectedSavedPerson = createFakeEntity();
		Mockito.when(personRepo.save(Mockito.any(Person.class))).thenReturn(expectedSavedPerson);

		MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedPerson.getId());
		MessageResponseDTO successMessage = personService.createPerson(personDTO);

		Assertions.assertEquals(expectedSuccessMessage, successMessage);
	}

	private MessageResponseDTO createExpectedMessageResponse(Long id) {
		return MessageResponseDTO.builder()
			.message("Created person with ID: " + id)
			.build();
	}

}
