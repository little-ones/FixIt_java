/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service.interfaces;

import Entity.Demande;
import java.util.ArrayList;

/**
 *
 * @author MedAmine
 * @param <T>
 */
public interface Crud<T> {
    public void add(T d);
    public void delete(int id);
    public void update(T id);
    public T show(int id);
    public ArrayList<T> showAll();
}
