/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.connaissances;

import java.util.Vector;


public class Regle {
    Vector<String> premisses;
    Vector<String> conclusions;
    String str;
    int numero;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    
     public Regle() {
        premisses= new Vector();
        conclusions = new Vector();
    }

    public Vector<String> getPremisses() {
        return premisses;
    }

    public void setPremisses(Vector<String> premisses) {
        this.premisses = premisses;
    }

    public Vector<String> getConclusions() {
        return conclusions;
    }

    public void setConclusions(Vector<String> conclusions) {
        this.conclusions = conclusions;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    

}
