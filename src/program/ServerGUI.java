package program;

import javax.swing.*;
import java.awt.*;


public class ServerGUI extends JFrame implements ServerView, LogsView{
    private static final int HEIGHT = 300;
    private static final int WIDTH = 400;
    private static final int POS_X = 600;
    private static final int POS_Y = 600;
    JButton btnStart = new JButton("Start server");
    JButton btnStop = new JButton("Close server");
    JButton btnQuit = new JButton("Quit");
    JPanel buttonsGroup = new JPanel();
    JTextArea chat = new JTextArea();
    private ServerController server;
    private Logs logs;

    public void setServerController(ServerController serverController) {
        this.server = serverController;
    }
    public void setLogs(Logs logs) {this.logs = logs;}

    ServerGUI(){
        settings();
        createPanel();

        setVisible(true);
    }

    private void settings() {
        setTitle("Server is offline");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);

    }
    private void createPanel() {
        buttonsGroup.add(btnStart);
        buttonsGroup.add(btnStop);
//        buttonsGroup.add(btnQuit);
        add(buttonsGroup, BorderLayout.SOUTH);
        add(chat);

        btnStart.addActionListener(e -> startServer());
        btnStop.addActionListener(e -> {
            saveLogs(chat.getText());
            stopServer();
            chat.setText("");
        });

    }

    protected void setChat(String message){
        chat.setText(chat.getText()  + message + '\n');
    }

    public void saveLogs(String message) {
        logs.saveLogs(message ); // delete + '\n'
    }
    public String readLogs(){
        return logs.readLogs();
    }

    private void stopServer() {
        server.stopServer();
        setTitle("Server is offline");
    }

    private void startServer() {
        server.startServer();
        setTitle("Server is online");
    }
}
