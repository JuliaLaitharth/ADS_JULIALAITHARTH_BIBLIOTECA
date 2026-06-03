/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.upf.biblioteca.facade;

import br.upf.biblioteca.entity.UsuarioBibliotecaEntity;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UsuarioBibliotecaFacade extends AbstractFacade<UsuarioBibliotecaEntity> {

    @PersistenceContext(unitName = "BibliotecaPU")
    private EntityManager em;

    public UsuarioBibliotecaFacade() {
        super(UsuarioBibliotecaEntity.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public List<UsuarioBibliotecaEntity> findAllOrderByNome() {
        return em.createQuery(
            "SELECT u FROM UsuarioBibliotecaEntity u ORDER BY u.nome",
            UsuarioBibliotecaEntity.class).getResultList();
    }

    public UsuarioBibliotecaEntity findByEmailSenha(String email, String senha) {
      try {
        return em.createQuery(
             "SELECT u FROM UsuarioBibliotecaEntity u" +
             " WHERE u.email = :email AND u.senha = :senha",
             UsuarioBibliotecaEntity.class)
             .setParameter("email", email)
             .setParameter("senha", senha)
             .getSingleResult();
      } catch (NoResultException e) {
        return null;
      }
    }

}