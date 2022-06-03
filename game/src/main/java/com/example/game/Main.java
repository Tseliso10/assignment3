package com.example.game;

import javafx.animation.AnimationTimer;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.util.StringConverter;

public class Main extends Application {
    TranslateTransition trans=new TranslateTransition();

    TranslateTransition trans1=new TranslateTransition();

    TranslateTransition trans2=new TranslateTransition();

    TranslateTransition trans3=new TranslateTransition();

    TranslateTransition trans4=new TranslateTransition();




    public ImageView createCloud(Scene scene) {
        ImageView cloud=new ImageView(new Image("cloud5.png"));

        cloud.setFitHeight(60);
        cloud.setFitWidth(60);
        cloud.setY(scene.getHeight() - cloud.getFitHeight());
        cloud.setX(1700);
        cloud.setY(300);
        trans.setNode(cloud);
        trans.setDuration(Duration.millis(7000));
        trans.setByX(-1800);
        trans.setCycleCount(Integer.MAX_VALUE);
        trans.play();

        return cloud;

    }
    private ImageView createCloud1(Scene scene) {
        ImageView cloud1=new ImageView(new Image("cloud2.png"));
        cloud1.setFitHeight(60);
        cloud1.setFitWidth(60);
        cloud1.setX(1700);
        cloud1.setY(450);
        trans1.setNode(cloud1);
        trans1.setDuration(Duration.millis(6000));
        trans1.setByX(-1800);
        trans1.setCycleCount(Integer.MAX_VALUE);
        trans1.play();

        return cloud1;

    }
    private ImageView createCloud2(Scene scene) {
        ImageView cloud2=new ImageView(new Image("cloud4.png"));
        cloud2.setFitHeight(60);
        cloud2.setFitWidth(60);
        cloud2.setX(1700);
        cloud2.setY(600);
        trans2.setNode(cloud2);
        trans2.setDuration(Duration.millis(7000));
        trans2.setByX(-1800);
        trans2.setCycleCount(Integer.MAX_VALUE);


        trans2.play();

        return cloud2;

    }
    private ImageView createCloud3(Scene scene) {
        ImageView cloud3=new ImageView(new Image("cloud3.png"));
        cloud3.setFitHeight(60);
        cloud3.setFitWidth(60);
        cloud3.setX(1700);
        cloud3.setY(150);
        trans3.setNode(cloud3);
        trans3.setDuration(Duration.millis(5000));
        trans3.setByX(-1800);
        trans3.setCycleCount(Integer.MAX_VALUE);
        trans3.play();

        return cloud3;

    }
    private ImageView createCloud4(Scene scene) {
        ImageView cloud4=new ImageView(new Image("cloud1.png"));
        cloud4.setFitHeight(60);
        cloud4.setFitWidth(60);
        cloud4.setX(1700);
        trans4.setNode(cloud4);
        trans4.setDuration(Duration.millis(3000));
        trans4.setByX(-1800);
        trans4.setByY(0);
        trans4.setCycleCount(Integer.MAX_VALUE);
        trans4.play();

        return cloud4;


    }

    private ImageView createPlane(Scene scene) {
        ImageView image = new ImageView(new Image("helicopter.png"));
        image.setFitWidth(200);
        image.setFitHeight(80);
        image.setY(scene.getHeight() - image.getFitHeight());

        return image;
    }



    @Override
    public void start(Stage stage) {

        Pane root = new Pane();

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());

        ImageView myplane = createPlane(scene);
        ImageView cloud1=createCloud(scene);
        ImageView cloud2=createCloud1(scene);
        ImageView cloud3=createCloud2(scene);
        ImageView cloud4=createCloud3(scene);
        ImageView cloud5=createCloud4(scene);

        Text  text=new Text();
        text.setX(300.0f);
        text.setY(50.0f);
        text.setCache(true);
        text.setText("");
        text.setFill(Color.RED);
        text.setFont(Font.font(null, FontWeight.BOLD,70));
        text.setTranslateY(100);
        text.setTranslateX(100);

        root.getChildren().addAll(myplane, cloud1,cloud2,cloud3,cloud4,cloud5,text);


        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            double y = myplane.getY();
            double x = myplane.getX();

            switch (event.getCode()){
                case UP -> myplane.setY(y-15);
                case DOWN -> myplane.setY(y+15);
                case LEFT-> myplane.setX(x-15);
                case RIGHT -> myplane.setX(x+15);
            }
            AnimationTimer at = new AnimationTimer(){
                @Override
                public void handle(long l) {
                    checkat(cloud1, cloud2, cloud3,cloud4,cloud5 );
                }

                private void checkat(ImageView cloud1, ImageView cloud2, ImageView cloud3, ImageView cloud4, ImageView cloud5) {


                    if(     myplane.getBoundsInParent().intersects(cloud1.getBoundsInParent()) ||
                            myplane.getBoundsInParent().intersects(cloud2.getBoundsInParent()) ||
                            myplane.getBoundsInParent().intersects(cloud4.getBoundsInParent()) ||
                            myplane.getBoundsInParent().intersects(cloud4.getBoundsInParent()) ||
                            myplane.getBoundsInParent().intersects(cloud5.getBoundsInParent())
                    )

                    {
                        trans.stop();
                        trans1.stop();
                        trans2.stop();
                        trans3.stop();
                        trans4.stop();

                        text.setText("GAME OVER");
                        myplane.setImage(new Image("ex.png"));



                        scene.addEventFilter(KeyEvent.KEY_PRESSED, event1 -> {
                            switch (event.getCode()){
                                case UP -> myplane.setY(y - 0);
                                case DOWN -> myplane.setY(y +0);
                                case LEFT-> myplane.setX(x-0);
                                case RIGHT -> myplane.setX(x+0);

                            }
                        });
                    }
                }
            };
            at.start();
        });

        stage.setTitle("PLANE CRASH");

        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }
}