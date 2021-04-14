  
public class InFixExpImp implements InFixExp {
	 private String exp;

	@Override
	public void setExp(String exp) {
		this.exp = exp + " $";
		
	}

	@Override
	public String getExp() {
		// TODO Auto-generated method stub
		return exp.substring(0, exp.length() - 2);
	}
	private boolean isNum(String s) {
		   try {
		        int d = Integer.parseInt(s);
		    } catch (NumberFormatException | NullPointerException e) {
		        return false;
		    }
		    return true;
		}

	@Override
	public int getNbTokens() {
		String[] take = exp.split(" +");
		return take.length;
	}
	private String getData(LinkedStack<String> s) {
		
		try {
			String temp =s.pop();
		
		s.push(temp);
		
		return temp;
		}catch(NullPointerException e) {
			return null;	
		}
		
	}

	@Override
	public Pair<Stack<Integer>, Stack<String>> evaluate(int k) {
		// TODO Auto-generated method stub
		String[] take = exp.split(" ");
			LinkedStack<Integer> st = new LinkedStack<>();
			LinkedStack<String> o = new LinkedStack<>();
			try {
				for (int i = 0; i < k; i++) {
				//	c = take[i];
					if (isNum(take[i])) {
					//	 System.out.println(take[i]+"add");
						st.push(Integer.parseInt(take[i])); }
					 else if (take[i].equals("(")) {
						o.push(take[i]);
					} else if (take[i].equals(")")) {
						if (hasOpening(o)) {
							while (!getData(o).equals("(")) {
								int result = OperatorResult(o.pop(), st.pop(), st.pop());
								st.push(result);
							}
							o.pop();
						} else {
							return null;
						}
					} else if (isOperator(take[i])) {
						while (!o.empty() && whatPrecedence(take[i], getData(o))) {
							int result = OperatorResult(o.pop(), st.pop(), st.pop());
							st.push(result);
						}
						o.push(take[i]);
					} else if (take[i].equals("$")) {
						while (!o.empty()) {
							int result = OperatorResult(o.pop(), st.pop(), st.pop());
							st.push(result);
						}
						o.push("$");
					} else {
						return null ;
					}
					/*
					 * print(st); print(o);
					 */
				}
				Pair<Stack<Integer>, Stack<String>> p = new Pair<>(st, o);
				return p;
			

			} catch ( ArithmeticException e) {
				return null;
			}
	}
	
	
	@Override
	public int validate() {
		int i = 0 ;
	int deg = 0;
		String[] take = exp.split(" +");
	//	String c;
	//	System.out.println(take[1]);
		LinkedStack<Integer> st = new LinkedStack<>();
		LinkedStack<String> o = new LinkedStack<>();
		try {
			for (i = 0; i < take.length; i++) {
			//	c = take[i];
			//	System.out.println(take[i]);
				if (isNum(take[i])) {
					deg++;
				//	 System.out.println(take[i]+"add");
					st.push(Integer.parseInt(take[i]));
				} else if (take[i].equals("(")) {
					o.push(take[i]);
				} else if (take[i].equals(")")&&deg>=2) {
					if (hasOpening(o)) {
						
						while (!getData(o).equals("(")) {
							int result = OperatorResult(o.pop(), st.pop(), st.pop());
							st.push(result);
							deg--;
						}
						o.pop();
						
						
					} else 
						return i;
					
				} else if (isOperator(take[i])&&!st.empty()) {
					
				//	System.out.println("test");
					if(deg>=2) {
					while (!o.empty() && whatPrecedence(take[i], getData(o))) {
						int result = OperatorResult(o.pop(), st.pop(), st.pop());
						st.push(result);
						deg--;
						//System.out.println(result);
					}
					}if(i+2<take.length) {
						o.push(take[i]);
					//	System.out.println("r");
					}else {
						return i+1;
					}
					
				} else if (take[i].equals("$")&&!hasOpening(o)) {
					//System.out.println(deg);
					if(deg>=2) {
					while (!o.empty()) {
						int result = OperatorResult(o.pop(), st.pop(), st.pop());
						st.push(result);
						//System.out.println(result);
						deg--;
					}
					}
					
				} else {
					return i ;
				}
				/*
				 * print(st); print(o);
				 */
			//	System.out.println(take[i]);
		//		System.out.println(take[i]);
			}
			if(!o.empty()) {
				if (deg==0) {
					return -1;
				}
				else  if (!getData(o).equals("$")) 
				return take.length;
			}
			
			if(deg == 1) {
				return -1;
			}else {
				return i;
			}
			
			

		} catch ( ArithmeticException  e) {
			return i;
		}

	}

/*	private int chek(String c) {
		{ 
		    if(c.equalsIgnoreCase(")")) 
		    return 1; 
		    else if(c.equalsIgnoreCase("*")||c.equalsIgnoreCase("/"))
		    return 2; 
		    else if(c.equalsIgnoreCase("+")||c.equalsIgnoreCase("-")) 
		    return 3; 
		    else
		    return -1; 
		} 
	}*/
	
	
	
	
	
