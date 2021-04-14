
public class PostFixExpImp implements PostFixExp {
	 private String exp;
	
	@Override
	public void setExp(String exp) {
		this.exp =exp; 
		
	}

	@Override
	public String getExp() {
	return exp;
	}

	
	/*private String removeSpeac() {
		return exp.replaceAll(" ", "");
		
		
}*/
	private boolean isNum(String s) {
		
		   try {
		        int d = Integer.parseInt(s);
		    } catch (NumberFormatException | NullPointerException e) {
		        return false;
		    }
		    return true;
		}
		
		
	
		
		
	
	@Override
	public Stack<Integer> evaluate(int k) {
	
        Stack<Integer> st = new LinkedStack<>(); 
        String[] take = exp.split(" +");
        String c;
   
     try {
    	 for(int i = 0; i <k; i++) {
    		c =  take[i];
    		
    		 if(isNum(c)) {
                	//System.out.println("t");
                st.push(Integer.parseInt(c));
                }else if(isOperator(c)) {
                	try {
        st.push(OperatorResult(c,st.pop(),st.pop()));
                	}catch(ArithmeticException  e) {
                		return null;
                	}
                }
                	
                
           
    		 

     }
    	 
    	 
    	 return st;
    	 
    	 
    	 
    	 
    	 
    	 
    	 }catch(Exception r) {
        	return null;
        }
		}
			

	

	@Override
	public int validate(){
	    // Method to evaluate value of a postfix expression 
		 String[] take = exp.split(" +");
	        String c;
		int i = 0;
		// char c;
       //create a stack 
       Stack<Integer> st = new LinkedStack<>(); 
   //     int result;
        try {  
        	for( i = 0; i <take.length; i++) {
    		c =  take[i];
    		
   		 if(isNum(c)) {
               	//System.out.println("t");
               st.push(Integer.parseInt(c));
               }else if(isOperator(c)) {
            	   st.push(OperatorResult(c,st.pop(),st.pop()));
               
               }

        }
       
       int count =0;
       while(!st.empty()) {
    	   st.pop();
    	   count++;
       }
      if (count==1) {
    return -1;}
      else {
    	   return i;  
      }
       }catch(ArithmeticException  e) {
    	   return i;

    	   }


   } 
	
	
		/*
		
		// TODO Auto-generated method stub
		return 0;
	}*/

	@Override
	public InFixExp toInFix() {
		// TODO Auto-generated method stub
		 Stack<String> st = new LinkedStack<>(); 
		 String c;
		 String[] take = exp.split(" +");
		 int count =0;
		 try {
			 for(int i = 0; i <take.length; i++) {
		    		c =  take[i];
		    		
		   		 if(isNum(c)) {
		               	//System.out.println("t");
		               st.push(c);
		               count++;
		               }else if(c.equalsIgnoreCase("*")||c.equalsIgnoreCase("/")||c.equalsIgnoreCase("+")
		            ||c.equalsIgnoreCase("-")||c.equalsIgnoreCase("<=")||c.equalsIgnoreCase(">=")||c.equalsIgnoreCase("==")) {
		           //    char o = c.charAt(0);
		               
		                 String s1 = st.pop();
						 String s2 = st.pop();
						 String res ="( "+s2+" "+c+" "+s1+" )";
						 st.push(res);
						 count--;
		               
					 }

		        }
			 InFixExp ife = new InFixExpImp(); 
				if(count==1) {
				ife.setExp(st.pop());
				return ife; }
				else {
					return null;
				}
			 
		 }catch (Exception e) {
		return null;
	}
	}	
	
	
	private boolean isOperator(String t) {
		return t.equals("*") || t.equals("/") || t.equals("%") || t.equals("+") || t.equals("-")
				|| t.equals("<") || t.equals(">") || t.equals(">=") || t.equals("<=") || t.equals("==") || t.equals("!=");
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

}
