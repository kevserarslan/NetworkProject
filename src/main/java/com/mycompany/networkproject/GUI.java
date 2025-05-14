/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.networkproject;

import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
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
    private int oyuncuNumarasi = 0;  // 0 = değil, 1 veya 2 ise sırası
    private int siradakiOyuncu = 0;

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
    private javax.swing.JLabel[] ucgenler;
    private java.awt.Button secilenButonMouse = null;  // fare ile taşımada kullanılacak

    public GUI() {
        initComponents();

        // Oyuncu bağlanmadan zar atılamaz
        zarAtButton.setEnabled(false);

        // Arka plan katmanı en arkada, butonlar önde
        jLayeredPane1.setLayer(jLabel1, 0);
        for (int i = 0; i < jLayeredPane1.getComponentCount(); i++) {
            Component c = jLayeredPane1.getComponent(i);
            if (c instanceof Button) {
                jLayeredPane1.setLayer(c, 1);
            }
        }

        // 24 üçgeni dizisine yerleştir
        ucgenler = new JLabel[]{
            jLabelucgen_0, jLabelucgen_1, jLabelucgen_2, jLabelucgen_3, jLabelucgen_4,
            jLabelucgen_5, jLabelucgen_6, jLabelucgen_7, jLabelucgen_8, jLabelucgen_9,
            jLabelucgen_10, jLabelucgen_11, jLabelucgen_12, jLabelucgen_13, jLabelucgen_14,
            jLabelucgen_15, jLabelucgen_16, jLabelucgen_17, jLabelucgen_18, jLabelucgen_19,
            jLabelucgen_20, jLabelucgen_21, jLabelucgen_22, jLabelucgen_23
        };

        // Tüm butonlar null olarak başlatılıyor
        for (int i = 0; i < 24; i++) {
            for (int j = 0; j < 5; j++) {
                butonlar[i][j] = null;
            }
        }

        // Taşları oluştur ve dizilere ekle
        Tas[] tasDizisi = new Tas[]{
            // Siyah taşlar (oyuncu 1)
            new Tas(23, 0, 1, button_23_0), new Tas(23, 1, 1, button_23_1),
            new Tas(5, 0, 1, button_5_0), new Tas(5, 1, 1, button_5_1),
            new Tas(5, 2, 1, button_5_2), new Tas(5, 3, 1, button_5_3), new Tas(5, 4, 1, button_5_4),
            new Tas(7, 0, 1, button_7_0), new Tas(7, 1, 1, button_7_1), new Tas(7, 2, 1, button_7_2),
            new Tas(12, 0, 1, button_12_0), new Tas(12, 1, 1, button_12_1), new Tas(12, 2, 1, button_12_2),
            new Tas(12, 3, 1, button_12_3), new Tas(12, 4, 1, button_12_4),
            // Beyaz taşlar (oyuncu 2)
            new Tas(11, 0, 2, button_11_0), new Tas(11, 1, 2, button_11_1), new Tas(11, 2, 2, button_11_2),
            new Tas(11, 3, 2, button_11_3), new Tas(11, 4, 2, button_11_4),
            new Tas(0, 0, 2, button_0_0), new Tas(0, 1, 2, button_0_1),
            new Tas(16, 0, 2, button_16_0), new Tas(16, 1, 2, button_16_1), new Tas(16, 2, 2, button_16_2),
            new Tas(18, 0, 2, button_18_0), new Tas(18, 1, 2, button_18_1), new Tas(18, 2, 2, button_18_2),
            new Tas(18, 3, 2, button_18_3), new Tas(18, 4, 2, button_18_4)
        };

        for (Tas tas : tasDizisi) {
            int ucgenIndex = tas.getUcgenNo();
            JLabel ucgen = ucgenler[ucgenIndex];
            Button buton = tas.getButon();

            ucgen.setLayout(null);
            tas.tasGorunurYap();

            // Şu anda görünür olan kaç buton var → kaçıncı sırada yerleştirilecek?
            int mevcut = 0;
            for (Component comp : ucgen.getComponents()) {
                if (comp instanceof Button && comp.isVisible()) {
                    mevcut++;
                }
            }

            buton.setLocation(0, ucgenIndex <= 11 ? mevcut * 30 : ucgen.getHeight() - 30 - mevcut * 30);
            buton.setName("button_" + ucgenIndex + "_" + mevcut);
            System.out.println("🧱 Başlangıçta eklenen buton: " + buton.getName());

            buton.setVisible(true);
            ucgen.add(buton);
            butonlar[ucgenIndex][mevcut] = buton;

            // ActionListener ekle
            buton.addActionListener(evt -> ortakButonTiklama(evt));

            // Dizilere ekle
            if (tas.getOyuncuNo() == 1) {
                siyahTaslar.add(tas);
            } else {
                beyazTaslar.add(tas);
            }
        }

        // Taş seçme ve üçgene taşıma listener'ları
        tasSecmeVeTasimaAyarlariniYap();

        // Client başlat
        client = new Client(this);
        client.connect("localhost", 12345);
    }

    private void tasSecmeVeTasimaAyarlariniYap() {
        // 🎯 1. Butonlara taş seçme action'ı ekle
        for (int ucgen = 0; ucgen < 24; ucgen++) {
            for (int poz = 0; poz < 5; poz++) {
                Button btn = butonlar[ucgen][poz];
                if (btn != null) {
                    btn.addActionListener(e -> {
                        if (btn.isVisible()) {
                            ArrayList<Tas> aktifTasListesi = (oyuncuNumarasi == 1) ? siyahTaslar : beyazTaslar;
                            for (Tas tas : aktifTasListesi) {
                                if (tas.getButon() == btn) {
                                    secilenButonMouse = btn;
                                    System.out.println("✅ Oyuncu " + oyuncuNumarasi + " taş seçti: " + btn.getName());
                                    return;
                                }
                            }
                            JOptionPane.showMessageDialog(null, "Bu taş size ait değil!");
                        }
                    });
                }
            }
        }

        // 🎯 2. Üçgenlere (JLabel) mouse listener ekle → taş taşıma
        for (int hedefUcgen = 0; hedefUcgen < ucgenler.length; hedefUcgen++) {
            JLabel ucgen = ucgenler[hedefUcgen];
            ucgen.setLayout(null);

            final int finalHedefUcgen = hedefUcgen;

            ucgen.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    if (secilenButonMouse != null) {
                        System.out.println("⬇ Üçgene tıklandı: " + finalHedefUcgen);

                        String name = secilenButonMouse.getName(); // örn: button_12_3
                        String[] parts = name.substring(7).split("_");
                        int eskiUcgen = Integer.parseInt(parts[0]);

                        System.out.println("📍 Eski üçgen: " + eskiUcgen);
                        System.out.println("🎯 Hedef üçgen: " + finalHedefUcgen);

                        if ((oyuncuNumarasi == 1 && finalHedefUcgen >= eskiUcgen)
                                || (oyuncuNumarasi == 2 && finalHedefUcgen <= eskiUcgen)) {
                            System.out.println("❌ Hatalı yön (oyuncu: " + oyuncuNumarasi + ")");
                            JOptionPane.showMessageDialog(null, "Ters yöne hamle yapılamaz!");
                            return;
                        }

                        int mesafe = Math.abs(finalHedefUcgen - eskiUcgen);
                        System.out.println("🎲 Zarlar: " + zarlar[0] + ", " + zarlar[1]);
                        System.out.println("📏 Mesafe: " + mesafe);

                        if (zarlar[0] != mesafe && zarlar[1] != mesafe) {
                            System.out.println("❌ Zarla uyumsuz hamle.");
                            JOptionPane.showMessageDialog(null, "Zar ile uyumlu değil!");
                            return;
                        }

                        java.awt.Container eskiParent = secilenButonMouse.getParent();
                        eskiParent.remove(secilenButonMouse);

                        int mevcutTasSayisi = 0;
                        for (Component comp : ucgen.getComponents()) {
                            if (comp instanceof Button && comp.isVisible()) {
                                mevcutTasSayisi++;
                            }
                        }

                        ucgen.add(secilenButonMouse);
                        ucgen.setComponentZOrder(secilenButonMouse, 0);

                        int ucgenHeight = ucgen.getHeight();
                        int y = (finalHedefUcgen <= 11)
                                ? 0 + mevcutTasSayisi * 30
                                : ucgenHeight - 30 - mevcutTasSayisi * 30;

                        secilenButonMouse.setLocation(0, y);
                        secilenButonMouse.setBackground(oyuncuNumarasi == 1 ? Color.BLACK : Color.WHITE);
                        String yeniIsim = "button_" + finalHedefUcgen + "_" + mevcutTasSayisi;

                        secilenButonMouse.setName(yeniIsim);

                        // 🔥 EKLENMESİ GEREKEN KRİTİK SATIR
                        butonlar[finalHedefUcgen][mevcutTasSayisi] = secilenButonMouse;
                        System.out.println("🔁 setName verildi: " + yeniIsim);

                        ucgen.revalidate();
                        ucgen.repaint();

                        System.out.println("✅ Taş başarıyla taşındı.");

                        // 🆕 Tas nesnesini güncelle
                        ArrayList<Tas> aktifTasListesi = (oyuncuNumarasi == 1) ? siyahTaslar : beyazTaslar;
                        for (Tas tas : aktifTasListesi) {
                            if (tas.getButon() == secilenButonMouse) {
                                tas.setUcgenNo(finalHedefUcgen);
                                break;
                            }
                        }
//                        //  HAMLE mesajı gönder (tüm client'lara yansıtmak için)
//                        String butonName = secilenButonMouse.getName();
//                        client.mesajGonder("HAMLE:" + eskiUcgen + "," + finalHedefUcgen + "," + butonName);
//                        System.out.println("📤 Gönderilen mesaj: HAMLE:" + eskiUcgen + "," + finalHedefUcgen + "," + butonName);
                        // 🔍 buton pozisyonunu bul (0-4)
                        int pozisyon = -1;
                        for (int i = 0; i < 5; i++) {
                            if (butonlar[eskiUcgen][i] == secilenButonMouse) {
                                pozisyon = i;
                                break;
                            }
                        }

                        if (pozisyon == -1) {
                            System.err.println("❌ Pozisyon bulunamadı, mesaj gönderilmedi.");
                        } else {
                            // 📨 Doğru formatta mesaj gönder
                            client.mesajGonder("HAMLE:" + eskiUcgen + "," + finalHedefUcgen + "," + pozisyon);
                            System.out.println("📤 Gönderilen mesaj: HAMLE:" + eskiUcgen + "," + finalHedefUcgen + "," + pozisyon);
                        }

                        // 🎯 Zar tüket
                        if (zarlar[0] == mesafe) {
                            zarlar[0] = -1;
                            System.out.println("🎯 Zar 1 (" + mesafe + ") kullanıldı.");
                        } else if (zarlar[1] == mesafe) {
                            zarlar[1] = -1;
                            System.out.println("🎯 Zar 2 (" + mesafe + ") kullanıldı.");
                        }

                        if (zarlar[0] == -1 && zarlar[1] == -1) {
                            System.out.println("⏭ Hamle bitti, sıra rakibe geçiyor.");
                            client.mesajGonder("HAMLE_BITTI");
                        }

                        secilenButonMouse = null;
                    }
                }
            });
        }
    }

    private void ortakButonTiklama(ActionEvent evt) {
        Button tiklanan = (Button) evt.getSource();

        for (Tas tas : (oyuncuNumarasi == 1 ? siyahTaslar : beyazTaslar)) {
            if (tas.getButon() == tiklanan) {
                int ucgenNo = tas.getUcgenNo();
                JLabel ucgen = ucgenler[ucgenNo];

                // 🔍 GUI'deki en üstteki butonu Y koordinatına göre bul
                Component[] comps = ucgen.getComponents();
                Button enUstButon = null;
                int enUstY = (ucgenNo <= 11) ? -1 : Integer.MAX_VALUE;

                for (Component comp : comps) {
                    if (comp instanceof Button && comp.isVisible()) {
                        int y = comp.getY();
                        if (ucgenNo <= 11) {
                            if (y > enUstY) {
                                enUstY = y;
                                enUstButon = (Button) comp;
                            }
                        } else {
                            if (y < enUstY) {
                                enUstY = y;
                                enUstButon = (Button) comp;
                            }
                        }
                    }
                }

                // ❌ En üstteki taş değilse reddet
                if (enUstButon != tiklanan) {
                    JOptionPane.showMessageDialog(null, "Sadece en üstteki taşı oynayabilirsin!");
                    return;
                }

                // 🔁 Öncekini sıfırla
                if (secilenTas != null && secilenTas.getButon() != null) {
                    secilenTas.getButon().setBackground(oyuncuNumarasi == 1 ? Color.BLACK : Color.WHITE);
                }

                secilenTas = tas;
                secilenButonMouse = tiklanan;
                tiklanan.setBackground(Color.RED);
                System.out.println("✅ Oyuncu " + oyuncuNumarasi + " taş seçti: " + tiklanan.getName());
                break;
            }
        }
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
        System.out.println("📩 gelenMesajGoster çağrıldı: " + mesaj);

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
                siradakiOyuncu = gelenSira;  // ⬅ sıradaki oyuncuyu güncelle

                if (siradakiOyuncu == oyuncuNumarasi) {
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
                oyuncuNumarasi = num;
                client.setOyuncuNumarasi(num); // Oyuncu numarası Client içine setleniyor

                JOptionPane.showMessageDialog(this, "🎮 Sen Oyuncu " + num + "'sin.");
            } catch (NumberFormatException e) {
                System.err.println("OYUNCU mesajı hatalı: " + mesaj);
            }

        } else if (mesaj.equals("ZARLAR_EŞİT")) {
            JOptionPane.showMessageDialog(this, "🎲 Zarlar eşit! Tekrar atılıyor.");
            ilkTur = true;
            oyunBasladi = false;
            zarAtButton.setEnabled(oyuncuNumarasi == 1); // sadece oyuncu 1 başlasın

        } else if (mesaj.startsWith("HAMLE:")) {
            String[] parts = mesaj.substring(6).split(",", 3);
            if (parts.length == 3) {
                try {
                    int eskiUcgen = Integer.parseInt(parts[0].trim());
                    int yeniUcgen = Integer.parseInt(parts[1].trim());
                    int pozisyon = Integer.parseInt(parts[2].trim());

                    Button tasBtn = butonlar[eskiUcgen][pozisyon];
                    if (tasBtn == null) {
                        System.err.println("❌ Belirtilen pozisyonda taş yok: " + eskiUcgen + ", poz: " + pozisyon);
                        return;
                    }

                    // GUI'den eski konumdan çıkar
                    Container eskiParent = tasBtn.getParent();
                    if (eskiParent != null) {
                        eskiParent.remove(tasBtn);
                    }

                    JLabel hedefUcgen = ucgenler[yeniUcgen];
                    hedefUcgen.setLayout(null);

                    int yeniPoz = 0;
                    for (Component c : hedefUcgen.getComponents()) {
                        if (c instanceof Button && c.isVisible()) {
                            yeniPoz++;
                        }
                    }

                    hedefUcgen.add(tasBtn);
                    hedefUcgen.setComponentZOrder(tasBtn, 0);

                    int y = (yeniUcgen <= 11)
                            ? yeniPoz * 30
                            : hedefUcgen.getHeight() - 30 - yeniPoz * 30;

                    tasBtn.setLocation(0, y);
                    tasBtn.setName("button_" + yeniUcgen + "_" + yeniPoz);
                    tasBtn.setVisible(true);

                    // Taş objesini de güncelle
                    for (Tas t : siyahTaslar) {
                        if (t.getButon() == tasBtn) {
                            t.setUcgenNo(yeniUcgen);
                            break;
                        }
                    }
                    for (Tas t : beyazTaslar) {
                        if (t.getButon() == tasBtn) {
                            t.setUcgenNo(yeniUcgen);
                            break;
                        }
                    }

                    butonlar[eskiUcgen][pozisyon] = null;
                    butonlar[yeniUcgen][yeniPoz] = tasBtn;

                    hedefUcgen.revalidate();
                    hedefUcgen.repaint();

                    System.out.println("✅ Doğru taş senkronize edildi.");

                } catch (Exception e) {
                    System.err.println("HAMLE mesajı hatalı: " + mesaj);
                    e.printStackTrace();
                }
            }

        } else if (mesaj.startsWith("CIKAR:")) {
            String[] parts = mesaj.substring(6).split(",");
            if (parts.length == 2) {
                try {
                    int ucgen = Integer.parseInt(parts[0].trim());
                    int poz = Integer.parseInt(parts[1].trim());
                    Button buton = butonlar[ucgen][poz];
                    if (buton != null) {
                        Container parent = buton.getParent();
                        if (parent != null) {
                            parent.remove(buton);
                        }
                        buton.setVisible(false);
                        butonlar[ucgen][poz] = null;
                        parent.revalidate();
                        parent.repaint();
                        System.out.println("📤 Rakip taş çıkarıldı (senkronize): " + ucgen + "," + poz);
                    }
                } catch (Exception e) {
                    System.err.println("CIKAR mesajı hatalı: " + mesaj);
                    e.printStackTrace();
                }
            }

        } else if (mesaj.startsWith("BITIS:")) {
            int kazanan = Integer.parseInt(mesaj.substring(6).trim());

            String mesajMetni;
            int mesajTipi;

            if (kazanan == oyuncuNumarasi) {
                mesajMetni = "🎉 Oyunu kazandınız!\nYeni bir oyun başlatmak ister misiniz?";
                mesajTipi = JOptionPane.INFORMATION_MESSAGE;
            } else {
                mesajMetni = "😞 Oyunu kaybettiniz.\nYeni bir oyun başlatmak ister misiniz?";
                mesajTipi = JOptionPane.WARNING_MESSAGE;
            }

            int secim = JOptionPane.showConfirmDialog(this,
                    mesajMetni,
                    "Oyun Bitti", JOptionPane.YES_NO_OPTION, mesajTipi);

            if (secim == JOptionPane.YES_OPTION) {
                this.dispose();
                new GUI().setVisible(true);
            } else {
                System.exit(0);
            }
        }

    }

    public void hataGoster(String mesaj) {
        JOptionPane.showMessageDialog(this, mesaj, "Bağlantı Hatası", JOptionPane.ERROR_MESSAGE);
    }
    // Şu anki oyuncunun taş listesi

    private ArrayList<Tas> aktifTaslar() {
        return (oyuncuNumarasi == 1) ? siyahTaslar : beyazTaslar;
    }

    private boolean tumTaslarEvdeMi(ArrayList<Tas> taslar, int oyuncuNo) {
        System.out.println("🔍 Evde mi kontrolü başlatıldı. Oyuncu: " + oyuncuNo);

        for (Tas tas : taslar) {
            int ucgenNo = tas.getUcgenNo();
            System.out.println("📍 Taş bulundu: üçgen " + ucgenNo);

            if (oyuncuNo == 1) {
                if (ucgenNo < 0 || ucgenNo > 5) {
                    System.out.println("❌ Siyah taş evde değil: " + ucgenNo);
                    return false;
                }
            }

            if (oyuncuNo == 2) {
                if (ucgenNo < 18 || ucgenNo > 23) {
                    System.out.println("❌ Beyaz taş evde değil: " + ucgenNo);
                    return false;
                }
            }
        }

        System.out.println("✅ Tüm taşlar evde!");
        return true;
    }

    private boolean tasCikarabilirMi(Tas tas, int zar, int oyuncuNo, ArrayList<Tas> taslar) {
        int ucgenNo = tas.getUcgenNo();
        System.out.println("🧮 taşCikarabilirMi: ucgen=" + ucgenNo + ", zar=" + zar + ", oyuncu=" + oyuncuNo);

        if (oyuncuNo == 1) {
            int hedef = zar - 1;
            System.out.println("🎯 Hedef (siyah): " + hedef);
            if (ucgenNo == hedef) {
                System.out.println("✅ Doğrudan çıkarılabilir");
                return true;
            }
            if (ucgenNo < hedef) {
                for (Tas t : taslar) {
                    if (t.getUcgenNo() > ucgenNo) {
                        System.out.println("❌ Üstte taş var → çıkarılamaz");
                        return false;
                    }
                }
                System.out.println("✅ İzin verildi (siyah iç taş)");
                return true;
            }

        } else if (oyuncuNo == 2) {
            int hedef = 24 - zar;
            System.out.println("🎯 Hedef (beyaz): " + hedef);
            if (ucgenNo == hedef) {
                System.out.println("✅ Doğrudan çıkarılabilir");
                return true;
            }
            if (ucgenNo > hedef) {
                for (Tas t : taslar) {
                    if (t.getUcgenNo() < ucgenNo) {
                        System.out.println("❌ Üstte taş var → çıkarılamaz");
                        return false;
                    }
                }
                System.out.println("✅ İzin verildi (beyaz iç taş)");
                return true;
            }
        }

        System.out.println("❌ Hiçbir koşul sağlanamadı → çıkarılamaz");
        return false;
    }

    private void tasCikar(int ucgenNo, Button[][] butonlar, ArrayList<Tas> tasListesi) {
        for (int i = 4; i >= 0; i--) {
            Button btn = butonlar[ucgenNo][i];
            if (btn != null && btn.isVisible()) {
                Container parent = btn.getParent();
                System.out.println("🚪 Taş çıkarılıyor: " + btn.getName());

                if (parent != null) {
                    parent.remove(btn);
                    parent.revalidate();
                    parent.repaint();
                    System.out.println("✅ GUI'den çıkarıldı.");
                } else {
                    System.out.println("⚠️ Uyarı: Parent null, GUI'den çıkarılamadı.");
                }

                client.mesajGonder("CIKAR:" + ucgenNo + "," + i);
                tasListesi.removeIf(t -> t.getButon() == btn);
                butonlar[ucgenNo][i] = null;
                System.out.println("📤 Taş listeden çıkarıldı.");

                // 🏁 Oyun bitti mi kontrolü
                if (tasListesi.isEmpty()) {
                    System.out.println("🎉 Tüm taşlar çıkarıldı! Oyuncu " + oyuncuNumarasi + " kazandı!");

                    String kazananRenk = (oyuncuNumarasi == 1) ? "Siyah" : "Beyaz";
                    JOptionPane.showMessageDialog(null,
                            "🏆 " + kazananRenk + " oyuncu oyunu kazandı!",
                            "Tebrikler", JOptionPane.INFORMATION_MESSAGE);

                    client.mesajGonder("BITIS:" + oyuncuNumarasi);
                    zarAtButton.setEnabled(false);
                }

                break;
            }
        }
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
        zarAtButton = new java.awt.Button();
        jLabelZar1 = new javax.swing.JLabel();
        jLabelzar2 = new javax.swing.JLabel();
        jLabelucgen_0 = new javax.swing.JLabel();
        button_0_1 = new java.awt.Button();
        button_0_0 = new java.awt.Button();
        jLabelucgen_1 = new javax.swing.JLabel();
        jLabelucgen_2 = new javax.swing.JLabel();
        jLabelucgen_3 = new javax.swing.JLabel();
        jLabelucgen_4 = new javax.swing.JLabel();
        jLabelucgen_5 = new javax.swing.JLabel();
        button_5_4 = new java.awt.Button();
        button_5_3 = new java.awt.Button();
        button_5_2 = new java.awt.Button();
        button_5_1 = new java.awt.Button();
        button_5_0 = new java.awt.Button();
        jLabelucgen_6 = new javax.swing.JLabel();
        jLabelucgen_7 = new javax.swing.JLabel();
        button_7_2 = new java.awt.Button();
        button_7_1 = new java.awt.Button();
        button_7_0 = new java.awt.Button();
        jLabelucgen_8 = new javax.swing.JLabel();
        jLabelucgen_9 = new javax.swing.JLabel();
        jLabelucgen_10 = new javax.swing.JLabel();
        jLabelucgen_11 = new javax.swing.JLabel();
        button_11_4 = new java.awt.Button();
        button_11_3 = new java.awt.Button();
        button_11_2 = new java.awt.Button();
        button_11_1 = new java.awt.Button();
        button_11_0 = new java.awt.Button();
        jLabelucgen_12 = new javax.swing.JLabel();
        button_12_0 = new java.awt.Button();
        button_12_1 = new java.awt.Button();
        button_12_2 = new java.awt.Button();
        button_12_3 = new java.awt.Button();
        button_12_4 = new java.awt.Button();
        jLabelucgen_13 = new javax.swing.JLabel();
        jLabelucgen_14 = new javax.swing.JLabel();
        jLabelucgen_15 = new javax.swing.JLabel();
        jLabelucgen_16 = new javax.swing.JLabel();
        button_16_0 = new java.awt.Button();
        button_16_1 = new java.awt.Button();
        button_16_2 = new java.awt.Button();
        jLabelucgen_17 = new javax.swing.JLabel();
        jLabelucgen_18 = new javax.swing.JLabel();
        button_18_0 = new java.awt.Button();
        button_18_1 = new java.awt.Button();
        button_18_2 = new java.awt.Button();
        button_18_3 = new java.awt.Button();
        button_18_4 = new java.awt.Button();
        jLabelucgen_19 = new javax.swing.JLabel();
        jLabelucgen_20 = new javax.swing.JLabel();
        jLabelucgen_21 = new javax.swing.JLabel();
        jLabelucgen_22 = new javax.swing.JLabel();
        jLabelucgen_23 = new javax.swing.JLabel();
        button_23_0 = new java.awt.Button();
        button_23_1 = new java.awt.Button();
        jLabelbar2 = new javax.swing.JLabel();
        jLabelbar1 = new javax.swing.JLabel();

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
        jLayeredPane1.add(jLabelucgen_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 30, 150));

        button_0_1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(button_0_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 30, 30, 30));

        button_0_0.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(button_0_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 60, 30, 30));
        jLayeredPane1.add(jLabelucgen_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 30, 30, 150));
        jLayeredPane1.add(jLabelucgen_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 30, 30, 150));
        jLayeredPane1.add(jLabelucgen_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(425, 30, 30, 150));
        jLayeredPane1.add(jLabelucgen_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 30, 30, 150));
        jLayeredPane1.add(jLabelucgen_5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 30, 150));

        button_5_4.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_5_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 30, 30));

        button_5_3.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_5_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 30, 30));

        button_5_2.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_5_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 90, 30, 30));

        button_5_1.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_5_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 30, 30));

        button_5_0.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_5_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 150, 30, 30));
        jLayeredPane1.add(jLabelucgen_6, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 30, 30, 150));
        jLayeredPane1.add(jLabelucgen_7, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 30, 150));

        button_7_2.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_7_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 30, 30, 30));

        button_7_1.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_7_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, 30, 30));

        button_7_0.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_7_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 90, 30, 30));
        jLayeredPane1.add(jLabelucgen_8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 30, 150));
        jLayeredPane1.add(jLabelucgen_9, new org.netbeans.lib.awtextra.AbsoluteConstraints(117, 30, 30, 150));
        jLayeredPane1.add(jLabelucgen_10, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 30, 30, 150));
        jLayeredPane1.add(jLabelucgen_11, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 30, 30, 150));

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
        jLayeredPane1.add(jLabelucgen_12, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 322, 30, 150));

        button_12_0.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_12_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 442, 30, 30));

        button_12_1.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_12_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 412, 30, 30));

        button_12_2.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_12_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 382, 30, 30));

        button_12_3.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_12_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 352, 30, 30));

        button_12_4.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_12_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 322, 30, 30));
        jLayeredPane1.add(jLabelucgen_13, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 322, 30, 150));
        jLayeredPane1.add(jLabelucgen_14, new org.netbeans.lib.awtextra.AbsoluteConstraints(118, 322, 30, 150));
        jLayeredPane1.add(jLabelucgen_15, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 322, 30, 150));
        jLayeredPane1.add(jLabelucgen_16, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 322, 30, 150));

        button_16_0.setBackground(new java.awt.Color(255, 255, 255));
        button_16_0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_16_0ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(button_16_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 442, 30, 30));

        button_16_1.setBackground(java.awt.Color.white);
        jLayeredPane1.add(button_16_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 412, 30, 30));

        button_16_2.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(button_16_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(202, 382, 30, 30));
        jLayeredPane1.add(jLabelucgen_17, new org.netbeans.lib.awtextra.AbsoluteConstraints(245, 322, 30, 150));
        jLayeredPane1.add(jLabelucgen_18, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 322, 30, 150));

        button_18_0.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(button_18_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 442, 30, 30));

        button_18_1.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(button_18_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 412, 30, 30));

        button_18_2.setBackground(new java.awt.Color(255, 255, 255));
        button_18_2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_18_2ActionPerformed(evt);
            }
        });
        jLayeredPane1.add(button_18_2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 382, 30, 30));

        button_18_3.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(button_18_3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 352, 30, 30));

        button_18_4.setBackground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(button_18_4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 322, 30, 30));
        jLayeredPane1.add(jLabelucgen_19, new org.netbeans.lib.awtextra.AbsoluteConstraints(382, 322, 30, 150));
        jLayeredPane1.add(jLabelucgen_20, new org.netbeans.lib.awtextra.AbsoluteConstraints(423, 322, 30, 150));
        jLayeredPane1.add(jLabelucgen_21, new org.netbeans.lib.awtextra.AbsoluteConstraints(465, 322, 30, 150));
        jLayeredPane1.add(jLabelucgen_22, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 322, 30, 150));
        jLayeredPane1.add(jLabelucgen_23, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 322, 30, 150));

        button_23_0.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_23_0, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 442, 30, 30));

        button_23_1.setBackground(new java.awt.Color(0, 0, 0));
        jLayeredPane1.add(button_23_1, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 412, 30, 30));

        jLabelbar2.setText("jLabel2");
        jLayeredPane1.add(jLabelbar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, 30, 150));
        jLayeredPane1.add(jLabelbar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 80, 30, 150));

        getContentPane().add(jLayeredPane1);
        jLayeredPane1.setBounds(0, 0, 610, 540);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void zarAtButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zarAtButtonActionPerformed
        // TODO add your handling code here:

        if (!zarAtButton.isEnabled()) {
            return;
        }

        if (ilkTur && !oyunBasladi) {
            // İlk turda sadece 1 zar atılır
            int zar = random.nextInt(6) + 1;

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

            // 🎯 EKLENEN KISIM: Taş çıkarma kontrolü
            if (tumTaslarEvdeMi(aktifTaslar(), oyuncuNumarasi)) {
                boolean tasCikardiMi = false;

                ArrayList<Tas> siraliTaslar = new ArrayList<>(aktifTaslar());
                siraliTaslar.sort((t1, t2) -> {
                    return (oyuncuNumarasi == 1)
                            ? Integer.compare(t1.getUcgenNo(), t2.getUcgenNo()) // siyah: 0 → 5
                            : Integer.compare(t2.getUcgenNo(), t1.getUcgenNo());  // beyaz: 23 → 18
                });

                for (int i = 0; i < zarlar.length; i++) {
                    int zar = zarlar[i];
                    for (Tas tas : siraliTaslar) {
                        if (tasCikarabilirMi(tas, zar, oyuncuNumarasi, aktifTaslar())) {
                            tasCikar(tas.getUcgenNo(), butonlar, aktifTaslar());

                            zarlar[i] = -1;
                            tasCikardiMi = true;
                            break;
                        }
                    }
                }

                if (!tasCikardiMi) {
                    JOptionPane.showMessageDialog(this, "Zarlar taş çıkarmaya uygun değil. Sıra rakibe geçti.");
                    int rakip = (oyuncuNumarasi == 1) ? 2 : 1;
                    client.mesajGonder("SIRA:" + rakip);
                } else {
                    System.out.println("✅ En az 1 taş çıkarıldı.");
                    if (zarlar[0] == -1 || zarlar[1] == -1) {
                        client.mesajGonder("HAMLE_BITTI");
                    }
                }
            }
        }
    }//GEN-LAST:event_zarAtButtonActionPerformed

    private void button_16_0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_16_0ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_16_0ActionPerformed

    private void button_18_2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_18_2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_button_18_2ActionPerformed

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
    private java.awt.Button button_16_0;
    private java.awt.Button button_16_1;
    private java.awt.Button button_16_2;
    private java.awt.Button button_18_0;
    private java.awt.Button button_18_1;
    private java.awt.Button button_18_2;
    private java.awt.Button button_18_3;
    private java.awt.Button button_18_4;
    private java.awt.Button button_23_0;
    private java.awt.Button button_23_1;
    private java.awt.Button button_5_0;
    private java.awt.Button button_5_1;
    private java.awt.Button button_5_2;
    private java.awt.Button button_5_3;
    private java.awt.Button button_5_4;
    private java.awt.Button button_7_0;
    private java.awt.Button button_7_1;
    private java.awt.Button button_7_2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelZar1;
    private javax.swing.JLabel jLabelbar1;
    private javax.swing.JLabel jLabelbar2;
    private javax.swing.JLabel jLabelucgen_0;
    private javax.swing.JLabel jLabelucgen_1;
    private javax.swing.JLabel jLabelucgen_10;
    private javax.swing.JLabel jLabelucgen_11;
    private javax.swing.JLabel jLabelucgen_12;
    private javax.swing.JLabel jLabelucgen_13;
    private javax.swing.JLabel jLabelucgen_14;
    private javax.swing.JLabel jLabelucgen_15;
    private javax.swing.JLabel jLabelucgen_16;
    private javax.swing.JLabel jLabelucgen_17;
    private javax.swing.JLabel jLabelucgen_18;
    private javax.swing.JLabel jLabelucgen_19;
    private javax.swing.JLabel jLabelucgen_2;
    private javax.swing.JLabel jLabelucgen_20;
    private javax.swing.JLabel jLabelucgen_21;
    private javax.swing.JLabel jLabelucgen_22;
    private javax.swing.JLabel jLabelucgen_23;
    private javax.swing.JLabel jLabelucgen_3;
    private javax.swing.JLabel jLabelucgen_4;
    private javax.swing.JLabel jLabelucgen_5;
    private javax.swing.JLabel jLabelucgen_6;
    private javax.swing.JLabel jLabelucgen_7;
    private javax.swing.JLabel jLabelucgen_8;
    private javax.swing.JLabel jLabelucgen_9;
    private javax.swing.JLabel jLabelzar2;
    private javax.swing.JLayeredPane jLayeredPane1;
    private java.awt.Button zarAtButton;
    // End of variables declaration//GEN-END:variables
}
