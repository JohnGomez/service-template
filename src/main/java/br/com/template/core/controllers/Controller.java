package br.com.template.core.controllers;

import br.com.template.core.services.CrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

public class Controller<T extends CrudService, D> {

    @Autowired
    private T service;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody D client) {
        service.create(client);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> getById(@PathVariable("id") Integer id) throws Throwable {
        return ResponseEntity.ok((D) service.get(id));
    }

    @GetMapping("")
    public ResponseEntity<List<D>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }
}
