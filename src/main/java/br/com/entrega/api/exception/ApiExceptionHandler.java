package br.com.entrega.api.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.entrega.domain.exception.NegocioException;


@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<ApiLogErro.Campo> campos = new ArrayList<>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			campos.add(new ApiLogErro.Campo(nome, mensagem));
		}
		
		ApiLogErro apiLogErro = new ApiLogErro();
		apiLogErro.setDataHora(LocalDateTime.now());
		apiLogErro.setStatus(status.value());
		apiLogErro.setTitulo("Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente");
		apiLogErro.setCampos(campos);
		
		return handleExceptionInternal(ex, apiLogErro , headers, status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocioException(NegocioException ex, WebRequest request){
		HttpStatus status = HttpStatus.BAD_REQUEST;
		ApiLogErro apiLogErro = new ApiLogErro();
		apiLogErro.setTitulo(ex.getMessage());
		apiLogErro.setDataHora(LocalDateTime.now());
		apiLogErro.setStatus(status.value());
		
		return handleExceptionInternal(ex, apiLogErro, new HttpHeaders(), status, request);
	}
	
}
