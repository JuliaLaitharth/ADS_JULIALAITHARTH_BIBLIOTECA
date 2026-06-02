/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.upf.biblioteca.controller;

import br.upf.biblioteca.entity.UsuarioBibliotecaEntity;
import br.upf.biblioteca.facade.UsuarioBibliotecaFacade;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;

/**
 *
 * @author julialaitharth
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

  @EJB private UsuarioBibliotecaFacade usuarioFacade;

  private UsuarioBibliotecaEntity usuario;
  private UsuarioBibliotecaEntity logado;

  @PostConstruct
  public void init() { usuario = new UsuarioBibliotecaEntity(); }

  public String validarLogin() {
    logado = usuarioFacade.findByEmailSenha(
               usuario.getEmail(), usuario.getSenha());
    if (logado != null) {
      HttpSession session = (HttpSession)
        FacesContext.getCurrentInstance().getExternalContext().getSession(true);
      session.setAttribute("usuarioLogado", logado);
      return "/admin/livro.xhtml?faces-redirect=true";
    }
    FacesContext.getCurrentInstance().addMessage(null,
      new FacesMessage(FacesMessage.SEVERITY_ERROR,
        "Credenciais inválidas!", null));
    return null;
  }

  public String logout() {
    HttpSession session = (HttpSession)
      FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    if (session != null) session.invalidate();
    return "/login.xhtml?faces-redirect=true";
  }

    public UsuarioBibliotecaFacade getUsuarioFacade() {
        return usuarioFacade;
    }

    public void setUsuarioFacade(UsuarioBibliotecaFacade usuarioFacade) {
        this.usuarioFacade = usuarioFacade;
    }

    public UsuarioBibliotecaEntity getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioBibliotecaEntity usuario) {
        this.usuario = usuario;
    }

    public UsuarioBibliotecaEntity getLogado() {
        return logado;
    }

    public void setLogado(UsuarioBibliotecaEntity logado) {
        this.logado = logado;
    }

  
}


