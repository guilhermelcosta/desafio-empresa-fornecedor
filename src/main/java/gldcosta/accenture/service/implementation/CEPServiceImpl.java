package gldcosta.accenture.service.implementation;

import gldcosta.accenture.service.CEPService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static gldcosta.accenture.constant.Constants.*;
import static java.lang.String.format;
import static java.util.Objects.isNull;
import static org.springframework.http.HttpMethod.GET;

/**
 * Implementação do serviço responsável por buscar informações de endereço a partir de um CEP.
 * Este serviço faz uma requisição HTTP para a API externa ViaCEP, retornando os dados do endereço
 * no formato de um Map<String, Object>.
 */
@Service
@AllArgsConstructor
public class CEPServiceImpl implements CEPService {

    /**
     * Faz uma chamada para a API ViaCEP e retorna as informações de endereço de um determinado CEP.
     *
     * @param cep o Código de Endereçamento Postal (CEP) que será utilizado para a busca do endereço
     * @return um Map contendo as informações do endereço retornadas pela API ViaCEP
     */
    @Override
    @Cacheable("cep")
    public Map<String, Object> obterDadosCEP(String cep) {

        RestTemplate restTemplate = new RestTemplate();
        String url = format(VIA_CEP_URL + cep + VIA_CEP_TIPO_RETORNO);

        return restTemplate.exchange(url, GET, null,
                                     new ParameterizedTypeReference<Map<String, Object>>() {
                                     }).getBody();
    }

    /**
     * Verifica se um CEP é válido, ou seja, se o CEP informado existe.
     *
     * @param cep o Código de Endereçamento Postal (CEP) que será validado.
     * @return true se o CEP for válido, false caso contrário.
     */
    @Override
    @Cacheable("cep")
    public boolean cepValido(String cep) {
        return isNull(obterDadosCEP(cep).get(CHAVE_ERRO));
    }

}
