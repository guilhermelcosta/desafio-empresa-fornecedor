package gldcosta.accenture.service.implementation;

import gldcosta.accenture.entity.FornecedorPessoaJuridica;
import gldcosta.accenture.repository.FornecedorPessoaJuridicaRepository;
import gldcosta.accenture.service.CEPService;
import gldcosta.accenture.service.FornecedorPessoaJuridicaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import static gldcosta.accenture.constant.Constants.CAMPOS_IGNORADOS;
import static gldcosta.accenture.constant.Constants.CHAVE_ERRO;
import static java.util.Objects.isNull;
import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
@AllArgsConstructor
public class FornecedorPessoaJuridicaServiceImpl implements FornecedorPessoaJuridicaService {

    private final CEPService cepService;
    private final FornecedorPessoaJuridicaRepository fornecedorRepository;

    @Override
    public FornecedorPessoaJuridica criar(FornecedorPessoaJuridica objeto) {

        log.info("[INFO] [criar] [criando fornecedor - pessoa jurídica: {}]", objeto);

        if (cepValido(objeto))
            return fornecedorRepository.save(objeto);
        else {
            log.error("[ERROR] [criar] [CEP inválido: {}]", objeto.getCep());

            throw new IllegalArgumentException("CEP inválido");
        }

    }

    @Override
    public FornecedorPessoaJuridica atualizar(FornecedorPessoaJuridica objeto, Long id) {

        log.info("[INFO] [atualizar] [atualizando fornecedor - pessoa jurídica: {}]", objeto);

        FornecedorPessoaJuridica fornecedor = buscarPorId(id);

        if (cepValido(objeto)) {

            copyProperties(objeto, fornecedor, CAMPOS_IGNORADOS);

            return fornecedorRepository.save(fornecedor);
        } else {
            log.error("[ERROR] [criar] [CEP inválido: {}]", objeto.getCep());

            throw new IllegalArgumentException("CEP inválido");
        }
    }

    @Override
    public FornecedorPessoaJuridica buscarPorId(Long id) {

        log.info("[INFO] [buscarPorId] [buscando fornecedor - pessoa jurídica por id: {}]", id);

        return fornecedorRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Fornecedor não encontrado"));
    }

    @Override
    public Page<FornecedorPessoaJuridica> buscarTodos(int indice, int tamanho) {

        log.info("[INFO] [buscarTodos] [buscando todos os fornecedores - pessoa jurídica]");

        return fornecedorRepository.findAll(PageRequest.of(indice, tamanho));
    }

    @Override
    public void deletarPorId(Long id) {

        log.info("[INFO] [deletarPorId] [deletando fornecedor - pessoa jurídica por id: {}]", id);

        buscarPorId(id);

        fornecedorRepository.deleteById(id);
    }

//    todo: passar para classe de cep
    private boolean cepValido(FornecedorPessoaJuridica objeto) {
        return isNull(cepService.obterDadosCEP(objeto.getCep()).get(CHAVE_ERRO));
    }
}
