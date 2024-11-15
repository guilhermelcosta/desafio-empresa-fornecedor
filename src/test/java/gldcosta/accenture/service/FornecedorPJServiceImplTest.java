package gldcosta.accenture.service;

import gldcosta.accenture.entity.FornecedorPJ;
import gldcosta.accenture.repository.FornecedorPJRepository;
import gldcosta.accenture.service.implementation.FornecedorPJServiceImpl;
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
class FornecedorPJServiceImplTest {

    @Mock
    private CEPService cepService;

    @Mock
    private FornecedorPJRepository fornecedorRepository;

    @InjectMocks
    private FornecedorPJServiceImpl fornecedorPJService;

    @Test
    @DisplayName("Teste de criação de fornecedor pessoa jurídica")
    void testCriar() {

        FornecedorPJ fornecedor = new FornecedorPJ();
        fornecedor.setCep("12345-678");

        when(cepService.cepValido(anyString())).thenReturn(true);
        when(fornecedorRepository.save(any(FornecedorPJ.class))).thenReturn(fornecedor);

        FornecedorPJ fornecedorPJ = fornecedorPJService.criar(fornecedor);

        assertNotNull(fornecedorPJ);
        verify(fornecedorRepository, times(1)).save(fornecedor);
    }

    @Test
    @DisplayName("Teste de atualização de fornecedor pessoa jurídica")
    void testAtualizar() {

        FornecedorPJ fornecedor = new FornecedorPJ();
        fornecedor.setCep("12345-678");

        when(fornecedorRepository.findById(anyLong())).thenReturn(Optional.of(fornecedor));
        when(cepService.cepValido(anyString())).thenReturn(true);
        when(fornecedorRepository.save(any(FornecedorPJ.class))).thenReturn(fornecedor);

        FornecedorPJ fornecedorPJ = fornecedorPJService.atualizar(fornecedor, 1L);

        assertNotNull(fornecedorPJ);
        verify(fornecedorRepository, times(1)).save(fornecedor);
    }

    @Test
    @DisplayName("Teste de busca de fornecedor pessoa jurídica por ID")
    void testBuscarPorId() {

        FornecedorPJ fornecedor = new FornecedorPJ();

        when(fornecedorRepository.findById(anyLong())).thenReturn(Optional.of(fornecedor));

        FornecedorPJ resultado = fornecedorPJService.buscarPorId(1L);

        assertNotNull(resultado);
        verify(fornecedorRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Teste de busca de todos os fornecedores pessoa jurídica")
    void testBuscarTodos() {

        Page<FornecedorPJ> pagina = new PageImpl<>(Collections.singletonList(new FornecedorPJ()));

        when(fornecedorRepository.findAll(any(PageRequest.class))).thenReturn(pagina);

        Page<FornecedorPJ> resultado = fornecedorPJService.buscarTodos(0, 10);

        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        verify(fornecedorRepository, times(1)).findAll(any(PageRequest.class));
    }

    @Test
    @DisplayName("Teste de deleção de fornecedor pessoa jurídica por ID")
    void testDeletarPorId() {

        FornecedorPJ fornecedor = new FornecedorPJ();

        when(fornecedorRepository.findById(anyLong())).thenReturn(Optional.of(fornecedor));
        doNothing().when(fornecedorRepository).deleteById(anyLong());

        fornecedorPJService.deletarPorId(1L);

        verify(fornecedorRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Teste de busca de fornecedores pessoa jurídica por ID da empresa")
    void testBuscarPorEmpresaId() {

        Page<FornecedorPJ> pagina = new PageImpl<>(Collections.singletonList(new FornecedorPJ()));

        when(fornecedorRepository.findAllByEmpresasId(anyLong(), any(PageRequest.class))).thenReturn(pagina);

        Page<FornecedorPJ> resultado = fornecedorPJService.buscarPorEmpresaId(1L, 0, 10);

        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        verify(fornecedorRepository, times(1)).findAllByEmpresasId(anyLong(), any(PageRequest.class));
    }

    @Test
    @DisplayName("Teste de busca de fornecedores pessoa jurídica por CNPJ")
    void testBuscarPorCNPJ() {

        Page<FornecedorPJ> pagina = new PageImpl<>(Collections.singletonList(new FornecedorPJ()));

        when(fornecedorRepository.findAllByCnpj(anyString(), any(PageRequest.class))).thenReturn(pagina);

        Page<FornecedorPJ> resultado = fornecedorPJService.buscarPorCNPJ("12345678000100", 0, 10);

        assertNotNull(resultado);
        assertEquals(1, resultado.getTotalElements());
        verify(fornecedorRepository, times(1)).findAllByCnpj(anyString(), any(PageRequest.class));
    }
}