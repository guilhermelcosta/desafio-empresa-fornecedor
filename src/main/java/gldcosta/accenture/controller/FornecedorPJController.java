package gldcosta.accenture.controller;

import gldcosta.accenture.controller.base.CrudController;
import gldcosta.accenture.entity.FornecedorPJ;
import gldcosta.accenture.service.FornecedorPJService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fornecedor-pj")
public class FornecedorPJController extends CrudController<FornecedorPJ, FornecedorPJ, Long> {

    private final FornecedorPJService fornecedorPJService;

    public FornecedorPJController(FornecedorPJService fornecedorPJService) {
        super(fornecedorPJService);
        this.fornecedorPJService = fornecedorPJService;
    }

    /**
     * Busca fornecedores PJ por empresa
     *
     * @param empresaId ID da empresa
     * @param indice    Índice da página
     * @param tamanho   Tamanho da página
     * @return Fornecedores PJ da empresa
     */
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<Page<FornecedorPJ>> buscarPorEmpresaId(@PathVariable Long empresaId,
                                                                 @RequestParam(defaultValue = "0") int indice,
                                                                 @RequestParam(defaultValue = "10") int tamanho) {
        return ResponseEntity.ok(fornecedorPJService.buscarPorEmpresaId(empresaId, indice, tamanho));
    }

    /**
     * Busca fornecedores PJ por CNPJ
     *
     * @param cnpj    CNPJ do fornecedor PJ
     * @param indice  Índice da página
     * @param tamanho Tamanho da página
     * @return Fornecedores PJ com o CNPJ informado
     */
    @GetMapping("/cnpj/{cnpj}")
    public ResponseEntity<Page<FornecedorPJ>> buscarPorCPF(@PathVariable String cnpj,
                                                           @RequestParam(defaultValue = "0") int indice,
                                                           @RequestParam(defaultValue = "10") int tamanho) {
        return ResponseEntity.ok(fornecedorPJService.buscarPorCNPJ(cnpj, indice, tamanho));
    }
}
