import java.util.Random;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;

public class Main extends Application
{
    int[] currentArray;
    VBox root;

    //Initializing GUI
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Sorts");
        primaryStage.getIcons().add(new Image("icon.png"));
        initRoot();
        primaryStage.setScene(new Scene(root));

        primaryStage.show();
    }

    //GUI components
    private void initRoot()
    {
        //VBox with rows of HBox's containing the components
        root = new VBox(25);
        root.setPadding(new Insets(20));
        root.setPrefSize(800, 600);
        root.getChildren().add(getGenerateButtons());
        root.getChildren().add(Helpers.displayText("Current: not initialized", null, Color.PINK));
        root.getChildren().add(getSortButtons());
    }

    //Options for generating arrays from 100 to 1,000,000
    private HBox getGenerateButtons()
    {
        HBox arrayOptions = new HBox(20);
        arrayOptions.setAlignment(Pos.CENTER);

        Button generateButton1 = new Button("Generate Array [100]");
        generateButton1.setPrefWidth(175);
        generateButton1.setOnMouseClicked(
            (MouseEvent me) -> {
                currentArray = generateArray(100);
            }
        );

        Button generateButton2 = new Button("Generate Array [10,000]");
        generateButton2.setPrefWidth(175);
        generateButton2.setOnMouseClicked(
            (MouseEvent me) -> {
                currentArray = generateArray(100000);
            }
        );

        Button generateButton3 = new Button("Generate Array [1,000,000]");
        generateButton3.setPrefWidth(175);
        generateButton3.setOnMouseClicked(
            (MouseEvent me) -> {
                currentArray = generateArray(100000000);
            }
        );

        arrayOptions.getChildren().addAll(generateButton1, generateButton2, generateButton3);

        return arrayOptions;
    }

    private HBox getSortButtons()
    {
        HBox arrayOptions = new HBox(20);
        arrayOptions.setAlignment(Pos.CENTER);

        Button mergeButton = new Button("Merge Sort");
        mergeButton.setPrefWidth(175);
        mergeButton.setOnMouseClicked(
            (MouseEvent me) -> {
                if(currentArray != null)
                {
                    sort("merge", "n log n");
                }
            }
        );

        Button selectionButton = new Button("Selection Sort");
        selectionButton.setPrefWidth(175);
        selectionButton.setOnMouseClicked(
            (MouseEvent me) -> {
                if(currentArray != null)
                {
                    sort("selection", "n^2");
                }
            }
        );

        Button insertionButton = new Button("Insertion Sort");
        insertionButton.setPrefWidth(175);
        insertionButton.setOnMouseClicked(
            (MouseEvent me) -> {
                if(currentArray != null)
                {
                    sort("insertion", "n^2");
                }
            }
        );

        arrayOptions.getChildren().addAll(mergeButton, selectionButton, insertionButton);

        return arrayOptions;
    }

    //Generates an array from 0 to the passed length
    private int[] generateArray(int length)
    {
        int[] array = new int[length];

        for(int i = 0; i < length; i++)
        {
            array[i] = i;
        }

        shuffle(array);

        //Updating 'Current: ' text which is at root[1]
        Platform.runLater(
            () -> { 
                root.getChildren().remove(1);
                root.getChildren().add(1, Helpers.displayText("Current: Randomized array with length of " + currentArray.length, null, Color.PINK));
        });

        return array;
    }

    //Shuffles array
    //Source: https://www.programcreek.com/2012/02/java-method-to-shuffle-an-int-array-with-random-order/
    private void shuffle(int[] array){
		Random rgen = new Random();  // Random number generator			
 
		for (int i = 0; i < array.length; i++) {
		    int randomPosition = rgen.nextInt(array.length);
		    int temp = array[i];
		    array[i] = array[randomPosition];
		    array[randomPosition] = temp;
		}
     }
    
    private void sort(String type, String efficiency)
    {
        double startTime = System.nanoTime()/1000000000.0;

        //Determing type of sort
        Sort sort;
        if(type.equals("merge"))
        {
            sort = new MergeSort();
        }
        else if(type.equals("selection"))
        {
            sort = new SelectionSort();
        }
        else
        {
            sort = new InsertionSort();
        }

        sort.sort(currentArray);

        double endTime = System.nanoTime()/1000000000.0;
        double timeElasped = endTime - startTime;

        Platform.runLater(
            () -> {
                //Remove previous sorting results if applicable
                if(root.getChildren().size() > 3)
                {
                    while(root.getChildren().size() != 3)
                    {
                        root.getChildren().remove(3);
                    }
                }
                if(Helpers.isSorted(currentArray))
                {
                    root.getChildren().add(Helpers.displayText("Success", null, Color.LIGHTGREEN));
                    root.getChildren().add(Helpers.displayText("Big O notation of Sort: " + efficiency, "Actual Time of Sort: " + timeElasped + " seconds", Color.LIGHTGREEN));
                }
                else
                {
                    root.getChildren().add(Helpers.displayText("Failure", null, Color.PINK));
                }

                //Re-randomize array
                shuffle(currentArray);
            }
            );
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}