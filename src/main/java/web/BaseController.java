package web;

import exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by andgra on 2014-11-21.
 */
public abstract class BaseController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleError(ResourceNotFoundException e) {
        logger.error(String.valueOf(e));
    }
}
