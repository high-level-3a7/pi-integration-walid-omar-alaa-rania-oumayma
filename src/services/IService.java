/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;
/**
 *
 * @author rania
 * @param <T>
 */
public interface IService<T> {
    public void ajouter(T t);
    public void supprimer(T t);
   
    public List<T> afficher();
    
    
}
