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

package com.liferay.dynamic.data.mapping.type.validation;

import com.liferay.dynamic.data.mapping.registry.BaseDDMFormFieldType;
import com.liferay.dynamic.data.mapping.registry.DDMFormFieldType;
import com.liferay.dynamic.data.mapping.registry.DDMFormFieldValueAccessor;
import com.liferay.dynamic.data.mapping.registry.DDMFormFieldValueParameterSerializer;
import com.liferay.dynamic.data.mapping.registry.DDMFormFieldValueRendererAccessor;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;

/**
 * @author Bruno Basto
 */
@Component(
	immediate = true, 
	property = {
		"ddm.form.field.type.js.class=Liferay.DDM.Field.Validation",
		"ddm.form.field.type.js.module=liferay-ddm-form-field-validation",
		"ddm.form.field.type.name=validation",
		"ddm.form.field.type.system=true"
	}, 
	service = DDMFormFieldType.class
)
public class ValidationDDMFormFieldType extends BaseDDMFormFieldType {

	@Override

	@Override
	public DDMFormFieldValueAccessor<?> getDDMFormFieldValueAccessor(
		Locale locale) {

		return null;
	}

	@Override
	public DDMFormFieldValueParameterSerializer
		getDDMFormFieldValueParameterSerializer() {

		return null;
	}

	@Override
	public DDMFormFieldValueRendererAccessor
		getDDMFormFieldValueRendererAccessor(Locale locale) {

		return null;
	}

	@Override
	public String getName() {
		return "validation";
	}

}
