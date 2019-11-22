/* *****************************************
 * CSCI205 - Software Engineering and Design
 * Fall 2019
 * Instructor: Prof. Brian King
 *
 * Name: Ryan Bailis
 * Section: MWF 11am
 * Instructor: Professor Brian King
 * Date: 11/21/19
 * Time: 11:21 PM
 *
 * Project: csci205FinalProject
 * Package: Import3DMaker
 * Class: ChessPiece3D
 *
 * Description:
 *
 * ****************************************
 */
package View3D;

import com.interactivemesh.jfx.importer.ImportException;
import com.interactivemesh.jfx.importer.stl.StlMeshImporter;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.TriangleMesh;

public class ChessPiece3D extends MeshView{

    private PieceEnum pieceType;
    private Color pieceColor;

    /**
     * A constructor to create a MeshView object from an STL file.
     * @param piece the STL file to be parsed and converted to a JavaFX MesView object
     * @param color the color of the MeshView in the viewport
     */
    ChessPiece3D(PieceEnum piece, Color color) {

        super();

        this.pieceType = piece;
        this.pieceColor = color;

        //create a StlMeshImporter object and try parsing with the filename
        StlMeshImporter stlImporter = new StlMeshImporter();
        try {
            stlImporter.read(this.getClass().getResource(piece.getFilename()));
        }
        catch (ImportException e) {
            System.err.println("Error. STL file does not exist.");
            e.printStackTrace();
        }

        //create a MeshView object from the StlMeshImporter
        stlImporter.getImport();
        TriangleMesh mesh = stlImporter.getImport();
        stlImporter.close();

        this.setMesh(mesh);

        //set the material, general properties, and scale of the mesh
        this.setMaterial(new PhongMaterial(color));
        this.setDrawMode(DrawMode.FILL);
        this.setVisible(true);

        //scale the pieces appropriately
        int scale = 3;
        this.setScaleX(scale);
        this.setScaleY(scale);
        this.setScaleZ(scale);
    }

    public PieceEnum getPieceType() {
        return pieceType;
    }

    public Color getPieceColor() {
        return pieceColor;
    }
}