/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.upf.biblioteca.facade;

import br.upf.biblioteca.entity.EmprestimoEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EmprestimoFacade extends AbstractFacade<EmprestimoEntity> {

    @PersistenceContext(unitName = "BibliotecaPU")
    private EntityManager em;

    public EmprestimoFacade() {
        super(EmprestimoEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<EmprestimoEntity> findAllOrderByData() {
        return em.createQuery(
            "SELECT e FROM EmprestimoEntity e ORDER BY e.dtretirada DESC",
            EmprestimoEntity.class).getResultList();
    }

    public List<EmprestimoEntity> findAbertos() {
        return em.createQuery(
            "SELECT e FROM EmprestimoEntity e " +
            "WHERE e.status = 'ABERTO' ORDER BY e.dtprevdevolucao",
            EmprestimoEntity.class).getResultList();
    }
}