package spring.rest.service;

import java.util.List;

public interface RestApiService<T> {

	List<T> read(T t);
	int create(T t);
	int update(T t);
	int delete(T t);

}
