package br.com.johnatanbrayan.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.johnatanbrayan.domain.Cliente;
import br.com.johnatanbrayan.domain.dto.ClienteNewDTO;
import br.com.johnatanbrayan.domain.enums.TipoCliente;
import br.com.johnatanbrayan.repository.ClienteRepository;
import br.com.johnatanbrayan.resources.exception.FieldMessage;
import br.com.johnatanbrayan.service.validation.utils.BR;

public class ClienteInsertValidation implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Override
	public void initialize(ClienteInsert ann) {}

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		
		if(aux != null) {
			list.add(new FieldMessage("email", "Email já existe!!"));
		}
		
		if(objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj","CPF inválido"));
		}
		
		if(objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}