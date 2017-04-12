/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia3;

import base.Parsing.Parsing;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import base.connaissances.Etat;
import base.connaissances.JSEngine;
import base.connaissances.Regle;


public class IA3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ScriptException, Exception,FileNotFoundException {
             Scanner sc = new Scanner(System.in);
          ScriptEngine engine = (new JSEngine()).getScriptJS();
        
        Vector<Regle> regles ;
        regles = Parsing.chargerRegles("E:\\Gl4\\Semestre1\\IA\\tp3\\IA3\\regles.txt");
       
        System.out.println("Veuillez saisir un but :");
        System.out.println("Donner x :");
        String x1 = sc.nextLine();
        System.out.println("Donner y");
        String y1 = sc.nextLine();
        String but="x=="+x1+";"+"y=="+y1;
      
 
 System.out.println("Veuillez saisir un depart :");
  System.out.println("Donner x :");
        String x2 = sc.nextLine();
        System.out.println("Donner y");
        String y2 = sc.nextLine();
        String depart="x="+x2+";"+"y="+y2;
System.out.println("Si vous voulez travailler avec heuristique ABS(X-2) tapez 1 sinon tapez 2 pour l'heuristique conditionelle  ");
 String heure= sc.nextLine();
      try{
        if (heure.equals("1")){ 
                 System.out.println("******algorithem A* avec heuristique =  ABS(X-2)********");
          Etat e = Systeme.expert.Resolveur.ResourdreA(depart, but, regles,"1");
      }
        else
        { System.out.println("******algorithem A* avec heuristique = if ?x=2 alors h=0 si ?x+?y <2 alors h=7 si ?y>2 alors h=3 sinon h=1***********");
            Etat e = Systeme.expert.Resolveur.ResourdreA(depart, but, regles,"2");
        }
                
     // Etat e = Systeme.expert.Resolveur.ResourdreLargeur(depart, but, regles);
      //  Etat e = Systeme.expert.Resolveur.ResourdreA(depart, but, regles,"if (x==2) {h=0;} else if (x+y<2){h=7;} else if (y>2) {h=3;}else h=1;");
//     Etat e=new Etat(depart,regles);
//      Vector<Etat> v= e.genereOperationsApplicables();
//      for (int i=0;i<v.size();i++)
//      {
//         System.out.println(v.get(i));
//      }
          
      //****************algorithem A* avec heuristique = ABS(X-2)***************
          
   
      
     //******algorithem A* avec heuristique = if ?x=2 alors h=0,
      //si ?x+?y <2 alors h=7, si ?y>2 alors h=3 sinon h=1***********

      
           // 

      
      System.out.println("Probléme résolu");
         save_trace(Systeme.expert.Resolveur.trace);
           // System.out.println(e.toString());
       }catch(Exception ex){ System.out.println("Problem insolvable");}
       
        
    }
      static void save_trace(String t) throws FileNotFoundException{
       Scanner reader = new Scanner(System.in);
   System.out.println("Voulez vous enregistrer la trace d'inference ?");
  
   System.out.println("oui(o) ou non (n)?");
   Boolean bool = true;
   String choix = "";
   while(bool){
             choix = reader.nextLine();
             if(choix.equals("o")){ 
                 bool = false;
                  try(  PrintWriter out = new PrintWriter( "trace.txt" )  ){
                     out.println( t );
             }
             System.out.println("Trace enregistrée sous E:\\Gl4\\Semestre1\\IA\\tp3\\IA3\\trace.txt");
             }
            
             if(choix.equals("n")){ bool = false ;}
 
    }
}}
