public interface InFixExp {

	// Set a new expression written in infix notation. Tokens are separated by a space.
	void setExp(String exp);

	// Return the expression in infix notation (without $).
	String getExp();

	// Return the number of tokens in the expression (including $).
	int getNbTokens();

	// Return a pair containing respectively operands and operations stacks after parsing k tokens ($ is considered as a token). If an error is encountered before k tokens are parsed, null is returned. An error can be a syntax error or division by zero. Assume that k is valid.
	Pair<Stack<Integer>, Stack<String>> evaluate(int k);

	// Check if the expression is valid (no syntax error and no division by zero). If the expression is invalid, the index of the token that caused the error is returned. If the expression is valid, the method returns -1.
	int validate();

	// Return the expression in postfix notation. If a syntax error is encountered, null is returned (notice that division by zero is not considered an error here. Tokens must be separated by a space.
	PostFixExp toPostFix();

}
