/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.upf.biblioteca.entity;

import jakarta.persistence.*;
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
    @Column(name = "id")
    private Integer id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "anopublicacao")
    private Integer anopublicacao;

    @Column(name = "exemplares")
    private Integer exemplares;

    @Column(name = "resumo")
    private String resumo;

    @Column(name = "imagem")
    private String imagem;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "livro_autor",
            joinColumns = @JoinColumn(name = "id_livro"),
            inverseJoinColumns = @JoinColumn(name = "id_autor"))
    private List<AutorEntity> autores = new ArrayList<>();

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public Integer getAnopublicacao() { return anopublicacao; }
    public void setAnopublicacao(Integer anopublicacao) { this.anopublicacao = anopublicacao; }

    public Integer getExemplares() { return exemplares; }
    public void setExemplares(Integer exemplares) { this.exemplares = exemplares; }

    public String getResumo() { return resumo; }
    public void setResumo(String resumo) { this.resumo = resumo; }

    public String getImagem() { return imagem; }
    public void setImagem(String imagem) { this.imagem = imagem; }

    public List<AutorEntity> getAutores() { return autores; }
    public void setAutores(List<AutorEntity> autores) { this.autores = autores; }

    @Override
    public int hashCode() { return (id != null) ? id.hashCode() : 0; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof LivroEntity)) return false;
        LivroEntity other = (LivroEntity) obj;
        return (id != null) && id.equals(other.id);
    }

    @Override
    public String toString() { return titulo; }
}
