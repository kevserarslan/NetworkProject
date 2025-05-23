/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TavlaOyunu;

/**
 *
 * @author Kevser
 */
import java.io.*;
import java.net.Socket;

public class Client {

    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private GUI gui;
    private int oyuncuNumarasi;

    public int getOyuncuNumarasi() {
        return oyuncuNumarasi;
    }

    public void setOyuncuNumarasi(int oyuncuNumarasi) {
        this.oyuncuNumarasi = oyuncuNumarasi;
    }

    public Client(GUI gui) {
        this.gui = gui;
    }

    public void connect(String host, int port) {
        try {
            socket = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            // Mesajları dinleyen thread
            new Thread(() -> {
                String mesaj;
                try {
                    while ((mesaj = in.readLine()) != null) {
                        gui.gelenMesajGoster(mesaj);  // GUI'de göster
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
            gui.hataGoster("Sunucuya bağlanılamadı!");
        }
    }

    public void mesajGonder(String mesaj) {
        if (out != null) {
            out.println(mesaj);
        }
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new GUI().setVisible(true);
        });
    }

}
