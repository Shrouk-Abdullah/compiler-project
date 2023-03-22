// import ANTLR's runtime libraries
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

import java.io.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {
        System.out.println("Enter java file path: ");
        Scanner sc = new Scanner(System.in);
        String fileName = sc.nextLine();

        //open file
        File file = new File(fileName);
        FileInputStream fis = null;

        // open the input file stream
        fis = new FileInputStream(file);


// create a CharStream that reads from standard input
        ANTLRInputStream input = new ANTLRInputStream(fis);
// create a lexer that feeds off of input CharStream
        JavaLexer lexer = new JavaLexer(input);
// create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);
// create a parser that feeds off the tokens buffer
        JavaParser parser = new JavaParser(tokens);
        ParseTree tree = parser.compilationUnit(); // begin parsing at compilationUnit rule
        //System.out.println(tree.toStringTree(parser)); // print LISP-style tree
        TokenStreamRewriter rewriter = new TokenStreamRewriter(tokens);
        maingram mg = new maingram(rewriter);
        mg.visit(tree);

        FileWriter writer = new FileWriter("output.txt");
        writer.write(mg.output());
        writer.close();
        System.out.println("the code in output");
    }

}
