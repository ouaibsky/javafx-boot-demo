package org.icroco.boot.javafx.pref;/*
									* Copyright 2015 the original author or authors.
									*
									* Licensed under the Apache License, Version 2.0 (the "License");
									* you may not use this file except in compliance with the License.
									* You may obtain a copy of the License at
									*
									* http://www.apache.org/licenses/LICENSE-2.0
									*
									* Unless required by applicable law or agreed to in writing, software
									* distributed under the License is distributed on an "AS IS" BASIS,
									* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
									* See the License for the specific language governing permissions and
									* limitations under the License.
									*/

import java.beans.BeanDescriptor;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class UserPrefBeanInfo extends SimpleBeanInfo {
	private static final BeanDescriptor beanDescriptor = new BeanDescriptor(UserPrefBeanInfo.class);
	private static PropertyDescriptor[] propDescriptors;

	static {
		beanDescriptor.setDisplayName("/user Preference");
	}

	@Override
	public BeanDescriptor getBeanDescriptor() {
		return beanDescriptor;
	}

	@Override
	public int getDefaultPropertyIndex() {
		return 0;
	}

	@Override
	public PropertyDescriptor[] getPropertyDescriptors() {
		if (propDescriptors == null) {
			propDescriptors = new PropertyDescriptor[1];
			try {
				propDescriptors[0] = new PropertyDescriptor("login", UserPref.class, "getLogin", "setLogin");
				propDescriptors[0].setDisplayName("Login");

//				CustomPropertyDescriptor cdp = new CustomPropertyDescriptor("id", SampleBean.class, "getId", "setId");
//				cdp.setDisplayName("Id");
//				cdp.setEditable(false);
//				propDescriptors[0] = cdp;
//				propDescriptors[1] = new PropertyDescriptor("firstName", SampleBean.class, "getFirstName", "setFirstName");
//				propDescriptors[1].setDisplayName("First Name");
//				propDescriptors[2] = new PropertyDescriptor("lastName", SampleBean.class, "getLastName", "setLastName");
//				propDescriptors[2].setDisplayName("Last Name");
//				propDescriptors[3] = new PropertyDescriptor("address", SampleBean.class, "getAddress", "setAddress");
//				propDescriptors[3].setDisplayName("Address");
//				propDescriptors[3].setPropertyEditorClass(PopupPropertyEditor.class);
//				propDescriptors[4] = new PropertyDescriptor("hiddenValue", SampleBean.class, "getHiddenValue", "setHiddenValue");
//				propDescriptors[4].setDisplayName("Hidden Value");
//				propDescriptors[4].setHidden(true);
			} catch (IntrospectionException ex) {
				ex.printStackTrace();
			}
		}
		return propDescriptors;
	}
}
