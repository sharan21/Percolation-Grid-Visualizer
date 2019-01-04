import java.awt.*;
import java.util.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class percolateModel {

    Scanner in = new Scanner(System.in);

    public final int numberOfNodes = 6;

    private final int startPoint = (int)Math.pow(numberOfNodes,2);
    private final int endPoint = (int)Math.pow(numberOfNodes,2)+1;

    final int numberOfInputs = 10; //needed only while using input lists


    createDiagram thisDiagram = new createDiagram(numberOfNodes);

    ArrayList<Integer> parentOfNode = new ArrayList<>();

    ArrayList<Integer> inputList = new ArrayList<>();

    ArrayList<Integer> rootOf = new ArrayList<>();


    public percolateModel(){
        initializeGraph();

    }

    public void initializeGraph(){

        for (int i = 0; i < Math.pow(numberOfNodes,2); i++) {
            parentOfNode.add(i);
            rootOf.add(i);

        }
        parentOfNode.add(startPoint);
        rootOf.add(startPoint); // root of start = start; terminating node
        parentOfNode.add(endPoint);
        rootOf.add(endPoint); // root of end = end; terminating node
        System.out.println("Initialization complete...");
    }

    public static void main(String args[]){


        percolateModel thisGraph = new percolateModel(); // Initialize the graph
        thisGraph.initializeVirtualEndPoints();
        //thisGraph.printparentOfNodes();
        //thisGraph.getArrayFromDiagram();

        while (true) {
            thisGraph.getArrayFromDiagram();
            thisGraph.checkAndUpdateGraph();
            thisGraph.printparentOfNodes();
            thisGraph.updateComponents();

            thisGraph.percolationCheck();

            if(thisGraph.percolationCheck()== true){
                break;
            }


            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }



        //thisGraph.defineList(); // Get the inputs


    }

    public void defineList(){// If you are not using createDiagram Object

        System.out.println("Ready to enter" + numberOfInputs + "values");

        for (int i = 0; i < numberOfInputs; i++) {
            System.out.println((i+1)+".");
            inputList.add(in.nextInt());
            System.out.print(" and ");
            inputList.add(in.nextInt());


        }



    }

    public void updateComponents(){

        //System.out.println(parentOfNode);

        for (int i = 0; i < parentOfNode.size(); i++) {

            int temp = i;
            while (parentOfNode.get(temp) != temp) {
                rootOf.set(i, parentOfNode.get(temp)); // 'i' is fixed as we are only finding root of i
                // go deeper
                temp = parentOfNode.get(temp);
                //System.out.println(parentOfNode);

            }


        }
        //System.out.println("connected components list...");
        //System.out.println(rootOf);


    }

    public void getArrayFromDiagram(){
        System.out.println("printing the array from createDiagram");
        System.out.println(thisDiagram.sendToModel);
        System.out.println("Nodes to update");
        System.out.println(thisDiagram.nodesToUpdate);




    }

    public void checkAndUpdateGraph(){// take thisDiagram.sendToModel and update the parentOfNode list
        while(thisDiagram.nodesToUpdate.size()!=0){
            //Pop the latest node

            int currentNode = thisDiagram.nodesToUpdate.get(0);
            System.out.println("Updating the neighbours of node" + currentNode);


            try{
            if(thisDiagram.sendToModel.get(currentNode+1)!= false && !isConnected(currentNode,currentNode-1)) {
                System.out.print("true");
                connectTwoNodes(currentNode, currentNode + 1);
            }
            }
            catch (IndexOutOfBoundsException e){

            }
            try{
            if(thisDiagram.sendToModel.get(currentNode-1)!= false && !isConnected(currentNode,currentNode-1)) {
                System.out.print("true");
                connectTwoNodes(currentNode, currentNode - 1);
            }
            }
            catch (IndexOutOfBoundsException e){

            }

            try {
                if (thisDiagram.sendToModel.get(currentNode + 4) != false && !isConnected(currentNode,currentNode-1) ) {
                    System.out.print("true");
                    connectTwoNodes(currentNode, currentNode + 4);
                }
            }
            catch (IndexOutOfBoundsException e){

            }
            try {
                if (thisDiagram.sendToModel.get(currentNode - 4) != false && !isConnected(currentNode,currentNode-1) ) {
                    System.out.print("true");
                    connectTwoNodes(currentNode, currentNode - 4);
                }
            }
            catch (IndexOutOfBoundsException e){

            }

            thisDiagram.nodesToUpdate.remove(0);

        }





    }

    private void initializeVirtualEndPoints(){
        for (int i = 0; i < numberOfNodes; i++) {
            parentOfNode.set(i,startPoint);
            //thisDiagram.sendToModel.set(i, true);


        }
        /*for (int i = (numberOfNodes)*(numberOfNodes-1); i < (int)Math.pow(numberOfNodes,2); i++) {
            parentOfNode.set(i,endPoint);
            //thisDiagram.sendToModel.set(i, true);

        }*/

    }
    public void printparentOfNodes(){
        System.out.println(parentOfNode);
        System.out.println(thisDiagram.nodesToUpdate);

    }

    public void connectTwoNodes(int b, int a){

        parentOfNode.set(b, a);
        //rootOf.set(a, rootOf.get(b));



    }

    public boolean isConnected(int firstNode, int secondNode){
        return (rootOf.get(firstNode)==rootOf.get(secondNode));
    }

    public boolean percolationCheck(){// check the last row of nodes if they have root as start node
        boolean hasPercolated = false;
        for (int i = (numberOfNodes)*(numberOfNodes-1); i < (int)Math.pow(numberOfNodes,2); i++) {
            if(rootOf.get(i) == this.startPoint){
                hasPercolated = true;
                System.out.print(" MODEL HAS PERCOLATION!!!");
                break;

            }

        }
        return hasPercolated;

    }









}
