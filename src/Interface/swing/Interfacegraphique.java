/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interface.swing;

import base.Parsing.Parsing;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.script.ScriptException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.FileDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import base.connaissances.Etat;
import base.connaissances.Regle;
import base.connaissances.Etat;
import base.connaissances.JSEngine;
import base.connaissances.Regle;
import base.Parsing.Parsing;
import Systeme.expert.ComparatorF;
import Systeme.expert.Resolveur;
import static Systeme.expert.Resolveur.PrintEtats;
import static Systeme.expert.Resolveur.trace;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Scanner;
import javax.script.ScriptEngine;

/**
 *
 * @author Nessrine
 */
public class Interfacegraphique extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JButton btnImport, Demarrerac, Demarrerprem,Sauve;
    private ArrayList<String> ListBF, ListBR;
    private JLabel lblAb, label, lblResultat, lblButH;
    private static JTextArea text;
    private JTextArea txtrAoubf, jt, jd;
    private FileDialog fDial;
    private int nbr = 0;
    Vector<Regle> regles;

    public static Etat ResourdreA(String départ, String but, Vector<Regle> br, String h) throws ScriptException, Exception {

        ScriptEngine engine = (new JSEngine()).getScriptJS();
        Vector<Etat> open = new Vector();
        int nbnoeud = 0;
        int k = 0;
        Vector<Etat> closed = new Vector();
        Etat e = new Etat(départ, br);
        open.add(e);
        System.out.println("Les etapes de résolution du probléme");
        text.append("Les etapes de résolution du probléme" + '\n' + '\r');
        trace += "Les etapes de résolution du probléme \n";
        do {

            Collections.sort(open, new ComparatorF());

            for (int j = 0; j < open.size(); j++) {

                for (int m = j + 1; m < open.size(); m++) {
                    if (open.get(m).getX().equals(open.get(j).getX()) && open.get(m).getY().equals(open.get(j).getY())) {
                        System.out.println("L'état" + open.get(j) + "posséde f=g+h le plus petit donc on supprime " + open.get(m) + "le fils de " + open.get(m).getPred() + "\n");
                        trace += "L'état" + open.get(j) + "posséde f=g+h le plus petit donc on supprime " + open.get(m) + "le fils de " + open.get(m).getPred() + "\n";
                        text.append("L'état" + open.get(j) + "posséde f=g+h le plus petit donc on supprime " + open.get(m) + "le fils de " + open.get(m).getPred() + '\n' + '\r');
                        open.remove(open.get(m));
                    }
                }

            }
            Etat current = open.get(0);
            if (open.get(0).verifBut(but)) {
                nbnoeud++;
                System.out.println("Le but " + open.get(0) + " est atteint avec succés \n");
                trace += "Le but " + open.get(0) + " est atteint avec succés \n";
                text.append("Le but " + open.get(0) + " est atteint avec succés \n" + '\n' + '\r');
                int cout = open.get(0).getG() + open.get(0).getH();
                System.out.println("Le  chemin parcouru est :");
                text.append("Le  chemin parcouru est :" + '\n' + '\r');
                trace += "Le  chemin parcouru est : \n";
                for (int m = 0; m < closed.size(); m++) {
                    System.out.print(closed.get(m) + "==>");
                    text.append(closed.get(m) + "==>");
                    trace += closed.get(m) + "==> ";
                }
                System.out.print(open.get(0) + "\n");
                trace += open.get(0) + "\n";
                 text.append(""+open.get(0)+ '\n' + '\r');
                System.out.println("Le cout de chemin est :" + cout);
                text.append("Le cout de chemin est :" + cout + '\n' + '\r');
                trace += "Le cout de chemin est :" + cout + "\n";
                System.out.println("Le nombre de noeuds examinés est :" + nbnoeud);
               
                text.append("Le nombre de noeuds examinés est :" + nbnoeud + '\n' + '\r');
                trace += "Le nombre de noeuds examinés est :" + nbnoeud + "\n";
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
                            System.out.println(closed.get(k) + "posséde f=g+h le plus grand et il se trouve dans la liste de closed donc on le fait sortir" + "\n");
                            text.append(closed.get(k) + "posséde f=g+h le plus grand et il se trouve dans la liste de closed donc on le fait sortir" + '\n' + '\r');
                            trace += closed.get(k) + "posséde f=g+h le plus grand et il se trouve dans la liste de closed donc on le fait sortir" + "\n";
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
        int nbnoeud=0;
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
                System.out.println("Le but " + open.get(0) + " est atteint avec succés \n");
                trace += "Le but " + open.get(0) + " est atteint avec succés \n";
                text.append("Le but " + open.get(0) + " est atteint avec succés \n" + '\n' + '\r');
             
                System.out.println("Le  chemin parcouru est :");
                text.append("Le  chemin parcouru est :" + '\n' + '\r');
                trace += "Le  chemin parcouru est : \n";
                for (int m = 0; m < closed.size(); m++) {
                    System.out.print(closed.get(m) + "==>");
                    text.append(closed.get(m) + "==>");
                    trace += closed.get(m) + "==> ";
                }
                System.out.print(open.get(0) + "\n");
                trace += open.get(0) + "\n";
                text.append(""+open.get(0)+ '\n' + '\r');
                System.out.println("Le nombre de noeuds examinés est :" + nbnoeud);
                 nbnoeud++;
                text.append("Le nombre de noeuds examinés est :" + nbnoeud + '\n' + '\r');
                trace += "Le nombre de noeuds examinés est :" + nbnoeud + "\n";
                return open.get(0);
            }

            Vector<Etat> r = current.genereOperationsApplicables();
            closed.add(open.get(0));
            nbnoeud++;
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
        text.append(str + '\n' + '\r');
        trace += str + "\n";
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            regles = Parsing.chargerRegles("E:\\Gl4\\Semestre1\\IA\\tp3\\IA3\\regles.txt");
        } catch (Exception ex) {
            System.out.println("Problem insolvable");
        }
        String nfile = "";
        Boolean choix = true;
        if (e.getSource() == jt) {
            jt.setText("");
        }

        if (e.getSource() == btnImport) {
            JOptionPane jop2 = new JOptionPane();

            //lecture du fichier de base de regle //		
            try {
                choix = true;
                while (choix) {
                    fDial = new FileDialog(this, "Choisir le fichier des bases des regles", FileDialog.LOAD);
                    fDial.setVisible(true);
                    String nom = fDial.getFile();
                    if (nom != null && ".txt".equals(nom.substring(nom.length() - 4, nom.length()))) {
                        String dir = fDial.getDirectory();
                        nfile = dir + nom;
                        choix = false;
                        JOptionPane.showMessageDialog(this, "BR est chargée avec succes", "Information", JOptionPane.INFORMATION_MESSAGE);
                    } else {

                        JOptionPane.showMessageDialog(this, "le fichier est obligatoirement un fichir text", "Erreur", JOptionPane.ERROR_MESSAGE);
                    }
                }

                InputStream ips = new FileInputStream(nfile);
                InputStreamReader ipsr = new InputStreamReader(ips);
                BufferedReader br = new BufferedReader(ipsr);
                String ligne, s = "";
                int i = 1;
                String Newligne = System.getProperty("line.separator"); //retour à la ligne
                ListBR = new ArrayList<String>();
                while ((ligne = br.readLine()) != null) {
                    ListBR.add(ligne);
                    s = s + "R" + ":" + ligne + Newligne;
                    i++;
                }
                txtrAoubf.setText(s);
                nbr = i - 1;
                br.close();
            } catch (Exception e1) {
                System.out.println(e1.toString());
            }
            btnImport.setEnabled(false);
            Demarrerac.setEnabled(true);

            Demarrerprem.setEnabled(true);
        }

        if (e.getSource() == Demarrerac) {
            try {
                Etat e1 = ResourdreA(jd.getText(), jt.getText(), regles, "1");
                Sauve.setEnabled(true);
            } catch (Exception e2) {

            }
        }
           if (e.getSource() ==  Demarrerprem) {
            try {
                Etat e1 = ResourdreLargeur(jd.getText(), jt.getText(), regles);
                 Sauve.setEnabled(true);
            } catch (Exception e2) {

            }
        }
           
               if (e.getSource() ==  Sauve) {
            try {
                save_trace(Resolveur.trace);
            } catch (Exception e2) {

            }
        }
       
    }

    
    static void save_trace(String t) throws FileNotFoundException{
    
  
                  try(  PrintWriter out = new PrintWriter( "trace.txt" )  ){
                     out.println( t );
             }
             System.out.println("Trace enregistrée sous E:\\Gl4\\Semestre1\\IA\\tp3\\IA3\\trace.txt");
             text.append("Trace enregistrée sous E:\\Gl4\\Semestre1\\IA\\tp3\\IA3\\trace.txt" + '\n' + '\r');
             }
            
             
 
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Interfacegraphique window = new Interfacegraphique();
                    window.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Interfacegraphique() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
       contentPane.setBackground( Color.pink);

        JPanel panel = new JPanel();
         panel.setBackground( Color.pink);
        panel.setBounds(20, 250, 600, 50);
        contentPane.add(panel);
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        btnImport = new JButton("Importer");
        panel.add(btnImport);
        btnImport.addActionListener(this);

        //ajout des boutons 
        Demarrerac = new JButton("Algorithme A*");
        Demarrerac.setEnabled(false);
        Demarrerac.addActionListener(this);
        panel.add(Demarrerac);

        Demarrerprem = new JButton("Recherche en largeur");
        panel.add(Demarrerprem);
        Demarrerprem.setEnabled(false);
        Demarrerprem.addActionListener(this);

          Sauve = new JButton("Sauvegarder");
        Sauve.setEnabled(false);
        Sauve.addActionListener(this);
          panel.add(Sauve);
        JPanel panel_1 = new JPanel();
         panel_1.setBackground( Color.pink);
        panel_1.setBounds(20, 5, 432, 14);
        contentPane.add(panel_1);
        panel_1.setLayout(new GridLayout(0, 2, 0, 0));

        JLabel lblBaseDesRegles = new JLabel("Base des régles");
        panel_1.add(lblBaseDesRegles);

        txtrAoubf = new JTextArea();
        txtrAoubf.setEditable(false);

        txtrAoubf.setText("pas de régles");
        txtrAoubf.setBounds(15, 35, 250, 200);
        contentPane.add(txtrAoubf);

        JPanel panel_2 = new JPanel();
        panel_2.setBounds(20, 300, 100, 14);
        contentPane.add(panel_2);
        panel_2.setLayout(new GridLayout(0, 2, 0, 0));
        JLabel lblBases = new JLabel("Résultat:");
          panel_2.setBackground( Color.pink);
        panel_2.add(lblBases);
        
      
 
        text = new JTextArea();
        text.setEditable(false);
        text.setLineWrap(true);


    text.setWrapStyleWord(true); 
   text.setText("les etapes de résolution:");
        text.setBounds(15, 335, 650, 350);

     
        contentPane.add(text);
         
         

        JLabel lblBut = new JLabel("But");
        lblBut.setBounds(325, 60, 80, 14);
        contentPane.add(lblBut);
        jt = new JTextArea("x==?;y==? ", 8, 8);

        jt.setBounds(370, 60, 158, 14);

        JLabel lbldep = new JLabel("Depart");
        lbldep.setBounds(325, 100, 80, 14);
        contentPane.add(lbldep);
        jd = new JTextArea("x=?;y=? ", 10, 10);

        jd.setBounds(370, 100, 158, 14);

        label = new JLabel("");
        label.setBounds(325, 134, 80, 14);
        contentPane.add(label);
        contentPane.add(jt);
        contentPane.add(jd);
    }
}
