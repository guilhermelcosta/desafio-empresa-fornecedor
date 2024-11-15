package gldcosta.accenture.controller;

import gldcosta.accenture.controller.base.CrudController;
import gldcosta.accenture.entity.FornecedorPF;
import gldcosta.accenture.entity.FornecedorPJ;
import gldcosta.accenture.service.FornecedorPFService;
import gldcosta.accenture.service.FornecedorPJService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fornecedor-pf")
public class FornecedorPFController extends CrudController<FornecedorPF, FornecedorPF, Long> {

    public FornecedorPFController(FornecedorPFService crudService) {
        super(crudService);
    }
}
