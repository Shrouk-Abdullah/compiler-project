import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import java.awt.*;
import java.io.BufferedReader;
import java.io.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws Exception {
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
        testcase ts = new testcase(rewriter);
        ts.visit(tree);
        maingram mg = new maingram(rewriter);
        mg.visit(tree);


        FileWriter writer = new FileWriter("output.java");
        writer.write(mg.output());
        writer.close();
        System.out.println("the code in test");

        String filePath = "C:\\Users\\Pc\\Desktop\\Final1\\final1\\output.java";
        String javaFileName = "output";
        JavaRunner.runJavaFile(filePath, javaFileName);


//
//        String newfilepath = "C:\\Users\\Pc\\Desktop\\Final1\\final1\\output.java";
//        File newfile2 = new File(newfilepath);
//        FileInputStream newfile = null;
//
//// open the input file stream
//        newfile = new FileInputStream(newfile2);
//        // create a CharStream that reads from standard input
//        ANTLRInputStream input2 = new ANTLRInputStream(newfile);
//// create a lexer that feeds off of input CharStream
//        JavaLexer lexer2 = new JavaLexer(input2);
//// create a buffer of tokens pulled from the lexer
//        CommonTokenStream tokens2 = new CommonTokenStream(lexer2);
//// create a parser that feeds off the tokens buffer
//        JavaParser parser2 = new JavaParser(tokens2);
//        ParseTree tree2 = parser2.compilationUnit(); // begin parsing at compilationUnit rule
////System.out.println(tree.toStringTree(parser2)); // print LISP-style tree
//        TokenStreamRewriter rewriter2 = new TokenStreamRewriter(tokens);
//    maingram mg = new maingram(rewriter2);
//        testcase ts = new testcase(rewriter2);
//        ts.visit(tree2);
//       mg.visit(tree2);
//
//        FileWriter writer2 = new FileWriter("test.java");
//        writer2.write(mg.output());
//        writer2.close();
//        System.out.println("the code in test");


        // execute java output

        genHTML(tokens,tree);

    }
