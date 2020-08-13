package sample;

import caculate.DataTime;
import caculate.Test;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

/***
 @author sky
 @date 2019/12/29
 @version 1.0
 */

public class Controller implements Initializable {


    private DataTime dataTime = new DataTime();
    public String expression = "";
    public String result="";
    @FXML
    private TextField dataResult;
    @FXML
    private Button bt;
    @FXML
    private TextField re;
    @FXML
    private DatePicker data1;
    @FXML
    private DatePicker data2;
    @FXML
    private TextField ex;
    private Object ChangeListener;

    @FXML
    public void dataButton(){

    }
    @FXML
    public void division(){
        expression = expression+"/";
        ex.setText(expression);
    }
    @FXML
    public void sub(){
        expression = expression+"-";
        ex.setText(expression);
    }
    @FXML
    public void add(){
        expression = expression+"+";
        ex.setText(expression);
    }
    @FXML
    public void multiplication(){
        expression = expression+"*";
        ex.setText(expression);
    }
    @FXML
    public void bt1(){
        expression = expression+"1";
        ex.setText(expression);
    }
    @FXML
    public void bt2(){
        expression = expression+"2";
        ex.setText(expression);
    }

    @FXML
    public void bt3(){
        expression = expression+"3";
        ex.setText(expression);
    }

    @FXML
    public void bt4(){
        expression = expression+"4";
        ex.setText(expression);
    }
    @FXML
    public void bt5(){
        expression = expression+"5";
        ex.setText(expression);
    }

    @FXML
    public void bt6(){
        expression = expression+"6";
        ex.setText(expression);
    }

    @FXML
    public void bt7(){
        expression = expression+"7";
        ex.setText(expression);
    }

    @FXML
    public void bt8(){
        expression = expression+"8";
        ex.setText(expression);
    }

    @FXML
    public void bt9(){
        expression = expression+"9";
        ex.setText(expression);
    }
    @FXML
    public void bt0(){
        expression = expression+"0";
        ex.setText(expression);
    }

    @FXML
    //撤回
    public void countermand(){
        String temp=ex.getText();
        String temp2="";
        if(temp.length()>0)
           temp2=ex.getText(0,temp.length()-1);
        expression=temp2;
        ex.setText(temp2);
    }
    @FXML
    public void alldelet(){
        expression="";
        ex.setText(expression);
    }
    @FXML
    public void dot(){
        expression=expression+".";
        ex.setText(expression);
    }
    @FXML
    public void blacketed1(){
        expression=expression+"(";
        ex.setText(expression);
    }
    @FXML
    public void blacketed2(){
        expression=expression+")";
        ex.setText(expression);
    }
    @FXML
    public void showResult(){
        Test test = new Test();
        result=test.Calculate(ex.getText());
        re.setText(result);
    }
    @FXML
    public void Money(){
        Stage stage = new Stage();
//        textField.setBackground();
        ImageView imageView = new ImageView(new Image("/pic/支付宝.jpg"));
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(imageView);
        Scene scene = new Scene(anchorPane);
        stage.setTitle("小钱钱");
        stage.setScene(scene);
        stage.setWidth(650);
        stage.setHeight(800);
        stage.show();
    }
    @FXML
    public void About(){
        TextArea textArea = new TextArea("开发者:Sky\n\n版本号:1.0\n\n");
        textArea.setDisable(true);
        Stage stage = new Stage();
        stage.setTitle("关于");
        ImageView imageView = new ImageView(new Image("/pic/支付宝.jpg"));
        AnchorPane anchorPane = new AnchorPane();
        //anchorPane.getChildren().addAll(imageView,textArea);
        anchorPane.getChildren().addAll(textArea);
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void Heple(){
        Stage stage = new Stage();
        TextArea textArea = new TextArea("本程序支持+ - * / sin cos tan ^计算(支持直接在表达式框输入表达式)同时也支持日期计算");
        textArea.setDisable(true);
        textArea.setFont(Font.font(15));
        textArea.setStyle("-fx-text-fill:red" +
                "");
//        textField.setBackground();
//        ImageView imageView = new ImageView(new Image("http://ww3.sinaimg.cn/bmiddle/9150e4e5gy1gadbg4611uj20j60j6ac7.jpg"));
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(textArea);
        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.setTitle("帮助");
        stage.show();
    }
    @FXML
    public void Blog() throws IOException {
        Runtime.getRuntime().exec("cmd /c start https://blog.csdn.net/qq_43821834/article/details/100601034");
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data1.setValue(LocalDate.now());
        data2.setValue(LocalDate.now());
        data1.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate date, LocalDate t1) {
                dataTime.DataTime(data1.getValue(),data2.getValue());
                dataResult.setText("相差"+dataTime.getYear()+"年"+"  "+dataTime.getMonth()+"月"+"  "+dataTime.getWeek()+"周"+"  "+dataTime.getDay()+"天"+"  "+"一共相差"+dataTime.getAllday()+"天");
//                dataResult.setText("相差"+dataTime.getDay()+"天");
            }
        });
        data2.valueProperty().addListener(new ChangeListener<LocalDate>() {
            @Override
            public void changed(ObservableValue<? extends LocalDate> observableValue, LocalDate date, LocalDate t1) {
                dataTime.DataTime(data1.getValue(),data2.getValue());
                dataResult.setText("相差"+dataTime.getYear()+"年"+"  "+dataTime.getMonth()+"月"+"  "+dataTime.getWeek()+"周"+"  "+dataTime.getDay()+"天"+"  "+"一共相差"+dataTime.getAllday()+"天");
//                dataResult.setText("相差"+dataTime.getDay()+"天");
            }
        });
    }

}
