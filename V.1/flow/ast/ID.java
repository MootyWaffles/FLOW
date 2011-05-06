package flow.ast;

public class ID extends Expression {
    
    String idname;
    
    public ID( String idname) {
	super();
        this.idname = idname;
    }    
    
    public String toString(){
    	return idname;
    }
}