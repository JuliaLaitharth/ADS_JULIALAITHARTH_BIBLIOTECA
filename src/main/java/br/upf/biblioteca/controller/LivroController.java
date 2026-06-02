/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.upf.biblioteca.controller;

import br.upf.biblioteca.entity.AutorEntity;
import br.upf.biblioteca.entity.LivroEntity;
import br.upf.biblioteca.facade.AutorFacade;
import br.upf.biblioteca.facade.LivroFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author julialaitharth
 */
@Named(value = "livroController")
@ViewScoped
public class LivroController implements Serializable {

  @EJB private LivroFacade livroFacade;
  @EJB private AutorFacade autorFacade;  // para carregar lista de autores

  private LivroEntity livro;
  private LivroEntity selected;
  private List<LivroEntity> livroList;

  @PostConstruct
  public void init() { prepareCreate(); }

  public void prepareCreate() {
    livro = new LivroEntity();
  }

  public List<LivroEntity> getLivroList() {
    if (livroList == null) {
      livroList = livroFacade.findAllOrderByTitulo();
    }
    return livroList;
  }

  public List<AutorEntity> getAutorList() {
    return autorFacade.findAllOrderByNome();
  }

  public void adicionarLivro() {
    try {
      livroFacade.create(livro);
      livroList = null; // força recarga da lista
      prepareCreate();
      addSuccessMessage("Livro cadastrado com sucesso!");
    } catch (Exception e) {
      addErrorMessage("Erro ao cadastrar: " + e.getMessage());
    }
  }

  public void editarLivro() {
    try {
      livroFacade.edit(selected);
      livroList = null;
      addSuccessMessage("Livro atualizado!");
    } catch (Exception e) { addErrorMessage(e.getMessage()); }
  }

  public void deletarLivro() {
    try {
      livroFacade.remove(selected);
      livroList = null;
      selected = null;
      addSuccessMessage("Livro excluído!");
    } catch (Exception e) { addErrorMessage(e.getMessage()); }
  }

  private void addSuccessMessage(String msg) {
    FacesContext.getCurrentInstance().addMessage(null,
      new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
  }
  private void addErrorMessage(String msg) {
    FacesContext.getCurrentInstance().addMessage(null,
      new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
  }

    public LivroFacade getLivroFacade() {
        return livroFacade;
    }

    public void setLivroFacade(LivroFacade livroFacade) {
        this.livroFacade = livroFacade;
    }

    public AutorFacade getAutorFacade() {
        return autorFacade;
    }

    public void setAutorFacade(AutorFacade autorFacade) {
        this.autorFacade = autorFacade;
    }

    public LivroEntity getLivro() {
        return livro;
    }

    public void setLivro(LivroEntity livro) {
        this.livro = livro;
    }

    public LivroEntity getSelected() {
        return selected;
    }

    public void setSelected(LivroEntity selected) {
        this.selected = selected;
    }


}

