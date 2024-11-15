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

    /**
     * Cria um novo fornecedor pessoa física.
     *
     * @param objeto O fornecedor pessoa física a ser criado.
     * @return O fornecedor pessoa física criado.
     * @throws IllegalArgumentException se o CEP fornecido for inválido.
     */
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

    /**
     * Atualiza um fornecedor pessoa física existente.
     *
     * @param objeto O fornecedor pessoa física com os novos dados.
     * @param id     O identificador do fornecedor pessoa física a ser atualizado.
     * @return O fornecedor pessoa física atualizado.
     * @throws IllegalArgumentException se o CEP fornecido for inválido.
     */
    @Override
    public FornecedorPF atualizar(FornecedorPF objeto, Long id) {

        log.info("[INFO] [atualizar] [atualizando fornecedor - pessoa física: {}]", objeto);

        FornecedorPF fornecedor = buscarPorId(id);

        if (cepService.cepValido(objeto.getCep())) {
            copyProperties(objeto, fornecedor, CAMPOS_IGNORADOS);
            return fornecedorRepository.save(fornecedor);
        } else {
            log.error("[ERROR] [atualizar] [CEP inválido: {}]", objeto.getCep());
            throw new IllegalArgumentException("CEP inválido");
        }
    }

    /**
     * Busca um fornecedor pessoa física pelo seu identificador.
     *
     * @param id O identificador do fornecedor pessoa física.
     * @return O fornecedor pessoa física encontrado.
     * @throws EntityNotFoundException se o fornecedor pessoa física não for encontrado.
     */
    @Override
    public FornecedorPF buscarPorId(Long id) {

        log.info("[INFO] [buscarPorId] [buscando fornecedor - pessoa física por id: {}]", id);

        return fornecedorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Fornecedor não encontrado"));
    }

    /**
     * Busca todos os fornecedores pessoa física com paginação.
     *
     * @param indice  O índice da página (começando em 0).
     * @param tamanho O tamanho da página.
     * @return Uma página de fornecedores pessoa física.
     */
    @Override
    public Page<FornecedorPF> buscarTodos(int indice, int tamanho) {

        log.info("[INFO] [buscarTodos] [buscando todos os fornecedores - pessoa física]");

        return fornecedorRepository.findAll(PageRequest.of(indice, tamanho));
    }

    /**
     * Deleta um fornecedor pessoa física pelo seu identificador.
     *
     * @param id O identificador do fornecedor pessoa física a ser deletado.
     */
    @Override
    public void deletarPorId(Long id) {

        log.info("[INFO] [deletarPorId] [deletando fornecedor - pessoa física por id: {}]", id);

        buscarPorId(id);

        fornecedorRepository.deleteById(id);
    }

    /**
     * Busca todos os fornecedores pessoa física associados a um identificador de empresa com paginação.
     *
     * @param empresaId O identificador da empresa.
     * @param pagina    O índice da página (começando em 0).
     * @param tamanho   O tamanho da página.
     * @return Uma página de fornecedores pessoa física.
     */
    @Override
    public Page<FornecedorPF> buscarPorEmpresaId(Long empresaId, int pagina, int tamanho) {

        log.info("[INFO] [buscarPorEmpresaId] [buscando fornecedores - pessoa física por id da empresa: {}]",
                 empresaId);

        return fornecedorRepository.findAllByEmpresasId(empresaId, PageRequest.of(pagina, tamanho));
    }

    /**
     * Busca todos os fornecedores pessoa física por CPF com paginação.
     *
     * @param cpf     O CPF dos fornecedores pessoa física a serem buscados.
     * @param pagina  O índice da página (começando em 0).
     * @param tamanho O tamanho da página.
     * @return Uma página de fornecedores pessoa física.
     */
    @Override
    public Page<FornecedorPF> buscarPorCPF(String cpf, int pagina, int tamanho) {

        log.info("[INFO] [buscarPorCPF] [buscando fornecedores - pessoa física por CPF: {}]", cpf);

        return fornecedorRepository.findAllByCpf(cpf, PageRequest.of(pagina, tamanho));
    }
}