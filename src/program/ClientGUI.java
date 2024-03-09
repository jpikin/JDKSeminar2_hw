package program;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ClientGUI extends JFrame implements ClientView {
    private ClientController clientController;

    private static final int HEIGHT = 300;
    private static final int WIDTH = 400;
    private static final int POS_X = 300;
    private static final int POS_Y = 300;
    JButton btnStart = new JButton("Connect");
    JButton btnStop = new JButton("Disconnect");
    JPanel buttons = new JPanel();
    JPanel netProtocol = new JPanel(new GridLayout(2, 2));
    JPanel bottomGroup = new JPanel(new GridLayout(2, 1));
    JPanel buttonsGroup = new JPanel();
    JTextField inputField = new JTextField();
    JTextField userNameField = new JTextField("user");
    JPasswordField password = new JPasswordField("password");
    JTextArea chat = new JTextArea();

    ClientGUI() {
        settings();
        createPanel();
        setVisible(true);
    }

    public void setClient(ClientController clientController) {
        this.clientController = clientController;
    }

    private void settings() {
        setTitle(ClientController.getClientID());
        setBounds(POS_X, POS_Y, WIDTH, HEIGHT);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(listener);
    }

    private void createPanel() {
        buttons.add(btnStart);
        buttons.add(btnStop);
        add(buttons, BorderLayout.SOUTH);
        bottomGroup.add(inputField, BorderLayout.NORTH);
        buttonsGroup.add(btnStart);
        buttonsGroup.add(btnStop);
        bottomGroup.add(buttonsGroup);
        add(bottomGroup, BorderLayout.SOUTH);
        netProtocol.add(new JPanel());
        netProtocol.add(new JTextField("127.0.0.1"));
        netProtocol.add(userNameField);
        netProtocol.add(password);
        add(netProtocol, BorderLayout.NORTH);
        add(chat);

        btnStart.addActionListener(e -> connectToServer());
        btnStop.addActionListener(e -> disconnectFromServer());
        userNameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {getUserName();
            }
        });
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (clientController.isOnlineCheck()) {
                    if (e.getKeyChar() == '\n') {
                        String currentMessage = clientController.getUserName() + ": " + inputField.getText();
                        sendMessageToServer(currentMessage);
                        setCurrentMessage();
                        inputField.setText("");
                    }
                } else {
                    chat.setText(clientController.getUserName() + " has not connected to server");
                    inputField.setText("");
                }
            }
        });
    }
    WindowListener listener = new WindowAdapter() {
        @Override
        public void windowClosing(WindowEvent we) {
                disconnectFromServer();
                setVisible(false);
                dispose();
        }
    };

    private void setCurrentMessage(){
        clientController.setCurrentMessage();
    }
    private void getUserName(){
        clientController.setUserName(userNameField.getText());
    }
    private void sendMessageToServer(String message) {
        clientController.sendMessageToServer(message);
    }

    private void disconnectFromServer() {
        if (clientController.isOnlineCheck()) {
            clientController.disconnectFromServer();
            chat.setText(clientController.getUserName() + " has disconnected from server");
        }
    }

    private void connectToServer() {
        if (clientController.serverCheck()) {
            if (!checkConnection()) {
                clientController.connectToServer();
                chat.setText(clientController.getUserName()
                        + " has connected to server\n"
                        + clientController.server.readLogs()
                        + clientController.server.getChat());
            }
        } else
            chat.setText("Server is offline");
    }

    public boolean checkConnection() {
        return clientController.isOnlineCheck();
    }
}
