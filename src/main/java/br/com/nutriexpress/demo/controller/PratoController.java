package br.com.nutriexpress.demo.controller;

import br.com.nutriexpress.demo.dto.PratoRequestDTO;
import br.com.nutriexpress.demo.dto.PratoResponseDTO;
import br.com.nutriexpress.demo.service.PratoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pratos")
public class PratoController {

    private final PratoService pratoService;

    public PratoController(PratoService pratoService) {
        this.pratoService = pratoService;
    }

    @GetMapping
    public ResponseEntity<List<PratoResponseDTO>> listarTodos(@RequestParam(required = false) String categoria) {
        if (categoria != null) {
            return ResponseEntity.ok(pratoService.listarPorCategoria(categoria));
        }
        return ResponseEntity.ok(pratoService.listarTodos());
    }

    @GetMapping("/calorias")
    public ResponseEntity<List<PratoResponseDTO>> listarPorCalorias(@RequestParam(name = "max") Integer max) {
        return ResponseEntity.ok(pratoService.listarPorCaloriasMaximas(max));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PratoResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pratoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<PratoResponseDTO> criar(@Valid @RequestBody PratoRequestDTO dto) {
        PratoResponseDTO criado = pratoService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PratoResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody PratoRequestDTO dto) {
        return ResponseEntity.ok(pratoService.atualizar(id, dto));
    }

    @PatchMapping("/{id}/valor")
    public ResponseEntity<PratoResponseDTO> atualizarValor(@PathVariable Long id, @RequestBody Map<String, BigDecimal> body) {
        if (!body.containsKey("valor") || body.get("valor") == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(pratoService.atualizarValor(id, body.get("valor")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        pratoService.remover(id);
        return ResponseEntity.noContent().build();
    }
}
