/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.networkproject;

import java.awt.Button;
import java.awt.Color;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Math.random;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevser
 */
public class GUI extends javax.swing.JFrame {
//    private BackgroundPanel kevser;

    /**
     * Creates new form GUI
     */
    private Client client;
    private int oyuncuSirasi = 0;  // 0 = değil, 1 veya 2 ise sırası
    boolean oyunBasladi = false;
    boolean ilkTur = true;
    int ilkZar1 = 0;
    int ilkZar2 = 0;

    //private java.awt.Button secilenButon = null; // BURAYA EKLE sonradan ekledim
    private ArrayList<Tas> siyahTaslar = new ArrayList<>();
    private ArrayList<Tas> beyazTaslar = new ArrayList<>();

    private Tas secilenTas = null;
    private int[] zarlar = new int[2];  // Örneğin zarlar[0] = 3, zarlar[1] = 5
    // Tüm butonlar
    Button[][] butonlar = new Button[24][5];
    
    private Button secilenButon = null;


    Random random = new Random();

    public GUI() {

        initComponents();
        client = new Client(this);
        client.connect("localhost", 12345);
        jLayeredPane1.setLayer(jLabel1, 0);  // Arka plan
        for (int i = 0; i < jLayeredPane1.getComponentCount(); i++) {
            java.awt.Component c = jLayeredPane1.getComponent(i);
            if (c instanceof java.awt.Button) {
                jLayeredPane1.setLayer(c, 1);
            }
        }

        zarAtButton.setEnabled(false); // ❗ Oyuncu eşleşmeden zar atamaz en son bunu ekledim

        // Siyah taşlar (Oyuncu 1)
// Siyah taşlar (Oyuncu 1)
        siyahTaslar.add(new Tas(23, 0, 1, button_23_0));
        siyahTaslar.add(new Tas(23, 1, 1, button_23_1));

        siyahTaslar.add(new Tas(5, 0, 1, button_5_0));
        siyahTaslar.add(new Tas(5, 1, 1, button_5_1));
        siyahTaslar.add(new Tas(5, 2, 1, button_5_2));
        siyahTaslar.add(new Tas(5, 3, 1, button_5_3));
        siyahTaslar.add(new Tas(5, 4, 1, button_5_4));

        siyahTaslar.add(new Tas(7, 2, 1, button_7_2));
        siyahTaslar.add(new Tas(7, 3, 1, button_7_3));
        siyahTaslar.add(new Tas(7, 4, 1, button_7_4));

        siyahTaslar.add(new Tas(12, 0, 1, button_12_0));
        siyahTaslar.add(new Tas(12, 1, 1, button_12_1));
        siyahTaslar.add(new Tas(12, 2, 1, button_12_2));
        siyahTaslar.add(new Tas(12, 3, 1, button_12_3));
        siyahTaslar.add(new Tas(12, 4, 1, button_12_4));

// Beyaz taşlar (Oyuncu 2)
        beyazTaslar.add(new Tas(11, 0, 2, button_11_0));
        beyazTaslar.add(new Tas(11, 1, 2, button_11_1));
        beyazTaslar.add(new Tas(11, 2, 2, button_11_2));
        beyazTaslar.add(new Tas(11, 3, 2, button_11_3));
        beyazTaslar.add(new Tas(11, 4, 2, button_11_4));

        beyazTaslar.add(new Tas(0, 3, 2, button_0_3));
        beyazTaslar.add(new Tas(0, 4, 2, button_0_4));

        beyazTaslar.add(new Tas(16, 0, 2, button_16_0));
        beyazTaslar.add(new Tas(16, 1, 2, button_16_1));
        beyazTaslar.add(new Tas(16, 2, 2, button_16_2));

        beyazTaslar.add(new Tas(18, 0, 2, button_18_0));
        beyazTaslar.add(new Tas(18, 1, 2, button_18_1));
        beyazTaslar.add(new Tas(18, 2, 2, button_18_2));
        beyazTaslar.add(new Tas(18, 3, 2, button_18_3));
        beyazTaslar.add(new Tas(18, 4, 2, button_18_4));

        for (Tas tas : siyahTaslar) {
            tas.tasGorunurYap();
        }
        for (Tas tas : beyazTaslar) {
            tas.tasGorunurYap();
        }

        // 0–23 üçgenleri, her biri 5 pozisyon
        butonlar[0][0] = button_0_0;
        butonlar[0][1] = button_0_1;
        butonlar[0][2] = button_0_2;
        butonlar[0][3] = button_0_3;
        butonlar[0][4] = button_0_4;
        butonlar[1][0] = button_1_0;
        butonlar[1][1] = button_1_1;
        butonlar[1][2] = button_1_2;
        butonlar[1][3] = button_1_3;
        butonlar[1][4] = button_1_4;
        butonlar[2][0] = button_2_0;
        butonlar[2][1] = button_2_1;
        butonlar[2][2] = button_2_2;
        butonlar[2][3] = button_2_3;
        butonlar[2][4] = button_2_4;
        butonlar[3][0] = button_3_0;
        butonlar[3][1] = button_3_1;
        butonlar[3][2] = button_3_2;
        butonlar[3][3] = button_3_3;
        butonlar[3][4] = button_3_4;
        butonlar[4][0] = button_4_0;
        butonlar[4][1] = button_4_1;
        butonlar[4][2] = button_4_2;
        butonlar[4][3] = button_4_3;
        butonlar[4][4] = button_4_4;
        butonlar[5][0] = button_5_0;
        butonlar[5][1] = button_5_1;
        butonlar[5][2] = button_5_2;
        butonlar[5][3] = button_5_3;
        butonlar[5][4] = button_5_4;
        butonlar[6][0] = button_6_0;
        butonlar[6][1] = button_6_1;
        butonlar[6][2] = button_6_2;
        butonlar[6][3] = button_6_3;
        butonlar[6][4] = button_6_4;
        butonlar[7][0] = button_7_0;
        butonlar[7][1] = button_7_1;
        butonlar[7][2] = button_7_2;
        butonlar[7][3] = button_7_3;
        butonlar[7][4] = button_7_4;
        butonlar[8][0] = button_8_0;
        butonlar[8][1] = button_8_1;
        butonlar[8][2] = button_8_2;
        butonlar[8][3] = button_8_3;
        butonlar[8][4] = button_8_4;
        butonlar[9][0] = button_9_0;
        butonlar[9][1] = button_9_1;
        butonlar[9][2] = button_9_2;
        butonlar[9][3] = button_9_3;
        butonlar[9][4] = button_9_4;
        butonlar[10][0] = button_10_0;
        butonlar[10][1] = button_10_1;
        butonlar[10][2] = button_10_2;
        butonlar[10][3] = button_10_3;
        butonlar[10][4] = button_10_4;
        butonlar[11][0] = button_11_0;
        butonlar[11][1] = button_11_1;
        butonlar[11][2] = button_11_2;
        butonlar[11][3] = button_11_3;
        butonlar[11][4] = button_11_4;
        butonlar[12][0] = button_12_0;
        butonlar[12][1] = button_12_1;
        butonlar[12][2] = button_12_2;
        butonlar[12][3] = button_12_3;
        butonlar[12][4] = button_12_4;
        butonlar[13][0] = button_13_0;
        butonlar[13][1] = button_13_1;
        butonlar[13][2] = button_13_2;
        butonlar[13][3] = button_13_3;
        butonlar[13][4] = button_13_4;
        butonlar[14][0] = button_14_0;
        butonlar[14][1] = button_14_1;
        butonlar[14][2] = button_14_2;
        butonlar[14][3] = button_14_3;
        butonlar[14][4] = button_14_4;
        butonlar[15][0] = button_15_0;
        butonlar[15][1] = button_15_1;
        butonlar[15][2] = button_15_2;
        butonlar[15][3] = button_15_3;
        butonlar[15][4] = button_15_4;
        butonlar[16][0] = button_16_0;
        butonlar[16][1] = button_16_1;
        butonlar[16][2] = button_16_2;
        butonlar[16][3] = button_16_3;
        butonlar[16][4] = button_16_4;
        butonlar[17][0] = button_17_0;
        butonlar[17][1] = button_17_1;
        butonlar[17][2] = button_17_2;
        butonlar[17][3] = button_17_3;
        butonlar[17][4] = button_17_4;
        butonlar[18][0] = button_18_0;
        butonlar[18][1] = button_18_1;
        butonlar[18][2] = button_18_2;
        butonlar[18][3] = button_18_3;
        butonlar[18][4] = button_18_4;
        butonlar[19][0] = button_19_0;
        butonlar[19][1] = button_19_1;
        butonlar[19][2] = button_19_2;
        butonlar[19][3] = button_19_3;
        butonlar[19][4] = button_19_4;
        butonlar[20][0] = button_20_0;
        butonlar[20][1] = button_20_1;
        butonlar[20][2] = button_20_2;
        butonlar[20][3] = button_20_3;
        butonlar[20][4] = button_20_4;
        butonlar[21][0] = button_21_0;
        butonlar[21][1] = button_21_1;
        butonlar[21][2] = button_21_2;
        butonlar[21][3] = button_21_3;
        butonlar[21][4] = button_21_4;
        butonlar[22][0] = button_22_0;
        butonlar[22][1] = button_22_1;
        butonlar[22][2] = button_22_2;
        butonlar[22][3] = button_22_3;
        butonlar[22][4] = button_22_4;
        butonlar[23][0] = button_23_0;
        butonlar[23][1] = button_23_1;
        butonlar[23][2] = button_23_2;
        butonlar[23][3] = button_23_3;
        butonlar[23][4] = button_23_4;

//        for (java.awt.Component comp : jLayeredPane1.getComponents()) {
//            if (comp instanceof java.awt.Button) {
//                java.awt.Button btn = (java.awt.Button) comp;
//                btn.addActionListener(new java.awt.event.ActionListener() {
//                    public void actionPerformed(java.awt.event.ActionEvent evt) {
//                        ortakButonTiklama(evt);
//                    }
//                });
//            }
//        }
        for (int ucgen = 0; ucgen < 24; ucgen++) {
            for (int pozisyon = 0; pozisyon < 5; pozisyon++) {
                Button btn = ucgenButonunuAl(ucgen, pozisyon);
                if (btn != null) {
                    btn.setName("button_" + ucgen + "_" + pozisyon);
                    btn.addActionListener(evt -> ortakButonTiklama(evt));
                    System.out.println("Tıklanan buton: " + btn.getName());
                }
            }
        }

    }
    private void ortakButonTiklama(java.awt.event.ActionEvent evt) {
    if (oyuncuSirasi != client.getOyuncuNumarasi()) {
        JOptionPane.showMessageDialog(null, "Sıra sizde değil!");
        return;
    }

    Button tiklanan = (Button) evt.getSource();
    System.out.println("🖱️ Tıklanan buton: " + tiklanan.getName());
    System.out.println("Hedef buton görünür mü? → " + tiklanan.isVisible());

    // 🟥 Taş seçimi
    if (secilenTas == null) {
        if (!tiklanan.isVisible()) {
            System.out.println("⚠️ Görünmez butona tıklandı, taş seçilemez.");
            return;
        }

        for (Tas tas : (oyuncuSirasi == 1 ? siyahTaslar : beyazTaslar)) {
            if (tas.getButon() == tiklanan) {
                secilenTas = tas;
                tiklanan.setBackground(Color.RED); // Seçimi göster
                System.out.println("🔴 Taş seçildi: Üçgen " + tas.getUcgenNo() + ", Pozisyon " + tas.getPozisyonNo());
                return;
            }
        }

        System.out.println("⚠️ Taş bulunamadı.");
        return;
    }

    // 🟦 Taş taşıma işlemi
    if (tiklanan.isVisible()) {
        System.out.println("⚠️ Zaten dolu bir yere taşınamaz.");
        return;
    }

    int hedefUcgen = ucgenNumarasiniBul(tiklanan);
    int eskiUcgen = secilenTas.getUcgenNo();

    // ❗ Hareket yönü kontrolü
    if ((oyuncuSirasi == 1 && hedefUcgen >= eskiUcgen) ||
        (oyuncuSirasi == 2 && hedefUcgen <= eskiUcgen)) {
        JOptionPane.showMessageDialog(null, "Ters yöne hamle yapılamaz!");
        return;
    }

    // ❗ Zar farkı (mutlak mesafe)
    int hareketMesafesi = Math.abs(hedefUcgen - eskiUcgen);

    System.out.println("🎲 Zarlar: " + zarlar[0] + " ve " + zarlar[1]);
    System.out.println("🎯 Hareket mesafesi: " + hareketMesafesi);

    if (hareketMesafesi != zarlar[0] && hareketMesafesi != zarlar[1]) {
        JOptionPane.showMessageDialog(null, "Zar ile uyumlu değil!");
        return;
    }

    // ✅ Hareketi uygula
    secilenTas.getButon().setVisible(false);

    tiklanan.setVisible(true);
    tiklanan.setBackground(oyuncuSirasi == 1 ? Color.BLACK : Color.WHITE);

    secilenTas.setButon(tiklanan);
    secilenTas.setUcgenNo(hedefUcgen);
    secilenTas.setPozisyonNo(pozisyonNumarasiniBul(tiklanan));

    System.out.println("✅ Taş başarıyla taşındı!");
    secilenTas = null;
}


