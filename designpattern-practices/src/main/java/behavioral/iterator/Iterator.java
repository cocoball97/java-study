package behavioral.iterator;

public interface Iterator<E> {
	// 반환하는 형태가 E  / default 접근 제어
	E next();
	boolean hasNext();
}
