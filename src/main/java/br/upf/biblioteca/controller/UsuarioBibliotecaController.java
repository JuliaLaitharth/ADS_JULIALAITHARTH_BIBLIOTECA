/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.upf.biblioteca.controller;

import br.upf.biblioteca.entity.UsuarioBibliotecaEntity;
import br.upf.biblioteca.facade.UsuarioBibliotecaFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named(value = "usuarioController")
@SessionScoped
public class UsuarioBibliotecaController implements Serializable {

    @EJB
    private UsuarioBibliotecaFacade usuarioFacade;

    private UsuarioBibliotecaEntity usuario;
    private UsuarioBibliotecaEntity selected;
    private List<UsuarioBibliotecaEntity> usuarioList;

    @PostConstruct
    public void init() {
        prepareCreate();
    }

    public void prepareCreate() {
        usuario = new UsuarioBibliotecaEntity();
        usuario.setAtivo(true);
        usuario.setLimiteemprestimos(3);
    }

    public List<UsuarioBibliotecaEntity> getUsuarioList() {
        if (usuarioList == null) {
            usuarioList = usuarioFacade.findAllOrderByNome();
        }
        return usuarioList;
    }

    public void adicionarUsuario() {
        try {
            usuario.setDtcadastro(new Date());
            usuarioFacade.create(usuario);
            usuarioList = null;
            prepareCreate();
            addSuccessMessage("Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            addErrorMessage("Erro ao cadastrar: " + e.getMessage());
        }
    }

    public void editarUsuario() {
        try {
            usuarioFacade.edit(selected);
            usuarioList = null;
            addSuccessMessage("Usuário atualizado com sucesso!");
        } catch (Exception e) {
            addErrorMessage("Erro ao editar: " + e.getMessage());
        }
    }

    public void deletarUsuario() {
        try {
            usuarioFacade.remove(selected);
            usuarioList = null;
            selected = null;
            addSuccessMessage("Usuário excluído com sucesso!");
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

    public UsuarioBibliotecaEntity getUsuario() { return usuario; }
    public void setUsuario(UsuarioBibliotecaEntity u) { this.usuario = u; }

    public UsuarioBibliotecaEntity getSelected() { return selected; }
    public void setSelected(UsuarioBibliotecaEntity s) { this.selected = s; }
}