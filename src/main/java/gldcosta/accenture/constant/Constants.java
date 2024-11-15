package gldcosta.accenture.constant;

import lombok.experimental.UtilityClass;

import java.util.List;

import static java.util.Collections.singletonList;

@UtilityClass
public class Constants {

    public static String[] CAMPOS_IGNORADOS = {"id"};

    public static String VIA_CEP_URL = "https://viacep.com.br/ws/";

    public static String VIA_CEP_TIPO_RETORNO = "/json/";

    public static String CHAVE_ERRO = "erro";

    public static String CHAVE_ESTADO = "estado";

    public static List<String> ESTADOS_MAPEADOS_FORNECEDOR_MENOR_DE_IDADE = singletonList("Paraná");

    public static int DEZOITO_ANOS = 18;
}
