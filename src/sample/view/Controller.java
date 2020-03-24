package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.paint.Color;
import sample.DrawerTask;
import sample.ImplementedRules;
import sample.rules.BaseRule;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Canvas canvas;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private ChoiceBox choiceBox;

    private DrawerTask task;
    private GraphicsContext gc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        prepareChoiceBox();
        prepareButtons();
    }

    private void prepareChoiceBox() {
        List<String> ruleNames = ImplementedRules.getRuleNames();
        ObservableList list = FXCollections.observableArrayList(ruleNames);
        choiceBox.setItems(list);
        choiceBox.setValue(list.get(0));
    }

    private void prepareButtons() {
        startButton.setOnAction(t -> {
            String chosenRule = (String) choiceBox.getValue();
            BaseRule rule = ImplementedRules.getRule(chosenRule);
            if (rule != null) {
                int xSize = (int) (canvas.getWidth()/5);
                int ySize = (int) (canvas.getHeight()/5);
                runDrawingTask(rule, xSize, ySize);
            }
        });
        stopButton.setOnAction(t -> {
            task.cancel();
            clearCanvas(gc);
        });
    }

    private void clearCanvas(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0,
                gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());
    }

    private void runDrawingTask(BaseRule rule, int xSize, int ySize) {
        gc = canvas.getGraphicsContext2D();
        clearCanvas(gc);
        task = new DrawerTask();
        task.setGc(gc);
        task.setRule(rule);
        task.setxSize(xSize);
        task.setySize(ySize);
        new Thread(task).start();
    }
}
