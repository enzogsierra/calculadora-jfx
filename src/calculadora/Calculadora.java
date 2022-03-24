/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

/**
 *
 * @author enzog
 */
public class Calculadora extends Application {
    
    @Override
    public void start(Stage stage) throws Exception 
    {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLCalculadora.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setTitle("Calculadora en Java");
        stage.setResizable(false);
        root.requestFocus();
        
        // Añadir icono
        Image icon = new Image(Calculadora.class.getResourceAsStream("icon.png"));
        stage.getIcons().add(icon);
        
        // Añadir estilos
        scene.getStylesheets().add(Calculadora.class.getResource("style.css").toExternalForm());
        
        //
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
