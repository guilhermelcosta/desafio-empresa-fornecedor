package gldcosta.accenture.controller.base;

import gldcosta.accenture.service.CrudService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.ResponseEntity.noContent;
import static org.springframework.http.ResponseEntity.ok;

/**
 * Controlador base para operações CRUD (Create, Read, Update, Delete).
 * Esta classe abstrata fornece endpoints RESTful para gerenciar entidades genéricas.
 *
 * @param <I>  O tipo da entidade.
 * @param <ID> O tipo do identificador da entidade.
 */
@AllArgsConstructor
public abstract class CrudController<I, O, ID> {

    private final CrudService<I, O, ID> crudService;

    /**
     * Cria uma nova entidade.
     *
     * @param objeto A entidade a ser criada.
     * @return A entidade criada.
     */
    @PostMapping
    public ResponseEntity<O> criar(@Valid @RequestBody I objeto) {
        return ok(crudService.criar(objeto));
    }

    /**
     * Atualiza uma entidade existente.
     *
     * @param objeto A entidade com os novos dados.
     * @param id     O identificador da entidade a ser atualizada.
     * @return A entidade atualizada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<O> atualizar(@Valid @RequestBody I objeto, @PathVariable ID id) {
        return ok(crudService.atualizar(objeto, id));
    }

    /**
     * Busca uma entidade pelo seu identificador.
     *
     * @param id O identificador da entidade.
     * @return A entidade encontrada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<O> buscarPorId(@PathVariable ID id) {
        return ok(crudService.buscarPorId(id));
    }

    /**
     * Busca todas as entidades com paginação.
     *
     * @param indice  O índice da página (começando em 0).
     * @param tamanho O tamanho da página.
     * @return Uma página de entidades.
     */
    @GetMapping
    public ResponseEntity<Page<O>> buscarTodos(@RequestParam(defaultValue = "0") int indice,
                                               @RequestParam(defaultValue = "10") int tamanho) {
        return ok(crudService.buscarTodos(indice, tamanho));
    }

    /**
     * Deleta uma entidade pelo seu identificador.
     *
     * @param id O identificador da entidade a ser deletada.
     * @return Uma resposta sem conteúdo.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable ID id) {
        crudService.deletarPorId(id);
        return noContent().build();
    }
}