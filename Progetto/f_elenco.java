//FINITO:FUNZIONA
import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
 
public class f_elenco extends JFrame implements ActionListener
{
    JPanel p2;
    JTextField t;
    JTextArea a2;
    JLabel lbl;
    JButton btn;
    HashMap<String, ArrayList<String>> dm = new HashMap<String, ArrayList<String>>();

    f_elenco(HashMap<String, ArrayList<String>> dm)
    {
        this.p2 = new JPanel(null);
        this.dm=dm;
        p2.setBackground(Color.YELLOW);
        //immagine icona finestra
        ImageIcon immagine= new ImageIcon("aereo.jpg");
        this.setIconImage(immagine.getImage());
        
        creazTest();

        creazTextA();
        
        creazLabel();
        
        creazBott();
        
        aggElem();
        
        setPos();

        btn.addActionListener(this);

        setFrame();
    }

    private void creazTextA() //creo il text area
    {
        a2 = new JTextArea();
        a2.setVisible(false);
    }

    private void setPos() //setto le posizioni di ogni elemento 
    {
        lbl.setBounds(20,20,270,15);
        t.setBounds(20,50, 130,25);
        btn.setBounds(20,90,150,40);
        a2.setBounds(20,150,300,200);
    }

    private void aggElem() //aggiungo gli elemnti al pannello
    {
        p2.add(lbl);
        p2.add(t);
        p2.add(btn);
        p2.add(a2);
        this.add(p2);
    }

    private void creazBott() //creo i bottoni presenti nella finestra
    {
        btn= new JButton("CONTROLLO");
    }

    private void creazLabel() //creo i label presenti nella finestra
    {
        lbl = new JLabel("Inserisci la comapgnia che desideri controllare");
    }

    private void creazTest()  //creo i testi della finestra
    {
        t = new JTextField();
    }

    private void setFrame() //setto il frame
    {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//MI PERMETTE DI CHIUDERE SOLO QUESTA FINESTRA (NON LA PRINCIPALE O TUTTO)
        this.setSize(400, 400);
        this.setTitle("ELENCO COMPAGNIE");
        this.setLocation(800, 400);
    }

    @Override
    public void actionPerformed(ActionEvent e) //da finire
    {
        String puls = e.getActionCommand();
        if(puls.equals("CONTROLLO"))
        {
            if(t.getText().equals(""))
            {
                //se non è stato inserito niente mi mostra un mess di errore
                String messEr= "ERRORE:\n Non è stata inserita nessuna compagnia !!!";
                JOptionPane.showMessageDialog(this, messEr);
                a2.setVisible(false); //nascondo il textArea
            }
            else
            {
                boolean k= false;
                String test = t.getText(); //prendo il dato inserito dall'utente: la compagnia che vuole controllare
                Object[] keys = dm.keySet().toArray(); //mi creo un array con le chiavi dell'hashmap 
                for(int i=0; i<keys.length; i++)
                {
                    if(test.equals(keys[i]))
                    {
                        k = true;
                    }
                }
                if(k==true)
                {
                    //mostrto 
                    a2.setVisible(true);
                    String arr= "";        //creo una variabile stringa per creare una stinga unica con gli lementi dell'arraylist associato alla chiave
                    for(String z: dm.get(test))
                    {
                        arr+=z;
                        arr+="\n";
                    }
                    a2.setText(arr); //inserisco nel textarea il contenuto lavariabile stringa composta dagli elementi dell'arraylist
                }
                else 
                {
                    //mess di errore
                    String messEr= "ERRORE:\n Non è stata inserita nessuna compagnia con questo nome";
                    JOptionPane.showMessageDialog(this, messEr);
                    a2.setVisible(false); //nascondo il textArea
                }
            }
        }
    }   
}