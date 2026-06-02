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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author julialaitharth
 */
@Entity
@Table(name = "emprestimo")
public class EmprestimoEntity implements Serializable {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id") private Integer id;

  @ManyToOne
  @JoinColumn(name = "id_livro", referencedColumnName = "id")
  private LivroEntity livro;

  @ManyToOne
  @JoinColumn(name = "id_usuario", referencedColumnName = "id")
  private UsuarioBibliotecaEntity usuario;

  @Column(name = "dtretirada")      private Date dtretirada;
  @Column(name = "dtprevdevolucao") private Date dtprevdevolucao;
  @Column(name = "dtdevolucao")     private Date dtdevolucao;
  @Column(name = "status")          private String status;
  @Column(name = "datahorareg")     private Date datahorareg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LivroEntity getLivro() {
        return livro;
    }

    public void setLivro(LivroEntity livro) {
        this.livro = livro;
    }

    public UsuarioBibliotecaEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBibliotecaEntity usuario) {
        this.usuario = usuario;
    }

    public Date getDtretirada() {
        return dtretirada;
    }

    public void setDtretirada(Date dtretirada) {
        this.dtretirada = dtretirada;
    }

    public Date getDtprevdevolucao() {
        return dtprevdevolucao;
    }

    public void setDtprevdevolucao(Date dtprevdevolucao) {
        this.dtprevdevolucao = dtprevdevolucao;
    }

    public Date getDtdevolucao() {
        return dtdevolucao;
    }

    public void setDtdevolucao(Date dtdevolucao) {
        this.dtdevolucao = dtdevolucao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDatahorareg() {
        return datahorareg;
    }

    public void setDatahorareg(Date datahorareg) {
        this.datahorareg = datahorareg;
    }
  
}

