package flow.ast;

public class FuncSequenceNode extends SequenceNode {

    public Type retType;
    private FuncSequenceNode prev;
    private StatementNode exec;
    
	public FuncSequenceNode(FuncSequenceNode prev, StatementNode exec)
	{
	    super(prev, exec);

	    retType = null;
	    this.exec = exec;
	    this.prev = prev;
	    
	}
	
	public String toString() {
		if (prev != null){
			return prev.toString() + exec + ";\n";
		}
		else{
			return exec + ";\n";
		}
	}
}