/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Systeme.expert;

import java.io.IOException;
import java.util.Collections;
import java.util.Vector;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import base.connaissances.Etat;
import base.connaissances.JSEngine;
import base.connaissances.Regle;

public class Resolveur {
    public static String  trace="";

    public static Etat ResourdreA(String départ, String but, Vector<Regle> br, String h) throws ScriptException, Exception {

        ScriptEngine engine = (new JSEngine()).getScriptJS();
        
        Vector<Etat> open = new Vector();
        Vector<Etat> closed = new Vector();
        int nbnoeud=0;
        int k = 0;
        Etat e = new Etat(départ, br);
        open.add(e);
System.out.println("Les etapes de résolution du probléme");
trace+="Les etapes de résolution du probléme \n" ;
        do {
            
            Collections.sort(open, new ComparatorF());

            for (int j = 0; j < open.size(); j++) {

                for (int m = j + 1; m < open.size(); m++) {
                    if (open.get(m).getX().equals(open.get(j).getX()) && open.get(m).getY().equals(open.get(j).getY())) {
                        System.out.println("L'état"+open.get(j)+"posséde f=g+h le plus petit donc on supprime "+open.get(m)+"le fils de "+open.get(m).getPred()+"\n");
                       trace+="L'état"+open.get(j)+"posséde f=g+h le plus petit donc on supprime "+open.get(m)+"le fils de "+open.get(m).getPred()+"\n";
                        open.remove(open.get(m));
                    }
                }

            }
            Etat current = open.get(0);
            if (open.get(0).verifBut(but)) {
                nbnoeud++;
                System.out.println("Le but "+open.get(0)+" est atteint avec succés \n");
                trace+="Le but "+open.get(0)+" est atteint avec succés \n";
                       int cout=open.get(0).getG()+open.get(0).getH();
        System.out.println("Le  chemin parcouru est :");
        trace+="Le  chemin parcouru est : \n";
        for (int m=0;m<closed.size();m++)
        {
            System.out.print(closed.get(m)+"==>");
            trace+=closed.get(m)+"==> ";
        }
        System.out.print(open.get(0)+"\n");
        trace+=open.get(0)+"\n";
System.out.println("Le cout de chemin est :"+cout);
trace+="Le cout de chemin est :"+cout+"\n";
System.out.println("Le nombre de noeuds examinés est :"+nbnoeud);
trace+="Le nombre de noeuds examinés est :"+nbnoeud+"\n";
                return open.get(0);
            }

            Vector<Etat> r = current.genereOperationsApplicables();
            closed.add(open.get(0));
            open.remove(open.get(0));
            nbnoeud++;
            for (int j = 0; j < r.size(); j++) {

                Etat current2 = r.get(j);
                String heuristique;
                if (h.equals("1")) {
                    heuristique = current2.Calculerheuristique(current2);
                } else {
                    heuristique = current2.Calculerheuristique1(current2);
                }
                current2.setH((int) engine.eval(heuristique));

                if (!closed.contains(r.get(j))) {
                    open.add(current2);

                } else {
                    while (k < closed.size()) {
                        // System.out.println(closed.size());
                        if (closed.get(k) == r.get(j) && current2.getG() >= closed.get(k).getG()) {
                            System.out.println(closed.get(k)+"posséde f=g+h le plus grand et il se trouve dans la liste de closed donc on le fait sortir" +"\n");
                            trace+=closed.get(k)+"posséde f=g+h le plus grand et il se trouve dans la liste de closed donc on le fait sortir" +"\n";
                            closed.remove(closed.get(k));
                        }
                        k++;
                    }

                }

            }
            PrintEtats(closed);
          
        } while (open.isEmpty() == false);
       


        throw new Exception();
    }

        public static Etat ResourdreLargeur(String départ, String but, Vector<Regle> br) throws ScriptException, Exception {

        ScriptEngine engine = (new JSEngine()).getScriptJS();
        Vector<Etat> open = new Vector();

        int k = 0;
        Vector<Etat> closed = new Vector();
        Etat e = new Etat(départ, br);
        open.add(e);

        do {
            //Collections.sort(open, new ComparatorF());

/*            for (int j = 0; j < open.size(); j++) {

                for (int m = j + 1; m < open.size(); m++) {
                    if (open.get(m).getX().equals(open.get(j).getX()) && open.get(m).getY().equals(open.get(j).getY())) {
                        open.remove(open.get(j));
                    }
                }

           }*/
            Etat current = open.get(0);
            if (open.get(0).verifBut(but)) {

                return open.get(0);
            }

            Vector<Etat> r = current.genereOperationsApplicables();
            closed.add(open.get(0));
            open.remove(open.get(0));
            for (int j = 0; j < r.size(); j++) {

                Etat current2 = r.get(j);
                String heuristique="0";
              
                current2.setH((int) engine.eval(heuristique));

                if (!closed.contains(r.get(j))) {
                    open.add(current2);

                } else {
                    while (k < closed.size()) {
                        // System.out.println(closed.size());
                        if (closed.get(k) == r.get(j) ) {
                            open.remove(r.get(j));
                        }
                        k++;
                    }

                }

            }
            PrintEtats(closed);
        } while (open.isEmpty() == false);

        throw new Exception();
    }
    public static void PrintEtats(Vector<Etat> v) {
        String str = "[";
        for (int i = 0; i < v.size(); i++) {
            str = str + v.get(i).toString() + ",";
        }
        str = str + "]";
        System.out.println(str);
        trace+=str+"\n";
    }

//    public static Etat ResourdreLargeur(String départ, String but, Vector<Regle> br) throws ScriptException, Exception {
//
//        ScriptEngine engine = (new JSEngine()).getScriptJS();
//        Vector<Etat> open = new Vector();
//
//        int k = 0;
//        Vector<Etat> closed = new Vector();
//        Etat e = new Etat(départ, br);
//        open.add(e);
//
//        do {
//            Etat current = open.get(0);
//            if (open.get(0).verifBut(but)) {
//
//                return open.get(0);
//            }
//
//            Vector<Etat> r = current.genereOperationsApplicables();
//            closed.add(open.get(0));
//            open.remove(open.get(0));
//            for (int j = 0; j < r.size(); j++) {
//
//                if (!closed.contains(r.get(j))) {
//                    open.add(open.get(j));
//
//                } else {
//                    while (k < closed.size()) {
//                        // System.out.println(closed.size());
//                        if (closed.get(k) == r.get(j)) {
//                            open.remove(closed.get(k));
//                        }
//                        k++;
//                    }
//
//                }
//            }
//            PrintEtats(closed);
//        } while (open.isEmpty() == false);
//
//        throw new Exception();
//    }
}
