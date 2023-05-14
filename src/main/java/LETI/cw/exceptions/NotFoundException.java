package LETI.cw.exceptions;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Id not found.")
public class NotFoundException extends RuntimeException {
    public NotFoundException() {

    }

}
