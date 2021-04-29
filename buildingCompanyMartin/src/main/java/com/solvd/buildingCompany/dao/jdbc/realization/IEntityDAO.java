package com.solvd.buildingCompany.dao.jdbc.realization;

public interface IEntityDAO<T> {

	public void save(T data);
	public void update(T data);
	public T getById(long id);
	public void delete(T data);
}
