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

@Named(value = "livroController")
@ViewScoped
public class LivroController implements Serializable {

    @EJB
    private LivroFacade livroFacade;

    @EJB
    private AutorFacade autorFacade;

    private LivroEntity livro;
    private LivroEntity selected;
    private List<LivroEntity> livroList;
    private List<LivroEntity> filteredList;

    @PostConstruct
    public void init() {
        prepareCreate();
    }

    public void prepareCreate() {
        livro = new LivroEntity();
    }

    public List<LivroEntity> getLivroList() {
        if (livroList == null)
            livroList = livroFacade.findAllOrderByTitulo();
        return livroList;
    }

    public List<AutorEntity> getAutorList() {
        return autorFacade.findAllOrderByNome();
    }

    public void adicionarLivro() {
        try {
            livroFacade.create(livro);
            livroList = null;
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
            addSuccessMessage("Livro atualizado com sucesso!");
        } catch (Exception e) {
            addErrorMessage("Erro ao editar: " + e.getMessage());
        }
    }

    public void deletarLivro() {
        try {
            livroFacade.remove(selected);
            livroList = null;
            selected = null;
            addSuccessMessage("Livro excluído com sucesso!");
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

    public LivroEntity getLivro() { return livro; }
    public void setLivro(LivroEntity livro) { this.livro = livro; }

    public LivroEntity getSelected() { return selected; }
    public void setSelected(LivroEntity selected) { this.selected = selected; }

    public List<LivroEntity> getFilteredList() { return filteredList; }
    public void setFilteredList(List<LivroEntity> filteredList) { this.filteredList = filteredList; }

}
