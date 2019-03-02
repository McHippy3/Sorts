import java.util.Random;

import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.Pos;

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

    //Generates an array from 0 to the passed length
    public static int[] generateArray(int length)
    {
        int[] array = new int[length];

        for(int i = 0; i < length; i++)
        {
            array[i] = i;
        }

        shuffle(array);

        return array;
    }

    //Shuffles array
    //Source: https://www.programcreek.com/2012/02/java-method-to-shuffle-an-int-array-with-random-order/
    public static void shuffle(int[] array){
		Random rgen = new Random();  // Random number generator			
 
		for (int i = 0; i < array.length; i++) {
		    int randomPosition = rgen.nextInt(array.length);
		    int temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
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