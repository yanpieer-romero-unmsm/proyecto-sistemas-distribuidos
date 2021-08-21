package com.unmsm.distributedsystems.reservation.expose.web;

import com.unmsm.distributedsystems.reservation.model.dto.InvoiceDto;
import com.unmsm.distributedsystems.reservation.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService service;

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<InvoiceDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping(produces = "application/json", value = "/{id}")
    public ResponseEntity<Optional<InvoiceDto>> findById(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody InvoiceDto invoiceDto){
        InvoiceDto course = service.save(invoiceDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(course.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
