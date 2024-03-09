package program;

public class ClientController {
    private ClientView clientView;
    ServerController server;

    private boolean isOnline = false;
    private String userName = "user";
    protected static int clientID = 1;
    public void setClientView(ClientView clientView) {
        this.clientView = clientView;
    }
    public void setServer(ServerController server){
        this.server = server;
    }

    protected static String getClientID() {
        return "Client " + clientID++;
    }

    public void connectToServer() {
        isOnline = true;
        server.addToList(this);
    }
    public boolean isOnlineCheck(){
        return isOnline;
    }
    public boolean serverCheck(){
        return server.getIsOnline();
    }


    public void disconnectFromServer() {
        isOnline = false;
        server.delFromList(this);
    }

    public void disconnectFromServer(String message){
        for (ClientGUI c: server.clientsGUI){
            if(c.checkConnection())
                c.chat.setText(message);
        }
        isOnline = false;
    }

    protected void sendMessageToServer(String txt){
        server.getMessageFromClients(txt);
    }
    protected String getUserName(){
        return userName;
    }
    protected void setUserName(String name){
        this.userName = name;
    }

    @Override
    public String toString(){
        return this.userName;
    }

    public void setCurrentMessage() {
        for (ClientGUI c: server.clientsGUI){
            if(c.checkConnection())
                c.chat.setText(server.readLogs() + server.getChat());
        }
    }
}
