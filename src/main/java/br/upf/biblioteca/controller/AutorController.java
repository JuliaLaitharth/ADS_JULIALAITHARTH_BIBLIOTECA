/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.upf.biblioteca.controller;

import br.upf.biblioteca.entity.AutorEntity;
import br.upf.biblioteca.facade.AutorFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named(value = "autorController")
@SessionScoped
public class AutorController implements Serializable {

    @EJB
    private AutorFacade autorFacade;

    private AutorEntity autor;
    private AutorEntity selected;
    private List<AutorEntity> autorList;

    @PostConstruct
    public void init() {
        prepareCreate();
    }

    public void prepareCreate() {
        autor = new AutorEntity();
    }

    public List<AutorEntity> getAutorList() {
        if (autorList == null) {
            autorList = autorFacade.findAllOrderByNome();
        }
        return autorList;
    }

    public void adicionarAutor() {
        try {
            autorFacade.create(autor);
            autorList = null;
            prepareCreate();
            addSuccessMessage("Autor cadastrado com sucesso!");
        } catch (Exception e) {
            addErrorMessage("Erro ao cadastrar: " + e.getMessage());
        }
    }

    public void editarAutor() {
        try {
            autorFacade.edit(selected);
            autorList = null;
            addSuccessMessage("Autor atualizado com sucesso!");
        } catch (Exception e) {
            addErrorMessage("Erro ao editar: " + e.getMessage());
        }
    }

    public void deletarAutor() {
        try {
            autorFacade.remove(selected);
            autorList = null;
            selected = null;
            addSuccessMessage("Autor excluído com sucesso!");
        } catch (Exception e) {
            addErrorMessage("Erro ao excluir: " + e.getMessage());
        }
    }

    private void addSuccessMessage(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
    }

    private void addErrorMessage(String msg) {
        FacesContext.getCurrentInstance().addMessage(null,
            new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
    }

    public AutorEntity getAutor() { return autor; }
    public void setAutor(AutorEntity autor) { this.autor = autor; }

    public AutorEntity getSelected() { return selected; }
    public void setSelected(AutorEntity selected) { this.selected = selected; }
}