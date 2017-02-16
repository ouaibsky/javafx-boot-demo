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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.beans.property.SimpleStringProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter()
//@Accessors(chain = true)
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserPref {
	String test;
	SalesAccount salesAccount;
	TraderAccount traderAccount;

	@JsonIgnore
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	SimpleStringProperty login = new SimpleStringProperty("test");

	@JsonProperty("login")
	public String getLogin() {
		return this.login.get();
	}

	@JsonProperty("login")
	public void setLogin(final String login) {
		this.login.setValue(login);
	}

	// @JsonProperty(value = "foo")
	// public String getFoo() {
	// return foo.getValue();
	// }
	//
	// @JsonProperty(value = "foo")
	//
	// public void setFoo(String value) {
	// foo.setValue(value);
	// }
}
