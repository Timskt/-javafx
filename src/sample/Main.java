package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/***
 @author sky
 @date 2019/12/29
 @version 1.0
 */
public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("计算器");
        primaryStage.getIcons().add(new Image("http://ww4.sinaimg.cn/bmiddle/9150e4e5gy1gadbg21uotj20j60j6jsi.jpg"));
        primaryStage.centerOnScreen();
        primaryStage.setScene(new Scene(root, 600, 755));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
