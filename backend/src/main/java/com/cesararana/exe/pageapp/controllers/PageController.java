package com.cesararana.exe.pageapp.controllers;

import com.cesararana.exe.pageapp.application.domain.DataView;
import com.cesararana.exe.pageapp.application.domain.Page;
import com.cesararana.exe.pageapp.application.domain.Query;
import com.cesararana.exe.pageapp.application.exceptions.PageAppException;
import com.cesararana.exe.pageapp.application.impl.PageAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestController()
@CrossOrigin("*")
@RequestMapping("/api/page")
@RequiredArgsConstructor
@ControllerAdvice
public class PageController {

    private final PageAppService pageService;

    @RequestMapping(value = "/{slug}", method = RequestMethod.GET)
    ResponseEntity<Page> getPage(@PathVariable() String slug) {
        return pageService
                .get(slug)
                .map(ResponseEntity::ok)
                .orElse( ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    DataView<Page> getAll(
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "0") int page
    ) {
        return pageService.getPaged(
                Query.builder()
                        .page(page)
                        .size(size)
                        .build()
        );
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    Page create(@RequestBody Page page) throws PageAppException {
        return pageService.save(page);
    }

    // handler

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Map<String, Object> handleValidationExceptions(
            ConstraintViolationException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(cv -> {
            errors.put(cv.getPropertyPath().toString(), cv.getMessage());
        });
        return Map.of("errors", errors);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PageAppException.class)
    public Map<String, Object> handleValidationExceptions(
            PageAppException ex) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", ex.getMessage());
        return Map.of("errors", errors);
    }
}
