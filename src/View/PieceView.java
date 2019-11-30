/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Jake Schaeffer
 * Section: 11am
 * Date: 11/13/19
 * Time: 11:34 AM
 *
 * Project: csci205finalproject
 * Package: View
 * Class: PieceView
 *
 * Description:
 *
 * ****************************************
 */
package View;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class PieceView{

    ImageView view;

    /**
     * Creates an ImageView object from a file or url that will fit onto the squareview
     *
     * @param imageFileName the filename or url
     */
    public PieceView(String imageFileName){
        // load the image
        Image image = new Image(imageFileName,60,60,false,false);
        //make the imageview
        view = new ImageView(image);
        //these are temporary
        view.setFitHeight(80);
        view.setFitWidth(80);
    }

    /**
     * @return the ImageView Object for the piece
     */
    public ImageView getView(){
        return view;
    }
}
