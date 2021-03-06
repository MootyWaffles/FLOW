%{
  import java.io.*;
  import java.util.*;
  import flow.ast.*;
  import flow.structure.*;
%}

/* keywords */
%token INT_T  /* keyword int, the type */
%token FLT_T  /* keyword float, the type */
%token STR_T  /* keyword string, the type */
%token NODE_T /* keyword Node, the type */
%token ARC_T  /* keyword Arc, the type */
%token GRAPH  /* keyword Graph */
%token LIST_T /* keyword List, the type */
%token WHILE  /* keyword while */
%token IF     /* keyword if */
%token OF     /* keyword of */
%token USE    /* keyword use */
%token RET    /* keyword return */
%token PRINT  /* keyword print */
%token STR    /* string literal */
%token FLT    /* floating-point number literal */
%token INT    /* integer literal */
%token LIST   /* list literal */
%token ID     /* identifier */

/* multicharacter operators */
%token ARC    /* arc connection operator -> */
%token EQ     /* equality operator == */
%token NEQ    /* inequality operator != */
%token LTE    /* less than or equal to <= */
%token GTE    /* greater than or equal to >= */

/* error tokens */
%token UNK /* an illegal identifier */

/* operator precedence */
%nonassoc EQ NEQ
%nonassoc '<' '>' LTE GTE
%right '='
%left '-' '+'
%left '%'
%left '*' '/'
%right CAST
%right NEG          /* negation--unary minus */
%left '.'           /* attribute access for aggregate types */

%%

valid_program : graph_decl
;

graph_decl : type_link graph_stmt_list { $$.sval = "import flow.structure.*;\nimport java.util.*;\npublic class Graph {\npublic Graph() {\nnodes = new FlowList<Node>();\narcs = new FlowList<Arc>();\n" + $2.obj.toString() + "\n}\nprivate FlowList<Arc> arcs; public FlowList<Arc> getarcs() { return arcs; }\n private FlowList<Node> nodes; public FlowList<Node> getnodes() { return nodes; } \n public int getnumNodes(){return nodes.size();} public int getnumArcs(){ return arcs.size();}\n";

                                         for (String label : labels)
                                         {
                                           $$.sval += "private Node " + label + ";\npublic Node get" + label + "() {\n  return " + label + ";\n}\n";
                                         }

                                         $$.sval += "\n}\n";
                                         if (errors == 0) { //only create output java file if there are no syntax errors
                                         try {
                                           FileWriter graph_file = new FileWriter(new File("Graph.java"));
                                           graph_file.write($$.sval);
                                           graph_file.flush();
                                         }
                                         catch(IOException e) {
                                           yyerror("Could not create Solver file.");
                                         }
                                       }
                                       else {
                                         System.out.println("\n" + errors + " errors\n");
                                       } }
;

type_link : USE STR ';'    { /* process the typedef file */ 
                             labels = new ArrayList<String>();
                             try {
                               String filepath = symbols.get("filepath") + $2.sval;
                               TypeParser tparser = new TypeParser(new FileReader(filepath), symbols);
                               tparser.yyparse();
                              }
                              catch(IOException e) {
                                yyerror("Could not open typedef file.");
                              } }
;

graph_stmt_list : graph_stmt ';'  { $$.obj = new SequenceNode(null, (StatementNode) $1.obj); }
| block_stmt                        { $$.obj = new SequenceNode(null, (StatementNode) $1.obj); }
| graph_stmt_list graph_stmt ';'  { $$.obj = new SequenceNode((SequenceNode) $1.obj, (StatementNode) $2.obj); }
| graph_stmt_list block_stmt       { $$.obj = new SequenceNode((SequenceNode) $1.obj, (StatementNode) $2.obj); }
;



func_stmt_list : func_stmt ';'           { $$.obj = new FuncSequenceNode(null, (StatementNode) $1.obj);
                                           ((FuncSequenceNode) $$.obj).retType = ((StatementNode) $1.obj).retType; }
                                           
| func_block_stmt                        { $$.obj = new FuncSequenceNode(null, (StatementNode) $1.obj);


                                           ((FuncSequenceNode) $$.obj).retType = ((StatementNode) $1.obj).retType; }
