package gldcosta.accenture.controller;

import gldcosta.accenture.service.CEPService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@Validated
@RequestMapping(value = "/cep")
@AllArgsConstructor
public class CEPController {

    private final CEPService cepService;

    /**
     * Retorna os dados de um CEP
     *
     * @param cep CEP a ser consultado
     * @return Dados do CEP
     */
    @GetMapping("/{cep}")
    public ResponseEntity<Map<String, Object>> dadosCEP(@PathVariable String cep) {

        return ok(cepService.obterDadosCEP(cep));
    }
}
