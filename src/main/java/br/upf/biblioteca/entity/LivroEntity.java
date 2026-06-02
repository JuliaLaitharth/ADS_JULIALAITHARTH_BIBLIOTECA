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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author julialaitharth
 */
@Entity
@Table(name = "livro")
public class LivroEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id") private Integer id;

  @Column(name = "titulo") private String titulo;
  @Column(name = "isbn")   private String isbn;
  @Column(name = "anopublicacao") private Integer anopublicacao;
  @Column(name = "exemplares")    private Integer exemplares;
  @Column(name = "resumo")        private String resumo;

  @ManyToMany
  @JoinTable(name = "livro_autor",
    joinColumns = @JoinColumn(name = "id_livro"),
    inverseJoinColumns = @JoinColumn(name = "id_autor"))
  private List<AutorEntity> autores = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getAnopublicacao() {
        return anopublicacao;
    }

    public void setAnopublicacao(Integer anopublicacao) {
        this.anopublicacao = anopublicacao;
    }

    public Integer getExemplares() {
        return exemplares;
    }

    public void setExemplares(Integer exemplares) {
        this.exemplares = exemplares;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public List<AutorEntity> getAutores() {
        return autores;
    }

    public void setAutores(List<AutorEntity> autores) {
        this.autores = autores;
    }
  
}