| func_stmt_list func_stmt ';'           { $$.obj = new FuncSequenceNode((FuncSequenceNode) $1.obj, (StatementNode) $2.obj);
                                           if (((StatementNode) $2.obj).retType == null) {
                                            //If func_stmt has type null, keep the statement list's current type
                                             ((FuncSequenceNode) $$.obj).retType = ((FuncSequenceNode) $1.obj).retType;
                                           }
                                           else if(((FuncSequenceNode) $1.obj).retType == null) {
                                            //If func_stmt_list has no type, take the func_stmt type
                                             ((FuncSequenceNode) $$.obj).retType = ((StatementNode) $2.obj).retType;   
                                           }
                                           else if (((FuncSequenceNode) $1.obj).retType != ((StatementNode) $2.obj).retType){
                                             yyerror("You are returning the wrong type" + ((FuncSequenceNode) $1.obj).retType + ((StatementNode) $2.obj).retType );
                                           }
                                           else{
                                             ((FuncSequenceNode) $$.obj).retType = ((StatementNode) $2.obj).retType;   
                                           } }

| func_stmt_list func_block_stmt         { $$.obj = new FuncSequenceNode((FuncSequenceNode) $1.obj, (StatementNode) $2.obj);
                                           if (((StatementNode) $2.obj).retType == null) {
                                             ((FuncSequenceNode) $$.obj).retType = ((FuncSequenceNode) $1.obj).retType;
                                           }
                                           else if(((FuncSequenceNode) $1.obj).retType == null) {
                                             ((FuncSequenceNode) $$.obj).retType = ((FuncSequenceNode) $1.obj).retType;   
                                           }
                                           else if (((FuncSequenceNode) $1.obj).retType != ((StatementNode) $2.obj).retType){
                                             yyerror("You are returning the wrong type.2" + ((FuncSequenceNode) $1.obj).retType + ((StatementNode) $2.obj).retType );
                                           }
                                           else{
                                             ((FuncSequenceNode) $$.obj).retType = ((StatementNode) $2.obj).retType;   
                                           } }
;

block_stmt: while_stmt
| if_stmt                           { $$.obj = $1.obj; }
| func_dec                          { $$.obj = $1.obj; functions.add(((FunctionNode)$1.obj).realToString()); }
;

func_block_stmt: func_while_stmt
| func_if_stmt {$$.obj = $1.obj;}
;

node_dec : '@' id attr_list            { $$.obj = new NodeDec((ID) $2.obj, (AttrList) $3.obj);
   symbols.put(((ID)$2.obj).toString(), (ID)$2.obj); ((ID)$2.obj).type = new pType("Node");}
| '@' id                               { $$.obj = new NodeDec((ID) $2.obj, null);
                                         symbols.put(((ID)$2.obj).toString(), (ID)$2.obj); ((ID)$2.obj).type = new pType("Node");}
;

arc_dec : id ARC id attr_list          { $$.obj = new ArcDec((ID) $1.obj, (ID) $3.obj, (AttrList) $4.obj);}
| id ARC id                            { $$.obj = new ArcDec((ID) $1.obj, (ID) $3.obj, null); }
;

graph_stmt: prim_dec
| assignment
| node_dec
| arc_dec
| label_app
| print_stmt
| func_call                         { $$.obj = $1.obj; }
;

label_app : id ':' node_dec            { $$.obj = new LabelNode((ID) $1.obj, (NodeDec) $3.obj);
                                         labels.add($1.sval); }
;

func_stmt: prim_dec
| assignment
| print_stmt
| func_call                         { $$.obj = $1.obj;}
| RET expr                          { $$.obj = new ReturnNode((Expression) $2.obj); 
                                      ((ReturnNode) $$.obj).retType = ((Expression) $2.obj).type;}
;

func_call : id '(' attr_list ')'                              { 
                                                                    ID function_name = (ID) symbols.get($1.obj.toString());
                                                                    fType functionType = (fType) function_name.type;
                                                                    //Check attr_list against the parameter types
                                                                    check_type((AttrList)$3.obj, functionType.paramTypes);
                                                                    $$.obj = new FunctionCall(function_name, (AttrList) $3.obj);
                                                                    ((Expression) $$.obj).type = function_name.type; 
                                                               }
;

