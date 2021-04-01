/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services1;

import java.util.List;
import models.Abonnement;
import models.Zonedacces;


/**
 *
 * @author ouma
 * @param <T>
 */
public interface IService<T> {
    public void ajouter(T t);
    public void supprimer(T t);
   
    public List<T> afficher();
    
   
}
