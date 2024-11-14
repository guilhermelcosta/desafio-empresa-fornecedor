package gldcosta.accenture.controller;

import gldcosta.accenture.controller.base.CrudController;
import gldcosta.accenture.entity.Empresa;
import gldcosta.accenture.service.CrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/empresa")
public class EmpresaController extends CrudController<Empresa, Long> {

    public EmpresaController(CrudService<Empresa, Long> crudService) {
        super(crudService);
    }
}
