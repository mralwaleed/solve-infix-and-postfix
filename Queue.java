public interface Queue<T> {
	int length();

	boolean full();

	void enqueue(T e);

	T serve();
}