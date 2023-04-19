import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.TerminalNode;

public class testcase extends JavaParserBaseVisitor<String>{

    private TokenStreamRewriter rewriter;
    boolean test;

    public testcase(TokenStreamRewriter rewriter) {
        this.rewriter= rewriter;
    }

    @Override
    public String visitTerminal(TerminalNode node) {
        if (node.getText().equals("else")) test = true;
        if (node.getText().equals("if")) test = false;
        return super.visitTerminal(node);
    }


    @Override
    public String visitStatement(JavaParser.StatementContext ctx) {
        String s = ctx.getStart().getText();
        s = s.toLowerCase();

        if(s.equals("if")  s.equals("for")  s.equals("while") || s.equals("else if")) {


            if (ctx.statement(0).getText().charAt(0) != '{') {
                rewriter.insertBefore(ctx.statement(0).getStart(), "{");
                rewriter.insertAfter(ctx.getStop(), "\t\n}");
            }
        }else if(test){
            test = false;
            System.out.println(ctx.getText());
            if (ctx.getText().length() >= 3 && ctx.getText().substring(0, 3).equals("if(")) return null;// not else if(
            if (ctx.getText().charAt(0) != '{') {
                rewriter.insertBefore(ctx.getStart(), "{");
                rewriter.insertAfter(ctx.getStop(), "}");
            }

        }

        return super.visitStatement(ctx);
    }
    public String code() {
        return rewriter.getText();
    }

}
