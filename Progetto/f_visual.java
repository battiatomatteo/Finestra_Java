//FINITO:FUNZIONA
import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;

public class f_visual extends JFrame
{
    JPanel p2;
    JLabel lbl, lbl1;
    JTextArea a;
    private ArrayList<String> st2;
    

    f_visual(ArrayList<String> st2)
    {
        this.p2 = new JPanel(null);
        p2.setBackground(Color.YELLOW);
        //immagine icona finestra
        ImageIcon immagine= new ImageIcon("aereo.jpg");
        this.setIconImage(immagine.getImage());

        this.st2=st2;
        
        creazLabel();

        creazTa();
        
        aggElem();
        
        setPos();

        setFrame();
        
        mostra();
    }

    private void creazTa() //creo il textarea presente nella finestra
    {
        a = new JTextArea();
    }

    private void setPos() //setto le posizioni di ogni elemento 
    {
        lbl.setBounds(20,20,270,15);
        lbl1.setBounds(20,40,300,15);
        a.setBounds(20,60,400,300);
    }

    private void aggElem() //aggiungo gli elemnti al pannello
    {
        p2.add(lbl);
        p2.add(lbl1);
        p2.add(a);
        this.add(p2);
    }

    private void creazLabel() //creo i label presenti nella finestra
    {
        lbl = new JLabel("Elenco: ");
        lbl1 = new JLabel("Compangia N_volo l.partenza l.arrivo Descrizione");
    }

    private void setFrame() //setto il frame
    {
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//MI PERMETTE DI CHIUDERE SOLO QUESTA FINESTRA (NON LA PRINCIPALE O TUTTO)
        this.setSize(500, 400);
        this.setTitle("Visualizza tutto");
        this.setLocation(800, 400);
    }
    
    private void mostra()
    {
        String s = "";
        for(String vs : st2) //FOR EACH:mi preleva gli elementi dell'arraylist per inseririsli nel JTextArea
        {
            s+=vs +"\n";
        }
        a.setText(s); //inserisce nel JTextArea
    }
}