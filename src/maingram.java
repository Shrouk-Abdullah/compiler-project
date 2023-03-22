import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTree;

public class maingram extends JavaParserBaseVisitor<String> {

    int currentBlock = 1;
    private TokenStreamRewriter rewriter;
    public maingram(TokenStreamRewriter rewriter){

        this.rewriter = rewriter;
    }

    @Override
    public String  visitBlock(JavaParser.BlockContext ctx) {
        Token text = ctx.getStart();
        rewriter.insertAfter(text , "// block number" + currentBlock);
        currentBlock++;
        return super.visitBlock(ctx);

    }
    public String output() {
        return rewriter.getText();
    }



}
