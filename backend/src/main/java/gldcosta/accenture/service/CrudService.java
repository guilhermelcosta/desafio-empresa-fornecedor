package gldcosta.accenture.service;

import org.springframework.data.domain.Page;

/**
 * Interface de serviço genérica para operações CRUD (Create, Read, Update, Delete).
 * Esta interface define os métodos básicos para gerenciar entidades.
 *
 * @param <I>  O tipo do objeto de transferência de dados de entrada (input).
 * @param <O>  O tipo do objeto de transferência de dados de saída (output).
 * @param <ID> O tipo do identificador da entidade.
 */
public interface CrudService<I, O, ID> {

    /**
     * Cria uma nova entidade.
     *
     * @param objeto A entidade a ser criada.
     * @return A entidade criada.
     */
    O criar(I objeto);

    /**
     * Atualiza uma entidade existente.
     *
     * @param objeto A entidade com os novos dados.
     * @param id     O identificador da entidade a ser atualizada.
     * @return A entidade atualizada.
     */
    O atualizar(I objeto, ID id);

    /**
     * Busca uma entidade pelo seu identificador.
     *
     * @param id O identificador da entidade.
     * @return A entidade encontrada.
     */
    O buscarPorId(ID id);

    /**
     * Busca todas as entidades com paginação.
     *
     * @param indice  O índice da página (começando em 0).
     * @param tamanho O tamanho da página.
     * @return Uma página de entidades.
     */
    Page<O> buscarTodos(int indice, int tamanho);

    /**
     * Deleta uma entidade pelo seu identificador.
     *
     * @param id O identificador da entidade a ser deletada.
     */
    void deletarPorId(ID id);
}