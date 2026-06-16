/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.upf.biblioteca.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author julialaitharth
 */
@Entity
@Table(name = "usuariobiblioteca")
public class UsuarioBibliotecaEntity implements Serializable {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id") private Integer id;
  @Column(name = "nome")             private String nome;
  @Column(name = "cpf")              private String cpf;
  @Column(name = "email")            private String email;
  @Column(name = "senha")            private String senha;
  @Column(name = "dtcadastro")       private Date dtcadastro;
  @Column(name = "limiteemprestimos") private Integer limiteemprestimos;
  @Column(name = "ativo")            private Boolean ativo;

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Date getDtcadastro() {
        return dtcadastro;
    }

    public void setDtcadastro(Date dtcadastro) {
        this.dtcadastro = dtcadastro;
    }

    public Integer getLimiteemprestimos() {
        return limiteemprestimos;
    }

    public void setLimiteemprestimos(Integer limiteemprestimos) {
        this.limiteemprestimos = limiteemprestimos;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
  
    @Override
    public int hashCode() {
        return (id != null) ? id.hashCode() : 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof UsuarioBibliotecaEntity)) return false;
        UsuarioBibliotecaEntity other = (UsuarioBibliotecaEntity) obj;
        return (id != null) && id.equals(other.id);
    }
}

