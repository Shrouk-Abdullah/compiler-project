public class output {
    public static void main(String[] args) throws Exception {

        System.out.println("hello world");
        boolean a = true;
        boolean b = false;

        int x = 10;
        int y = 5;
        int z = 2;

        
        if (a) {
            System.out.println("a is true");
        }


        if (b) {
            System.out.println("b is true");
        } else {
            System.out.println("b is false");
        }
        for(int i = 0 ; i < 3  ;i++)
        {
            System.out.println("hello");
        }

       
        if (x > y) {
            if (x > z) {
                System.out.println("x is the largest number");
            } else {
                System.out.println("z is the largest number");
            }
        } else {
            if (y > z) {
                System.out.println("y is the largest number");
            } else {
                System.out.println("z is the largest number");
            }
        }
        int var = 1; // initialize the loop counter

        while ( var <= 5) { // specify the condition
            System.out.println(var); // code to be executed
            var++; // increment the loop counter
        }

    }



}
