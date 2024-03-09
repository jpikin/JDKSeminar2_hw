package program;

public class App {
    public static void main(String[] args) {

        Logs logs = new Logs();

        ServerGUI serverGUI = new ServerGUI();
        ServerController server = new ServerController();
        server.setServerView(serverGUI);
        serverGUI.setServerController(server);
        server.setServerGUI(serverGUI);
        serverGUI.setLogs(logs);
        logs.setServer(serverGUI);


        ClientGUI clientGUI1 = new ClientGUI();
        ClientController clientController1 = new ClientController();
        clientController1.setClientView(clientGUI1);
        clientGUI1.setClient(clientController1);
        clientController1.setServer(server);
        server.clientsGUI.add(clientGUI1);


        ClientGUI clientGUI2 = new ClientGUI();
        ClientController clientController2 = new ClientController();
        clientController2.setClientView(clientGUI2);
        clientGUI2.setClient(clientController2);
        clientController2.setServer(server);
        server.clientsGUI.add(clientGUI2);
    }
}
