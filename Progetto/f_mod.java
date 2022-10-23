import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

public class f_mod extends JFrame implements ActionListener
{
    JPanel p5;
    JLabel lbl,lbl1, lbl2, lbl3, lbl4, lbl5;
    JButton btn, btn2;
    JTextField t,t1, t2, t3, t4, t5;
    HashMap<String, ArrayList<String>> dm = new HashMap<String, ArrayList<String>>();

    f_mod(HashMap<String, ArrayList<String>> dm2)
    {
        this.p5 = new JPanel(null);
        p5.setBackground(Color.YELLOW);
        //immagine icona finestra
        ImageIcon immagine= new ImageIcon("aereo.jpg");
        this.setIconImage(immagine.getImage());
        this.dm=dm2;

        creazTest();
        
        creazLabel();
        
        creazBott();
        
        aggElem();
        
        setPos();

        btn.addActionListener(this);
        btn2.addActionListener(this);


        setFrame();
    }
    
    private void setPos() 
    {
        lbl.setBounds(20,20,270,15);
        t.setBounds(20,50, 130,25);
        btn.setBounds(20,90,150,40);
        lbl1.setBounds(20,150, 50, 20);
        t1.setBounds(20, 180, 100, 20);
        lbl2.setBounds(170,150, 90, 20);
        t2.setBounds(170, 180, 100, 20);
        lbl3.setBounds(320,150, 90, 20);
        t3.setBounds(320, 180, 100, 20);
        lbl4.setBounds(450,150, 90, 20);
        t4.setBounds(450, 180, 100, 20);
        lbl5.setBounds(20,210, 90, 20);
        t5.setBounds(20, 230, 200, 50);
        btn2.setBounds(230, 230, 120, 50);
    }

    private void aggElem() 
    {
        this.p5.add(lbl);
        this.p5.add(t);
        this.p5.add(btn);
        this.p5.add(lbl1);
        this.p5.add(t1);
        this.p5.add(lbl2);
        this.p5.add(t2);
        this.p5.add(lbl3);
        this.p5.add(t3);
        this.p5.add(lbl4);
        this.p5.add(t4);
        this.p5.add(lbl5);
        this.p5.add(t5);
        this.p5.add(btn2);
        this.add(p5);
    }

    private void creazBott() 
    {
        btn= new JButton("MODIFICA");
        btn2= new JButton("INSERISCI");
        btn2.setVisible(false);
    }

    private void creazLabel() 
    {
        lbl = new JLabel("Inserisci inserisci il volo che desideri modificare");
        lbl1 = new JLabel("n.volo");
        lbl1.setVisible(false);
        lbl2 = new JLabel("compagnia");
        lbl2.setVisible(false);
        lbl3 = new JLabel("l.partenza");
        lbl3.setVisible(false);
        lbl4 = new JLabel("l.arrivo");
        lbl4.setVisible(false);
        lbl5 = new JLabel("Descrizione");
        lbl5.setVisible(false);
    }
    
    private void creazTest() 
    {
        t = new JTextField();
        t1 = new JTextField();
        t1.setVisible(false);
        t2 = new JTextField();
        t2.setVisible(false);
        t3 = new JTextField();
        t3.setVisible(false);
        t4 = new JTextField();
        t4.setVisible(false);
        t5 = new JTextField();
        t5.setVisible(false);
    }

    private void setFrame() //setto il frame
    {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//MI PERMETTE DI CHIUDERE SOLO QUESTA FINESTRA (NON LA PRINCIPALE O TUTTO)
        this.setSize(600, 370);
        this.setTitle("MODIFICA");
        this.setLocation(800, 400);
    }
    @Override
    public void actionPerformed(ActionEvent e) //da finire
    {
        switch(e.getActionCommand())
        {   case "MODIFICA":
                if(t.getText().equals("")) 
                {
                    JOptionPane.showMessageDialog(this, "DEVI INSERISRE IL NUMERO DEL VOLO!!!");
                    t1.setVisible(false);
                    t2.setVisible(false);
                    t3.setVisible(false);
                    t4.setVisible(false);
                    t5.setVisible(false);
                    lbl1.setVisible(false);
                    lbl2.setVisible(false);
                    lbl3.setVisible(false);
                    lbl4.setVisible(false);
                    lbl5.setVisible(false);
                    btn2.setVisible(false);
                }
                else
                {
                    boolean k= false;
                    String test = t.getText(); //prendo il dato inserito dall'utente: il volo che vuole controllare
                    Object[] keys = dm.keySet().toArray(); //mi creo un array con le chiavi dell'hashmap 
                    for(int i=0; i<keys.length; i++)
                    {
                        if(test.equals(keys[i]))
                        {
                            k = true;
                            dm.remove(keys[i]);//tolgo l'elemento associato a quel volo per inserire quello nuovo
                        }
                    }
                    if(k==true)
                    {
                        t1.setVisible(true);
                        t2.setVisible(true);
                        t3.setVisible(true);
                        t4.setVisible(true);
                        t5.setVisible(true);
                        lbl1.setVisible(true);
                        lbl2.setVisible(true);
                        lbl3.setVisible(true);
                        lbl4.setVisible(true);
                        lbl5.setVisible(true);
                        btn2.setVisible(true);
                        
                        System.out.println("FATTO");
                    }
                        
                    else 
                    {
                        //mess di errore
                        String messEr= "ERRORE:\n Non è stata inserita nessun volo con questo codice";
                        JOptionPane.showMessageDialog(this, messEr);
                    }
                }
                //.delate("elenco.txt");
                break;
            case "INSERISCI":
            {
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
                else
                { //mi insetrisce il nuovo volo 
                    String sn = nuovoVolo();
                    String chiave= t1.getText();
                    ArrayList<String> st3 = new ArrayList<String>(); //creo la nuova lista da associare alla nuova chiave
                    st3.add(sn); //inserisco nella nuova lista la stringa con i dati dell'utente
                    dm.put(chiave, st3);
                    String mess_riepilogo= riepilogo2();
                    JOptionPane.showMessageDialog(this, mess_riepilogo);//mostro il messaggio di riepilogo
                    //nascondo tutto
                    t1.setVisible(false);
                    t2.setVisible(false);
                    t3.setVisible(false);
                    t4.setVisible(false);
                    t5.setVisible(false);
                    lbl1.setVisible(false);
                    lbl2.setVisible(false);
                    lbl3.setVisible(false);
                    lbl4.setVisible(false);
                    lbl5.setVisible(false);
                    btn2.setVisible(false);
                    //FIN QUA FUNZIONA
                }
            }
        }
        //svuoto il file per inserire gli elementi aggiornati-->manca 
        //ATTENZIONE!!!(nella finestra che si apre cliccando "VISUALIZZA" LA LISTA AGGIORNATA SARà VISTA DOPO LA RIAPERTURA DELLA FINESTRA PRINCIPALE!!!)    
    }

    private String riepilogo2() //creazione messaggio di riepilogo
    {
        return "RIEPILOGO:\n Numero volo: " + t1.getText() + "\nCompagnia: " + t2.getText() + "\nL.ritorno: " +t3.getText() + "\nL.arrivo: " +t4.getText() + "\nDescrizione: " +t5.getText() ;
    }

    private String nuovoVolo() 
    {
        return t1.getText() + "     " +t2.getText() + "          " +t3.getText() + "         " +t4.getText() + "          " +t5.getText();
    }
}