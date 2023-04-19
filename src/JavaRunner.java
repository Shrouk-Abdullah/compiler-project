import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class JavaRunner extends main {

    public static void runJavaFile(String filePath, String javaFileName) {
        try {
            // Create a JavaCompiler instance
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

            // Compile the Java file
            int compilationResult = compiler.run(null, null, null, filePath);

            // Check if the compilation was successful
            if (compilationResult == 0) {
                System.out.println("Java file compiled successfully.");

                // Build the command to run the compiled Java file

                String[] command = {"java", javaFileName};

                // Start a new process to execute the command
                ProcessBuilder processBuilder = new ProcessBuilder(command);
                Process process = processBuilder.start();

                // Get the output of the Java program
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

                // Wait for the Java program to complete
                process.waitFor();

                // Check if the Java program completed successfully
                if (process.exitValue() == 0) {
                    System.out.println("Java file executed successfully.");
                } else {
                    System.err.println("Java file execution failed.");
                }
            } else {
                System.err.println("Java file compilation failed.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
