/* *****************************************************************************
 *  Name:    	 Bora ÇOBAN
 *  Department:  Computer Engineering
 *  Description:  Design the calculator on the Windows 10 level with JavaFX.    
 **************************************************************************** */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class Question1 extends Application
{

   static StringBuilder sb=new StringBuilder();
    static TextField textField=new TextField();
    static  TextField textFieldEkran=new TextField();
    static String operation=null;
    static String memory=null;
    static String numberOfTransactions=null;

    public static void main(String[] args)
    {
        Application.launch(args);
    }
    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage stage)
    {
        GridPane root=new GridPane();

        root.setVgap(20);
        root.setHgap(20);
        root.setPadding(new Insets(50,0,70,0));
        root.setAlignment(Pos.TOP_CENTER);

        textField.setPrefSize(220,50);
        textField.setAlignment(Pos.BASELINE_RIGHT);
        textField.setFont(Font.font("Verdana", FontWeight.BOLD,18));
        root.add(textField,1,1);

        textFieldEkran.setPrefSize(220,50);
        textFieldEkran.setAlignment(Pos.BASELINE_RIGHT);
        textFieldEkran.setFont(Font.font("Verdana", FontWeight.BOLD,18));
        textFieldEkran.setDisable(true);
        root.add(textFieldEkran,1,0);

        FlowPane flowPane=new FlowPane(Orientation.HORIZONTAL,6,10);
        flowPane.setPrefSize(220,220);

        flowPane.setAlignment(Pos.TOP_CENTER);

        String[] buttons={"MC","MR","M+","M-","%","CE","C","<-","/","7","8","9",
        		"*","4","5","6","-","1","2","3","+","±","0",",","x²","1/x","√","="};
        for(String button:buttons){

            Button btn;
            if(isNumeric(button)) {
                 btn = new Button(button);
                btn.setPrefSize(50, 50);
                btn.setTextFill(Color.WHITE);
                btn.setStyle("-fx-background-color:#FF9100");
                btn.setId(button);
                btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        btn.setEffect(new Bloom());
                        btn.setTextFill(Color.BLACK);

                    }
                });

                btn.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        btn.setEffect(null);
                    }
                });

            }
            else
            {

                 btn = new Button(button);
                btn.setPrefSize(50, 50);
                btn.setTextFill(Color.BLACK);
                btn.setStyle("-fx-background-color:aqua");
                btn.setId(button);

                btn.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        DropShadow dropShadow=new DropShadow();
                        dropShadow.setColor(Color.GAINSBORO);
                        btn.setEffect(dropShadow);

                    }
                });

                btn.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        btn.setEffect(null);
                    }
                });
            }

            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    if(isNumeric(button)) {
                      ekranaYaz(button);
                    }
                    else
                    {
                        switch (button){
                            case  "√":
                        	operation="kök";
                        	numberOfTransactions=textField.getText().trim();
                            textFieldEkran.setText(numberOfTransactions);
                            sb.delete(0,sb.length());
                            break;
                            case  "x²":
                            	operation="kare";
                            	numberOfTransactions=textField.getText().trim();
                                textFieldEkran.setText(numberOfTransactions);
                                sb.delete(0,sb.length());
                                break;
                            case  "1/x":
                            	operation="böl";
                            	numberOfTransactions=textField.getText().trim();
                                textFieldEkran.setText(numberOfTransactions);
                                sb.delete(0,sb.length());
                                break;
                            case  "%":
                            	operation="kalan";
                            	numberOfTransactions=textField.getText().trim();
                                textFieldEkran.setText(numberOfTransactions);
                                sb.delete(0,sb.length());
                                textField.setText("");
                                break;
                        
                            case "CE":
                                sb.delete(0,sb.length());
                                memory="0";
                                ekranaYaz(sb.toString());
                                break;


                            case "C":
                               memory=sb.toString();
                               sb.delete(0,sb.length());
                               textField.setText(sb.toString());
                            case "<-":
                                if(sb.length()>1){
                                sb.deleteCharAt(sb.length()-1);
                                textField.setText(sb.toString());}
                                else
                                {
                                    sb.delete(0,1);
                                    if(sb.length()==0){
                                        textField.setText("0");
                                    }

                                }
                                break;

                            case "/":
                            	operation="bolme";
                            	numberOfTransactions=textField.getText().trim();
                                textFieldEkran.setText(numberOfTransactions);
                                sb.delete(0,sb.length());
                                textField.setText("");
                                break;


                            case "*":

                            	operation="carpma";
                            	numberOfTransactions=textField.getText().trim();
                                textFieldEkran.setText(numberOfTransactions);
                                sb.delete(0,sb.length());
                                textField.setText("");
                                break;

                            case "+":

                            	operation="toplama";
                            	numberOfTransactions=textField.getText().trim();
                                textFieldEkran.setText(numberOfTransactions);
                                sb.delete(0,sb.length());
                                textField.setText("");
                                break;

                            case "-":

                            	operation="cikarma";
                            	numberOfTransactions=textField.getText().trim();
                                textFieldEkran.setText(numberOfTransactions);
                                sb.delete(0,sb.length());
                                textField.setText("");
                                break;

                            case "±":
                                if(!sb.toString().contains("-")) {
                                    sb.insert(0, "-");
                                    textField.setText(sb.toString());
                                }
                                else{
                                    sb.deleteCharAt(0);
                                    textField.setText(sb.toString());
                                }
                                break;

                            case ",":

                                if(!sb.toString().contains(".")) {
                                    sb.append(".");
                                    textField.setText(sb.toString());
                                }
                                else{
                                    int index=sb.indexOf(".");
                                   sb.delete(index,sb.length());
                                    textField.setText(sb.toString());
                                }

                                break;

                            case "=":

                                try {
                                  Double ValueOfMemory = Double.parseDouble(numberOfTransactions);

                                 Double ValueOfScreen =Double.parseDouble(textField.getText());

                                  switch (operation) {
                                       case "böl":
                                          	textField.setText(String.valueOf(1/ValueOfScreen));
                                           break;
                                       case "kök":
                                           	textField.setText(String.valueOf(Math.sqrt(ValueOfScreen)));
                                            break;
                                        case "kare":
                                        	textField.setText(String.valueOf(Math.pow(ValueOfScreen, 2)));
                                            break; 
                                        case "kalan":
                                        	textField.setText(String.valueOf(ValueOfMemory % ValueOfScreen));
                                            sb.append(textField.getText());
                                            break; 
                                  
                                        case "bolme":
                                            textField.setText(String.valueOf(ValueOfMemory / ValueOfScreen));
                                            sb.append(textField.getText());
                                            break;
                                        case "carpma":
                                            textField.setText(String.valueOf(ValueOfMemory * ValueOfScreen));
                                            sb.append(textField.getText());
                                            break;
                                        case "toplama":
                                            textField.setText(String.valueOf(ValueOfMemory + ValueOfScreen));
                                            sb.append(textField.getText());
                                            break;
                                        case "cikarma":
                                            textField.setText(String.valueOf(ValueOfMemory - ValueOfScreen));
                                            sb.append(textField.getText());
                                            break;
                                        default:
                                            break;
                                    }

                                }catch (Exception ex){
                                    System.out.println(ex.getMessage());
                                }
                                break;



                                default:
                                    break;

                        }
                    }


                }
            });

            flowPane.getChildren().add(btn);



        }

        root.add(flowPane,1,2);
        Label label=new Label("Windows 10 Calculator");
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-text-fill:#fff;-fx-font-size:10;");

        root.add(label,1,3);

        root.setStyle("-fx-background-color:lightgray");
        Scene scene=new Scene(root,1200,800);

            stage.setScene(scene);
            stage.setTitle("Hesap Makinası");

        stage.show();
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    public static double sayiyaCevir(String s){

       return 20d;
    }

    public static void ekranaYaz(String s){
        if(s!=null){
        sb.append(s);
        textField.setText(sb.toString());}
        else
        {
            sb=sb.delete(0,sb.length());
            textField.setText(sb.toString());
        }
    }
}
