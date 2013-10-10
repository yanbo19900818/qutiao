package com.qutiao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qutiao.dao.IDispatcherDao;
import com.qutiao.domain.QutiaoDispatcher;

@Service
public class DispatcherService implements IDispatcherService {
	@Autowired
	private IDispatcherDao dispatcherDao;

	@Override
	public long addDispatcher(QutiaoDispatcher dispatcher) {
		// TODO Auto-generated method stub
		long id = dispatcherDao.saves(dispatcher);
		return id;
	}

	@Override
	public QutiaoDispatcher searchDispatcherById(long id) {
		// TODO Auto-generated method stub
		QutiaoDispatcher dispatcher = dispatcherDao.searchById(id);
		return dispatcher;
	}


}
