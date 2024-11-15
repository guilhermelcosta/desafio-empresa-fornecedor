package gldcosta.accenture.service.implementation;

import gldcosta.accenture.entity.FornecedorPF;
import gldcosta.accenture.repository.FornecedorPFRepository;
import gldcosta.accenture.service.CEPService;
import gldcosta.accenture.service.FornecedorPFService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static gldcosta.accenture.constant.Constants.CAMPOS_IGNORADOS;
import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
@AllArgsConstructor
public class FornecedorPFServiceImpl implements FornecedorPFService {

    private final CEPService cepService;
    private final FornecedorPFRepository fornecedorRepository;

    @Override
    public FornecedorPF criar(FornecedorPF objeto) {

        log.info("[INFO] [criar] [criando fornecedor - pessoa física: {}]", objeto);

        if (cepService.cepValido(objeto.getCep()))
            return fornecedorRepository.save(objeto);
        else {
            log.error("[ERROR] [criar] [CEP inválido: {}]", objeto.getCep());

            throw new IllegalArgumentException("CEP inválido");
        }

    }

    @Override
    public FornecedorPF atualizar(FornecedorPF objeto, Long id) {

        log.info("[INFO] [atualizar] [atualizando fornecedor - pessoa física: {}]", objeto);

        FornecedorPF fornecedor = buscarPorId(id);

        if (cepService.cepValido(objeto.getCep())) {

            copyProperties(objeto, fornecedor, CAMPOS_IGNORADOS);

            return fornecedorRepository.save(fornecedor);
        } else {
            log.error("[ERROR] [criar] [CEP inválido: {}]", objeto.getCep());

            throw new IllegalArgumentException("CEP inválido");
        }
    }

    @Override
    public FornecedorPF buscarPorId(Long id) {

        log.info("[INFO] [buscarPorId] [buscando fornecedor - pessoa física por id: {}]", id);

        return fornecedorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Fornecedor não encontrado"));
    }

    @Override
    public Page<FornecedorPF> buscarTodos(int indice, int tamanho) {

        log.info("[INFO] [buscarTodos] [buscando todos os fornecedores - pessoa física]");

        return fornecedorRepository.findAll(PageRequest.of(indice, tamanho));
    }

    @Override
    public void deletarPorId(Long id) {

        log.info("[INFO] [deletarPorId] [deletando fornecedor - pessoa física por id: {}]", id);

        buscarPorId(id);

        fornecedorRepository.deleteById(id);
    }

    @Override
    public Page<FornecedorPF> buscarPorEmpresaId(Long empresaId, int pagina, int tamanho) {

        return fornecedorRepository.findAllByEmpresasId(empresaId, PageRequest.of(pagina, tamanho));
    }
}
