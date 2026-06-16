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
    public long countEmprestimosAbertosPorLivro(Integer idLivro) {
        return em.createQuery(
            "SELECT COUNT(e) FROM EmprestimoEntity e " +
            "WHERE e.livro.id = :idLivro AND e.status = 'ABERTO'",
            Long.class)
            .setParameter("idLivro", idLivro)
            .getSingleResult();
    }
    public long countEmprestimosAbertosPorUsuario(Integer idUsuario) {
        return em.createQuery(
            "SELECT COUNT(e) FROM EmprestimoEntity e " +
            "WHERE e.usuario.id = :idUsuario AND e.status = 'ABERTO'",
            Long.class)
            .setParameter("idUsuario", idUsuario)
            .getSingleResult();
    }
}