func_dec : param '(' param_list ')' 
            {//At this point, we know that we are going to end up inside a function body, 
            Param param = (Param) $1.obj;
            ID function_id = param.id;
            //Check that this function name was not previously used by something else
            if(symbols.containsKey(function_id.toString())) {
                yyerror(function_id.toString() + " already declared. Cannot reuse as function name");
            }
            Type ret_type = param.type;
            ParamList params = (ParamList) $3.obj;
            //Add the id to the symbol table
            function_id.type = new fType(ret_type.type, params);
            symbols.put(function_id.toString(), function_id);
            //so we'll save the current symbol table and start a new on for the function
            old = symbols;
            //Create a new symbol table (clone because we want access to higher scoped ids too)
            symbols = (Hashtable) old.clone();
            //Add the symbols from the param_list into the symbol table
            for(Param p : params.toArrayList()) {
                ID id = p.id;
                id.type = p.type;
                //Check that the parameter name is not the same as the function name
                if(id.toString().equals(function_id.toString())) {
                    yyerror("Your parameter cannot be the same as your function name " + function_id.toString());
                }
                //Add the id/overwrite the id into the symbol table
                symbols.put(id.toString(), id);
            }
            }
            
            '{'  func_stmt_list '}'  { $$.obj = new FunctionNode((Param) $1.obj, (ParamList) $3.obj, (FuncSequenceNode) $7.obj); 
                                       if (!((Param) $1.obj).id.type.type.equals(((FuncSequenceNode) $7.obj).retType.type)) {
                                         yyerror("Function " + ((Param) $1.obj).id.toString() + " returns the wrong type.");
                                       } 
                                       //Restore the old symbol table
                                       symbols = old; }

;

while_stmt : WHILE '(' expr ')' '{' graph_stmt_list '}'      { $$.obj = new WhileNode((Expression) $3.obj, (SequenceNode) $6.obj); }
;

func_while_stmt : WHILE '(' expr ')' '{' func_stmt_list '}'      { $$.obj = new WhileNode((Expression) $3.obj, (SequenceNode) $6.obj); }
;

if_stmt : IF '(' expr ')' '{' graph_stmt_list '}'            { $$.obj = new IfNode((Expression) $3.obj, (SequenceNode) $6.obj); }
;

func_if_stmt : IF '(' expr ')' '{' func_stmt_list '}'            { $$.obj = new IfNode((Expression) $3.obj, (SequenceNode) $6.obj);}
;

expr : '(' expr ')'            { $$.obj = $2.obj; }
| '(' type ')' expr %prec CAST { $$.obj = new Cast($2.sval,(Expression) $4.obj);
                                 ((Expression) $$.obj).type = (Type) $2.obj; }
| '-' expr %prec NEG           { $$.obj = new Unary((Expression) $2.obj, $1.sval);
                                 if (((Expression) $1.obj).type.type.equals("String")){
                                   yyerror("NEG is not a string operation.");
                                 }
                                 ((Expression) $$.obj).type = ((Expression) $2.obj).type; }
| expr '>' expr                { $$.obj = new Comparison((Expression) $1.obj, (Expression) $3.obj, ">");
                                 if (((Expression) $1.obj).type.type.equals("String") || ((Expression) $3.obj).type.type.equals("String")){
                                   yyerror("> is not a string operation.");
                                 }
                                 ((Expression) $$.obj).type = check_type((Expression) $1.obj, (Expression) $3.obj); }
| expr GTE expr                { $$.obj = new Comparison((Expression) $1.obj, (Expression) $3.obj, ">=");
                                 if (((Expression) $1.obj).type.type.equals("String") || ((Expression) $3.obj).type.type.equals("String")){
                                   yyerror(">= is not a string operation.");
                                 }
                                 ((Expression) $$.obj).type = check_type((Expression) $1.obj, (Expression) $3.obj); }
| expr '<' expr                { $$.obj = new Comparison((Expression) $1.obj, (Expression) $3.obj, "<");
                                 if (((Expression) $1.obj).type.type.equals("String") || ((Expression) $3.obj).type.type.equals("String")){
                                   yyerror("< is not a string operation.");
                                 }
                                 ((Expression) $$.obj).type = check_type((Expression) $1.obj, (Expression) $3.obj); }
| expr LTE expr                { $$.obj = new Comparison((Expression) $1.obj, (Expression) $3.obj, "<=");
                                 if (((Expression) $1.obj).type.type.equals("String") || ((Expression) $3.obj).type.type.equals("String")){
                                   yyerror("LTE is not a string operation.");
                                 }
                                 ((Expression) $$.obj).type = check_type((Expression) $1.obj, (Expression) $3.obj); }
| expr NEQ expr                { $$.obj = new Comparison((Expression) $1.obj, (Expression) $3.obj, "!=");
                                 ((Expression) $$.obj).type = check_type((Expression) $1.obj, (Expression) $3.obj); }
