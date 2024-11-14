package gldcosta.accenture.service.implementation;

import gldcosta.accenture.entity.Empresa;
import gldcosta.accenture.repository.EmpresaRepository;
import gldcosta.accenture.service.EmpresaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static gldcosta.accenture.constant.Constants.CAMPOS_IGNORADOS;
import static org.springframework.beans.BeanUtils.copyProperties;

/**
 * Implementação do serviço para gerenciar entidades {@link Empresa}.
 * Esta classe fornece métodos para criar, atualizar, buscar e deletar empresas.
 */
@Slf4j
@Service
@AllArgsConstructor
public class EmpresaServiceImpl implements EmpresaService {

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
}