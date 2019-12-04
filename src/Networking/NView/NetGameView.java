/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: James Campbell
 * Section: 11 am
 * Date: 12/3/19
 * Time: 7:48 PM
 *
 * Project: CSCI205FinalProject
 * Package: Networking.View
 * Class: NetGameView
 *
 * Description:
 *
 * ****************************************
 */
package Networking.NView;

import SinglePlatform.Model.GameManager;
import SinglePlatform.View.BoardView;
import SinglePlatform.View.GameView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class NetGameView extends GameView {
    public NetGameView(GameManager model) {
        super(model);
    }

    @Override
    public VBox makeSideBoardCoords() {
        return super.makeSideBoardCoords();
    }

    @Override
    public HBox makeTopBoardCoords() {
        return super.makeTopBoardCoords();
    }

    @Override
    public void killPiece(int row, int col, FlowPane deadPieceHolder) {
        super.killPiece(row, col, deadPieceHolder);
    }

    @Override
    public Text getInCheckTextBlack() {
        return super.getInCheckTextBlack();
    }

    @Override
    public Text getInCheckTextWhite() {
        return super.getInCheckTextWhite();
    }

    @Override
    public BoardView getBoard() {
        return super.getBoard();
    }

    @Override
    public HBox getRoot() {
        return super.getRoot();
    }

    @Override
    public FlowPane getDeadPieceHolderWhite() {
        return super.getDeadPieceHolderWhite();
    }

    @Override
    public FlowPane getDeadPieceHolderBlack() {
        return super.getDeadPieceHolderBlack();
    }
}