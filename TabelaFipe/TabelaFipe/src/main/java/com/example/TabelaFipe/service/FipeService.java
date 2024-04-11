package com.example.TabelaFipe.service;

import com.example.TabelaFipe.model.FipeEntity;
import com.example.TabelaFipe.repository.FipeRepository;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;


import java.util.List;

@Service
public class FipeService {
    @Autowired
    private FipeRepository fipeRepository;
    public List<FipeEntity> obterTodos(){
        return fipeRepository.findAll();
    }

    private String consultarURL(String apiUrl) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            return "Falha ao obter dados. Código de status: " + responseEntity.getStatusCode();
        }
    }

    public String consultarModelos(int idMarca) {
        String apiUrl = "https://parallelum.com.br/fipe/api/v1/carros/marcas/" + idMarca + "/modelos";
        String responseBody = consultarURL(apiUrl);

        if (!responseBody.isEmpty()) {
            JSONArray modelosArray = new JSONObject(responseBody).getJSONArray("modelos");

            for (int i = 0; i < modelosArray.length(); i++) {
                JSONObject modeloObj = modelosArray.getJSONObject(i);
                String nomeModelo = modeloObj.getString("nome");

                FipeEntity fipeEntity = new FipeEntity();
                fipeEntity.setMarca("Marca");
                fipeEntity.setModelo(nomeModelo);

                fipeRepository.save(fipeEntity);
            }

            return "Modelos consultados e salvos no MongoDB.";
        } else {
            return "Falha ao consultar modelos.";
        }
    }

    public String consultarAnos(int idMarca, int idModelo) {
        String apiUrl = "https://parallelum.com.br/fipe/api/v1/carros/marcas/" + idMarca + "/modelos/" + idModelo + "/anos";
        String responseBody = consultarURL(apiUrl);

        if (!responseBody.isEmpty()) {
            JSONArray anosArray = new JSONObject(responseBody).getJSONArray("anos");

            for (int i = 0; i < anosArray.length(); i++) {
                JSONObject anoObj = anosArray.getJSONObject(i);
                String nomeAno = anoObj.getString("nome");

                FipeEntity fipeEntity = new FipeEntity();
                fipeEntity.setMarca("Marca"); // Defina a marca conforme necessário
                fipeEntity.setModelo("Modelo"); // Defina o modelo conforme necessário
                fipeEntity.setAnoModelo(nomeAno);

                fipeRepository.save(fipeEntity);
            }

            return "Anos consultados e salvos no MongoDB.";
        } else {
            return "Falha ao consultar anos.";
        }
    }

    public String consultarValor(int idMarca, int idModelo, String ano) {
        String apiUrl = "https://parallelum.com.br/fipe/api/v1/carros/marcas/" + idMarca + "/modelos/" + idModelo + "/anos/" + ano;
        String responseBody = consultarURL(apiUrl);

        if (!responseBody.isEmpty()) {
            JSONObject valorObj = new JSONObject(responseBody);

            FipeEntity fipeEntity = new FipeEntity();
            fipeEntity.setMarca("Marca"); // Defina a marca conforme necessário
            fipeEntity.setModelo("Modelo"); // Defina o modelo conforme necessário
            fipeEntity.setAnoModelo("Ano"); // Defina o ano conforme necessário
            fipeEntity.setValor(valorObj.getString("valor"));

            fipeRepository.save(fipeEntity);

            return "Valor consultado e salvo no MongoDB.";
        } else {
            return "Falha ao consultar valor.";
        }
    }

    public String consultarMarcas() {
        String apiUrl = "https://parallelum.com.br/fipe/api/v1/carros/marcas";
        String responseBody = consultarURL(apiUrl);

        if (!responseBody.isEmpty()) {
            JSONArray marcasArray = new JSONArray(responseBody);

            for (int i = 0; i < marcasArray.length(); i++) {
                JSONObject marcaObj = marcasArray.getJSONObject(i);
                String nomeMarca = marcaObj.getString("nome");
                int codigoMarca = marcaObj.getInt("codigo");

                FipeEntity fipeEntity = new FipeEntity();
                fipeEntity.setMarca(nomeMarca);
                fipeEntity.setCodigo(codigoMarca);

                fipeRepository.save(fipeEntity);
            }

            return "Marcas consultadas e salvas no MongoDB.";
        } else {
            return "Falha ao consultar marcas.";
        }
    }

}