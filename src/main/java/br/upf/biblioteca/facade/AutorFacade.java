/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.upf.biblioteca.facade;

import br.upf.biblioteca.entity.AutorEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AutorFacade extends AbstractFacade<AutorEntity> {

    @PersistenceContext(unitName = "BibliotecaPU")
    private EntityManager em;

    public AutorFacade() {
        super(AutorEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<AutorEntity> findAllOrderByNome() {
        return em.createQuery(
            "SELECT a FROM AutorEntity a ORDER BY a.nome",
            AutorEntity.class).getResultList();
    }
}