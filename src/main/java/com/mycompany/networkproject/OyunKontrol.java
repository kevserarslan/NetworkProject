package com.mycompany.networkproject;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.util.ArrayList;
import javax.swing.JLabel;

public class OyunKontrol {

   public static void tasiBaraGonder(Tas kirilanTas, JLabel barLabel, int oyuncuNo, ArrayList<Tas> tasListesi) {
    Button buton = kirilanTas.getButon();

    // Eski parent'tan çıkar
    Container parent = buton.getParent();
    if (parent != null) parent.remove(buton);

    barLabel.setLayout(null);

    // Bar'daki mevcut buton sayısını bul
    int mevcut = 0;
    for (Component c : barLabel.getComponents()) {
        if (c instanceof Button && c.isVisible()) {
            mevcut++;
        }
    }

    // Alt alta yığılsın
    int y = barLabel.getHeight() - 30 - (mevcut * 30);

    // Özellikler
    buton.setSize(30, 30);
    buton.setLocation(0, y);
    buton.setBackground(oyuncuNo == 1 ? Color.BLACK : Color.WHITE);
    buton.setVisible(true);

    barLabel.add(buton);
    barLabel.setComponentZOrder(buton, 0);
    barLabel.revalidate();
    barLabel.repaint();

    // UçgenNo -1 yapılır
    kirilanTas.setUcgenNo(-1);
    kirilanTas.setPozisyonNo(-1);

    System.out.println("🚫 Rakip taş bara eklendi. Mevcut: " + mevcut + ", y: " + y);
}

}
