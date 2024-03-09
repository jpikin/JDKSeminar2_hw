package program;


import java.io.*;

public class Logs {

    private ServerGUI server;

    public void setServer(ServerGUI server){
        this.server = server;
    }

    /**
     * Метод читает и возвращает файл с логами.
     */
    public String readLogs() {
        StringBuilder txt = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/program/logs.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                txt.append(line+'\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return txt.toString();
    }

    /**
     * Метод записывает текущее сообщение в файл с логами.
     *
     * @param txt Текущее сообщение.
     */
    public void saveLogs(String txt) {
        try {
            File saveFile = new File("./src/program/logs.txt");
            FileWriter writer = new FileWriter(saveFile, true);
            writer.write(txt);
            writer.close();

        } catch (IOException e) {
            server.chat.setText(txt + "\nОшибка при сохранении лога");
        }
    }
}
