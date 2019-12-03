package View;/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Ryan Bailis
 * Section: MWF 11am
 * Instructor: Professor Brian King
 * Date: 11/26/19
 * Time: 11:43 PM
 *
 * Project: csci205FinalProject
 * Package: View
 * Class: View.GameMenu
 *
 * Description:
 *
 * ****************************************
 */

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class GameMenu extends MenuBar {

    public GameMenu() {

        super();

        Menu view = new Menu("View (2D/3D");
        MenuItem view2D = new MenuItem("2D");
        MenuItem view3D = new MenuItem("3D");
        view.getItems().addAll(view2D,view3D);

        Menu player1PieceColorMenu = new Menu("Player 1 Piece Color");
        createColorOptions(player1PieceColorMenu);

        Menu player2PieceColorMenu = new Menu("Player 2 Piece Color");
        createColorOptions(player2PieceColorMenu);

        Menu gameSettingsMenu = new Menu("Settings");
        gameSettingsMenu.getItems().addAll(view,player1PieceColorMenu,player2PieceColorMenu);

        Menu quitMenu = new Menu("Quit Game");
        MenuItem quitSaveGame = new MenuItem("Save and Quit");
        MenuItem quitGame = new MenuItem("Quit");
        quitMenu.getItems().addAll(quitSaveGame,quitGame);

        this.getMenus().add(quitMenu);


    }

    private void createColorOptions(Menu menu) {
        int iconSize = 10;
        List<Color> colorList = new ArrayList<>();

        colorList.add(Color.RED);
        colorList.add(Color.BLUE);
        colorList.add(Color.GREEN);
        colorList.add(Color.YELLOW);
        colorList.add(Color.PURPLE);
        colorList.add(Color.ORANGE);

        for (Color color : colorList) {
            MenuItem menuItem = new MenuItem(color.toString());
            Shape icon = new Circle(iconSize,color);
            menuItem.setGraphic(icon);

            menu.getItems().add(menuItem);
        }


    }
}