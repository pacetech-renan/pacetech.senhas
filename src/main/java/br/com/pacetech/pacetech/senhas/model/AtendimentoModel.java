/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.pacetech.pacetech.senhas.model;

import java.util.Date;

/**
 *
 * @author Renan Miguel
 */
public class AtendimentoModel {
    
    private Integer id;
    private String nome;
    private Date data;
    private Integer status;

    public AtendimentoModel() {
    }

    
    public AtendimentoModel(Integer id, String nome, Date data, Integer status) {
        this.id = id;
        this.nome = nome;
        this.data = data;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AtendimentoModel{" + "id=" + id + ", nome=" + nome + ", data=" + data + ", status=" + status + '}';
    }
    
}
