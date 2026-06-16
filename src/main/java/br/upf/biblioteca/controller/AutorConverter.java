package br.upf.biblioteca.controller;

import br.upf.biblioteca.entity.AutorEntity;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter(value = "autorConverter")
public class AutorConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) return null;
        try {
            return context.getApplication()
                .evaluateExpressionGet(context,
                    "#{autorController.autorList}", java.util.List.class)
                .stream()
                .filter(a -> String.valueOf(((AutorEntity) a).getId()).equals(value))
                .findFirst().orElse(null);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) return "";
        if (value instanceof AutorEntity) {
            return String.valueOf(((AutorEntity) value).getId());
        }
        return "";
    }
}