package gldcosta.accenture.controller;

import gldcosta.accenture.controller.base.CrudController;
import gldcosta.accenture.entity.FornecedorPF;
import gldcosta.accenture.service.FornecedorPFService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fornecedor-pf")
public class FornecedorPFController extends CrudController<FornecedorPF, FornecedorPF, Long> {

    private final FornecedorPFService fornecedorPFService;

    public FornecedorPFController(FornecedorPFService fornecedorPFService) {
        super(fornecedorPFService);
        this.fornecedorPFService = fornecedorPFService;
    }

    /**
     * Busca fornecedores PF por empresa
     *
     * @param empresaId ID da empresa
     * @param indice    Índice da página
     * @param tamanho   Tamanho da página
     * @return Fornecedores PF da empresa
     */
    @GetMapping("/empresa/{empresaId}")
    public ResponseEntity<Page<FornecedorPF>> buscarPorEmpresaId(@PathVariable Long empresaId,
                                                                 @RequestParam(defaultValue = "0") int indice,
                                                                 @RequestParam(defaultValue = "10") int tamanho) {
        return ResponseEntity.ok(fornecedorPFService.buscarPorEmpresaId(empresaId, indice, tamanho));
    }

    /**
     * Busca fornecedores PF por CPF
     *
     * @param cpf     CPF do fornecedor PF
     * @param indice  Índice da página
     * @param tamanho Tamanho da página
     * @return Fornecedores PF com o CPF informado
     */
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Page<FornecedorPF>> buscarPorCPF(@PathVariable String cpf,
                                                           @RequestParam(defaultValue = "0") int indice,
                                                           @RequestParam(defaultValue = "10") int tamanho) {
        return ResponseEntity.ok(fornecedorPFService.buscarPorCPF(cpf, indice, tamanho));
    }
}
