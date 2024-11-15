package gldcosta.accenture.controller;

import gldcosta.accenture.controller.base.CrudController;
import gldcosta.accenture.entity.Empresa;
import gldcosta.accenture.service.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/empresa")
public class EmpresaController extends CrudController<Empresa, Empresa, Long> {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        super(empresaService);
        this.empresaService = empresaService;
    }

    /**
     * Atualiza os fornecedores PJ de uma empresa
     *
     * @param idEmpresa        ID da empresa
     * @param idFornecedoresPJ Lista de IDs dos fornecedores PJ
     * @return Empresa atualizada
     */
    @PutMapping("/{idEmpresa}/fornecedor-pj")
    public ResponseEntity<Empresa> atualizarFornecedorPJ(@PathVariable Long idEmpresa,
                                                         @RequestBody List<Long> idFornecedoresPJ) {
        return ResponseEntity.ok(empresaService.atualizarFornecedorPJ(idEmpresa, idFornecedoresPJ));
    }

    /**
     * Atualiza os fornecedores PF de uma empresa
     *
     * @param idEmpresa        ID da empresa
     * @param idFornecedoresPF Lista de IDs dos fornecedores PF
     * @return Empresa atualizada
     */
    @PutMapping("/{idEmpresa}/fornecedor-pf")
    public ResponseEntity<Empresa> atualizarFornecedorPF(@PathVariable Long idEmpresa,
                                                         @RequestBody List<Long> idFornecedoresPF) {
        return ResponseEntity.ok(empresaService.atualizarFornecedorPF(idEmpresa, idFornecedoresPF));
    }
}
