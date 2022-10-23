//manca metà del terzo pulsante, il primo, secondo e terzo funzionano
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;

public class volo extends JFrame implements ActionListener 
{
    JPanel p;
    JTextField t1, t2, t3, t4, t5;
    JLabel lbl1, lbl2, lbl3, lbl4, lbl5;
    JButton btn1, btn2, btn3, btn4;
   
    ArrayList<String> st2 = new ArrayList<String>();
    HashMap<String, ArrayList<String>> dm = new HashMap<String, ArrayList<String>>(); //dizionario per la classe f_elenco (chiave di tipo stringa e valori di tipo ArrayList<String>)
    HashMap<String, ArrayList<String>> dm2 = new HashMap<String, ArrayList<String>>(); //dizionario per la classe f_mod (chiave di tipo stringa e valori di tipo ArrayList<String>)
    
    volo()
    {
        this.p = new JPanel(null);
        //colore di sfondo
        p.setBackground(Color.YELLOW);
        //immagine icona finestra
        ImageIcon immagine= new ImageIcon("aereo.jpg");
        this.setIconImage(immagine.getImage());

        creazTest();
        
        creazLabel();
        
        creazBott();
        
        aggElem();
        
        setPos();
        
        btn1.addActionListener(this);
        btn2.addActionListener(this);
        btn3.addActionListener(this);
        btn4.addActionListener(this);
        
        setFrame();

        recuperoFile();
        recuperoFile2();

        mostraF();
    }
    
    private void creazBott() //creo i bottoni presenti nella finestra
    {
        btn1= new JButton("Inserisci");
        btn2= new JButton("Visualizza");
        btn3= new JButton("Modifica");
        btn4= new JButton("Elenco");
    }
    
    private void creazLabel() //creo i label presenti nella finestra
    {
        lbl1 = new JLabel("n.volo");
        lbl2 = new JLabel("compagnia");
        lbl3 = new JLabel("l.partenza");
        lbl4 = new JLabel("l.arrivo");
        lbl5 = new JLabel("Descrizione");
    }
    
    private void creazTest() //creo i testi della finestra
    {
        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextField();
    }
    
    private void aggElem() //aggiungo gli elemnti al pannello
    {
        this.p.add(lbl1);
        this.p.add(t1);
        this.p.add(lbl2);
        this.p.add(t2);
        this.p.add(lbl3);
        this.p.add(t3);
        this.p.add(lbl4);
        this.p.add(t4);
        this.p.add(lbl5);
        this.p.add(t5);
        this.p.add(btn1);
        this.p.add(btn2);
        this.p.add(btn3);
        this.p.add(btn4);
        this.add(this.p);
    }
    
    private void setPos() //setto le posizioni di ogni elemento 
    {
        lbl1.setBounds(20,20, 50, 20);
        t1.setBounds(20, 50, 100, 20);
        lbl2.setBounds(170,20, 90, 20);
        t2.setBounds(170, 50, 100, 20);
        lbl3.setBounds(320,20, 90, 20);
        t3.setBounds(320, 50, 100, 20);
        lbl4.setBounds(450,20, 90, 20);
        t4.setBounds(450, 50, 100, 20);
        lbl5.setBounds(20,80, 90, 20);
        t5.setBounds(20, 100, 200, 50);
        btn1.setBounds(20,170, 100, 40);
        btn2.setBounds(150,170, 100, 40);
        btn3.setBounds(280,170, 100, 40);
        btn4.setBounds(410,170, 100, 40);
    }
    
