import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
public class output {
    
 static ArrayList<Integer> executedBlocks = new ArrayList<Integer>();
public static void main(String[] args) throws Exception { // block number 0
executedBlocks.add(0);

        System.out.println("hello world");
        boolean a = true;
        boolean b = false;

        if (a) { // block number 1
executedBlocks.add(1);
            System.out.println("a is true");
        }


        if (b) { // block number 2
executedBlocks.add(2);
            System.out.println("b is true");
        } else { // block number 3
executedBlocks.add(3);
            System.out.println("b is false");
        }
        for(int i = 0 ; i < 3  ;i++)
        { // block number 4
executedBlocks.add(4);
            System.out.println("hello");
        }

        int x = 10;
        int y = 5;
        int z = 2;

        if (x > y) { // block number 5
executedBlocks.add(5);
            if (x > z) { // block number 6
executedBlocks.add(6);
                System.out.println("x is the largest number");
            } else { // block number 7
executedBlocks.add(7);
                System.out.println("z is the largest number");
            }
        } else { // block number 8
executedBlocks.add(8);
            if (y > z) { // block number 9
executedBlocks.add(9);
                System.out.println("y is the largest number");
            } else { // block number 10
executedBlocks.add(10);
                System.out.println("z is the largest number");
            }
        }
        int var = 1; // initialize the loop counter

        while ( var <= 5) { // block number 11
executedBlocks.add(11); // specify the condition
            System.out.println(var); // code to be executed
            var++; // increment the loop counter
        }

    String outputFileName = "output.txt";
try (FileWriter fileWriter = new FileWriter(outputFileName)) {
String listblocks = "";
 for (int i = 0; i < executedBlocks.size(); i++) {
 fileWriter.write("Block number " + executedBlocks.get(i) + " is visited \n");
listblocks =  executedBlocks.get(i) + "\n" + listblocks; }
 FileWriter writer = new FileWriter("blocksnum.txt");
 writer.write(listblocks);
writer.close();
} catch (IOException e) {
 e.printStackTrace();
}
}



}
