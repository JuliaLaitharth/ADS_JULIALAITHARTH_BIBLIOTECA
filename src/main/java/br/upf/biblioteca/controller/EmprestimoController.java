/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.upf.biblioteca.controller;

import br.upf.biblioteca.entity.EmprestimoEntity;
import br.upf.biblioteca.entity.LivroEntity;
import br.upf.biblioteca.entity.UsuarioBibliotecaEntity;
import br.upf.biblioteca.facade.EmprestimoFacade;
import br.upf.biblioteca.facade.LivroFacade;
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

@Named(value = "emprestimoController")
@SessionScoped
public class EmprestimoController implements Serializable {

    @EJB
    private EmprestimoFacade emprestimoFacade;

    @EJB
    private LivroFacade livroFacade;

    @EJB
    private UsuarioBibliotecaFacade usuarioFacade;

    private EmprestimoEntity emprestimo;
    private EmprestimoEntity selected;
    private List<EmprestimoEntity> emprestimoList;

    @PostConstruct
    public void init() {
        prepareCreate();
    }

    public void prepareCreate() {
        emprestimo = new EmprestimoEntity();
        emprestimo.setStatus("ABERTO");
    }

    public List<EmprestimoEntity> getEmprestimoList() {
        if (emprestimoList == null) {
            emprestimoList = emprestimoFacade.findAllOrderByData();
        }
        return emprestimoList;
    }

    public List<LivroEntity> getLivroList() {
        return livroFacade.findAllOrderByTitulo();
    }

    public List<UsuarioBibliotecaEntity> getUsuarioList() {
        return usuarioFacade.findAllOrderByNome();
    }

    public void adicionarEmprestimo() {
        try {
            LivroEntity livro = emprestimo.getLivro();
            if (livro != null) {
                long emprestimosAbertos = emprestimoFacade.countEmprestimosAbertosPorLivro(livro.getId());
                int totalExemplares = livro.getExemplares() != null ? livro.getExemplares() : 0;
                if (emprestimosAbertos >= totalExemplares) {
                    addErrorMessage("Todos os exemplares de '" + livro.getTitulo() + "' já estão emprestados!");
                    return;
                }
            }
            UsuarioBibliotecaEntity usuario = emprestimo.getUsuario();
            if (usuario != null) {
                long emprestimosUsuario = emprestimoFacade.countEmprestimosAbertosPorUsuario(usuario.getId());
                int limite = usuario.getLimiteemprestimos() != null ? usuario.getLimiteemprestimos() : 0;
                if (emprestimosUsuario >= limite) {
                    addErrorMessage("Usuário '" + usuario.getNome() + "' atingiu o limite de " + limite + " empréstimo(s)!");
                    return;
                }
            }
            emprestimo.setDatahorareg(new Date());
            emprestimoFacade.create(emprestimo);
            emprestimoList = null;
            prepareCreate();
            addSuccessMessage("Empréstimo registrado com sucesso!");
        } catch (Exception e) {
            addErrorMessage("Erro ao registrar: " + e.getMessage());
        }
    }

    public void editarEmprestimo() {
        try {
            emprestimoFacade.edit(selected);
            emprestimoList = null;
            addSuccessMessage("Empréstimo atualizado com sucesso!");
        } catch (Exception e) {
            addErrorMessage("Erro ao editar: " + e.getMessage());
        }
    }

    public void deletarEmprestimo() {
        try {
            emprestimoFacade.remove(selected);
            emprestimoList = null;
            selected = null;
            addSuccessMessage("Empréstimo excluído com sucesso!");
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

    public EmprestimoEntity getEmprestimo() { return emprestimo; }
    public void setEmprestimo(EmprestimoEntity e) { this.emprestimo = e; }

    public EmprestimoEntity getSelected() { return selected; }
    public void setSelected(EmprestimoEntity s) { this.selected = s; }
}