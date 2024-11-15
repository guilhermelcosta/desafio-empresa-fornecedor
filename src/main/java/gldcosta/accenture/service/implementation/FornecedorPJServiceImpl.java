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

import static gldcosta.accenture.constant.Constantes.CAMPOS_IGNORADOS;
import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
@AllArgsConstructor
public class FornecedorPJServiceImpl implements FornecedorPJService {

    private final CEPService cepService;
    private final FornecedorPJRepository fornecedorRepository;

    /**
     * Cria um novo fornecedor pessoa jurídica.
     *
     * @param objeto O fornecedor pessoa jurídica a ser criado.
     * @return O fornecedor pessoa jurídica criado.
     * @throws IllegalArgumentException se o CEP fornecido for inválido.
     */
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

    /**
     * Atualiza um fornecedor pessoa jurídica existente.
     *
     * @param objeto O fornecedor pessoa jurídica com os novos dados.
     * @param id     O identificador do fornecedor pessoa jurídica a ser atualizado.
     * @return O fornecedor pessoa jurídica atualizado.
     * @throws IllegalArgumentException se o CEP fornecido for inválido.
     */
    @Override
    public FornecedorPJ atualizar(FornecedorPJ objeto, Long id) {

        log.info("[INFO] [atualizar] [atualizando fornecedor - pessoa jurídica: {}]", objeto);

        FornecedorPJ fornecedor = buscarPorId(id);

        if (cepService.cepValido(objeto.getCep())) {
            copyProperties(objeto, fornecedor, CAMPOS_IGNORADOS);
            return fornecedorRepository.save(fornecedor);
        } else {
            log.error("[ERROR] [atualizar] [CEP inválido: {}]", objeto.getCep());
            throw new IllegalArgumentException("CEP inválido");
        }
    }

    /**
     * Busca um fornecedor pessoa jurídica pelo seu identificador.
     *
     * @param id O identificador do fornecedor pessoa jurídica.
     * @return O fornecedor pessoa jurídica encontrado.
     * @throws EntityNotFoundException se o fornecedor pessoa jurídica não for encontrado.
     */
    @Override
    public FornecedorPJ buscarPorId(Long id) {

        log.info("[INFO] [buscarPorId] [buscando fornecedor - pessoa jurídica por id: {}]", id);

        return fornecedorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Fornecedor não encontrado"));
    }

    /**
     * Busca todos os fornecedores pessoa jurídica com paginação.
     *
     * @param indice  O índice da página (começando em 0).
     * @param tamanho O tamanho da página.
     * @return Uma página de fornecedores pessoa jurídica.
     */
    @Override
    public Page<FornecedorPJ> buscarTodos(int indice, int tamanho) {

        log.info("[INFO] [buscarTodos] [buscando todos os fornecedores - pessoa jurídica]");

        return fornecedorRepository.findAll(PageRequest.of(indice, tamanho));
    }

    /**
     * Deleta um fornecedor pessoa jurídica pelo seu identificador.
     *
     * @param id O identificador do fornecedor pessoa jurídica a ser deletado.
     */
    @Override
    public void deletarPorId(Long id) {

        log.info("[INFO] [deletarPorId] [deletando fornecedor - pessoa jurídica por id: {}]", id);

        buscarPorId(id);

        fornecedorRepository.deleteById(id);
    }

    /**
     * Busca todos os fornecedores pessoa jurídica associados a um identificador de empresa com paginação.
     *
     * @param empresaId O identificador da empresa.
     * @param pagina    O índice da página (começando em 0).
     * @param tamanho   O tamanho da página.
     * @return Uma página de fornecedores pessoa jurídica.
     */
    @Override
    public Page<FornecedorPJ> buscarPorEmpresaId(Long empresaId, int pagina, int tamanho) {

        log.info("[INFO] [buscarPorEmpresaId] [buscando fornecedores - pessoa jurídica por id da empresa: {}]",
                 empresaId);

        return fornecedorRepository.findAllByEmpresasId(empresaId, PageRequest.of(pagina, tamanho));
    }

    /**
     * Busca todos os fornecedores pessoa jurídica por CNPJ com paginação.
     *
     * @param cnpj    O CNPJ dos fornecedores pessoa jurídica a serem buscados.
     * @param pagina  O índice da página (começando em 0).
     * @param tamanho O tamanho da página.
     * @return Uma página de fornecedores pessoa jurídica.
     */
    @Override
    public Page<FornecedorPJ> buscarPorCNPJ(String cnpj, int pagina, int tamanho) {

        log.info("[INFO] [buscarPorCNPJ] [buscando fornecedores - pessoa jurídica por CNPJ: {}]", cnpj);

        return fornecedorRepository.findAllByCnpj(cnpj, PageRequest.of(pagina, tamanho));
    }
}