/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.server.manager.internal;

import com.liferay.portal.kernel.util.MapUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.server.manager.Executor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;
import org.osgi.service.component.annotations.ReferencePolicy;
import org.osgi.service.component.annotations.ReferencePolicyOption;

/**
 * @author Marcellus Tavares
 */
@Component(immediate = true, service = ExecutorServiceRegistry.class)
public class ExecutorServiceRegistry {

	public Set<String> getAvailableExecutorPaths() {
		return Collections.unmodifiableSet(_executors.keySet());
	}

	public Executor getExecutor(String path) {
		return _executors.get(path);
	}

	@Reference(
		cardinality = ReferenceCardinality.MULTIPLE,
		policy = ReferencePolicy.DYNAMIC,
		policyOption = ReferencePolicyOption.GREEDY,
		unbind = "unregisterExecutor"
	)
	protected synchronized void registerExecutor(
		Executor executor, Map<String, Object> properties) {

		String executorPath = MapUtil.getString(
			properties, "server.manager.executor.path");

		if (Validator.isNull(executorPath)) {
			throw new IllegalStateException(
				"Unable to register the Executor. " +
					"Please specify the \"server.manager.executor.path\""+
						" property.");
		}

		_executors.put(executorPath, executor);
	}

	protected synchronized void unregisterExecutor(
		Executor executor, Map<String, Object> properties) {

		String executorPath = MapUtil.getString(
			properties, "server.manager.executor.path");

		_executors.remove(executorPath);
	}

	private final Map<String, Executor> _executors = new HashMap<>();

}