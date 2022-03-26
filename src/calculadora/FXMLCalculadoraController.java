package calculadora;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;


public class FXMLCalculadoraController implements Initializable 
{
    @FXML
    private TextField txfScreen;
    @FXML
    private Text txtResult;
    @FXML
    private Button btn_n0;
    @FXML
    private Button btn_n1;
    @FXML
    private Button btn_n2;
    @FXML
    private Button btn_n3;
    @FXML
    private Button btn_n4;
    @FXML
    private Button btn_n5;
    @FXML
    private Button btn_n6;
    @FXML
    private Button btn_n7;
    @FXML
    private Button btn_n8;
    @FXML
    private Button btn_n9;
    @FXML
    private Button btn_comma;
    @FXML
    private Button btn_plus;
    @FXML
    private Button btn_minus;
    @FXML
    private Button btn_mul;
    @FXML
    private Button btn_div;
    @FXML
    private Button btn_mod;
    @FXML
    private Button btn_equals;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        reset();
    }

    
    //
    private String n1; // Almacena el primer valor
    private String n2; // Almacena el segundo valor
    private char operator; // Almacena el operador seleccionado (+, -, *, /, %)
    

    @FXML
    private void onNumberClick(ActionEvent event) // Al presionar en algun numero
    {
        Button btn = (Button) event.getSource();
        String number = btn.getText();
        
        if(operator == '\0') // Si no hay un operador seleccionado, editamos la variable "n1"
        {
            if(number.equals("0") && n1.equals("0")) return; // Evitar que se añadan varios ceros por delante
            n1 += number;
        }
        else // Si hay un operador seleccionado, editamos la variable "n2"
        {
            if(number.equals("0") && n2.equals("0")) return; // ...
            n2 += number;
        }
        
        drawScreen();
    }
    
    @FXML
    private void onCommaClick(ActionEvent event) // Al presionar la coma decimal
    {
        if(operator == '\0') // Editamos la variable "n1"
        {
            if(n1.contains(",")) return; // Si ya contiene una coma, saltear
            if(n1.isEmpty()) n1 = "0"; // Si "n1" está vacío, poner un "0" por delante como default
            
            n1 += ",";
        }
        else // Editamos la variable "n2"
        {
            if(n2.contains(",")) return;
            if(n2.isEmpty()) n2 = "0";
            
            n2 += ",";
        }
        
        drawScreen();
    }
    
    @FXML 
    private void onOperatorClick(ActionEvent event) // Al presionar en algun operador (+, -, *, /, %)
    {
        Button btn = (Button) event.getSource();
        
        operator = btn.getText().charAt(0); // Extraemos el texto como character
        drawScreen();
    }
    
    @FXML
    private void onResolveClick(ActionEvent event) // Al presionar el boton de resolver (=)
    {  
        if(n1.isEmpty() || operator == '\0' || n2.isEmpty()) return; // Evitamos hacer operaciones no validas
        
        double v1 = Double.parseDouble(n1.replace(",", ".")); // Parsear "n1" (string) a double
        double v2 = Double.parseDouble(n2.replace(",", ".")); // ...
        double result = 0.0d;
        
        switch(operator) // Verificar el operador que se está usando
        {
            case '+': result = v1 + v2; break; 
            case '-': result = v1 - v2; break;
            case '*': result = v1 * v2; break;
            case '÷': result = v1 / v2; break;
            case '%': result = v1 % v2; break;
        }
        
        
        // Mostrar textos
        DecimalFormat decimalFormat = new DecimalFormat("0.######"); // Formatear el numero
        String format = decimalFormat.format(result);
        
        txtResult.setText(n1 + " " + operator + " " + n2 + " ="); 
        txfScreen.setText(format);
        
        // Resetear
        n1 = format;
        n2 = "";
        operator = '\0';
    }
    
    @FXML
    private void onClearAll(ActionEvent event) // Resetear todos los valores
    {
        reset();
    }
    
    @FXML
    private void onClearEntry(ActionEvent event) // Limpiar la operacion actual
    {
        if(!n2.isEmpty()) n2 = ""; // Limpiar "n2" si existe, sino...
        else if(operator != '\0') operator = '\0'; // Limpiar el operador si existe, sino...
        else n1 = ""; // Limpiar "n1"
        
        drawScreen();
    }
    
    
    @FXML
    private void onKeyPressed(KeyEvent event) 
    {
        switch(event.getCode())
        {
            case NUMPAD0:
            case DIGIT0: 
            {
                if(event.isShiftDown()) btn_equals.fire();
                else btn_n0.fire();
                break;
            }
            
            case NUMPAD1:
            case DIGIT1: btn_n1.fire(); break;
            
            case NUMPAD2:
            case DIGIT2: btn_n2.fire(); break;
            
            case NUMPAD3:
            case DIGIT3: btn_n3.fire(); break;
            
            case NUMPAD4:
            case DIGIT4: btn_n4.fire(); break;
            
            case NUMPAD5: btn_n5.fire(); break;
            case DIGIT5: 
            {
                if(event.isShiftDown()) btn_mod.fire(); 
                else btn_n5.fire();
                break;
            }
            
            case NUMPAD6:
            case DIGIT6: btn_n6.fire(); break;
            
            case NUMPAD7: btn_n7.fire(); break;
            case DIGIT7: 
            {
                if(event.isShiftDown()) btn_div.fire();
                else btn_n7.fire();
                break;
            }
            
            case NUMPAD8:
            case DIGIT8: btn_n8.fire(); break;
            
            case NUMPAD9:
            case DIGIT9: btn_n9.fire(); break;
            
            case COMMA: btn_comma.fire(); break;
            case PLUS: 
            {
                if(event.isShiftDown()) btn_mul.fire();
                else btn_plus.fire();
                break;
            } 
            case MINUS: btn_minus.fire(); break;
            
            //
            case BACK_SPACE:
            {
                if(!n2.isEmpty()) n2 = n2.substring(0, n2.length() - 1);
                else if(operator != '\0') operator = '\0';
                else if(!n1.isEmpty()) n1 = n1.substring(0, n1.length() - 1);
                
                drawScreen();
            }   
        }
    }
    
    
    // Utils
    private void drawScreen() // "Dibuja" la operacion en pantalla
    {
        String text = "0";
        
        if(!n1.isEmpty()) text = n1;
        if(operator != '\0') text += " " + operator;
        if(!n2.isEmpty()) text += " " + n2;
        
        txfScreen.setText(text);
    }
    
    private void reset() // Resetea todos los valores a default
    {
        n1 = n2 = "";
        operator = '\0';
        
        drawScreen();
        txtResult.setText("");
    }
}
