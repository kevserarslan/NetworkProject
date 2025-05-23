///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
// ✅ GameRoom.java (Sunucu)
package TavlaOyunu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameRoom implements Runnable {

    private Socket player1;
    private Socket player2;

    private Integer ilkZar1 = null;
    private Integer ilkZar2 = null;
    private boolean oyuncularHazir = false;

    private BufferedReader in1, in2;
    private PrintWriter out1, out2;

    public GameRoom(Socket p1, Socket p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    @Override
    public void run() {
        try {
            in1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
            out1 = new PrintWriter(player1.getOutputStream(), true);

            in2 = new BufferedReader(new InputStreamReader(player2.getInputStream()));
            out2 = new PrintWriter(player2.getOutputStream(), true);

            out1.println("OYUNCU:1");
            out2.println("OYUNCU:2");

            // Oyuncular eşleşti, oyuna hazır
            oyuncularHazir = true;
            out1.println("BASLAT");
            out2.println("BASLAT");

            out1.println("SIRA:1");
            out2.println("SIRA:0");

            while (true) {
                kontrolEt(in1, out1, out2, 1);
                kontrolEt(in2, out2, out1, 2);

                if (ilkZar1 != null && ilkZar2 != null) {
                    if (ilkZar1 > ilkZar2) {
                        out1.println("SIRA:1");
                        out2.println("SIRA:0");
                        out1.println("ILKTURBITTI");
                        out2.println("ILKTURBITTI");
                    } else if (ilkZar2 > ilkZar1) {
                        out1.println("SIRA:0");
                        out2.println("SIRA:2");
                        out1.println("ILKTURBITTI");
                        out2.println("ILKTURBITTI");
                    } else {
                        out1.println("ZARLAR_EŞİT");
                        out2.println("ZARLAR_EŞİT");
                        out1.println("SIRA:1");
                        out2.println("SIRA:0");
                    }
                    ilkZar1 = null;
                    ilkZar2 = null;
                }

                Thread.sleep(50);
            }
        } catch (IOException ex) {
            if (!"NORMAL_CIKIS".equals(ex.getMessage())) {
                ex.printStackTrace(); //  Sadece beklenmeyen hataları göster
            } else {
                System.out.println("ℹ️ Oyuncu pencereyi kapattı, bağlantı sonlandırıldı.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                player1.close();
                player2.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void kontrolEt(BufferedReader in, PrintWriter sender, PrintWriter receiver, int oyuncuNo) throws IOException {
        if (!oyuncularHazir) {
            return;
        }

        if (in.ready()) {
            String msg = in.readLine();
            System.out.println("[Oyuncu" + oyuncuNo + "] " + msg);

            if (msg.startsWith("ILKZAR:")) {
                int zar = Integer.parseInt(msg.substring(7).trim());
                if (oyuncuNo == 1) {
                    ilkZar1 = zar;
                    receiver.println("ILKZAR:" + zar);
                    out2.println("SIRA:2");
                } else {
                    ilkZar2 = zar;
                    receiver.println("ILKZAR:" + zar);
                }

            } else if (msg.equals("HAMLE_BITTI")) {
                // Oyuncu hamlesini bitirdi → sırayı rakibe geçir
                //sender.println("SIRA:0");               // kendi pasif
                int rakip = (oyuncuNo == 1) ? 2 : 1;
                sender.println("SIRA:0");
                receiver.println("SIRA:" + rakip);

            } else if (msg.startsWith("SIRA:")) {
                receiver.println(msg); // yine de destekle (manuel sıraya geçiş için)

            } else if (msg.startsWith("HAMLE:")) {
                System.out.println("➡ HAMLE mesajı iletildi: " + msg);
                receiver.println(msg); // karşı oyuncuya ilet
            } else if (msg.startsWith("BITIS:")) {
                System.out.println(" BITIS mesajı geldi: " + msg);
                receiver.println(msg);  // Diğer oyuncuya ilet
                sender.println(msg);    // Kendisine tekrar gönder (GUI tetiklensin)
            } else if (msg.equals("CIKIS")) {
                System.out.println(" Oyuncu " + oyuncuNo + " çıkış yaptı.");
                receiver.println("RAKIP_CIKTI");
                // Bu client kapatıldıktan sonra döngü dışına çıkmak için IOException fırlat
                throw new IOException("NORMAL_CIKIS");
            } else {
                System.out.println("➡ Genel mesaj iletildi: " + msg);
                receiver.println(msg); // diğer tüm mesajları aynen aktar
            }
        }
    }
}
