package com.qutiao.service;

import com.qutiao.domain.QutiaoDispatcher;

public interface IDispatcherService {
	public long addDispatcher(QutiaoDispatcher dispatcher);

	public QutiaoDispatcher searchDispatcherById(long id);

}
