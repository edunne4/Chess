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

    public PieceView(String imageFile) {
        // load the image
        Image image = new Image(imageFile);
        //make the imageview
        ImageView rookView = new ImageView(image);
        rookView.setFitHeight(windowHeight / SIDE_LENGTH);
        rookView.setFitWidth(windowHeight / SIDE_LENGTH);
    }
}
