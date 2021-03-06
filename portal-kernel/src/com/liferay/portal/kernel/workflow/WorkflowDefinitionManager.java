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

package com.liferay.portal.kernel.workflow;

import com.liferay.portal.kernel.messaging.proxy.MessagingProxy;
import com.liferay.portal.kernel.messaging.proxy.ProxyMode;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Micha Kiener
 * @author Shuyang Zhou
 * @author Brian Wing Shun Chan
 * @author Marcellus Tavares
 * @author Eduardo Lundgren
 */
@MessagingProxy(mode = ProxyMode.SYNC)
public interface WorkflowDefinitionManager {

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             #deployWorkflowDefinition(long, long, String, String,
	 *             byte[])}
	 * @review
	 */
	@Deprecated
	public WorkflowDefinition deployWorkflowDefinition(
			long companyId, long userId, String title, byte[] bytes)
		throws WorkflowException;

	public default WorkflowDefinition deployWorkflowDefinition(
			long companyId, long userId, String title, String name,
			byte[] bytes)
		throws WorkflowException {

		throw new UnsupportedOperationException();
	}

	public int getActiveWorkflowDefinitionCount(long companyId)
		throws WorkflowException;

	public int getActiveWorkflowDefinitionCount(long companyId, String name)
		throws WorkflowException;

	public List<WorkflowDefinition> getActiveWorkflowDefinitions(
			long companyId, int start, int end,
			OrderByComparator<WorkflowDefinition> orderByComparator)
		throws WorkflowException;

	public List<WorkflowDefinition> getActiveWorkflowDefinitions(
			long companyId, String name, int start, int end,
			OrderByComparator<WorkflowDefinition> orderByComparator)
		throws WorkflowException;

	/**
	 * @deprecated As of 7.0.0, replaced by {@link
	 *             #getLatestWorkflowDefinition(long, String)}
	 * @review
	 */
	@Deprecated
	public WorkflowDefinition getLatestKaleoDefinition(
			long companyId, String name)
		throws WorkflowException;

	public default WorkflowDefinition getLatestWorkflowDefinition(
			long companyId, String name)
		throws WorkflowException {

		throw new UnsupportedOperationException();
	}

	public default List<WorkflowDefinition> getLatestWorkflowDefinitions(
			long companyId, int start, int end,
			OrderByComparator<WorkflowDefinition> orderByComparator)
		throws WorkflowException {

		Map<String, WorkflowDefinition> latestWorkflowDefinitions =
			new HashMap<>();

		List<WorkflowDefinition> workflowDefinitions = getWorkflowDefinitions(
			companyId, start, end, orderByComparator);

		for (WorkflowDefinition workflowDefinition : workflowDefinitions) {
			String name = workflowDefinition.getName();

			WorkflowDefinition latestWorkflowDefinition =
				latestWorkflowDefinitions.get(name);

			if (latestWorkflowDefinition != null) {
				if (workflowDefinition.getVersion() >
						latestWorkflowDefinition.getVersion()) {

					latestWorkflowDefinitions.put(name, workflowDefinition);
				}
			}
			else {
				latestWorkflowDefinitions.put(name, workflowDefinition);
			}
		}

		return ListUtil.fromMapValues(latestWorkflowDefinitions);
	}

	public WorkflowDefinition getWorkflowDefinition(
			long companyId, String name, int version)
		throws WorkflowException;

	public int getWorkflowDefinitionCount(long companyId)
		throws WorkflowException;

	public int getWorkflowDefinitionCount(long companyId, String name)
		throws WorkflowException;

	public List<WorkflowDefinition> getWorkflowDefinitions(
			long companyId, int start, int end,
			OrderByComparator<WorkflowDefinition> orderByComparator)
		throws WorkflowException;

	public List<WorkflowDefinition> getWorkflowDefinitions(
			long companyId, String name, int start, int end,
			OrderByComparator<WorkflowDefinition> orderByComparator)
		throws WorkflowException;

	public void undeployWorkflowDefinition(
			long companyId, long userId, String name, int version)
		throws WorkflowException;

	public WorkflowDefinition updateActive(
			long companyId, long userId, String name, int version,
			boolean active)
		throws WorkflowException;

	public WorkflowDefinition updateTitle(
			long companyId, long userId, String name, int version, String title)
		throws WorkflowException;

	public void validateWorkflowDefinition(byte[] bytes)
		throws WorkflowException;

}