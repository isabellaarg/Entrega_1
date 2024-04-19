package com.example.TabelaFipe.service;

import com.example.TabelaFipe.model.FipeEntity;
import com.example.TabelaFipe.repository.FipeRepository;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;


import java.util.List;
import java.util.Optional;

@Service
public class FipeService {
    @Autowired
    private FipeRepository fipeRepository;
    public List<FipeEntity> obterTodos(){
        return fipeRepository.findAll();
    }

    private String consultarURL(String apiUrl) {
        String dados = "";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            return responseEntity.getBody();
        } else {
            dados = "Falha ao obter dados. Código de status: " + responseEntity.getStatusCode();
        }
        return dados;
    }





    public String consultarMarcas() {
        String apiUrl = "https://parallelum.com.br/fipe/api/v1/carros/marcas";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);


        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            String responseBody = responseEntity.getBody();
            JSONArray marcasArray = new JSONArray(responseBody);

            for (int i = 0; i < marcasArray.length(); i++) {
                JSONObject marcaObj = marcasArray.getJSONObject(i);
                String codigo = marcaObj.getString("codigo");
                String nomeMarca = marcaObj.getString("nome");

                FipeEntity fipeEntity = new FipeEntity();
                fipeEntity.setCodigoMarca(codigo);
                fipeEntity.setMarca(nomeMarca);

                fipeRepository.save(fipeEntity);
            }

            return "Marcas salvas com sucesso";
        } else {
            return "Falha ao obter dados. Codigo de status: " + responseEntity.getStatusCode();
        }
    }

    public String consultarModelos(int idMarca) {
        String apiUrl = "https://parallelum.com.br/fipe/api/v1/carros/marcas/" + idMarca + "/modelos";
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                String responseBody = responseEntity.getBody();
                JSONObject responseJson = new JSONObject(responseBody);

                if (responseJson.has("modelos")) {
                    JSONArray modelosArray = responseJson.getJSONArray("modelos");

                    for (int i = 0; i < modelosArray.length(); i++) {
                        JSONObject modeloObj = modelosArray.getJSONObject(i);
                        int codigo = modeloObj.getInt("codigo");
                        String nome = modeloObj.getString("nome");

                        FipeEntity fipeEntity = new FipeEntity();
                        fipeEntity.setCodigoModelo(String.valueOf(codigo));
                        fipeEntity.setModelo(nome);
                        fipeEntity.setIdMarca(idMarca);

                        fipeRepository.save(fipeEntity);
                    }

                    return "Consulta de modelos concluída com sucesso.";
                } else {
                    return "O objeto JSON retornado não possui a chave 'modelos'.";
                }
            } else {
                return "A API retornou um código de status não bem-sucedido: " + responseEntity.getStatusCodeValue();
            }
        } catch (Exception e) {
            // Captura de outras exceções genéricas
            e.printStackTrace();
            return "Ocorreu um erro ao consultar os modelos: " + e.getMessage();
        }
    }

    public String consultarAnos(int idMarca, int idModelo) {
        return consultarURL("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + idMarca + "/modelos/" +  idModelo + "/anos");
    }

    public String consultarValor(int idMarca, int idModelo, String ano){
        return consultarURL("https://parallelum.com.br/fipe/api/v1/carros/marcas/" + idMarca + "/modelos/" +  idModelo + "/anos/" + ano);
    }
    public FipeEntity criarMarca(FipeEntity marca) {
        return fipeRepository.save(marca);
    }

    public Optional<FipeEntity> buscarMarcaPorId(String id) {
        return fipeRepository.findById(id);
    }

    public List<FipeEntity> buscarTodasAsMarcas() {
        return fipeRepository.findAll();
    }

    public void excluirMarcaPorId(String id) {
        fipeRepository.deleteById(id);
    }

    public FipeEntity atualizar(String id, FipeEntity newModelo) {
        FipeEntity existingModelo = fipeRepository.findById(id).orElse(null);

        if(existingModelo != null){
            existingModelo.setModelo(newModelo.getModelo());
            existingModelo.setIdMarca(newModelo.getIdMarca());
            return fipeRepository.save(existingModelo);
        }else{
            return null;
        }
    }



}