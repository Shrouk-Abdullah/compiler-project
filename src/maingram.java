import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;

import java.io.FileWriter;


public class maingram extends JavaParserBaseVisitor {

    static int currentBlock = 0;


    private TokenStreamRewriter rewriter;
    public maingram(TokenStreamRewriter rewriter){
        this.rewriter = rewriter;
    }

    String imports = "import java.util.ArrayList;\n" +
            "import java.io.FileWriter;\n" +
            "import java.io.IOException;\n";

    String ArrayDeclaration = "\n static ArrayList<Integer> executedBlocks = new ArrayList<Integer>();\n";


    @Override
    public Object visitCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        rewriter.insertBefore(ctx.getStart(), imports );
        return super.visitCompilationUnit(ctx);
    }

    @Override
    public Object visitClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) {
        rewriter.insertBefore(ctx.getStart() , ArrayDeclaration );
        rewriter.insertBefore(ctx.getStop() , ModifiedOutput);
        return super.visitClassBodyDeclaration(ctx);
    }

    @Override
    public Object visitBlock(JavaParser.BlockContext ctx) {
        Token text = ctx.getStart();
        rewriter.insertAfter(text, " // block number " + currentBlock + "\n" + "executedBlocks.add(" + currentBlock + ");");
        currentBlock++;
        return super.visitBlock(ctx);
    }

    String ModifiedOutput  = "String outputFileName = \"output.txt\";\n" +
            "try (FileWriter fileWriter = new FileWriter(outputFileName)) {\n" +
            "String listblocks = \"\";\n"+
            " for (int i = 0; i < executedBlocks.size(); i++) {\n" +
            " fileWriter.write(\"Block number \" + executedBlocks.get(i) + \" is visited \\n\");\n" +
            "listblocks =  executedBlocks.get(i) + \"\\n\" + listblocks;" +
            " }\n" +
           " FileWriter writer = new FileWriter(\"blocksnum.txt\");\n"+
            " writer.write(listblocks);\n"+
            "writer.close();\n"+
            "} catch (IOException e) {\n" +
            " e.printStackTrace();\n" +
            "}\n";



    public String output() {

        return rewriter.getText();
    }






}
