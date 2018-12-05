package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;


public class Controller implements Initializable {
    File fileObject;
    ObservableList<String> list = FXCollections.observableArrayList();
    List<Integer> numbers;
    String path;
    @FXML
    private TextArea resultText;
    @FXML
    private TextArea processText;
    @FXML
    private ListView<Integer> sanleText;

    public void openFile(ActionEvent actionEvent) throws FileNotFoundException {
        path = getPathFile();
        numbers = readLinesFromFile(path);
        resultText.appendText(numbers.toString());
    }

    public String getPathFile() {

        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения
        fileChooser.setTitle("Open Document");//Заголовок диалога
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");//Расширение
        fileChooser.getExtensionFilters().add(extFilter);
        fileChooser.getExtensionFilters().addAll(extFilter);

        String path = fileChooser.showOpenDialog(new Stage()).getAbsolutePath();
        return path;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resultText.setWrapText(true);
        processText.setWrapText(true);
    }

    public static List<Integer> readLinesFromFile(String filePath)
            throws FileNotFoundException {

        // if (filePath == null || filePath.length() == 0) return null;

        File file = new File(filePath);
        List<Integer> lines = new ArrayList<>();
        Scanner scanner = new Scanner(file, "UTF-8");

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] temp = line.split(" ");
            for (String number : temp) {
                lines.add(Integer.parseInt(number));
            }
        }
        return lines;
    }

    public void calcule() throws FileNotFoundException {
        numbers = Logic.readFromFile(path);
        resultText.setText(numbers.toString());
        processText.appendText(Logic.process(numbers).toString());

    }
}