    private void setFrame() //setto il frame
    {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 270);
        this.setTitle("SCEGLI IL TUO VOLO");
        this.setLocation(800, 400);
        
    }
    
    public static void main(String args[])
    {
        volo f= new volo();
        f.setVisible(true);
    }
    
    //gestione eventi
    public void actionPerformed(ActionEvent e) 
    {
        String mf2=aggiungiFile();
        st2.add(mf2);
        String mmv=mModVolo();
        dm=cotnrollochiave(mf2); //metodo per controllare la chiave (terzo pulsante)
        dm2=cotnrollochiave2(mmv);
        //gestione eventi
        switch(e.getActionCommand())
        {
            case "Inserisci": //controllo ed inserisco in un file i dati passati dall'uttente
                int n_v;
                String n_volo = t1.getText();
                if(n_volo.equals("")) n_v = 0;
                else n_v = Integer.parseInt(n_volo);
                String compagnia = t2.getText();
                String l_partenza = t3.getText();
                String l_arrivo = t4.getText();
                if(n_v==0 || compagnia.equals("") || l_partenza.equals("") || l_arrivo.equals("") ) //controllo i vari campi di testo se sono vuoti o no
                { //se trovo un campo di testo o più vuoti creo un messaggio da mostrare all'uttente 
                    String mess ="Non è stato inserito:";
                    if(n_v == 0) mess += "\n-numero volo"; //non mi da errore se è una stringa ma non spunta il mess. di riepilogo
                    if(compagnia.equals("")) mess += "\n-compangia";
                    if(l_partenza.equals("")) mess += "\n-l_partenza";
                    if(l_arrivo.equals("")) mess += "\n-l_arrivo";
                    JOptionPane.showMessageDialog(this, mess);
                }
                else //se è tutto apposto li inserisco nel file
                {
                    //aggiungo ai file
                    String s =aggiungiFile();
                    //creo il file che conterrà l'elenco
                    Scanner scan = new Scanner(s);
                    String fileContent =" ";
                    while(scan.hasNextLine())
                    {
                        fileContent = fileContent.concat(scan.nextLine()) ;
                    }
                    FileWriter writer;
                    try {
                        writer = new FileWriter("elenco.txt", true);
                        writer.write(fileContent+ "\n");
                        writer.close();
                    } catch (IOException e1) 
                    {
                        e1.printStackTrace();
                    }
                    
                //mess riepilogo
                String m = riepilogo();
                JOptionPane.showMessageDialog(this, m);//mostro il messaggio di riepilogo
                scan.close();
                }
                //svuoto i campi di testo
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                break;
            case "Visualizza": //mi mostra tutti i dati inseriti in una finestra 
                f_visual f2 = new f_visual(st2);
                f2.setVisible(true);
                break;
            case "Modifica": //mi permette di modificare dei dati già inseriti precedentemente----DA FINIRE!!!!(manca da cambiare il file con dati nuovi)
                f_mod f3 = new f_mod(dm2); 
                f3.setVisible(true);
                break;
            case "Elenco": // mostro attraverso un controllo se la compagnia inserita è prsente nel file
                f_elenco f4 = new f_elenco(dm); //creo la finestra dove controllo la "compagnia" (parametro in ingresso : dm ->hashmap)
                f4.setVisible(true);
                break;
        }   
    }

    //metodi per gli eventi

    

    private void recuperoFile() //PS:se non parte può essersi creata una riga vuota nel file: elenco.txt; 
    {
        //recupero le cose che sono presenti nel file
        ArrayList<String> list = read("elenco.txt"); //insieme di tutte le righe del file
        for(String riga : list) //riga=stringa dei dati presi dal file 
        {
            boolean r = false;
            StringTokenizer st = new StringTokenizer(riga,"     "); //StringTokenizer=oggetto per  scomporre la lista in vari campi
            String comp = st.nextToken(); //nextToken = ottengo il campo successivo
            Object[] keys = dm.keySet().toArray();
            for(int i=0; i<keys.length; i++)
            {
                if(comp.equals(keys[i]))
                {
                    r = true;
                }
            }
            //inserimento dei nuovi dati 
            if(r==true)
            {
                //se è vero inserisco la stringa in una lista già esistente
                ArrayList<String> val = dm.get(comp);
                val.add(riga);
            }
            else
            {
                //se è falso inserisco la nuova stringa in una nuova lista con chiave: la sua compagnia
                ArrayList<String> st3 = new ArrayList<String>(); //creo la nuova lista da associare alla nuova chiave
                st3.add(riga); //inserisco nella nuova lista la stringa con i dati dell'utente
                dm.put(comp, st3);
            }
        }  
    }
    private void recuperoFile2() //PS:se non parte può essersi creata una riga vuota nel file: elenco.txt; 
    {
        //recupero le cose che sono presenti nel file
        ArrayList<String> list1 = read("elenco.txt"); //insieme di tutte le righe del file
        for(String riga1 : list1) //riga=stringa dei dati presi dal file 
        {
            boolean r = false;
            StringTokenizer st1 = new StringTokenizer(riga1,"     "); //StringTokenizer=oggetto per  scomporre la lista in vari campi
            String comp2 = st1.nextToken(); //nextToken = ottengo il campo successivo
            Object[] keys = dm2.keySet().toArray();
            for(int i=0; i<keys.length; i++)
            {
                if(comp2.equals(keys[i]))
                {
                    r = true;
                }
            }
            //inserimento dei nuovi dati 
            if(r==true)
            {
                //se è vero inserisco la stringa in una lista già esistente
                ArrayList<String> val2 = dm2.get(comp2);
                val2.add(riga1);
            }
            else
            {
                //se è falso inserisco la nuova stringa in una nuova lista con chiave: la sua compagnia
                ArrayList<String> st3 = new ArrayList<String>(); //creo la nuova lista da associare alla nuova chiave
                st3.add(riga1); //inserisco nella nuova lista la stringa con i dati dell'utente
                dm2.put(comp2, st3);
            }
        }  
    }

    public ArrayList<String> read(String percorso) //mi crea una lista di stringhe con tutte le righe del file
    {
        ArrayList<String> righe = new ArrayList<String>();
        try{
            FileReader i = new  FileReader(percorso);
            BufferedReader o = new BufferedReader(i);
            String letto = o.readLine();
            while(letto != null)
            {
                righe.add(letto);
                letto = o.readLine();
            }
            i.close();
        }
        catch(Exception e){
        }
        return righe;
    }
    private HashMap<String, ArrayList<String>> cotnrollochiave(String mf2) 
    {
        //controllo chiave
        boolean v= false;
        String comp= t2.getText();
        Object[] keys = dm.keySet().toArray();
        for(int i=0; i<keys.length; i++)
        {
            if(comp.equals(keys[i]))
            {
                v = true;
            }
        }
        //inserimento dei nuovi dati 
        if(v==true)
        {
            //se è vero inserisco la stringa in una lista già esistente
            ArrayList<String> valore = dm.get(comp);
            valore.add(mf2);
        }
        else
        {
            //se è falso inserisco la nuova stringa in una nuova lista con chiave: la sua compagnia
            ArrayList<String> st3 = new ArrayList<String>(); //creo la nuova lista da associare alla nuova chiave
            st3.add(mf2); //inserisco nella nuova lista la stringa con i dati dell'utente
            dm.put(comp, st3);
        }
        return dm;
    }

    private void mostraF()//metodo che mi permette di mostrare in f_visual (il bottone "VISUALIZZA") cosa trovo nel file
    {
        try
        {
            FileReader i = new FileReader("elenco.txt");
            BufferedReader o = new BufferedReader(i);
            String letto=o.readLine();
            while(letto!=null)
            {
                st2.add(letto);
                letto=o.readLine();
            }
            i.close();
        }
        catch(Exception e)
        {
        }
    }

    private String riepilogo() //ritorno il messaggio da mostrare (con tutti i dati inseriti) all'utente
    {
        return "RIEPILOGO:\n Numero volo: " + t1.getText() + "\nCompagnia: " + t2.getText() + "\nL.ritorno: " +t3.getText() + "\nL.arrivo: " +t4.getText() + "\nDescrizione: " +t5.getText() ;
    }
    
    private String aggiungiFile() //ritorno una stringa con al suo intenro i dati inseriti dall'utente
    {
        return  t2.getText() + "     " + t1.getText()+ "          " +t3.getText() + "         " +t4.getText() + "          " +t5.getText();
    }

    private String mModVolo() //ritorno una stringa con al suo intenro i dati inseriti dall'utente
    {
        return  t1.getText() + "     " +t2.getText() + "          " +t3.getText() + "         " +t4.getText() + "          " +t5.getText();
    }

    private HashMap<String, ArrayList<String>> cotnrollochiave2(String mmv) 
    {
        //controllo chiave
        boolean v= false;
        String comp= t1.getText();
        Object[] keys = dm2.keySet().toArray();
        for(int i=0; i<keys.length; i++)
        {
            if(comp.equals(keys[i]))
            {
                v = true;
            }
        }
        //inserimento dei nuovi dati 
        if(v==true)
        {
            //se è vero inserisco la stringa in una lista già esistente
            ArrayList<String> valore2 = dm2.get(comp);
            valore2.add(mmv);
        }
        else
        {
            //se è falso inserisco la nuova stringa in una nuova lista con chiave: la sua compagnia
            ArrayList<String> st3 = new ArrayList<String>(); //creo la nuova lista da associare alla nuova chiave
            st3.add(mmv); //inserisco nella nuova lista la stringa con i dati dell'utente
            dm2.put(comp, st3);
        }
        return dm2;
    }
}