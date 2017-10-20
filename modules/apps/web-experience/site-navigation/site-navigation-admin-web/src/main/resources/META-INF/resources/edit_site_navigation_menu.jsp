<%--
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
--%>

<%@ include file="/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

SiteNavigationMenu siteNavigationMenu = siteNavigationAdminDisplayContext.getSiteNavigationMenu();

portletDisplay.setShowBackIcon(true);
portletDisplay.setURLBack(redirect);

renderResponse.setTitle(((siteNavigationMenu == null) ? LanguageUtil.get(request, "add-new-menu") : siteNavigationMenu.getName()));
%>

<portlet:actionURL name="/navigation_menu/edit_site_navigation_menu" var="editSitaNavigationMenuURL">
	<portlet:param name="mvcPath" value="/edit_site_navigation_menu.jsp" />
	<portlet:param name="groupId" value="<%= String.valueOf(scopeGroupId) %>" />
</portlet:actionURL>

<aui:form action="<%= editSitaNavigationMenuURL %>" cssClass="container-fluid-1280" name="fm">
	<aui:input name="redirect" type="hidden" value="<%= redirect %>" />
	<aui:input name="siteNavigationMenuId" type="hidden" value="<%= siteNavigationAdminDisplayContext.getSiteNavigationMenuId() %>" />
	<aui:input name="selectedItemType" type="hidden" value="" />

	<aui:model-context bean="<%= siteNavigationMenu %>" model="<%= SiteNavigationMenu.class %>" />

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input autoFocus="<%= true %>" label="name" name="name" placeholder="name">
				<aui:validator name="required" />
			</aui:input>
		</aui:fieldset>

		<c:choose>
			<c:when test="<%= siteNavigationMenu != null %>">

			</c:when>
			<c:otherwise>
				<aui:fieldset>
					<div class="d-flex" id="<portlet:namespace/>siteNavigationMenuItemTypes">

						<%
						for (SiteNavigationMenuItemType siteNavigationMenuItemType : siteNavigationMenuItemTypeRegistry.getSiteNavigationMenuItemTypes()) {
						%>

							<div class="card col-md-2 item-type pt-xl-3 text-center" data-type="<%= siteNavigationMenuItemType.getType() %>">
								<liferay-ui:icon
									icon="<%= siteNavigationMenuItemType.getIcon() %>"
									markupView="lexicon"
								/>

								<h5 class="mt-xl-3">
									<%= siteNavigationMenuItemType.getLabel(locale) %>
								</h5>
							</div>

						<%
						}
						%>

					</div>

					<div class="text-center">
						<h3>
							<span class="d-block mt-xl-3">
								<liferay-ui:message key="this-menu-is-empty" />
							</span>
							<span class="d-block mt-xl-3">
								<liferay-ui:message key="please-add-elements-like-pages-categories-urls" />
							</span>
						</h3>
					</div>
				</aui:fieldset>
			</c:otherwise>
		</c:choose>
	</aui:fieldset-group>

	<aui:button-row>
		<aui:button cssClass="btn-lg" type="submit" />

		<aui:button cssClass="btn-lg" href="<%= redirect %>" type="cancel" />
	</aui:button-row>
</aui:form>

<c:if test="<%= siteNavigationMenu == null %>">
	<aui:script use="aui-base">
		A.one('#<portlet:namespace/>siteNavigationMenuItemTypes').delegate(
			'click',
			function(event) {
				var currentTarget = event.currentTarget;

				document.getElementById('<portlet:namespace/>selectedItemType').value = currentTarget.attr('data-type');

				submitForm(document.<portlet:namespace/>fm);
			},
			'.item-type'
		);
	</aui:script>
</c:if>