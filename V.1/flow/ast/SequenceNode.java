package flow.ast;

public class SequenceNode extends ASTNode {

	public SequenceNode(SequenceNode prev, StatementNode exec)
	{
		this.prev = prev;
		this.exec = exec;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		if (prev != null){
			return prev.toString() + exec.toString() + ";";
		}
		else{
			return exec + ";";
		}
	}

	private SequenceNode prev;
	private StatementNode exec;

}