| expr EQ expr                 { $$.obj = new Comparison((Expression) $1.obj, (Expression) $3.obj, "==");
                                 ((Expression) $$.obj).type = check_type((Expression) $1.obj, (Expression) $3.obj); }
| expr '+' expr                { $$.obj = new Arithmetic((Expression) $1.obj, (Expression) $3.obj, "+");
                                 ((Expression) $$.obj).type = check_type((Expression) $1.obj, (Expression) $3.obj); }
| expr '-' expr                { $$.obj = new Arithmetic((Expression) $1.obj, (Expression) $3.obj, "-");
                                 if (((Expression) $1.obj).type.type.equals("String") || ((Expression) $3.obj).type.type.equals("String")){
                                   yyerror("Subtraction is not a string operation.");
                                 }
                                 ((Expression) $$.obj).type = check_type((Expression) $1.obj, (Expression) $3.obj); }
| expr '*' expr                { $$.obj = new Arithmetic((Expression) $1.obj, (Expression) $3.obj, "*");
                                 if (((Expression) $1.obj).type.type.equals("String") || ((Expression) $3.obj).type.type.equals("String")){
                                   yyerror("Multiplication is not a string operation.");
                                 }
                                 ((Expression) $$.obj).type = check_type((Expression) $1.obj, (Expression) $3.obj); }
| expr '/' expr                { $$.obj = new Arithmetic((Expression) $1.obj, (Expression) $3.obj, "/");
                                 if (((Expression) $1.obj).type.type.equals("String") || ((Expression) $3.obj).type.type.equals("String")){
                                   yyerror("Division is not a string operation.");
                                 }
                                 ((Expression) $$.obj).type = check_type((Expression) $1.obj, (Expression) $3.obj); }
| expr '%' expr                { $$.obj = new Arithmetic((Expression) $1.obj, (Expression) $3.obj, "%");
                                 if (((Expression) $1.obj).type.type.equals("String") || ((Expression) $3.obj).type.type.equals("String")){
                                   yyerror("Modulus is not a string operation.");
                                 }
                                 ((Expression) $$.obj).type = check_type((Expression) $1.obj, (Expression) $3.obj); }
| id '.' id                    { $$.obj = new Dot((ID) $1.obj, $3.obj.toString());
                                 if (((Expression) $1.obj).type.type.equals("Node")) {
                                   if (((Hashtable) symbols.get("node_attributes")).containsKey(((ID) $3.obj).toString()))
                                     ((Expression) $$.obj).type = new Type(((Hashtable) symbols.get("node_attributes")).get($3.obj.toString()).toString());
                                   else {
                                     yyerror("Node attribute '" + $3.obj.toString() + "' is not defined");
                                     ((Expression) $$.obj).type = new pType("error");
                                   }
                                 }
                                 else if (((Expression) $1.obj).type.type.equals("Arc")) {
                                   if (((Hashtable) symbols.get("arc_attributes")).containsKey(((ID) $3.obj).toString()))
                                     ((Expression) $$.obj).type = new Type(((Hashtable) symbols.get("arc_attributes")).get($3.obj.toString()).toString());
                                   else {
                                     yyerror("Arc attribute '" + $3.obj.toString() + "' is not defined");
                                     ((Expression) $$.obj).type = new pType("error");
                                   }
                                 }
                                 else if (((Expression) $1.obj).type.type.length() > 4 && ((Expression) $1.obj).type.type.substring(0,4).equals("list")) {
                                   if ($3.obj.toString().equals("length")) {
                                     ((Expression) $$.obj).type = new pType("int");
                                   }
                                   else {
                                     yyerror("List attribute '" + $3.obj.toString() + "' is not defined");
                                     ((Expression) $$.obj).type = new pType("error");
                                   }
                                 }
				 else if (((Expression) $1.obj).type.type.equals("String")){
				   if ($3.obj.toString().equals("length")){
				     $$.obj = new StrDot((ID) $1.obj, $3.obj.toString());
				     ((Expression) $$.obj).type = new pType("int");
				   }

				 }
                                 else {
                                   yyerror("Dot operator applied to invalid type: " + ((ID) $1.obj).toString() + " is of type " + ((Expression) $1.obj).type.type);
                                   ((Expression) $$.obj).type = new pType("error");
                                 } }
| GRAPH '.' id                 { $$.obj = new Dot(new ID("graph"), $3.obj.toString());
                                 if (((Hashtable) symbols.get("labels")).containsKey($3.obj.toString())) {
                                   ((Expression) $$.obj).type = new Type(((Hashtable) symbols.get("labels")).get($3.obj.toString()).toString());
                                 }
                                 else {
                                   yyerror("Graph attribute '" + $3.obj.toString() + "' is not defined");
                                   ((Expression) $$.obj).type = new pType("error");
                                 } }
