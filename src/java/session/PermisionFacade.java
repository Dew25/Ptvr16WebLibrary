/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entity.secure.Permision;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Melnikov
 */
@Stateless
public class PermisionFacade extends AbstractFacade<Permision> {

    @PersistenceContext(unitName = "Ptvr16WebLibraryPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PermisionFacade() {
        super(Permision.class);
    }
    
}
