package br.com.nutriexpress.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PratoRequestDTO(
        @NotBlank(message = "O nome é obrigatório")
        String nome,
        
        @NotBlank(message = "A descrição é obrigatória")
        String descricao,
        
        @NotNull(message = "O valor é obrigatório")
        @Positive(message = "O valor deve ser positivo")
        BigDecimal valor,
        
        @NotBlank(message = "A categoria é obrigatória")
        String categoria,
        
        @NotNull(message = "As calorias são obrigatórias")
        @Positive(message = "As calorias devem ser positivas")
        Integer calorias,
        
        @NotNull(message = "A quantidade é obrigatória")
        @Positive(message = "A quantidade deve ser positiva")
        Double quantidade,
        
        @NotBlank(message = "A unidade de medida é obrigatória")
        String unidadeMedida
) {}
