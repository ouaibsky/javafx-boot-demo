package org.icroco.boot.javafx.config;/*
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

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.icroco.boot.javafx.MainPanePresenter;
import org.icroco.boot.javafx.pref.UserPref;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import javax.annotation.PreDestroy;
import javax.inject.Inject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@AutoConfigureBefore(MainPanePresenter.class)
@Slf4j
public class MyConfig {
	static String outputFileName = System.getProperty("user.home") + File.separatorChar
			+ ".onx" + File.separatorChar + "text.txt";

	@Inject
    UserPref userPreferences;

	@Inject
	ObjectMapper mapper;

	@Bean
	ObjectMapper mapper() {
		return new ObjectMapper();
	}

	@Bean
	ExecutorService executorService() {
		return Executors.newCachedThreadPool(new CustomizableThreadFactory("task"));
	}

	@Bean
	UserPref userPref(ObjectMapper mapper) {
		File f = new File(outputFileName);
		UserPref pref = null;
		if (f.exists()) {
			FileInputStream inputStream = null;
			try {
				inputStream = new FileInputStream(outputFileName);
				pref = mapper.readValue(inputStream, UserPref.class);
				log.info("Read from: {}, userPref: ", outputFileName, pref);
				return pref;
			}
			catch (IOException e) {
				log.error("Failed to load user pref", e);
			}
		}

		pref = new UserPref();
		return pref;
	}

	@PreDestroy
	public void preDestroy()  {
		File f = new File(outputFileName);
		f.getParentFile().mkdirs();

		try {
			FileOutputStream out = new FileOutputStream(outputFileName);

			mapper.writerWithDefaultPrettyPrinter().writeValue(out, userPreferences);
		} catch (IOException ex) {
			log.error("Failed to serialized", ex);
		}
		log.info("Saved: {}", outputFileName);
	}
}
