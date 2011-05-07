import flow.structure.*; public class Node {
  public Node() {
 arcsIn = new FlowList<Arc>(); arcsOut = new FlowList<Arc>(); arcs = new FlowList<Arc>(); degree = inDegree = outDegree = 0;  }
 void addInArc(Arc in)
   {
      arcsIn.add(in);
      arcs.add(in);
      inDegree++;
      degree++;
   }

   void addOutArc(Arc out)
   {
      arcsOut.add(out);
      arcs.add(out);
      outDegree++;
      degree++;
   }

   int getdegree() { return degree; }
   int getdnDegree() { return inDegree; }
   int getoutDegree() { return outDegree; }

   FlowList<Arc> getarcs() { return arcs; }
   FlowList<Arc> getarcsIn() { return arcsOut; }
   FlowList<Arc> getarcsOut() { return arcsIn; }

   int inDegree;
   int outDegree;
   int degree;

   FlowList<Arc> arcsIn;
   FlowList<Arc> arcsOut;
   FlowList<Arc> arcs;}