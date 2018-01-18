package com.sftp.dao.common;

import java.util.List;

import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.SqlSession;

import com.sftp.dto.IModel;

public interface DBManagerInterface {

	int insert(String queryName, IModel modelObject) throws Exception;

	int update(String queryName, IModel modelObject) throws Exception;

	void deleteObjectById(String queryName, int id) throws Exception;

	Integer getUniqueCount(String queryName, Object modelObject) throws Exception;

	Integer getUniqueCount(String queryName, int id) throws Exception;

	Object getResultAsObjectById(String queryName, int id) throws Exception;

	Object getResultAsObjectByLoginId(String queryName, String LoginId) throws Exception;

	IModel getResultAsObject(String queryName, IModel parametersObject) throws Exception;

	Object getResultAsObject(String queryName, Object parametersObject) throws Exception;

	<T> List<T> getResultAsList(String queryName, T parameterObject) throws Exception;

	<T> List<T> getResultAsList(String queryName, T parameterObject, int skipResults, int maxResults) throws Exception;

	<T> List<T> getResultList(String queryName, Object parameterObject) throws Exception;

	<T> List<T> getResultList(String queryName, Object parameterObject, int skipResults, int maxResults)
			throws Exception;

	SqlSession getSession();

	SqlSession getBatchSession();

	void resetSqlSessionFactory();

	<T> void getResultByHandler(String queryName, T parameterObject, ResultHandler handler) throws Exception;

	<T> void getResultByHandler(String queryName, T parameterObject, ResultHandler handler, int skipResults,
			int maxResults) throws Exception;

	void deleteObjectByModel(String queryName, IModel modelObject) throws Exception;

}