| assignment                   { $$.obj = $1.obj; }
| access                       { $$.obj = $1.obj; }
| id                           { $$.obj = $1.obj;
                                 if (!symbols.containsKey(((ID) $1.obj).toString())) {
                                   yyerror("Undeclared variable '" + ((ID) $1.obj).toString() + "'");
                                   ((Expression) $$.obj).type = new pType("error");
                                 }
                                 else {
                                   ((Expression) $$.obj).type = ((ID) $1.obj).type;
                                 } }
| func_call                    { $$.obj = $1.obj; }
| pvalue                       { $$.obj = $1.obj; }
| list_lit                     { $$.obj = $1.obj; }
;

param_list : param_list ',' param      { $$.obj = new ParamList((ParamList)$1.obj, (Param)$3.obj); }
| param                                { $$.obj = new ParamList(null, (Param)$1.obj); }
| /* empty string */                   { $$.obj = null; }
;

param : type id                        { $$.obj = new Param((Type) $1.obj, (ID) $2.obj); }
;

assignment : access '=' expr           { $$.obj = ((ListAccess) $1.obj).makeLVal((Expression) $3.obj);
                                         ((Expression) $$.obj).type = check_type((Expression) $1.obj, (Expression) $3.obj); }
| id '=' expr                          { $$.obj = new Arithmetic((Expression) $1.obj, (Expression) $3.obj, "=");
                                         ((Expression) $$.obj).type = check_type((Expression) $1.obj, (Expression) $3.obj); }
;

access : id '[' expr ']'               { $$.obj = new ListAccess((ID) $1.obj, (Expression) $3.obj);
                                         if (!symbols.containsKey(((ID) $1.obj).toString())) {
                                           yyerror("Undeclared list '" + ((ID) $1.obj).toString() + "'");
                                           ((Expression) $$.obj).type = new pType("error");
                                         }
                                         else if (!((Expression) $1.obj).type.type.substring(0,4).equals("list")) {
                                           yyerror("Only Lists can be indexed. " + ((Expression) $1.obj).type.type);
                                           ((Expression) $$.obj).type = new pType("error");
                                         }
                                         else if (((Expression) $3.obj).type.type != "int") {
                                           yyerror("Lists can only be indexed by ints.");
                                           ((Expression) $$.obj).type = new pType("error");
                                         }
                                         else {
                                           ((Expression) $$.obj).type = new Type(((ID) $1.obj).type.type.substring(5));
                                         } }
;

list_lit : '[' attr_list ']'                { $$.obj = new ListLit((AttrList) $2.obj);
	      ((Expression) $$.obj).type = new Type("list " + check_type((AttrList) $2.obj).toString()); }
;

/*list_dec : list_type id                     { $$.obj = new ListDec((Type) $1.obj, (ID) $2.obj, null);
                                              //added space, was new Type("list" ...) -> new Type("list " ...)
                                              ((ID) $2.obj).type = new Type("list " + $1.obj);
                                              symbols.put(((ID) $2.obj).toString(), $2.obj); }

| list_type id '=' list_lit                 { $$.obj = new ListDec((Type) $1.obj, (ID) $2.obj, (AttrList) $4.obj);
                                              ((ID) $2.obj).type = check_type((Type) $1.obj, (AttrList) $4.obj);
                                              symbols.put(((ID) $2.obj).toString(), $2.obj); }
;*/

type : ptype                           { $$.obj = $1.obj; }
| NODE_T                               { $$.obj = new Type("Node"); }
| ARC_T                                { $$.obj = new Type("Arc"); }
| list_type                            { $$.obj = $1.obj; }
;

list_type : LIST_T OF type             { $$.obj = new Type("list " + $3.obj.toString()); }
;

prim_dec : type id                     { $$.obj = new PrimDec((Type) $1.obj, (ID) $2.obj, null);
                                         if (symbols.containsKey($2.obj.toString())) {
                                           yyerror("Variable " + $2.obj.toString() + " already declared");
                                         }
                                         else {
                                           ((Expression) $2.obj).type = (Type) $1.obj;
                                           symbols.put(((ID) $2.obj).toString(), $2.obj);
                                         } }
