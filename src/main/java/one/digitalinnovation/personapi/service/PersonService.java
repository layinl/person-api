package one.digitalinnovation.personapi.service;

import one.digitalinnovation.personapi.dto.MessageResponseDTO;
import one.digitalinnovation.personapi.dto.request.PersonDTO;
import one.digitalinnovation.personapi.entity.Person;
import one.digitalinnovation.personapi.exception.PersonNotFoundException;
import one.digitalinnovation.personapi.mapper.PersonMapper;
import one.digitalinnovation.personapi.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonService {

	private PersonRepository personRepo;

	private final PersonMapper personMapper = PersonMapper.INSTANCE;

	@Autowired
	public PersonService(PersonRepository personRepo) {
		this.personRepo = personRepo;
	}

	public MessageResponseDTO createPerson(PersonDTO personDTO) {
		Person savedPerson = personRepo.save(personMapper.toModel(personDTO));
		return createMessageResponseDTO(savedPerson.getId(), "Created person with ID: ");
	}

	public List<PersonDTO> listAll() {
		return personRepo.findAll()
			.stream()
			.map(personMapper::toDTO)
			.collect(Collectors.toList());
	}

	public PersonDTO findById(Long id) throws PersonNotFoundException {
		return personMapper.toDTO(returnIfExists(id));
	}

	public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
		returnIfExists(id);
		Person updatedPerson = personRepo.save(personMapper.toModel(personDTO));
		return createMessageResponseDTO(id, "Updated person with ID: ");
	}

	public void deleteById(Long id) throws PersonNotFoundException {
		returnIfExists(id);
		personRepo.deleteById(id);
	}

	private MessageResponseDTO createMessageResponseDTO(Long id, String message) {
		return MessageResponseDTO.builder().message(message + id).build();
	}

	private Person returnIfExists(Long id) throws PersonNotFoundException {
		return personRepo.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
	}

}
