package gldcosta.accenture.service.implementation;

import gldcosta.accenture.entity.FornecedorPJ;
import gldcosta.accenture.repository.FornecedorPJRepository;
import gldcosta.accenture.service.CEPService;
import gldcosta.accenture.service.FornecedorPJService;
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
public class FornecedorPJServiceImpl implements FornecedorPJService {

    private final CEPService cepService;
    private final FornecedorPJRepository fornecedorRepository;

    @Override
    public FornecedorPJ criar(FornecedorPJ objeto) {

        log.info("[INFO] [criar] [criando fornecedor - pessoa jurídica: {}]", objeto);

        if (cepService.cepValido(objeto.getCep()))
            return fornecedorRepository.save(objeto);
        else {
            log.error("[ERROR] [criar] [CEP inválido: {}]", objeto.getCep());

            throw new IllegalArgumentException("CEP inválido");
        }

    }

    @Override
    public FornecedorPJ atualizar(FornecedorPJ objeto, Long id) {

        log.info("[INFO] [atualizar] [atualizando fornecedor - pessoa jurídica: {}]", objeto);

        FornecedorPJ fornecedor = buscarPorId(id);

        if (cepService.cepValido(objeto.getCep())) {

            copyProperties(objeto, fornecedor, CAMPOS_IGNORADOS);

            return fornecedorRepository.save(fornecedor);
        } else {
            log.error("[ERROR] [criar] [CEP inválido: {}]", objeto.getCep());

            throw new IllegalArgumentException("CEP inválido");
        }
    }

    @Override
    public FornecedorPJ buscarPorId(Long id) {

        log.info("[INFO] [buscarPorId] [buscando fornecedor - pessoa jurídica por id: {}]", id);

        return fornecedorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Fornecedor não encontrado"));
    }

    @Override
    public Page<FornecedorPJ> buscarTodos(int indice, int tamanho) {

        log.info("[INFO] [buscarTodos] [buscando todos os fornecedores - pessoa jurídica]");

        return fornecedorRepository.findAll(PageRequest.of(indice, tamanho));
    }

    @Override
    public void deletarPorId(Long id) {

        log.info("[INFO] [deletarPorId] [deletando fornecedor - pessoa jurídica por id: {}]", id);

        buscarPorId(id);

        fornecedorRepository.deleteById(id);
    }

    @Override
    public Page<FornecedorPJ> buscarPorEmpresaId(Long empresaId, int pagina, int tamanho) {

        return fornecedorRepository.findAllByEmpresasId(empresaId, PageRequest.of(pagina, tamanho));
    }
}
