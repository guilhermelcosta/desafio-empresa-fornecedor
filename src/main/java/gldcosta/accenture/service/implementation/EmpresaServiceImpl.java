package gldcosta.accenture.service.implementation;

import gldcosta.accenture.entity.Empresa;
import gldcosta.accenture.entity.FornecedorPF;
import gldcosta.accenture.entity.FornecedorPJ;
import gldcosta.accenture.repository.EmpresaRepository;
import gldcosta.accenture.service.CEPService;
import gldcosta.accenture.service.EmpresaService;
import gldcosta.accenture.service.FornecedorPFService;
import gldcosta.accenture.service.FornecedorPJService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static gldcosta.accenture.constant.Constants.*;
import static java.time.LocalDate.now;
import static java.util.stream.Collectors.toSet;
import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * Implementação do serviço para gerenciar entidades {@link Empresa}.
 * Esta classe fornece métodos para criar, atualizar, buscar e deletar empresas.
 */
@Slf4j
@Service
@AllArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

    private final FornecedorPJService fornecedorPJService;
    private final FornecedorPFService fornecedorPFService;
    private final CEPService cepService;
    private final EmpresaRepository empresaRepository;

    /**
     * Cria uma nova empresa.
     *
     * @param objeto A empresa a ser criada.
     * @return A empresa criada.
     */
    @Override
    public Empresa criar(Empresa objeto) {

        log.info("[INFO] [criar] [criando empresa: {}]", objeto);

        return empresaRepository.save(objeto);
    }

    /**
     * Atualiza uma empresa existente.
     *
     * @param objeto A empresa com os novos dados.
     * @param id     O identificador da empresa a ser atualizada.
     * @return A empresa atualizada.
     */
    @Override
    public Empresa atualizar(Empresa objeto, Long id) {

        log.info("[INFO] [atualizar] [atualizando empresa: {}]", objeto);

        Empresa empresa = buscarPorId(id);

        copyProperties(objeto, empresa, CAMPOS_IGNORADOS);

        return empresaRepository.save(empresa);
    }

    /**
     * Busca uma empresa pelo seu identificador.
     *
     * @param id O identificador da empresa.
     * @return A empresa encontrada.
     * @throws EntityNotFoundException Se a empresa não for encontrada.
     */
    @Override
    public Empresa buscarPorId(Long id) {

        log.info("[INFO] [buscarPorTd] [buscando empresa por id: {}]", id);

        return empresaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Empresa não encontrada"));
    }

    /**
     * Busca todas as empresas com paginação.
     *
     * @param indice  O índice da página (começando em 0).
     * @param tamanho O tamanho da página.
     * @return Uma página de empresas.
     */
    @Override
    public Page<Empresa> buscarTodos(int indice, int tamanho) {

        log.info("[INFO] [buscarTodos] [buscando todas as empresas]");

        return empresaRepository.findAll(PageRequest.of(indice, tamanho));
    }

    /**
     * Deleta uma empresa pelo seu identificador.
     *
     * @param id O identificador da empresa a ser deletada.
     */
    @Override
    public void deletarPorId(Long id) {

        log.info("[INFO] [deletarPorTd] [deletando empresa por id: {}]", id);

        buscarPorId(id);

        empresaRepository.deleteById(id);
    }

    /**
     * Atualiza os fornecedores PJ de uma empresa.
     *
     * @param idEmpresa        O identificador da empresa.
     * @param idFornecedoresPJ A lista de identificadores dos fornecedores PJ.
     * @return A empresa atualizada.
     */
    @Override
    public Empresa atualizarFornecedorPJ(Long idEmpresa, List<Long> idFornecedoresPJ) {

        log.info("[INFO] [atualizarFornecedorPJ] [atualizando fornecedores PJ da empresa: {}]", idEmpresa);

        Empresa empresa = buscarPorId(idEmpresa);
        Set<FornecedorPJ> fornecedorPJ = idFornecedoresPJ.stream()
                .map(fornecedorPJService::buscarPorId)
                .collect(toSet());

        empresa.setFornecedoresPJ(fornecedorPJ);

        return empresaRepository.save(empresa);
    }

    /**
     * Atualiza os fornecedores PF de uma empresa.
     *
     * @param idEmpresa        O identificador da empresa.
     * @param idFornecedoresPF A lista de identificadores dos fornecedores PF.
     * @return A empresa atualizada.
     */
    @Override
    public Empresa atualizarFornecedorPF(Long idEmpresa, List<Long> idFornecedoresPF) {

        log.info("[INFO] [atualizarFornecedorPF] [atualizando fornecedores PF da empresa: {}]", idEmpresa);

        Empresa empresa = buscarPorId(idEmpresa);
        Set<FornecedorPF> fornecedorPF = idFornecedoresPF.stream()
                .map(fornecedorPFService::buscarPorId)
                .collect(toSet());

        validarIdadeFornecedorPF(empresa, fornecedorPF);
        empresa.setFornecedoresPF(fornecedorPF);

        return empresaRepository.save(empresa);
    }

    /**
     * Valida a idade dos fornecedores PF de uma empresa.
     *
     * @param empresa      A empresa.
     * @param fornecedorPF O conjunto de fornecedores PF.
     * @throws RuntimeException Se algum fornecedor PF for menor de idade e a empresa estiver no Paraná.
     */
    private void validarIdadeFornecedorPF(Empresa empresa, Set<FornecedorPF> fornecedorPF) {
        Map<String, Object> dadosCEP = cepService.obterDadosCEP(empresa.getCep());

        if (ESTADOS_MAPEADOS_FORNECEDOR_MENOR_DE_IDADE.contains(dadosCEP.get(CHAVE_ESTADO))) {
            fornecedorPF.forEach(fornecedor -> {
                if (fornecedorPF.iterator().next().getDataNascimento().isAfter(now().minusYears(DEZOITO_ANOS)))
                    throw new RuntimeException("Fornecedor menor de idade não pode ser cadastrado no Paraná");
            });
        }
    }
}