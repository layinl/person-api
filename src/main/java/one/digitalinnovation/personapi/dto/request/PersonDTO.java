package one.digitalinnovation.personapi.dto.request;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import one.digitalinnovation.personapi.entity.Phone;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

	private Long id;

	@NotEmpty
	@Size(min = 2, max = 100)
	private String firstName;

	@NotEmpty
	@Size(min = 2, max = 100)
	private String lastName;

	@NotEmpty
	@CPF
	private String cpf;

	private LocalDate birthDate;

	@Valid
	@NotEmpty
	private List<Phone> phones;

}