    private int pozisyonNumarasiniBul(Button buton) {
    String name = buton.getName();  // örnek: button_5_3
    if (name == null || !name.startsWith("button_")) {
        return -1;
    }
    try {
        String[] parts = name.substring(7).split("_");
        return Integer.parseInt(parts[1]);  // ikinci kısım: pozisyon numarası
    } catch (Exception e) {
        return -1;
    }
}


    private int ucgenNumarasiniBul(Button buton) {
        String name = buton.getName();  // örnek: button_5_3
        if (name == null || !name.startsWith("button_")) {
            return -1;
        }
        try {
            String[] parts = name.substring(7).split("_");
            return Integer.parseInt(parts[0]);  // ilk kısım: üçgen numarası
        } catch (Exception e) {
            return -1;
        }
    }


    public Button ucgenButonunuAl(int ucgenNo, int pozisyonNo) {
        if (ucgenNo >= 0 && ucgenNo < 24 && pozisyonNo >= 0 && pozisyonNo < 5) {
            return butonlar[ucgenNo][pozisyonNo];
        }
        return null;
    }

    public void gelenMesajGoster(String mesaj) {
        if (mesaj.startsWith("ILKZAR:")) {
            String veri = mesaj.substring(8).trim();
            if (!veri.isEmpty()) {
                try {
                    int zar = Integer.parseInt(veri);
                    String yol = "com/mycompany/networkproject/images/" + zar + ".png";
                    URL imageUrl = getClass().getClassLoader().getResource(yol);
                    if (imageUrl != null) {
                        jLabelzar2.setIcon(new ImageIcon(imageUrl)); // rakibin ilk zarı
                    }
                } catch (NumberFormatException e) {
                    System.err.println("ILKZAR hatalı: " + veri);
                }
            }

        } else if (mesaj.startsWith("ZAR:") && mesaj.contains(",")) {
            String[] gelenzarlar = mesaj.substring(4).split(",");
            if (gelenzarlar.length == 2) {
                try {
                    int z1 = Integer.parseInt(gelenzarlar[0].trim());
                    int z2 = Integer.parseInt(gelenzarlar[1].trim());

                    // 🔴 BUNLAR YOKSA HAMLE ENGELLENİR
                    zarlar[0] = z1;
                    zarlar[1] = z2;

                    String yol1 = "com/mycompany/networkproject/images/" + z1 + ".png";
                    String yol2 = "com/mycompany/networkproject/images/" + z2 + ".png";

                    URL image1 = getClass().getClassLoader().getResource(yol1);
                    URL image2 = getClass().getClassLoader().getResource(yol2);

                    if (image1 != null) {
                        jLabelzar2.setIcon(new ImageIcon(image1)); // rakip zar 1
                    }
                    if (image2 != null) {
                        jLabelZar1.setIcon(new ImageIcon(image2)); // rakip zar 2
                    }
                } catch (NumberFormatException e) {
                    System.err.println("ZAR:x,y hatalı: " + mesaj);
                }
            }

        } else if (mesaj.startsWith("ZAR:")) {
            String veri = mesaj.substring(4).trim();
            if (!veri.isEmpty()) {
                try {
                    int zar = Integer.parseInt(veri);
                    String yol = "com/mycompany/networkproject/images/" + zar + ".png";
                    URL imageUrl = getClass().getClassLoader().getResource(yol);
                    if (imageUrl != null) {
                        jLabelzar2.setIcon(new ImageIcon(imageUrl)); // rakip zar (tekli)
                    }
                } catch (NumberFormatException e) {
                    System.err.println("ZAR:x hatalı: " + veri);
                }
            }

        } else if (mesaj.startsWith("SIRA:")) {
            try {
                int gelenSira = Integer.parseInt(mesaj.substring(5).trim());

                if (gelenSira == oyuncuSirasi) {
                    zarAtButton.setEnabled(true);
                    if (ilkTur && !oyunBasladi) {
                        JOptionPane.showMessageDialog(this, "🎯 Oyuna sen başlıyorsun!");
                    }
                } else {
                    zarAtButton.setEnabled(false);
                }

            } catch (NumberFormatException e) {
                System.err.println("SIRA mesajı hatalı: " + mesaj);
            }

        } else if (mesaj.equals("ILKTURBITTI")) {
            ilkTur = false;
            oyunBasladi = true;
            JOptionPane.showMessageDialog(this, "🎲 İlk tur bitti. Şimdi çift zar atılacak.");

        } else if (mesaj.equals("BASLAT")) {
            JOptionPane.showMessageDialog(this, "🎮 Rakip bulundu, oyuna başlayabilirsin!");
            // Not: zarAtButton, SIRA: mesajına göre aktif edilir

        } else if (mesaj.startsWith("OYUNCU:")) {
            try {
                int num = Integer.parseInt(mesaj.substring(7).trim());
                oyuncuSirasi = num;
                client.setOyuncuNumarasi(num); // Oyuncu numarası Client içine setleniyor

                JOptionPane.showMessageDialog(this, "🎮 Sen Oyuncu " + num + "'sin.");
            } catch (NumberFormatException e) {
                System.err.println("OYUNCU mesajı hatalı: " + mesaj);
            }

        } else if (mesaj.equals("ZARLAR_EŞİT")) {
            JOptionPane.showMessageDialog(this, "🎲 Zarlar eşit! Tekrar atılıyor.");
            ilkTur = true;
            oyunBasladi = false;
            zarAtButton.setEnabled(oyuncuSirasi == 1); // sadece oyuncu 1 başlasın

        } else {
            JOptionPane.showMessageDialog(this, mesaj);
        }
    }

