package control;

import java.awt.*;
import java.util.*;

import javax.swing.*;

import control.samples.*;
import model.*;
import view.*;

/**
 * Test class.
 * @author cryingshadow
 */
public class Main {

    /**
     * @param args Ignored.
     */
    public static void main(final String[] args) {
        final JFrame frame = new JFrame("SnakeTest");
        final Container content = frame.getContentPane();
        final CompetitionControl control =
            new CompetitionControl(
                new Settings(),
                Arrays.asList(new RandomSnakeControl(), new RandomSnakeControl())
            );
//        final Maze maze =
//            new Maze(
//                new Field[][]{
//                    {
//                        new Field(FieldType.FREE, Optional.empty()),
//                        new Field(FieldType.WALL, Optional.empty()),
//                        new Field(FieldType.FOOD, Optional.empty())
//                    },
//                    {
//                        new Field(FieldType.SNAKE, Optional.of(new SnakePart(Color.RED, false))),
//                        new Field(FieldType.SNAKE, Optional.of(new SnakePart(Color.GREEN, true))),
//                        new Field(FieldType.COLLISION, Optional.empty())
//                    }
//                }
//            );
        final MazeDisplay display = new MazeDisplay(control.getCurrentMaze(), 50);
        content.add(display);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
        final Thread frameThread =
            new Thread(
                new Runnable(){

                    @Override
                    public void run() {
                        frame.setVisible(true);
                    }

                }
            );
        frameThread.start();
        try {
            for (int i = 0; i < 50; i++) {
                Thread.sleep(500);
                control.turn();
                display.setMaze(control.getCurrentMaze());
            }
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

}
