package gldcosta.accenture.service.implementation;

import gldcosta.accenture.dto.FornecedorPessoaJuridicaDto;
import gldcosta.accenture.entity.Empresa;
import gldcosta.accenture.entity.FornecedorPessoaJuridica;
import gldcosta.accenture.repository.EmpresaRepository;
import gldcosta.accenture.repository.FornecedorPessoaJuridicaRepository;
import gldcosta.accenture.service.FornecedorPessoaJuridicaService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Set;

import static gldcosta.accenture.constant.Constants.IGNORED_FIELDS;
import static java.util.stream.Collectors.toSet;
import static org.springframework.beans.BeanUtils.copyProperties;

@Slf4j
@Service
@AllArgsConstructor
public class FornecedorPessoaJuridicaServiceImpl implements FornecedorPessoaJuridicaService {

    private final FornecedorPessoaJuridicaRepository fornecedorRepository;
    private EmpresaRepository empresaRepository;

    @Override
    public FornecedorPessoaJuridica criar(FornecedorPessoaJuridicaDto objeto) {

        log.info("[INFO] [criar] [criando fornecedor - pessoa jurídica: {}]", objeto);

//        todo: validar cep

        Set<Empresa> empresas = objeto.empresaIds().stream().map(id -> {
            return empresaRepository.findById(id).orElseThrow(
                    () -> new EntityNotFoundException("Empresa não encontrada"));
        }).collect(toSet());

        FornecedorPessoaJuridica fornecedor = new FornecedorPessoaJuridica(empresas);

        copyProperties(objeto, fornecedor, IGNORED_FIELDS);

        return fornecedorRepository.save(fornecedor);
    }

    @Override
    public FornecedorPessoaJuridica atualizar(FornecedorPessoaJuridicaDto objeto, Long id) {

        log.info("[INFO] [atualizar] [atualizando fornecedor - pessoa jurídica: {}]", objeto);

        FornecedorPessoaJuridica fornecedor = buscarPorId(id);

        copyProperties(objeto, fornecedor, IGNORED_FIELDS);

        return fornecedorRepository.save(fornecedor);
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
}
