public interface Stack<T> {
	boolean empty();

	boolean full();

	void push(T e);

	T pop();
	
	
	 

	void reverse();
}