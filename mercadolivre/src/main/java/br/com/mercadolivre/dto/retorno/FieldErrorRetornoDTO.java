package br.com.mercadolivre.dto.retorno;

public class FieldErrorRetornoDTO {

	  private String field;
	  private String message;

	  FieldErrorRetornoDTO() { 
	  }

	  public FieldErrorRetornoDTO(String field, String message) {
		  this.field = field;
	      this.message = message;
	  }

	  public String getField() {
	      return field;
	  }

	  public String getMessage() {
		  return message;
	  }
}
