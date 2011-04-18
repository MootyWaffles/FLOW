package flow.ast;

public class IfNode extends StatementNode {

	public IfNode(Expression exp, SequenceNode block) {
		this.exp = exp;
		this.block = block;
	}

	private Expression exp;
	private SequenceNode block;

	@Override
	public String emit() {
		return "if (" + exp.emit() + ") {\n" + block.emit() +"}\n";
	}

}
