///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package com.mycompany.networkproject;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.PrintWriter;
//import java.net.Socket;
//
///**
// *
// * @author Kevser
// */
//public class GameRoom implements Runnable {
//
//    private Socket player1;
//    private Socket player2;
//
//    private Integer ilkZar1 = null;
//    private Integer ilkZar2 = null;
//
//    private BufferedReader in1, in2;
//    private PrintWriter out1, out2;
//
//    public GameRoom(Socket p1, Socket p2) {
//        this.player1 = p1;
//        this.player2 = p2;
//    }
//
//    @Override
//    public void run() {
//        try {
//            in1 = new BufferedReader(new InputStreamReader(player1.getInputStream()));
//            out1 = new PrintWriter(player1.getOutputStream(), true);
//
//            in2 = new BufferedReader(new InputStreamReader(player2.getInputStream()));
//            out2 = new PrintWriter(player2.getOutputStream(), true);
//
//            out1.println("\uD83D\uDFE2 Eşleştin! Oyuna başlıyorsun.");
//            out2.println("\uD83D\uDFE2 Eşleştin! Oyuna başlıyorsun.");
//
//            // Bu kısmı GameRoom.run() içinde başta yapma!
//            out1.println("OYUNCU:1");
//            out2.println("OYUNCU:2");
//
//           // out1.println("🟢 Rakip bulundu. Oyuna başlıyorsunuz.");
//            //out2.println("🟢 Rakip bulundu. Oyuna başlıyorsunuz.");
//
//// İlk zarı Oyuncu 1 atsın
//            out1.println("SIRA:1");  // sadece bundan sonra aktif edilir
//            out2.println("SIRA:0");  // pasif
//
//            while (true) {
//                kontrolEt(in1, out1, out2, 1);
//                kontrolEt(in2, out2, out1, 2);
//
//                if (ilkZar1 != null && ilkZar2 != null) {
//                    if (ilkZar1 > ilkZar2) {
//                        out1.println("SIRA:1");
//                        out2.println("SIRA:0");
//                    } else if (ilkZar2 > ilkZar1) {
//                        out1.println("SIRA:0");
//                        out2.println("SIRA:2");
//                    } else {
//                        out1.println("ZARLAR_EŞľT");
//                        out2.println("ZARLAR_EŞľT");
//                        
//                    }
//
//                    out1.println("ILKTURBITTI");
//                    out2.println("ILKTURBITTI");
//
//                    ilkZar1 = null;
//                    ilkZar2 = null;
//                }
//
//                Thread.sleep(50);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                player1.close();
//                player2.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
//
//    private void kontrolEt(BufferedReader in, PrintWriter sender, PrintWriter receiver, int oyuncuNo) throws IOException {
//        if (in.ready()) {
//            String msg = in.readLine();
//            System.out.println("[Oyuncu" + oyuncuNo + "] " + msg);
//
//            if (msg.startsWith("ILKZAR:")) {
//                int zar = Integer.parseInt(msg.substring(7).trim());
//                if (oyuncuNo == 1) {
//                    ilkZar1 = zar;
//                    out2.println("SIRA:2");
//                } else {
//                    ilkZar2 = zar;
//                }
//
//                receiver.println("ILKZAR:" + zar);
//
//            } else {
//                receiver.println(msg);
//
//                if (msg.startsWith("ZAR:")) {
//                    sender.println("SIRA:0");
//                    receiver.println("SIRA:" + oyuncuNo);
//                }
//            }
//        }
//    }
//}
//
// ✅ GameRoom.java (Sunucu)
package com.mycompany.networkproject;

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

//            if (msg.startsWith("ILKZAR:")) {
//                int zar = Integer.parseInt(msg.substring(7).trim());
//                if (oyuncuNo == 1) {
//                    ilkZar1 = zar;
//                } else {
//                    ilkZar2 = zar;
//                }
//                receiver.println("ILKZAR:" + zar);
//
//            }
            if (msg.startsWith("ILKZAR:")) {
                int zar = Integer.parseInt(msg.substring(7).trim());
                if (oyuncuNo == 1) {
                    ilkZar1 = zar;
                    receiver.println("ILKZAR:" + zar);
                    // Oyuncu 2'ye sıra ver
                    out2.println("SIRA:2");
                } else {
                    ilkZar2 = zar;
                    receiver.println("ILKZAR:" + zar);
                }
            } else {
                receiver.println(msg);

                if (msg.startsWith("ZAR:")) {
                    sender.println("SIRA:0");
                    receiver.println("SIRA:" + oyuncuNo);
                }
            }
        }
    }
}
