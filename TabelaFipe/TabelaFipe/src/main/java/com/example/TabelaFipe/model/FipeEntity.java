package com.example.TabelaFipe.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "fipe")
public class FipeEntity {
    @Id
    private String id;
    private String dados;
    private String marca;
    private String nomeModelo;
    private String nomeAno;
    private String valor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDados() {
        return dados;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getNomeModelo() {
        return nomeModelo;
    }

    public void setNomeModelo(String nomeModelo) {
        this.nomeModelo = nomeModelo;
    }

    public String getNomeAno() {
        return nomeAno;
    }

    public void setNomeAno(String nomeAno) {
        this.nomeAno = nomeAno;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public void setModelo(String modelo) {
    }

    public void setAnoModelo(String nomeAno) {
    }

    public void setCodigo(int codigoMarca) {
    }
}



