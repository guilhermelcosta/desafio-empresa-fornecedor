package gldcosta.accenture.controller;

import gldcosta.accenture.controller.base.CrudController;
import gldcosta.accenture.dto.FornecedorPessoaJuridicaDto;
import gldcosta.accenture.entity.FornecedorPessoaJuridica;
import gldcosta.accenture.service.FornecedorPessoaJuridicaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/fornecedor-pj")
public class FornecedorPessoaJuridicaController extends CrudController<FornecedorPessoaJuridicaDto, FornecedorPessoaJuridica, Long> {

    public FornecedorPessoaJuridicaController(FornecedorPessoaJuridicaService crudService) {
        super(crudService);
    }
}
