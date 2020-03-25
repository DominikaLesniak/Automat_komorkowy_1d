package sample.cell_auto;

import javafx.concurrent.Task;
import javafx.scene.canvas.GraphicsContext;
import sample.rules.Rule;

public class DrawerTask  extends Task {

    private Rule rule;
    private GraphicsContext gc;
    private Simulation simulation;
    private int xSize;
    private int ySize;

    public DrawerTask() {
        simulation = new Simulation();
    }

    public void setGc(GraphicsContext gc) {
        this.gc = gc;
    }
    public void setRule(Rule rule) {
        this.rule = rule;
    }

    @Override
    protected Object call() throws Exception {
        int[][] board = simulation.runSimulation(rule, xSize, ySize);
        for (int i = 0; i < ySize; i++) {
            for (int j = 0; j < xSize; j++) {
                if (isCancelled()) {
                    break;
                }
                if (board[i][j] == 1) {
                    paintPixel(j, i, gc);
                }
            }
        }
        return 0;
    }

    private void paintPixel(int i, int j, GraphicsContext gc) {
        gc.setFill(javafx.scene.paint.Color.BLACK);
        gc.fillRect( 5 * i,
                 5 * j,
                5, 5);
    }

    public void setxSize(int xSize) {
        this.xSize = xSize;
    }

    public void setySize(int ySize) {
        this.ySize = ySize;
    }
}