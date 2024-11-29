package behavioral.iterator;

public interface Aggregate<E> {
	// 다양한 타입 입력 가능. 어떤 타입이 들어올지 모른다는 의미
	Iterator<E> createIterator();
}
