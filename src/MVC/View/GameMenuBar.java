/* *****************************************
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
 * Package: MVC.View
 * Class: MVC.View.GameMenu
 *
 * Description:
 *
 * ****************************************
 */
package MVC.View;

import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;
import java.util.List;

public class GameMenuBar extends MenuBar {

    //TODO - set selected RadioMenuButtons on game load.
    public GameMenuBar() {

        super();

        Menu view = new Menu("MVC.View (2D/3D)");
        ToggleGroup viewGroup = new ToggleGroup();
        RadioMenuItem view2D = new RadioMenuItem("2D");
        RadioMenuItem view3D = new RadioMenuItem("3D");
        view.getItems().addAll(view2D,view3D);
        viewGroup.getToggles().addAll(view2D,view3D);

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

        this.getMenus().addAll(gameSettingsMenu,quitMenu);


    }

    private void createColorOptions(Menu menu) {
        int iconSize = 10;
        List<Color> colorList = new ArrayList<>();

        colorList.add(Color.RED);
        colorList.add(Color.ORANGE);
        colorList.add(Color.YELLOW);
        colorList.add(Color.GREEN);
        colorList.add(Color.BLUE);
        colorList.add(Color.PURPLE);
        colorList.add(Color.WHITE);


        for (Color color : colorList) {
            RadioMenuItem menuItem = new RadioMenuItem(String.valueOf(color.toString()));
            ToggleGroup toggleGroup = new ToggleGroup();

            Shape icon = new Circle(iconSize,color);
            menuItem.setGraphic(icon);
            icon.setStroke(Color.BLACK);

            toggleGroup.getToggles().add(menuItem);
            menu.getItems().add(menuItem);
        }


    }
}