	@Override
	public PostFixExp toPostFix() {
		// TODO Auto-generated method stub
		String total ="";
		LinkedStack<String> o = new LinkedStack<>();
		String[] take = exp.split(" +");
		for (int i = 0; i < take.length-1; i++) {
			String c = take[i];
			if (isNum(take[i])) {
				//	 System.out.println(take[i]+"add");
					total += take[i]+" ";
			} else  if (c.equals("(")) {
				o.push(c); }
			else if(c.equals(")")) {
			    while (!o.empty() && !getData(o).equals("(")) 
                    total += o.pop()+" ";
                  
                if (!o.empty() && !getData(o).equals("(")) 
                    return null; // invalid expression                 
                else
                    o.pop(); 
			}else // an operator is encountered 
            { 
                while (!o.empty() && ChekPrecedence(c) <= ChekPrecedence(getData(o))){ 
                    if(getData(o).equals("(")) 
                        return null; 
                    total += o.pop()+" "; 
             } 
                o.push(c); 
            }
       
        } 
       
        // pop all the operators from the stack 
        while (!o.empty()){ 
            if(getData(o).equals("(")) 
                return null; 
            total += o.pop(); 
         } 
     //   System.out.println(total);
        PostFixExpImp pfe = new PostFixExpImp();
		pfe.setExp(total);
		return pfe;
        
    
			}
	
	
	private int ChekPrecedence(String op) {
		switch (op) {
		case "<":
		case ">":
		case ">=":
		case "<=":
		case "==":
		case "!=":
			return 1;

		case "+":
		case "-":
			return 2;

		case "*":
		case "/":
		case "%":	
			return 3;

		}
		return -1;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	private boolean isOperator(String t) {
		return t.equals("*") || t.equals("/") || t.equals("%") || t.equals("+") || t.equals("-")
				|| t.equals("<") || t.equals(">") || t.equals(">=") || t.equals("<=") || t.equals("==") || t.equals("!=");
	}

	private boolean hasOpening(LinkedStack<String> o) {
		LinkedStack<String> temp = new LinkedStack<>();
		boolean open = false;
		while (!o.empty()) {
			String s = o.pop();
			if (s.equals("("))
				open = true;
			temp.push(s);
		}
		while (!temp.empty()) {
			o.push(temp.pop());
		}
		return open;
	}
	private int OperatorResult(String s , int a , int b) throws ArithmeticException{
		switch (s) {
		case "*":
			return a * b;
		case "/":
			if (a == 0)
				throw new ArithmeticException();
			return b / a;
		case "%":
			return b % a;
		case "+":
			return b + a;
		case "-":
			return b - a;
		case "<": {
			boolean bo = b < a;
			return (bo ? 1 : 0);
		}
		case ">": {
			boolean bo = b > a;
			return (bo ? 1 : 0);
		}
		case ">=": {
			boolean bo = b >= a;
			return (bo ? 1 : 0);
		}
		case "<=": {
			boolean bo = b <= a;
			return (bo ? 1 : 0);
		}
		case "==": {
			boolean bo = a == b;
			return (bo ? 1 : 0);
		}
		case "!=": {
			boolean bo = a != b;
			return (bo ? 1 : 0);
		}
		}
		return 0;
	}
	private boolean whatPrecedence(String o1, String o2) {
		if (o2.equals("(") || o2.equals(")"))
			return false;
		if ((o1.equals("*") || o1.equals("/")) && (o2.equals("+") || o2.equals("-")))
			return false;
		if ((o1.equals("+") || o1.equals("-")) && (o2.equals("<") || o2.equals(">") || o2.equals(">=")
				|| o2.equals("<=") || o2.equals("==") || o2.equals("!=")))
			return false;
		else
			return true;
	}
	
	
	
	
	
	
	
	
	/*public void test() {
		LinkedStack<String> s = new LinkedStack<>();
		 String[] take = exp.split(" ");
		 for(int i=0;i<take.length;i++) {
			 s.push(take[i]);
		 }
		print(s);
		System.out.println(getData(s));
		print(s);
		//return s;
	}*/
	 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public int validate() {
 	int i=0;
 	 char c;
 	  Stack<Integer> s = new LinkedStack<>();
 	  Stack<Character> o = new LinkedStack<>();
 	  boolean flag= false;
       for( i = 0; i < exp.length(); i++) { 
    	  
            c = exp.charAt(i); 
             
           if(c == ' ') 
           continue; 
             
           // If the scanned character is an operand  
           // (number here),extract the number 
           // Push it to the stack. 
           else if(Character.isDigit(c)) { 
                int n ; 
               System.out.println("test");
               //extract the characters and store it in num 
             
                   n = ((int)(c-'0'));
             
                 //  c = exp.charAt(i); 
               //i--; 
 
               //push the number in stack 
               s.push(n); 
           } 
             
           // If the scanned character is an operator, pop two 
           // elements from stack apply the operator 
           else if(!flag){ 
               
               if(c=='*'||c=='/'||c=='^'||c=='+'||c=='-'||c=='(') {
	                   o.push(c); 
	                  
               }else if(c==')') {
            	   o.push(c);
            	   flag =true; }
           }else {
        	   char j ;
        	   int val1 = s.pop(); 
               int val2 = s.pop(); 
        	   while(!o.empty()) {
        		   char temp ;
        		   temp = o.pop();
        		   if(temp!='(') {
        			   o.push(temp);
        		   
        		    j =o.pop();
        		   if(j==')') {
                  	 j =o.pop();
                   }
        		   
                     
                    
                   switch(j) 
                   { 
                      
                       case '+': 
                       s.push(val2+val1); 
                       break; 
                         
                       case '-': 
                       s.push(val2- val1); 
                       break; 
                         
                       case '/': 
                       s.push(val2/val1); 
                       break; 
                         
                       case '*': 
                       s.push(val2*val1); 
                       break; 
                     
                      
                       
               } 
        	   }else {
        		//   o.pop();
        	   }
        		   
        	   }
        		   
        		   
        		   
        		   
        	   o.push(c);
        	   flag =false;
                   
       } 
               
               }
	                   
               
       while(!o.empty()) {
    	   
    	   char j ;
    	   
		   char temp ;
		   temp = o.pop();
		   if(temp!='(') {
			   o.push(temp); 

		   j =o.pop();
		   if(j==')') {
          	 j =o.pop();
           }
		   
             
		   int val1 = s.pop(); 
           int val2 = s.pop(); 
           switch(j) 
           { 
           
               case '+': 
               s.push(val2+val1); 
               break; 
                 
               case '-': 
               s.push(val2- val1); 
               break; 
                 
               case '/': 
               s.push(val2/val1); 
               break; 
                 
               case '*': 
               s.push(val2*val1); 
               break; 
             
           }
               
       } 
		   }
	
	
	return s.pop();
	
	
	
}*/

}
