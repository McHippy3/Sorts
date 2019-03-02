import java.util.Random;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class Helpers
{
    //Returns a VBox containing up to two pieces of text
    public static VBox displayText(String str1, String str2, Color color)
    {
        VBox container = new VBox(5);
        container.setPadding(new Insets(15));
        container.setAlignment(Pos.CENTER);
        container.setBackground(new Background(new BackgroundFill(color, CornerRadii.EMPTY, Insets.EMPTY)));

        Text t1 = new Text(str1);
        t1.setFont(Font.font(15));
        container.getChildren().add(t1);

        if(str2 != null)
        {
            Text t2 = new Text(str2);
            t2.setFont(Font.font(15));
            container.getChildren().add(t2);
        }

        return container;
    }

    //Returns true if the array is sorted correctly
    public static boolean isSorted(int[] array)
    {
        int prevInt = array[0];
        for(int i = 1; i < array.length; i++)
        {
            if(array[i] <= prevInt)
            {
                return false;
            }

            prevInt = array[i];
        }
        return true;
    }
}