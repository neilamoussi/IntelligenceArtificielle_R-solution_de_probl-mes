/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.Parsing;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Vector;
import base.connaissances.Regle;


public class Parsing {
    
    static public Regle parseRegle(String str) {
        Regle r = new Regle();
        r.setStr(str);

        //tab2[0] contient premisse et tab2[1] contient conclusion
        String [] tab1=str.split(":");
        String[] tab2 = tab1[1].split("IF ");
        tab2 = tab2[1].split(" THEN ");
        //numero
      int id = Integer.parseInt(tab1[0]);
      r.setNumero(id);
        //conclusion
      //  r.setConclusions(tab2[1]);
      String[] tab4 = tab2[1].split(";");
        if (tab4.length == 0) {
            r.getConclusions().add(tab2[1].trim());
        } else {
            for (int i = 0; i < tab4.length; i++) {
                r.getConclusions().add(tab4[i].trim());
            }
        }
        
        //premisse
        String[] tab3 = tab2[0].split(" and ");
        if (tab3.length == 0) {
            r.getPremisses().add(tab2[0].trim());
        } else {
            for (int i = 0; i < tab3.length; i++) {
                r.getPremisses().add(tab3[i].trim());
            }
        }
        //System.out.println(r.str);
        return r;
    }
    
    public static Vector<Regle> chargerRegles(String chemin) throws FileNotFoundException, IOException {
        Vector<Regle> br = new Vector<Regle>();
        String msg;
        InputStream ips = new FileInputStream(chemin);
        InputStreamReader ipsr = new InputStreamReader(ips);
        BufferedReader reader = new BufferedReader(ipsr);

        while ((msg = reader.readLine()) != null) {
            br.add(parseRegle(msg));
        }
        reader.close();
        return br;

    }
    
}