| type id '=' expr                     { $$.obj = new PrimDec((Type) $1.obj, (ID) $2.obj, (Expression) $4.obj);
                                         if (symbols.containsKey($2.obj.toString())) {
                                           yyerror("Variable " + $2.obj.toString() + " already declared");
                                         }
                                         else {
                                           ((Expression) $2.obj).type = check_type((Type) $1.obj, (Expression) $4.obj);
                                           symbols.put(((ID) $2.obj).toString(), $2.obj);

                                         } }
;

attr_list : expr                       { $$.obj = new AttrList(null, (Expression) $1.obj); }
| attr_list ',' expr                   { $$.obj = new AttrList((AttrList) $1.obj, (Expression) $3.obj); }
;

id : ID                                { if (symbols.containsKey($1.sval)) {
                                           $$.obj = symbols.get($1.sval);
                                         }
                                         else {
                                           $$.obj = new ID($1.sval);
                                         } }
;

ptype : INT_T                          { $$.obj = new pType("int"); }
| FLT_T                                { $$.obj = new pType("double"); }
| STR_T                                { $$.obj = new pType("String"); }

;

pvalue : INT                           { $$.obj = new pValue($1.ival);
                                         ((Expression) $$.obj).type = new pType("int"); }
| FLT                                  { $$.obj = new pValue($1.dval);
                                         ((Expression) $$.obj).type = new pType("double"); }
| STR                                  { $$.obj = new pValue("\"" + $1.sval + "\"");
                                         ((Expression) $$.obj).type = new pType("String"); }
;

print_stmt : PRINT expr                { $$.obj = new Print((Expression) $2.obj); }
;

%%

  private GraphLexer lexer;
  private Hashtable symbols;
  //We need another Hashtable for temporary storage
  private Hashtable old;
  private ArrayList<String> labels;
  private ArrayList<String> functions;
  private int errors; //Keeps track of syntax errors

  private int yylex () {
    int yyl_return = -1;
    try {
      yylval = new GraphParserVal(0);
      yyl_return = lexer.yylex();
    }
    catch (IOException e) {
      System.err.println("IO error :" + e);
    }

    //Print the token value - used for debugging
    //System.out.println(yyl_return);

    return yyl_return;
  }

  private Type check_type(Expression e1, Expression e2) {
    if (!e1.type.type.equals(e2.type.type)) {
      yyerror("Type mismatch error:  " + e1.type.type + " != " + e2.type.type);
      return new pType("error");
    }
    else return e1.type;
  }
  
  private Type check_type(Type t1, Expression e2) {
    if (!t1.type.equals(e2.type.type)) {
      yyerror("Type mismatch error:  " + t1.type + " != " + e2.type.type);
      return new pType("error");
    }
    else return t1;
  }  
  
  private Type check_type(Type t1, AttrList e2) {
    ArrayList<Expression> attrs = e2.toArrayList();
    Type ret;
    for(Expression attr : attrs) {
        //check_type(type t1, expression e2) will put an error into yyerror
        ret = check_type(t1, attr);
        if(ret.type.equals("error")) {
            return ret;
        }
    }
    return t1;
  }  

  private Type check_type(AttrList e2) {
    ArrayList<Expression> attrs = e2.toArrayList();
    Type t1 = attrs.get(0).type;
    Type ret;
    for(Expression attr : attrs) {
        //check_type(type t1, expression e2) will put an error into yyerror
        ret = check_type(t1, attr);
        if(ret.type.equals("error")) {
            return ret;
        }
    }
    return t1;
  }  
  
  private Type check_type(AttrList attrs, ArrayList<Param> params) {
    ArrayList<Expression> attrslist = attrs.toArrayList();
    if(attrslist.size() != params.size()) {
        yyerror("Expected " + params.size() + " args, got " + attrslist.size());
        return new pType("error");
    }
    for(int i=0; i<params.size(); i++) {
        String paramType = params.get(i).type.type;
        String attrType = attrslist.get(i).type.type;
        if(!paramType.equals(attrType)) {
            yyerror("Expected type " + paramType + " got " + attrType);
            return new pType("error");
        }
    }
    return new pType("success");
  }  

  public void yyerror (String error) {
    System.err.println("Error: " + error + "\n\tat line " + (lexer.getLine() + 1));
    errors++;
  }

  public GraphParser(Reader r) {
    lexer = new GraphLexer(r, this);
  }

  public GraphParser(Reader r, Hashtable symbols)
  {
    errors = 0;
    lexer = new GraphLexer(r, this);
    this.symbols = symbols;
  }
