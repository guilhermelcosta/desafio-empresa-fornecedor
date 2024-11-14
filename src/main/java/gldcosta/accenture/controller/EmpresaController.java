package gldcosta.accenture.controller;

import gldcosta.accenture.controller.base.CrudController;
import gldcosta.accenture.entity.Empresa;
import gldcosta.accenture.service.EmpresaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/empresa")
public class EmpresaController extends CrudController<Empresa, Empresa, Long> {

    public EmpresaController(EmpresaService crudService) {
        super(crudService);
    }
}
