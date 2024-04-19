package com.example.ClimaAPI.service;
import com.example.ClimaAPI.model.ClimaEntity;
import com.example.ClimaAPI.repository.ClimaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import java.util.List;

@Service
public class ClimaService {
    @Autowired
    private ClimaRepository climaRepository;

    public List<ClimaEntity> obterTodos(){ return climaRepository.findAll(); }

    public ClimaEntity obterPorId(String id){ return climaRepository.findById(id).orElse(null); }

    public ClimaEntity inserir(ClimaEntity clima){ return climaRepository.save(clima); }

    public ClimaEntity atualizar(String id, ClimaEntity newClima){
        ClimaEntity existingClima = climaRepository.findById(id).orElse(null);
        if (existingClima != null) {
            existingClima.setName(newClima.getName());
            existingClima.setState(newClima.getState());
            return climaRepository.save(existingClima);
        } else {
            return null;
        }
    }

    public void excluir(String id){ climaRepository.deleteById(id); }

    public String preverTempo(){
        String dadosMeteorologicos = "";
        String apiUrl = "https://apiadvisor.climatempo.com.br/api/v1/weather/locale/6879/current?token=37b9058bdffa93f6370bd3e31b991ce8";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);

        if(responseEntity.getStatusCode().is2xxSuccessful()) {
            String responseBody = responseEntity.getBody();

            JSONObject jsonObject = new JSONObject(responseBody);
            String name = jsonObject.getString("name");
            String state = jsonObject.getString("state");
            JSONObject data = jsonObject.getJSONObject("data");
            double temperature = data.getDouble("temperature");
            int humidity = data.getInt("humidity");
            String condition = data.getString("condition");

            ClimaEntity climaEntity = new ClimaEntity();
            climaEntity.setName(name);
            climaEntity.setState(state);
            climaEntity.setTemperature(temperature);
            climaEntity.setHumidity(humidity);
            climaEntity.setCondition(condition);


            climaRepository.save(climaEntity);

        } else {
            dadosMeteorologicos = "Falha ao obter dados meteorologicos. Codigo de status: " + responseEntity.getStatusCode();
        }
        return dadosMeteorologicos;
    }

    public ClimaEntity inserirClima(ClimaEntity dadosMeteorologicos){
        return climaRepository.save(dadosMeteorologicos);
    }
}
