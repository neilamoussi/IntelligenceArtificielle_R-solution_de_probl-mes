/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.connaissances;

import java.io.IOException;
import java.util.Objects;
import java.util.Vector;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class Etat {
    String x ;
    String y ;
    private int g,h=0;
  
    private Vector<String> values;
    private Vector<Regle> regles;
    private Vector<Etat> succ;
    private Etat pred;
    private Regle last;
      public String trace;
  
    
     public Etat(String s, Vector br){
        g=0;
        pred =null;
        succ = new Vector<Etat>();
        regles= br;
        values = new Vector();
        String[] clauses = s.split(";");
        for(int i=0; i<clauses.length; i++){
            values.add(clauses[i]);
            if(clauses[i].split("=")[0].contains("x")){ x = clauses[i].split("=")[1].trim();}
            if(clauses[i].split("=")[0].contains("y")){ y = clauses[i].split("=")[1].trim();}
        }
      
    }
     
     public Etat(Etat e){
        g=e.getG()+1;
        this.regles=e.getRegles();
        this.values=e.getValues();
        this.x = e.x;
        this.y = e.y;
        pred=e;
        succ = new Vector();
    }
     
      public Vector<Etat> genereOperationsApplicables() throws ScriptException{
          ScriptEngine engine = (new JSEngine()).getScriptJS();
        for(int i=0;i<regles.size();i++){
            if(verif(regles.get(i))){
                Etat e=new Etat(this);
                for(int j=0 ; j<regles.get(i).getConclusions().size();j++){
                if(regles.get(i).getConclusions().get(j).startsWith("x")){
                String str = (String) regles.get(i).getConclusions().get(j);
                str = str.substring(1);
                str = str.replace("x",this.x);
                str = str.replace("y",this.y);
                str = "x"+str;
                e.x = (String) engine.eval(str).toString();

                }
                
                if(regles.get(i).getConclusions().get(j).startsWith("y")){
                String str = (String) regles.get(i).getConclusions().get(j);
                str = str.substring(1);
                str = str.replace("x",this.x);
                str = str.replace("y",this.y);
                str = "y"+str;
                e.y = (String) engine.eval(str).toString();

                }
                e.values.clear();
                e.values.add("x="+e.x);
                e.values.add("y="+e.y);
                 e.last=regles.get(i);
                // e.pred=this;
               
              
                }
               
                this.succ.add(e);
               //  System.out.println("R"+e.last.getNumero()+"(x/"+this.x+",y/"+this.y+") "+"("+e.x+","+e.y+")");
                 
                
            }
        }
//         for (int k=0; k<succ.size();k++)
//                {
//                     System.out.println(succ.get(k).getLast().getNumero());
//                }
        return succ;
    }
      
   
    
    public boolean verif(Regle r) throws ScriptException{
        boolean test=true;
        for(int i=0;i<r.getPremisses().size();i++){
            if(!verifPremiss(r.getPremisses().get(i))) test=false;
        }
        return test;
        
    }

    public boolean verifBut(String str) throws ScriptException{
        boolean test = true;
        String[] s = str.split(";");
        for(int i=0;i<s.length;i++){
            if(!verifPremiss(s[i])) test=false;
        }
        return test;        
    }
    
    public boolean verifPremiss(String pr) throws ScriptException{
        ScriptEngine engine = (new JSEngine()).getScriptJS();     
        
        
        if(pr.contains("x")){ pr = pr.replace("x",x); }
        if(pr.contains("y")){ pr = pr.replace("y",y); }
        
        return (Boolean) engine.eval(pr);
        
    }

    public Vector<String> getValues() {
        return values;
    }

    public void setValues(Vector<String> values) {
        this.values = values;
    }

    public Vector<Regle> getRegles() {
        return regles;
    }

    public void setRegles(Vector<Regle> regles) {
        this.regles = regles;
    }

    public Vector<Etat> getSucc() {
        return succ;
    }

    public void setSucc(Vector<Etat> succ) {
        this.succ = succ;
    }

    public Etat getPred() {
        return pred;
    }

    public void setPred(Etat pred) {
        this.pred = pred;
    }

    public Regle getLast() {
        return last;
    }

    public void setLast(Regle last) {
        this.last = last;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }
    
    @Override
    public String toString() {
         String result="(";
         result+= "x="+this.x+";y="+this.y+";";
        result+=")";
        return result;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Etat other = (Etat) obj;
        if (!Objects.equals(this.toString(), other.toString())) {
            return false;
        }
        return true;
    }
        public String Calculerheuristique(Etat e)throws IOException, ScriptException, Exception
      {
    String x1=e.getX();
    ScriptEngine engine = (new JSEngine()).getScriptJS();
    int a= (int)engine.eval(x1+"-2");
    a=Math.abs(a);
    String h=Integer.toString(a); 
    return h;
      }
        
        
      public String Calculerheuristique1(Etat e)throws IOException, ScriptException, Exception
      {
     String x1=e.getX();
     String y1=e.getY();
     
    
  ScriptEngine engine = (new JSEngine()).getScriptJS();
  if (x1.equals("2"))
  {
      return "0";
  }
  else if ((boolean)engine.eval((x1+y1)+">2"))
  {
      return "7";
  }
  else if ((boolean)engine.eval(y1+">2"))
          {
              return "3";
          }
return "1";
      }
}
