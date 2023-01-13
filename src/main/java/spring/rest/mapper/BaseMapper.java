package spring.rest.mapper;

import java.util.List;

public interface BaseMapper<T> {

	List<T> select(T t);
	int insert(T t);
	int update(T t);
	int delete(T t);

}
