/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package TavlaOyunu;

/**
 *
 * @author Kevser
 */


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class Server {
    private static final int PORT = 12345;
    private static final LinkedList<Socket> playerQueue = new LinkedList<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Sunucu başlatıldı. Oyuncular bekleniyor...");

            while (true) {
                Socket playerSocket = serverSocket.accept();
                System.out.println("Yeni oyuncu bağlandı: " + playerSocket);

                synchronized (playerQueue) {
                    playerQueue.add(playerSocket);

                    if (playerQueue.size() >= 2) {
                        Socket player1 = playerQueue.remove();
                        Socket player2 = playerQueue.remove();

                        GameRoom gameRoom = new GameRoom(player1, player2);
                        new Thread(gameRoom).start();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