    public void hataGoster(String mesaj) {
        JOptionPane.showMessageDialog(this, mesaj, "Bağlantı Hatası", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        jLabel1 = new javax.swing.JLabel();
        button_0_4 = new java.awt.Button();
        button_0_3 = new java.awt.Button();
        button_5_4 = new java.awt.Button();
        button_5_3 = new java.awt.Button();
        button_5_2 = new java.awt.Button();
        button_5_1 = new java.awt.Button();
        button_5_0 = new java.awt.Button();
        button_7_4 = new java.awt.Button();
        button_7_3 = new java.awt.Button();
        button_7_2 = new java.awt.Button();
        button_11_4 = new java.awt.Button();
        button_11_3 = new java.awt.Button();
        button_11_2 = new java.awt.Button();
        button_11_1 = new java.awt.Button();
        button_11_0 = new java.awt.Button();
        button_12_0 = new java.awt.Button();
        button_12_1 = new java.awt.Button();
        button_12_2 = new java.awt.Button();
        button_12_3 = new java.awt.Button();
        button_12_4 = new java.awt.Button();
        button_16_0 = new java.awt.Button();
        button_16_1 = new java.awt.Button();
        button_16_2 = new java.awt.Button();
        button_18_0 = new java.awt.Button();
        button_18_1 = new java.awt.Button();
        button_18_2 = new java.awt.Button();
        button_18_3 = new java.awt.Button();
        button_18_4 = new java.awt.Button();
        button_23_0 = new java.awt.Button();
        button_23_1 = new java.awt.Button();
        zarAtButton = new java.awt.Button();
        jLabelZar1 = new javax.swing.JLabel();
        jLabelzar2 = new javax.swing.JLabel();
        button_1_4 = new java.awt.Button();
        button_1_3 = new java.awt.Button();
        button_1_2 = new java.awt.Button();
        button_1_1 = new java.awt.Button();
        button_1_0 = new java.awt.Button();
        button_0_2 = new java.awt.Button();
        button_0_1 = new java.awt.Button();
        button_0_0 = new java.awt.Button();
        button_2_4 = new java.awt.Button();
        button_2_3 = new java.awt.Button();
        button_2_2 = new java.awt.Button();
        button_2_1 = new java.awt.Button();
        button_2_0 = new java.awt.Button();
        button_3_4 = new java.awt.Button();
        button_3_3 = new java.awt.Button();
        button_3_2 = new java.awt.Button();
        button_3_1 = new java.awt.Button();
        button_3_0 = new java.awt.Button();
        button_4_4 = new java.awt.Button();
        button_4_3 = new java.awt.Button();
        button_4_2 = new java.awt.Button();
        button_4_1 = new java.awt.Button();
        button_4_0 = new java.awt.Button();
        button_6_4 = new java.awt.Button();
        button_6_3 = new java.awt.Button();
        button_6_2 = new java.awt.Button();
        button_6_1 = new java.awt.Button();
        button_6_0 = new java.awt.Button();
        button_7_1 = new java.awt.Button();
        button_7_0 = new java.awt.Button();
        button_8_4 = new java.awt.Button();
        button_8_3 = new java.awt.Button();
        button_8_2 = new java.awt.Button();
        button_8_1 = new java.awt.Button();
        button_8_0 = new java.awt.Button();
        button_9_4 = new java.awt.Button();
        button_9_3 = new java.awt.Button();
        button_9_2 = new java.awt.Button();
        button_9_1 = new java.awt.Button();
        button_9_0 = new java.awt.Button();
        button_10_4 = new java.awt.Button();
        button_10_3 = new java.awt.Button();
        button_10_2 = new java.awt.Button();
        button_10_1 = new java.awt.Button();
        button_10_0 = new java.awt.Button();
        button_23_2 = new java.awt.Button();
        button_23_3 = new java.awt.Button();
        button_22_0 = new java.awt.Button();
        button_22_1 = new java.awt.Button();
        button_22_2 = new java.awt.Button();
        button_22_3 = new java.awt.Button();
        button_22_4 = new java.awt.Button();
        button_21_0 = new java.awt.Button();
        button_21_1 = new java.awt.Button();
        button_21_2 = new java.awt.Button();
        button_21_3 = new java.awt.Button();
        button_21_4 = new java.awt.Button();
        button_20_0 = new java.awt.Button();
        button_20_1 = new java.awt.Button();
        button_20_2 = new java.awt.Button();
        button_20_3 = new java.awt.Button();
        button_20_4 = new java.awt.Button();
        button_19_0 = new java.awt.Button();
        button_19_1 = new java.awt.Button();
        button_19_2 = new java.awt.Button();
        button_19_3 = new java.awt.Button();
        button_19_4 = new java.awt.Button();
        button_17_0 = new java.awt.Button();
        button_17_1 = new java.awt.Button();
        button_17_2 = new java.awt.Button();
        button_17_3 = new java.awt.Button();
        button_17_4 = new java.awt.Button();
        button_16_3 = new java.awt.Button();
        button_16_4 = new java.awt.Button();
        button_15_0 = new java.awt.Button();
        button_15_1 = new java.awt.Button();
        button_15_2 = new java.awt.Button();
        button_15_3 = new java.awt.Button();
        button_15_4 = new java.awt.Button();
        button_14_0 = new java.awt.Button();
        button_14_1 = new java.awt.Button();
        button_14_2 = new java.awt.Button();
        button_14_3 = new java.awt.Button();
        button_14_4 = new java.awt.Button();
        button_13_0 = new java.awt.Button();
        button_13_1 = new java.awt.Button();
        button_13_2 = new java.awt.Button();
        button_13_3 = new java.awt.Button();
        button_13_4 = new java.awt.Button();
        button_23_4 = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(600, 500));
        setMinimumSize(new java.awt.Dimension(612, 545));
        setPreferredSize(new java.awt.Dimension(613, 545));
        getContentPane().setLayout(null);

        jLayeredPane1.setFocusable(false);
        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/mycompany/networkproject/images/backgomman.jpg"))); // NOI18N
        jLabel1.setText("jLabel1");
        jLabel1.setFocusable(false);
        jLayeredPane1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 610, 500));

        button_0_4.setBackground(new java.awt.Color(255, 255, 255));
        button_0_4.setForeground(new java.awt.Color(255, 255, 255));
        button_0_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_0_4ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(button_0_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 31, 30, 30));

        button_0_3.setForeground(new java.awt.Color(255, 255, 255));
        button_0_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_0_3ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(button_0_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 62, 30, 30));

        button_5_4.setBackground(new java.awt.Color(0, 0, 0));
        button_5_4.setForeground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(button_5_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 30, 30));

        button_5_3.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_5_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 30, 30));

        button_5_2.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_5_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, 30, 30));

        button_5_1.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_5_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 30, 30));

        button_5_0.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_5_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, 30, 30));

        button_7_4.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_7_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 30, 30, 30));

        button_7_3.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_7_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 60, 30, 30));

        button_7_2.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_7_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 90, 30, 30));

        button_11_4.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(button_11_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 30, 30, 30));

        button_11_3.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(button_11_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 60, 30, 30));

        button_11_2.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(button_11_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 90, 30, 30));

        button_11_1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(button_11_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 120, 30, 30));

        button_11_0.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(button_11_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 150, 30, 30));

        button_12_0.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_12_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 440, 30, 30));

        button_12_1.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_12_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 410, 30, 30));

        button_12_2.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_12_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 379, 30, 30));

        button_12_3.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_12_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 350, 30, 30));

        button_12_4.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_12_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 320, 30, 30));

        button_16_0.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(button_16_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 440, 30, 30));

        button_16_1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(button_16_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 409, 30, 30));

        button_16_2.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(button_16_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 379, 30, 30));

        button_18_0.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(button_18_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 440, 30, 30));

        button_18_1.setBackground(new java.awt.Color(255, 255, 255));
        button_18_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_18_1ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(button_18_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 410, 30, 30));

        button_18_2.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(button_18_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 380, 30, 30));

        button_18_3.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(button_18_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 350, 30, 30));

        button_18_4.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(button_18_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 320, 30, 30));

        button_23_0.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_23_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 440, 30, 30));

        button_23_1.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_23_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 410, 30, 30));

        zarAtButton.setBackground(new java.awt.Color(255, 255, 255));
        zarAtButton.setLabel("Zar at");
        zarAtButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zarAtButtonActionPerformed(evt);
            }
        });
        jLayeredPane1.add(zarAtButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 50, 40));
        jLayeredPane1.add(jLabelZar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 230, 30, 30));
        jLayeredPane1.add(jLabelzar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 290, 30, 30));

        button_1_4.setVisible(false);
        jLayeredPane1.add(button_1_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 30, 30));

        button_1_3.setVisible(false);
        jLayeredPane1.add(button_1_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 30, 30));

        button_1_2.setVisible(false);
        jLayeredPane1.add(button_1_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 90, 30, 30));

        button_1_1.setVisible(false);
        jLayeredPane1.add(button_1_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 120, 30, 30));

        button_1_0.setVisible(false);
        jLayeredPane1.add(button_1_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 150, 30, 30));

        button_0_2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        button_0_2.setVisible(false);
        jLayeredPane1.add(button_0_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 92, 30, 30));

        button_0_1.setVisible(false);
        jLayeredPane1.add(button_0_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 120, 30, 30));

        button_0_0.setVisible(false);
        jLayeredPane1.add(button_0_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, 30, 30));

        button_2_4.setVisible(false);
        jLayeredPane1.add(button_2_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 30, 30, 30));

        button_2_3.setVisible(false);
        jLayeredPane1.add(button_2_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 60, 30, 30));

        button_2_2.setVisible(false);
        jLayeredPane1.add(button_2_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 90, 30, 30));

        button_2_1.setVisible(false);
        jLayeredPane1.add(button_2_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 120, 30, 30));

        button_2_0.setVisible(false);
        jLayeredPane1.add(button_2_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 150, 30, 30));

        button_3_4.setVisible(false);
        jLayeredPane1.add(button_3_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 30, 30, 30));

        button_3_3.setVisible(false);
        jLayeredPane1.add(button_3_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 60, 30, 30));

        button_3_2.setVisible(false);
        jLayeredPane1.add(button_3_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 90, 30, 30));

        button_3_1.setVisible(false);
        jLayeredPane1.add(button_3_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 120, 30, 30));

        button_3_0.setVisible(false);
        jLayeredPane1.add(button_3_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 150, 30, 30));

        button_4_4.setVisible(false);
        jLayeredPane1.add(button_4_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 30, 30, 30));

        button_4_3.setVisible(false);
        jLayeredPane1.add(button_4_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 60, 30, 30));

        button_4_2.setVisible(false);
        jLayeredPane1.add(button_4_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 90, 30, 30));

        button_4_1.setVisible(false);
        jLayeredPane1.add(button_4_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 120, 30, 30));

        button_4_0.setVisible(false);
        jLayeredPane1.add(button_4_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 150, 30, 30));

        button_6_4.setVisible(false);
        jLayeredPane1.add(button_6_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 30, 30, 30));

        button_6_3.setVisible(false);
        button_6_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_6_3ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(button_6_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 60, 30, 30));

        button_6_2.setVisible(false);
        jLayeredPane1.add(button_6_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 90, 30, 30));

        button_6_1.setVisible(false);
        jLayeredPane1.add(button_6_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 120, 30, 30));

        button_6_0.setVisible(false);
        jLayeredPane1.add(button_6_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 150, 30, 30));

        button_7_1.setVisible(false);
        jLayeredPane1.add(button_7_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 120, 30, 30));

        button_7_0.setVisible(false);
        jLayeredPane1.add(button_7_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 150, 30, 30));

        button_8_4.setVisible(false);
        jLayeredPane1.add(button_8_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 30, 30));

        button_8_3.setVisible(false);
        jLayeredPane1.add(button_8_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 30, 30));

        button_8_2.setVisible(false);
        jLayeredPane1.add(button_8_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 30, 30));

        button_8_1.setVisible(false);
        jLayeredPane1.add(button_8_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 30, 30));

        button_8_0.setVisible(false);
        jLayeredPane1.add(button_8_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 30, 30));

        button_9_4.setVisible(false);
        jLayeredPane1.add(button_9_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 30, 30, 30));

        button_9_3.setVisible(false);
        jLayeredPane1.add(button_9_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 60, 30, 30));

        button_9_2.setVisible(false);
        jLayeredPane1.add(button_9_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 90, 30, 30));

        button_9_1.setVisible(false);
        jLayeredPane1.add(button_9_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 120, 30, 30));

        button_9_0.setVisible(false);
        jLayeredPane1.add(button_9_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 150, 30, 30));

        button_10_4.setVisible(false);
        jLayeredPane1.add(button_10_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 30, 30, 30));

        button_10_3.setVisible(false);
        jLayeredPane1.add(button_10_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 60, 30, 30));

        button_10_2.setVisible(false);
        jLayeredPane1.add(button_10_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 90, 30, 30));

        button_10_1.setVisible(false);
        jLayeredPane1.add(button_10_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 120, 30, 30));

        button_10_0.setVisible(false);
        jLayeredPane1.add(button_10_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 150, 30, 30));

        button_23_2.setVisible(false);
        jLayeredPane1.add(button_23_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 380, 30, 30));

        button_23_3.setVisible(false);
        jLayeredPane1.add(button_23_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 350, 30, 30));

        button_22_0.setVisible(false);
        jLayeredPane1.add(button_22_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 439, 30, 30));

        button_22_1.setVisible(false);
        jLayeredPane1.add(button_22_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 408, 30, 30));

        button_22_2.setVisible(false);
        jLayeredPane1.add(button_22_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 378, 30, 30));

        button_22_3.setVisible(false);
        jLayeredPane1.add(button_22_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 348, 30, 30));

        button_22_4.setVisible(false);
        jLayeredPane1.add(button_22_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(508, 318, 30, 30));

        button_21_0.setVisible(false);
        jLayeredPane1.add(button_21_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 440, 30, 30));

        button_21_1.setVisible(false);
        jLayeredPane1.add(button_21_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 410, 30, 30));

        button_21_2.setVisible(false);
        jLayeredPane1.add(button_21_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 380, 30, 30));

        button_21_3.setVisible(false);
        jLayeredPane1.add(button_21_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 350, 30, 30));

        button_21_4.setVisible(false);
        button_21_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_21_4ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(button_21_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 320, 30, 30));

        button_20_0.setVisible(false);
        jLayeredPane1.add(button_20_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 440, 30, 30));

        button_20_1.setVisible(false);
        button_20_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_20_1ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(button_20_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 410, 30, 30));

        button_20_2.setVisible(false);
        jLayeredPane1.add(button_20_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(422, 380, 30, 30));

        button_20_3.setVisible(false);
        button_20_3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_20_3ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(button_20_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(422, 350, 30, 30));

        button_20_4.setVisible(false);
        jLayeredPane1.add(button_20_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(422, 320, 30, 30));

        button_19_0.setVisible(false);
        jLayeredPane1.add(button_19_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 440, 30, 30));

        button_19_1.setVisible(false);
        jLayeredPane1.add(button_19_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 410, 30, 30));

        button_19_2.setVisible(false);
        jLayeredPane1.add(button_19_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 380, 30, 30));

        button_19_3.setVisible(false);
        jLayeredPane1.add(button_19_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 350, 30, 30));

        button_19_4.setVisible(false);
        jLayeredPane1.add(button_19_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 320, 30, 30));

        button_17_0.setVisible(false);
        button_17_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_17_0ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(button_17_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 440, 30, 30));

        button_17_1.setVisible(false);
        button_17_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_17_1ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(button_17_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 410, 30, 30));

        button_17_2.setVisible(false);
        jLayeredPane1.add(button_17_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 380, 30, 30));

        button_17_3.setVisible(false);
        jLayeredPane1.add(button_17_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 350, 30, 30));

        button_17_4.setVisible(false);
        jLayeredPane1.add(button_17_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 320, 30, 30));

        button_16_3.setVisible(false);
        jLayeredPane1.add(button_16_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 348, 30, 30));

        button_16_4.setVisible(false);
        jLayeredPane1.add(button_16_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 318, 30, 30));

        button_15_0.setVisible(false);
        jLayeredPane1.add(button_15_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 440, 30, 30));

        button_15_1.setVisible(false);
        jLayeredPane1.add(button_15_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 410, 30, 30));

        button_15_2.setVisible(false);
        jLayeredPane1.add(button_15_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 30, 30));

        button_15_3.setVisible(false);
        jLayeredPane1.add(button_15_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 350, 30, 30));

        button_15_4.setVisible(false);
        jLayeredPane1.add(button_15_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 320, 30, 30));

        button_14_0.setVisible(false);
        jLayeredPane1.add(button_14_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 440, 30, 30));

        button_14_1.setVisible(false);
        jLayeredPane1.add(button_14_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 410, 30, 30));

        button_14_2.setVisible(false);
        button_14_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_14_2ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(button_14_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 380, 30, 30));

        button_14_3.setVisible(false);
        jLayeredPane1.add(button_14_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 350, 30, 30));

        button_14_4.setVisible(false);
        jLayeredPane1.add(button_14_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 320, 30, 30));

        button_13_0.setVisible(false);
        jLayeredPane1.add(button_13_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 440, 30, 30));

        button_13_1.setVisible(false);
        jLayeredPane1.add(button_13_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 410, 30, 30));

        button_13_2.setVisible(false);
        jLayeredPane1.add(button_13_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 380, 30, 30));

        button_13_3.setVisible(false);
        jLayeredPane1.add(button_13_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 350, 30, 30));

        button_13_4.setVisible(false);
        jLayeredPane1.add(button_13_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 320, 30, 30));

        button_23_4.setVisible(false);
        button_23_4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_23_4ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(button_23_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 320, 30, 30));

        getContentPane().add(jLayeredPane1);
        jLayeredPane1.setBounds(0, 0, 640, 540);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_0_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_0_4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_0_4ActionPerformed

    private void button_0_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_0_3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_0_3ActionPerformed

    private void zarAtButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zarAtButtonActionPerformed
        // TODO add your handling code here:
