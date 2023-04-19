import org.antlr.v4.runtime.*;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Html extends JavaParserBaseVisitor<String> {
    TokenStreamRewriter rewriter;
    ArrayList<String> visitedBlocks;

    int count = 0;
    public Html(TokenStreamRewriter rewriter , ArrayList<String> visitedBlocks) {
        this.rewriter = rewriter;
        this.visitedBlocks = visitedBlocks;
    }
    

            String imports = "\nimport java.util.*;\nimport java.io.*;\n" ;
            String HtmlCode = " <!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<title>Compilers Project</title>\n" +
                    "<meta charset = \"UTF-8\">\n" +
                    "<meta name = \"description\" content = \"Compilers Project: visited Blocks\">\n" +
                    "<style>\n" +
                    ".green {\n" +
                    "background-color: #00693E;\n" +
                    " }\n" +
                    ".red {\n" +
                    "background-color: #800020;\n" +
                    "}\n" +
                    "</style>\n" +
                    "<script></script>\n" +
                    "</head>\n" +
                    "<body style=\"background:#00693E; color:white; padding:10; margin:0;\">\n" +
                    "<pre>\n";
    @Override
    public String visitCompilationUnit(JavaParser.CompilationUnitContext ctx) {
        rewriter.insertBefore(ctx.getStart(), imports);
        rewriter.insertBefore(ctx.getStart(), HtmlCode);


        rewriter.insertAfter(ctx.getStop(), "" +
                "</pre>\n" +
                " </body>\n" +
                "</html>\n");
        return super.visitCompilationUnit(ctx);
    }

    @Override
    public String visitClassBodyDeclaration(JavaParser.ClassBodyDeclarationContext ctx) {
        rewriter.insertAfter(ctx.getStart(), "\n\n\tstatic public Integer blocksVisited[] = {};");
        rewriter.insertAfter(ctx.getStart(), "\n\tstatic public ArrayList&lt;Integer&gt; arrayList = new ArrayList&lt;Integer&gt;(Arrays.asList(blocksVisited));\n");
        return super.visitClassBodyDeclaration(ctx);
    }

    @Override
    public String visitBlock(JavaParser.BlockContext ctx) {

        String color = this.visitedBlocks.contains(String.valueOf(count)) ? "green" : "red";
        String injectedMessage = "<div class='" + color + "' id='" + count + "'>";
        rewriter.insertAfter(ctx.getStart(), "\t\t"+injectedMessage +"// block number " + count + '\n');
        rewriter.insertAfter(ctx.getStart(), "\t\t\tarrayList.add(" + count + ");");
        rewriter.insertBefore(ctx.getStop(), "</div>");
        ++count;




        return super.visitBlock(ctx);
    }


}

