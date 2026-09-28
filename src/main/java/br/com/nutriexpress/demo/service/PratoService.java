package br.com.nutriexpress.demo.service;

import br.com.nutriexpress.demo.dto.PratoRequestDTO;
import br.com.nutriexpress.demo.dto.PratoResponseDTO;
import br.com.nutriexpress.demo.exception.PratoNaoEncontradoException;
import br.com.nutriexpress.demo.exception.RegraNegocioException;
import br.com.nutriexpress.demo.model.Prato;
import br.com.nutriexpress.demo.repository.PratoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PratoService {

    private final PratoRepository pratoRepository;

    public PratoService(PratoRepository pratoRepository) {
        this.pratoRepository = pratoRepository;
    }

    public PratoResponseDTO criar(PratoRequestDTO dto) {
        // Regra de negócio: não permitir dois pratos com o mesmo nome
        if (pratoRepository.existsByNome(dto.nome())) {
            throw new RegraNegocioException("Já existe um prato cadastrado com este nome.");
        }

        Prato prato = toEntity(dto);
        Prato salvo = pratoRepository.save(prato);
        return toDTO(salvo);
    }

    public List<PratoResponseDTO> listarTodos() {
        return pratoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PratoResponseDTO buscarPorId(Long id) {
        Prato prato = pratoRepository.findById(id)
                .orElseThrow(() -> new PratoNaoEncontradoException("Prato não encontrado com o ID: " + id));
        return toDTO(prato);
    }

    public List<PratoResponseDTO> listarPorCategoria(String categoria) {
        return pratoRepository.findByCategoria(categoria).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<PratoResponseDTO> listarPorCaloriasMaximas(Integer max) {
        return pratoRepository.findByCaloriasLessThanEqual(max).stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public PratoResponseDTO atualizar(Long id, PratoRequestDTO dto) {
        Prato prato = pratoRepository.findById(id)
                .orElseThrow(() -> new PratoNaoEncontradoException("Prato não encontrado com o ID: " + id));

        // Regra de negócio: não permitir atualizar o nome para um já existente (se não for o próprio)
        if (!prato.getNome().equals(dto.nome()) && pratoRepository.existsByNome(dto.nome())) {
            throw new RegraNegocioException("Já existe um prato cadastrado com este nome.");
        }

        prato.setNome(dto.nome());
        prato.setDescricao(dto.descricao());
        prato.setValor(dto.valor());
        prato.setCategoria(dto.categoria());
        prato.setCalorias(dto.calorias());
        prato.setQuantidade(dto.quantidade());
        prato.setUnidadeMedida(dto.unidadeMedida());

        Prato salvo = pratoRepository.save(prato);
        return toDTO(salvo);
    }

    public PratoResponseDTO atualizarValor(Long id, BigDecimal valor) {
        Prato prato = pratoRepository.findById(id)
                .orElseThrow(() -> new PratoNaoEncontradoException("Prato não encontrado com o ID: " + id));
        
        prato.setValor(valor);
        Prato salvo = pratoRepository.save(prato);
        return toDTO(salvo);
    }

    public void remover(Long id) {
        if (!pratoRepository.existsById(id)) {
            throw new PratoNaoEncontradoException("Prato não encontrado com o ID: " + id);
        }
        pratoRepository.deleteById(id);
    }

    private Prato toEntity(PratoRequestDTO dto) {
        Prato prato = new Prato();
        prato.setNome(dto.nome());
        prato.setDescricao(dto.descricao());
        prato.setValor(dto.valor());
        prato.setCategoria(dto.categoria());
        prato.setCalorias(dto.calorias());
        prato.setQuantidade(dto.quantidade());
        prato.setUnidadeMedida(dto.unidadeMedida());
        return prato;
    }

    private PratoResponseDTO toDTO(Prato prato) {
        return new PratoResponseDTO(
                prato.getId(),
                prato.getNome(),
                prato.getDescricao(),
                prato.getValor(),
                prato.getCategoria(),
                prato.getCalorias(),
                prato.getQuantidade(),
                prato.getUnidadeMedida()
        );
    }
}
