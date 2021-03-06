package flow.structure;

import java.util.ArrayList;

public class SuperGraph {
	protected FlowList<SuperNode> nodes;
	protected FlowList<SuperArc> arcs;

	public SuperGraph(){
		nodes = new FlowList<SuperNode>();
		arcs = new FlowList<SuperArc>();
	}
	
	public FlowList<SuperNode> getnodes(){
		return nodes;
	}
	
	public FlowList<SuperArc> getarcs(){
		return arcs;
	}
	
	public int getnumNodes(){
		return nodes.size();
	}
	
	public int getnumArcs(){
		return arcs.size();
	}
}
