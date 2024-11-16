package gldcosta.accenture.service;

import gldcosta.accenture.entity.Empresa;
import gldcosta.accenture.entity.FornecedorPF;
import gldcosta.accenture.entity.FornecedorPJ;
import gldcosta.accenture.repository.EmpresaRepository;
import gldcosta.accenture.service.implementation.EmpresaServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpresaServiceImplTest {

    @Mock
    private FornecedorPJService fornecedorPJService;

    @Mock
    private FornecedorPFService fornecedorPFService;

    @Mock
    private EmpresaRepository empresaRepository;

    @InjectMocks
    private EmpresaServiceImpl empresaService;

    @Test
    @DisplayName("Teste de criação de empresa")
    void testCriar() {

        Empresa empresa = new Empresa();

        when(empresaRepository.save(any(Empresa.class))).thenReturn(empresa);

        Empresa resultado = empresaService.criar(empresa);

        assertNotNull(resultado);
        verify(empresaRepository, times(1)).save(empresa);
    }

    @Test
    @DisplayName("Teste de atualização de empresa")
    void testAtualizar() {

        Empresa empresa = new Empresa();

        when(empresaRepository.findById(anyLong())).thenReturn(Optional.of(empresa));
        when(empresaRepository.save(any(Empresa.class))).thenReturn(empresa);

        Empresa resultado = empresaService.atualizar(empresa, 1L);

        assertNotNull(resultado);
        verify(empresaRepository, times(1)).save(empresa);
    }

    @Test
    @DisplayName("Teste de busca de empresa por ID")
    void testBuscarPorId() {

        Empresa empresa = new Empresa();

        when(empresaRepository.findById(anyLong())).thenReturn(Optional.of(empresa));

        Empresa resultado = empresaService.buscarPorId(1L);

        assertNotNull(resultado);
        verify(empresaRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Teste de busca de todas as empresas")
    void testBuscarTodos() {

        Page<Empresa> pagina = new PageImpl<>(Collections.singletonList(new Empresa()));

        when(empresaRepository.findAll(any(PageRequest.class))).thenReturn(pagina);

        Page<Empresa> resultado = empresaService.buscarTodos(0, 10);

        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        verify(empresaRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    @DisplayName("Teste de deleção de empresa por ID")
    void testDeletarPorId() {

        Empresa empresa = new Empresa();

        when(empresaRepository.findById(anyLong())).thenReturn(Optional.of(empresa));
        doNothing().when(empresaRepository).deleteById(anyLong());

        empresaService.deletarPorId(1L);

        verify(empresaRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Teste de atualização de fornecedores PJ de uma empresa")
    void testAtualizarFornecedorPJ() {

        Empresa empresa = new Empresa();
        FornecedorPJ fornecedorPJ = new FornecedorPJ();

        when(empresaRepository.findById(anyLong())).thenReturn(Optional.of(empresa));
        when(fornecedorPJService.buscarPorId(anyLong())).thenReturn(fornecedorPJ);
        when(empresaRepository.save(any(Empresa.class))).thenReturn(empresa);

        Empresa resultado = empresaService.atualizarFornecedorPJ(1L, List.of(1L));

        assertNotNull(resultado);
        verify(empresaRepository, times(1)).save(empresa);
    }

    @Test
    @DisplayName("Teste de atualização de fornecedores PF de uma empresa")
    void testAtualizarFornecedorPF() {

        Empresa empresa = new Empresa();
        FornecedorPF fornecedorPF = new FornecedorPF();

        when(empresaRepository.findById(anyLong())).thenReturn(Optional.of(empresa));
        when(fornecedorPFService.buscarPorId(anyLong())).thenReturn(fornecedorPF);
        when(empresaRepository.save(any(Empresa.class))).thenReturn(empresa);

        Empresa resultado = empresaService.atualizarFornecedorPF(1L, List.of(1L));

        assertNotNull(resultado);
        verify(empresaRepository, times(1)).save(empresa);
    }
}