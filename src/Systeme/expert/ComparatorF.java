/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Systeme.expert;

import java.util.Comparator;
import base.connaissances.Etat;


public class ComparatorF implements Comparator<Etat> {
    
    @Override
    public int compare(Etat o1, Etat o2) {
        return Integer.compare(o1.getG()+o1.getH(), o2.getG()+o2.getH());
    }
    
}
