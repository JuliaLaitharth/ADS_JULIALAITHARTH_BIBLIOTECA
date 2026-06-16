/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.upf.biblioteca.controller;

import br.upf.biblioteca.entity.UsuarioBibliotecaEntity;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;
import java.util.List;

/**
 *
 * @author julialaitharth
 */
@FacesConverter(value = "usuarioConverter")
public class UsuarioConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) return null;
        try {
            List<UsuarioBibliotecaEntity> list =
                (List<UsuarioBibliotecaEntity>) component.getAttributes().get("usuarioList");
            if (list != null) {
                for (UsuarioBibliotecaEntity u : list) {
                    if (String.valueOf(u.getId()).equals(value)) return u;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) return "";
        if (value instanceof UsuarioBibliotecaEntity) {
            return String.valueOf(((UsuarioBibliotecaEntity) value).getId());
        }
        return "";
    }
}