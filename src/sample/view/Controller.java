package sample.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import sample.cell_auto.DrawerTask;
import sample.rules.ImplementedRuleNames;
import sample.rules.Rule;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private static int CANVAS_HEIGHT;
    private static int CANVAS_WIDTH;

    @FXML
    private Canvas canvas;
    @FXML
    private Button startButton;
    @FXML
    private Button stopButton;
    @FXML
    private ChoiceBox choiceBox;
    @FXML
    private TextField widthTextField;
    @FXML
    private TextField heightTextField;

    private DrawerTask task;
    private GraphicsContext gc;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CANVAS_HEIGHT = (int) canvas.getHeight()/5;
        CANVAS_WIDTH = (int) canvas.getWidth()/5;
        prepareChoiceBox();
        prepareButtons();
    }

    private void prepareChoiceBox() {
        List<String> ruleNames = ImplementedRuleNames.getRuleNames();
        ObservableList list = FXCollections.observableArrayList(ruleNames);
        choiceBox.setItems(list);
        choiceBox.setValue(list.get(0));
    }

    private void prepareButtons() {
        startButton.setOnAction(t -> {
            String chosenRule = (String) choiceBox.getValue();

            Rule rule = new Rule(chosenRule);
            int xSize = getWidth();
            int ySize = getHeight();
            try {
                runDrawingTask(rule, xSize, ySize);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        stopButton.setOnAction(t -> {
            task.cancel();
            clearCanvas(gc);
            clearTextFields();
        });
    }

    private void clearCanvas(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0,
                gc.getCanvas().getWidth(),
                gc.getCanvas().getHeight());
    }

    private void clearTextFields() {
        heightTextField.setText("");
        widthTextField.setText("");
    }

    private void runDrawingTask(Rule rule, int xSize, int ySize) throws InterruptedException {
        gc = canvas.getGraphicsContext2D();
        clearCanvas(gc);
        task = new DrawerTask();
        task.setGc(gc);
        task.setRule(rule);
        task.setxSize(xSize);
        task.setySize(ySize);
        task.setxDislocation((CANVAS_WIDTH-xSize)/2);
        Thread thread = new Thread(task);
        thread.start();
        thread.join();
    }

    private Integer getHeight() {
        String input = heightTextField.getText();
        if (!input.isEmpty()) {
            int height = Integer.parseInt(input);
            if(height <= 0) return 1;
            if(height <= CANVAS_HEIGHT)
                return height;
        }
        return CANVAS_HEIGHT;
    }

    private int getWidth() {
        String input = widthTextField.getText();
        if (!input.isEmpty()) {
            int width = Integer.parseInt(input);
            if(width <= 0) return 1;
            if(width <= CANVAS_WIDTH)
                return width;
        }
        return CANVAS_WIDTH;
    }
}
