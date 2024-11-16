package gldcosta.accenture.service;

import gldcosta.accenture.entity.FornecedorPF;
import gldcosta.accenture.repository.FornecedorPFRepository;
import gldcosta.accenture.service.implementation.FornecedorPFServiceImpl;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FornecedorPFServiceImplTest {

    @Mock
    private CEPService cepService;

    @Mock
    private FornecedorPFRepository fornecedorRepository;

    @InjectMocks
    private FornecedorPFServiceImpl fornecedorPFService;

    @Test
    @DisplayName("Teste de criação de fornecedor pessoa física")
    void testCriar() {

        FornecedorPF fornecedor = new FornecedorPF();
        fornecedor.setCep("12345-678");

        when(cepService.cepValido(anyString())).thenReturn(true);
        when(fornecedorRepository.save(any(FornecedorPF.class))).thenReturn(fornecedor);

        FornecedorPF fornecedorPF = fornecedorPFService.criar(fornecedor);

        assertNotNull(fornecedorPF);
        verify(fornecedorRepository, times(1)).save(fornecedor);
    }

    @Test
    @DisplayName("Teste de atualização de fornecedor pessoa física")
    void testAtualizar() {

        FornecedorPF fornecedor = new FornecedorPF();
        fornecedor.setCep("12345-678");

        when(fornecedorRepository.findById(anyLong())).thenReturn(Optional.of(fornecedor));
        when(cepService.cepValido(anyString())).thenReturn(true);
        when(fornecedorRepository.save(any(FornecedorPF.class))).thenReturn(fornecedor);

        FornecedorPF fornecedorPF = fornecedorPFService.atualizar(fornecedor, 1L);

        assertNotNull(fornecedorPF);
        verify(fornecedorRepository, times(1)).save(fornecedor);
    }

    @Test
    @DisplayName("Teste de busca de fornecedor pessoa física por ID")
    void testBuscarPorId() {

        FornecedorPF fornecedor = new FornecedorPF();

        when(fornecedorRepository.findById(anyLong())).thenReturn(Optional.of(fornecedor));

        FornecedorPF resultado = fornecedorPFService.buscarPorId(1L);

        assertNotNull(resultado);
        verify(fornecedorRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Teste de busca de todos os fornecedores pessoa física")
    void testBuscarTodos() {

        Page<FornecedorPF> pagina = new PageImpl<>(Collections.singletonList(new FornecedorPF()));

        when(fornecedorRepository.findAll(any(PageRequest.class))).thenReturn(pagina);

        Page<FornecedorPF> resultado = fornecedorPFService.buscarTodos(0, 10);

        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        verify(fornecedorRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    @DisplayName("Teste de deleção de fornecedor pessoa física por ID")
    void testDeletarPorId() {

        FornecedorPF fornecedor = new FornecedorPF();

        when(fornecedorRepository.findById(anyLong())).thenReturn(Optional.of(fornecedor));
        doNothing().when(fornecedorRepository).deleteById(anyLong());

        fornecedorPFService.deletarPorId(1L);

        verify(fornecedorRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Teste de busca de fornecedores pessoa física por ID da empresa")
    void testBuscarPorEmpresaId() {

        Page<FornecedorPF> pagina = new PageImpl<>(Collections.singletonList(new FornecedorPF()));

        when(fornecedorRepository.findAllByEmpresasId(anyLong(), any(PageRequest.class))).thenReturn(pagina);

        Page<FornecedorPF> resultado = fornecedorPFService.buscarPorEmpresaId(1L, 0, 10);

        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        verify(fornecedorRepository, times(1)).findAllByEmpresasId(anyLong(), any(PageRequest.class));
    }

    @Test
    @DisplayName("Teste de busca de fornecedores pessoa física por CPF")
    void testBuscarPorCPF() {

        Page<FornecedorPF> pagina = new PageImpl<>(Collections.singletonList(new FornecedorPF()));

        when(fornecedorRepository.findAllByCpf(anyString(), any(PageRequest.class))).thenReturn(pagina);

        Page<FornecedorPF> resultado = fornecedorPFService.buscarPorCPF("12345678900", 0, 10);

        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        verify(fornecedorRepository, times(1)).findAllByCpf(anyString(), any(PageRequest.class));
    }
}