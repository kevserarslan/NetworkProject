/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.networkproject;

import java.awt.Button;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author Kevser
 */
public class OyunKontrol {

    private ArrayList<Tas> siyahTaslar;
    private ArrayList<Tas> beyazTaslar;

    public OyunKontrol(ArrayList<Tas> siyah, ArrayList<Tas> beyaz) {
        this.siyahTaslar = siyah;
        this.beyazTaslar = beyaz;
    }

    public boolean hamleGecerliMi(Tas secilenTas, int hedefUcgen, int[] zarlar, int oyuncuNo) {
        int mevcutUcgen = secilenTas.getUcgenNo();
        int mesafe = Math.abs(hedefUcgen - mevcutUcgen);

        if (oyuncuNo == 1 && hedefUcgen >= mevcutUcgen) return false;
        if (oyuncuNo == 2 && hedefUcgen <= mevcutUcgen) return false;

        return mesafe == zarlar[0] || mesafe == zarlar[1];
    }

    public void tasiTasi(Tas tas, Button hedefButon, int hedefUcgen, int hedefPozisyon) {
        tas.getButon().setVisible(false);
        hedefButon.setVisible(true);
        hedefButon.setBackground(tas.getOyuncuNo() == 1 ? Color.BLACK : Color.WHITE);
        tas.setButon(hedefButon);
        tas.setUcgenNo(hedefUcgen);
        tas.setPozisyonNo(hedefPozisyon);
    }

    public Tas tasBul(Button buton, int oyuncuNo) {
        ArrayList<Tas> liste = (oyuncuNo == 1) ? siyahTaslar : beyazTaslar;
        for (Tas t : liste) {
            if (t.getButon() == buton) return t;
        }
        return null;
    }
}
