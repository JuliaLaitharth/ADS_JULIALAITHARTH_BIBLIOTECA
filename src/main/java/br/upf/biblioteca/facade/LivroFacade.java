/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.upf.biblioteca.facade;

import br.upf.biblioteca.entity.LivroEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author julialaitharth
 */
@Stateless
public class LivroFacade extends AbstractFacade<LivroEntity> {

  @PersistenceContext(unitName = "BibliotecaPU")
  private EntityManager em;

  public LivroFacade() { super(LivroEntity.class); }

  @Override
  protected EntityManager getEntityManager() { return em; }

  public List<LivroEntity> findAllOrderByTitulo() {
    return em.createQuery("SELECT l FROM LivroEntity l ORDER BY l.titulo",
                         LivroEntity.class).getResultList();
  }

    public List<LivroEntity> findAllOrderByTitulo() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

