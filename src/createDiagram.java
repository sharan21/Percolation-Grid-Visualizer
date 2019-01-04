import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Container;
import java.util.Scanner;

public class createDiagram implements ActionListener {


    public int sizeOfDiagram = 6;
    boolean[][] nodesOfDiagram = new boolean[sizeOfDiagram][sizeOfDiagram];
    Container grid = new Container();

    ArrayList<Boolean> sendToModel = new ArrayList<>();
    ArrayList<Integer> nodesToUpdate = new ArrayList<>();




    JFrame frame = new JFrame("Map");
    JButton reset = new JButton("reset");
    JButton[][] buttons = new JButton[sizeOfDiagram][sizeOfDiagram];

    public createDiagram(int size){
        sizeOfDiagram = size;
        initializeFrameAndButtons(); // with the sizeOfDiagram
        initializeNodes();


    }
    public createDiagram(){
        initializeFrameAndButtons();
        initializeNodes();
    }


    private void initializeFrameAndButtons(){

        System.out.println("Initializing the diagram...");
        frame.setSize(400,400);
        frame.setLayout(new BorderLayout());
        frame.add(reset, BorderLayout.NORTH);
        reset.addActionListener(this);

        grid.setLayout(new GridLayout(sizeOfDiagram,sizeOfDiagram));

        for(int a=0;a <this.sizeOfDiagram; a++) {
            for (int b = 0; b < this.sizeOfDiagram; b++) {
                buttons[a][b] = new JButton();
                buttons[a][b].addActionListener(this);
                buttons[a][b].setText("X");
                grid.add(buttons[a][b]);
            }
        }
        frame.add(grid,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
   public static void main(String args[]){

        //initialize the default diagram
        createDiagram thisDiagram = new createDiagram();


    }


    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource().equals(reset)) { // fix reset

            System.out.println("Reset was hit...");
            nodesOfDiagram = new boolean[sizeOfDiagram][sizeOfDiagram];
            initializeFrameAndButtons();
            grid = new Container();

        }

        else { // a button was hit
            for(int x=0;x<sizeOfDiagram;x++){
                for(int y=0;y<sizeOfDiagram;y++){

                    if(event.getSource().equals(buttons[x][y])){ // Checking if button has been pressed
                        System.out.println("A button was hit");
                        // mark the nodesOfDiagram as Hit or True
                        nodesOfDiagram[x][y] = true;
                        buttons[x][y].setText("");
                        addNodesToUpdate(x*sizeOfDiagram+y);
                        arrayListConversion(x, y);


                    }
                }
            }
        }

    }


    public void arrayListConversion(int a, int b){ // convert boolean array to array list to port to percolate model


        sendToModel.set(a*sizeOfDiagram+b, true);

        //System.out.println("Finished converting to Array list...");
        //System.out.println(sendToModel);


    }
    public void addNodesToUpdate(int x){
        nodesToUpdate.add(x);
    }

    public void initializeNodes(){

        for (int i = 0; i < sizeOfDiagram; i++) {
            for (int j = 0; j < sizeOfDiagram; j++) {
                sendToModel.add(i*sizeOfDiagram+j, false);

            }

        }
        /*for (int i = 0; i < sizeOfDiagram; i++) {
            sendToModel.set(i, true);


        }
        for (int i = (sizeOfDiagram)*(sizeOfDiagram-1); i < (int)Math.pow(sizeOfDiagram,2); i++) {
            sendToModel.set(i, true);

        }*/
    }




    // TO DO: DEFINE FUNCTION TO PERFORM ACTION WHEN BUTTON IS PRESSED AND LINK IT TO PERCOLATE MODEL
}
