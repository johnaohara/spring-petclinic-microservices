/*
 * Copyright 2002-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.quarkus.samples.petclinic.discovery;

import io.quarkus.boot.QuarkusApplication;
import io.quarkus.boot.autoconfigure.QuarkusBootApplication;
import io.quarkus.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Maciej Szarlinski
 */
@EnableEurekaServer
@QuarkusBootApplication
public class DiscoveryServerApplication {

	public static void main(String[] args) {
		QuarkusApplication.run(DiscoveryServerApplication.class, args);
	}
}
