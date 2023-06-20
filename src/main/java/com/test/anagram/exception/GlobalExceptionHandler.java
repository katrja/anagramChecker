package com.test.anagram.exception;


import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  /**
   * Handles javax.validation.ConstraintViolationException. Thrown when @Validated fails.
   *
   * @param ex the ConstraintViolationException
   * @return the ApiException object
   */
  @ExceptionHandler(ConstraintViolationException.class)
  protected ModelAndView handleConstraintViolation(ConstraintViolationException ex) {
    List<ValidationMessage> errors = ex.getConstraintViolations().stream()
        .map(constraintViolation -> new ValidationMessage(
            getFieldFromPath(constraintViolation.getPropertyPath()),
            constraintViolation.getInvalidValue().toString(), constraintViolation.getMessage()))
        .collect(Collectors.toList());

    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("exceptions", errors);
    modelAndView.setViewName("index");
    return modelAndView;
  }

  private String getFieldFromPath(Path fieldPath) {
    PathImpl pathImpl = (PathImpl) fieldPath;
    return pathImpl.getLeafNode().toString();
  }

  @ExceptionHandler(ValidationMessageException.class)
  public final ModelAndView handleValidationMessageException(ValidationMessageException ex) {
    ModelAndView modelAndView = new ModelAndView();
    modelAndView.addObject("blankFieldValue", true);
    modelAndView.setViewName("index");
    return modelAndView;
  }
}
