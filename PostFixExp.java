public interface PostFixExp {

	// Set a new expression written in postfix notation. Tokens are separated by a space.
	void setExp(String exp);

	// Return the expression in postfix notation. Tokens are separated by a space.
	String getExp();

	// Return the operands stack after parsing k tokens. If an error is encountered before k tokens are parsed, null is returned. An error can be a syntax error or division by zero. Assume that k is valid.
	Stack<Integer> evaluate(int k);

	// Check if the expression is valid (no syntax error and no division by zero). If the expression is invalid, the index of the token that caused the error is returned. If the expression is valid, the method returns -1.
	int validate();

	// Return the expression in infix notation. If a syntax error is encountered, null is returned (notice that division by zero is not considered an error here). Parentheses must be put around every operation, like "( ( 3 * 2 ) + ( 6 / 3 ) )". Tokens must be separated by a space.
	InFixExp toInFix();
}
