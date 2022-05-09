package com.hotel.service.exceptions;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.IM_USED)
@Data
public class ResourceIsUsedException extends RuntimeException{
    private String resourceName;
    private String fieldName;
    private Object fieldValue;

    public ResourceIsUsedException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s с этим %s : '%s' уже используется", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
