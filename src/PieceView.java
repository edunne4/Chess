/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jake Schaeffer
 * Section: 11am
 * Date: 11/13/19
 * Time: 11:04 AM
 *
 * Project: csci205finalproject
 * Package: PACKAGE_NAME
 * Class: PieceView
 *
 * Description:
 *
 * ****************************************
 */

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PieceView {

    ImageView view;

    public PieceView(String imageFile, BoardView) {
        // load the image
        Image image = new Image(imageFile);
        //make the imageview
        ImageView view = new ImageView(image);
        view.setFitHeight(BoardView.getWindowHeight() / BoardView.SIDE_LENGTH);
        view.setFitWidth(BoardView.getWindowHeight() / BoardView.SIDE_LENGTH);
    }

    public ImageView getView() {
        return view;
    }
}
