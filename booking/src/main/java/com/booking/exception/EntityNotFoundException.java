package com.booking.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException{
	private String entityName;
    private String field;
    private Object value;

    public EntityNotFoundException( String entityName, String field, Object value) {
        super(String.format("%s not found with %s : '%s'", entityName, field, value));
        this.entityName = entityName;
        this.field = field;
        this.value = value;
    }

	public String getEntityName() {
		return entityName;
	}

	public String getField() {
		return field;
	}

	public Object getValue() {
		return value;
	}

}
