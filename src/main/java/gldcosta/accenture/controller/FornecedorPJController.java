package gldcosta.accenture.controller;

import gldcosta.accenture.controller.base.CrudController;
import gldcosta.accenture.entity.FornecedorPJ;
import gldcosta.accenture.service.FornecedorPJService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fornecedor-pj")
public class FornecedorPJController extends CrudController<FornecedorPJ, FornecedorPJ, Long> {

    public FornecedorPJController(FornecedorPJService crudService) {
        super(crudService);
    }
}