//        
//        zarAtButton.addActionListener(new ActionListener() {
//    public void actionPerformed(ActionEvent evt) {
//        int zar = random.nextInt(6) + 1;
//String yol = "com/mycompany/networkproject/images/" + zar + ".png";
//
//URL imageUrl = getClass().getClassLoader().getResource(yol);
//if (imageUrl != null) {
//    jLabelZar1.setIcon(new ImageIcon(imageUrl));
//} else {
//    System.out.println("GÖRSEL BULUNAMADI: " + yol);
//}
//
//    }
//});
//      if (zarAtButton.isEnabled()) {
//        int zar = random.nextInt(6) + 1;
//
//        String yol = "com/mycompany/networkproject/images/" + zar + ".png";
//        URL imageUrl = getClass().getClassLoader().getResource(yol);
//
//        if (imageUrl != null) {
//            jLabelZar1.setIcon(new ImageIcon(imageUrl));
//        } else {
//            System.out.println("GÖRSEL BULUNAMADI: " + yol);
//        }
//
//        client.mesajGonder("ZAR:" + zar);
//        zarAtButton.setEnabled(false);
//    }

        if (!zarAtButton.isEnabled()) {
            return;
        }

        if (ilkTur && !oyunBasladi) {
            // İlk turda sadece 1 zar atılır
            int zar = random.nextInt(6) + 1;

            // 🎯 Zarları diziye ata (BUNU EKLE)
            String yol = "com/mycompany/networkproject/images/" + zar + ".png";
            URL imageUrl = getClass().getClassLoader().getResource(yol);
            if (imageUrl != null) {
                jLabelZar1.setIcon(new ImageIcon(imageUrl)); // kendi zarı üstte
            }

            client.mesajGonder("ILKZAR:" + zar);
            zarAtButton.setEnabled(false);

        } else if (!ilkTur && oyunBasladi) {
            // Normal oyun: 2 zar atılır
            int zar1 = random.nextInt(6) + 1;
            int zar2 = random.nextInt(6) + 1;
            zarlar[0] = zar1;
            zarlar[1] = zar2;

            System.out.println("🎲 Zarlar atıldı: " + zar1 + " ve " + zar2);

            String yol1 = "com/mycompany/networkproject/images/" + zar1 + ".png";
            String yol2 = "com/mycompany/networkproject/images/" + zar2 + ".png";

            URL image1 = getClass().getClassLoader().getResource(yol1);
            URL image2 = getClass().getClassLoader().getResource(yol2);

            if (image1 != null) {
                jLabelZar1.setIcon(new ImageIcon(image1));
            }
            if (image2 != null) {
                jLabelzar2.setIcon(new ImageIcon(image2));
            }

            client.mesajGonder("ZAR:" + zar1 + "," + zar2);
            zarAtButton.setEnabled(false);
        }
    }//GEN-LAST:event_zarAtButtonActionPerformed

    private void button_6_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_6_3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_6_3ActionPerformed

    private void button_14_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_14_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_14_2ActionPerformed

    private void button_23_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_23_4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_23_4ActionPerformed

    private void button_20_3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_20_3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_20_3ActionPerformed

    private void button_17_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_17_0ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_button_17_0ActionPerformed

    private void button_18_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_18_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_18_1ActionPerformed

    private void button_17_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_17_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_17_1ActionPerformed

    private void button_20_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_20_1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_20_1ActionPerformed

    private void button_21_4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_21_4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_21_4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button_0_0;
    private java.awt.Button button_0_1;
    private java.awt.Button button_0_2;
    private java.awt.Button button_0_3;
    private java.awt.Button button_0_4;
    private java.awt.Button button_10_0;
    private java.awt.Button button_10_1;
    private java.awt.Button button_10_2;
    private java.awt.Button button_10_3;
    private java.awt.Button button_10_4;
    private java.awt.Button button_11_0;
    private java.awt.Button button_11_1;
    private java.awt.Button button_11_2;
    private java.awt.Button button_11_3;
    private java.awt.Button button_11_4;
    private java.awt.Button button_12_0;
    private java.awt.Button button_12_1;
    private java.awt.Button button_12_2;
    private java.awt.Button button_12_3;
    private java.awt.Button button_12_4;
    private java.awt.Button button_13_0;
    private java.awt.Button button_13_1;
    private java.awt.Button button_13_2;
    private java.awt.Button button_13_3;
    private java.awt.Button button_13_4;
    private java.awt.Button button_14_0;
    private java.awt.Button button_14_1;
    private java.awt.Button button_14_2;
    private java.awt.Button button_14_3;
    private java.awt.Button button_14_4;
    private java.awt.Button button_15_0;
    private java.awt.Button button_15_1;
    private java.awt.Button button_15_2;
    private java.awt.Button button_15_3;
    private java.awt.Button button_15_4;
    private java.awt.Button button_16_0;
    private java.awt.Button button_16_1;
    private java.awt.Button button_16_2;
    private java.awt.Button button_16_3;
    private java.awt.Button button_16_4;
    private java.awt.Button button_17_0;
    private java.awt.Button button_17_1;
    private java.awt.Button button_17_2;
    private java.awt.Button button_17_3;
    private java.awt.Button button_17_4;
    private java.awt.Button button_18_0;
    private java.awt.Button button_18_1;
    private java.awt.Button button_18_2;
    private java.awt.Button button_18_3;
    private java.awt.Button button_18_4;
    private java.awt.Button button_19_0;
    private java.awt.Button button_19_1;
    private java.awt.Button button_19_2;
    private java.awt.Button button_19_3;
    private java.awt.Button button_19_4;
    private java.awt.Button button_1_0;
    private java.awt.Button button_1_1;
    private java.awt.Button button_1_2;
    private java.awt.Button button_1_3;
    private java.awt.Button button_1_4;
    private java.awt.Button button_20_0;
    private java.awt.Button button_20_1;
    private java.awt.Button button_20_2;
    private java.awt.Button button_20_3;
    private java.awt.Button button_20_4;
    private java.awt.Button button_21_0;
    private java.awt.Button button_21_1;
    private java.awt.Button button_21_2;
    private java.awt.Button button_21_3;
    private java.awt.Button button_21_4;
    private java.awt.Button button_22_0;
    private java.awt.Button button_22_1;
    private java.awt.Button button_22_2;
    private java.awt.Button button_22_3;
    private java.awt.Button button_22_4;
    private java.awt.Button button_23_0;
    private java.awt.Button button_23_1;
    private java.awt.Button button_23_2;
    private java.awt.Button button_23_3;
    private java.awt.Button button_23_4;
    private java.awt.Button button_2_0;
    private java.awt.Button button_2_1;
    private java.awt.Button button_2_2;
    private java.awt.Button button_2_3;
    private java.awt.Button button_2_4;
    private java.awt.Button button_3_0;
    private java.awt.Button button_3_1;
    private java.awt.Button button_3_2;
    private java.awt.Button button_3_3;
    private java.awt.Button button_3_4;
    private java.awt.Button button_4_0;
    private java.awt.Button button_4_1;
    private java.awt.Button button_4_2;
    private java.awt.Button button_4_3;
    private java.awt.Button button_4_4;
    private java.awt.Button button_5_0;
    private java.awt.Button button_5_1;
    private java.awt.Button button_5_2;
    private java.awt.Button button_5_3;
    private java.awt.Button button_5_4;
    private java.awt.Button button_6_0;
    private java.awt.Button button_6_1;
    private java.awt.Button button_6_2;
    private java.awt.Button button_6_3;
    private java.awt.Button button_6_4;
    private java.awt.Button button_7_0;
    private java.awt.Button button_7_1;
    private java.awt.Button button_7_2;
    private java.awt.Button button_7_3;
    private java.awt.Button button_7_4;
    private java.awt.Button button_8_0;
    private java.awt.Button button_8_1;
    private java.awt.Button button_8_2;
    private java.awt.Button button_8_3;
    private java.awt.Button button_8_4;
    private java.awt.Button button_9_0;
    private java.awt.Button button_9_1;
    private java.awt.Button button_9_2;
    private java.awt.Button button_9_3;
    private java.awt.Button button_9_4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelZar1;
    private javax.swing.JLabel jLabelzar2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private java.awt.Button zarAtButton;
    // End of variables declaration//GEN-END:variables
}
