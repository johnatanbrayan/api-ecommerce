package br.com.johnatanbrayan.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.johnatanbrayan.domain.Cliente;
import br.com.johnatanbrayan.domain.dto.ClienteDTO;
import br.com.johnatanbrayan.repository.ClienteRepository;
import br.com.johnatanbrayan.resources.exception.FieldMessage;

public class ClienteUpdateValidation implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Override
	public void initialize(ClienteUpdate ann) {}
	
	@Autowired
	private HttpServletRequest request;

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public boolean isValid(ClienteDTO objDto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		
		Long uriPathId = Long.parseLong(map.get("id"));
				
		Cliente aux = clienteRepository.findByEmail(objDto.getEmail());
		
		if(aux != null && !aux.getId().equals(uriPathId)) {
			list.add(new FieldMessage("email", "Email já existe!!"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return list.isEmpty();
	}
}