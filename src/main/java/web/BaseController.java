package web;

import exception.ResourceNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by andgra on 2014-11-21.
 */
public abstract class BaseController {
    Logger logger = (Logger) Logger.getInstance(getClass());
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleError(ResourceNotFoundException e) {
        logger.error(e);
    }
}
