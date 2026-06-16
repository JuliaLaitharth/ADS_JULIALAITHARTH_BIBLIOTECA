package br.upf.biblioteca.controller;

import br.upf.biblioteca.entity.LivroEntity;
import br.upf.biblioteca.facade.LivroFacade;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import javax.naming.InitialContext;

@FacesConverter(value = "livroConverter")
public class LivroConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) return null;
        try {
            LivroFacade facade = (LivroFacade) new InitialContext().lookup("java:module/LivroFacade");
            return facade.find(Integer.parseInt(value));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) return "";
        if (value instanceof LivroEntity) {
            return String.valueOf(((LivroEntity) value).getId());
        }
        return "";
    }
}

