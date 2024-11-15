package gldcosta.accenture.controller;

import gldcosta.accenture.controller.base.CrudController;
import gldcosta.accenture.entity.FornecedorPF;
import gldcosta.accenture.entity.FornecedorPJ;
import gldcosta.accenture.service.FornecedorPFService;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fornecedor-pf")
public class FornecedorPFController extends CrudController<FornecedorPF, FornecedorPF, Long> {

    private final FornecedorPFService fornecedorPJService;

    public FornecedorPFController(FornecedorPFService fornecedorPFService) {
        super(fornecedorPFService);
        this.fornecedorPJService = fornecedorPFService;
    }

    @GetMapping("/empresa/{empresaId}")
    ResponseEntity<Page<FornecedorPF>> buscarPorEmpresaId(@PathVariable Long empresaId,
                                                          @RequestParam(defaultValue = "0") int indice,
                                                          @RequestParam(defaultValue = "10") int tamanho) {
        return ResponseEntity.ok(fornecedorPJService.buscarPorEmpresaId(empresaId, indice, tamanho));
    }
}
