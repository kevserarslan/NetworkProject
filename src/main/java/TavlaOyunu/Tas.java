/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TavlaOyunu;

import java.awt.Button;
import java.awt.Color;

/**
 *
 * @author Kevser
 */

public class Tas {
    private int ucgenNo;       // 0–23 arası üçgen
    private int pozisyonNo;    // 0–4 arası pozisyon (üst üste gelen taşlar)
    private int oyuncuNo;      // 1: siyah (üstten sağa doğru gider), 2: beyaz (alttan sağa doğru gider)
    private Button buton;      // Bu taşın GUI'deki buton nesnesi

    public Tas(int ucgenNo, int pozisyonNo, int oyuncuNo, Button buton) {
        this.ucgenNo = ucgenNo;
        this.pozisyonNo = pozisyonNo;
        this.oyuncuNo = oyuncuNo;
        this.buton = buton;
    }

    public int getUcgenNo() {
        return ucgenNo;
    }

    public void setUcgenNo(int ucgenNo) {
        this.ucgenNo = ucgenNo;
    }

    public int getPozisyonNo() {
        return pozisyonNo;
    }

    public void setPozisyonNo(int pozisyonNo) {
        this.pozisyonNo = pozisyonNo;
    }

    public int getOyuncuNo() {
        return oyuncuNo;
    }

    public Button getButon() {
        return buton;
    }
    public void setButon(Button buton) {
    this.buton = buton;
}

  public void tasGorunurYap() {
    System.out.println("🔍 Taş görünür yapılıyor: Üçgen " + ucgenNo + ", Pozisyon " + pozisyonNo);
    if (oyuncuNo == 1) {
        buton.setBackground(Color.BLACK);
    } else {
        buton.setBackground(Color.WHITE);
    }
}



    public void tasGizle() {
    buton.setBackground(null); // Arka planı sıfırla
   
    // Ama görünmez yapma!
}

}