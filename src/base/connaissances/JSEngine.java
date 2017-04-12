/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package base.connaissances;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;


public  class JSEngine {
    public static ScriptEngineManager manager = null;
    public static ScriptEngine engine = null;
          
      public   ScriptEngine getScriptJS(){
         if(manager == null){ manager = new ScriptEngineManager(); }
         if(engine == null){ engine = manager.getEngineByName("js");}
         return engine;
         }   
          
}
