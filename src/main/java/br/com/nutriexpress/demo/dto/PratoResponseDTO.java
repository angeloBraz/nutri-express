package br.com.nutriexpress.demo.dto;

import java.math.BigDecimal;

/**
 * DTO de resposta para o recurso Prato.
 * Justificativa (Critério do PDF): Optamos por retornar todos os campos da entidade, 
 * pois todas essas informações (calorias, preço, descrição) são relevantes e necessárias 
 * para o front-end montar o cardápio e os detalhes do prato para o cliente. 
 * Não há dados sensíveis a serem omitidos neste domínio.
 */
public record PratoResponseDTO(
        Long id,
        String nome,
        String descricao,
        BigDecimal valor,
        String categoria,
        Integer calorias,
        Double quantidade,
        String unidadeMedida
) {}
