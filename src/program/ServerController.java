package program;

import java.util.ArrayList;

public class ServerController {

    private ServerView serverView;
    private boolean isOnline = false;
    private ServerGUI serverGUI;
    ArrayList<ClientController> clientList = new ArrayList<>();
    ArrayList<ClientGUI> clientsGUI = new ArrayList<>();


    public void addToList(ClientController c) {
        clientList.add(c);
    }

    public void delFromList(ClientController c) {
        clientList.remove(c);
    }

    public boolean getIsOnline() {
        return this.isOnline;
    }

    public void setServerView(ServerView serverGUI) {
        this.serverView = serverGUI;
    }
    public void setServerGUI(ServerGUI serverGUI){
        this.serverGUI = serverGUI;
    }

    public void startServer() {
        if (!this.isOnline) this.isOnline = true;
    }

    public void stopServer() {
        if (isOnline) this.isOnline = false;
        for (ClientController c: clientList) {
            if (c.isOnlineCheck()) {
                c.disconnectFromServer("Connection closed");
            }
        }
        clientList.clear();
    }
    public void getMessageFromClients(String message){
        serverGUI.setChat(message);
    }

    public String readLogs(){
        return serverGUI.readLogs();
    }
    protected String getChat(){
        return serverGUI.chat.getText();
    }
}
