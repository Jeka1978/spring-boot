/*
 * Copyright 2012-2015 the original author or authors.
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

package org.springframework.boot.actuate.endpoint.mvc;

import java.util.Collections;
import java.util.Map;

import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * A strategy for the MVC layer on top of an {@link Endpoint}. Implementations are allowed
 * to use {@code @RequestMapping} and the full Spring MVC machinery, but should not use
 * {@code @Controller} or {@code @RequestMapping} at the type level (since that would lead
 * to a double mapping of paths, once by the regular MVC handler mappings and once by the
 * {@link EndpointHandlerMapping}).
 *
 * @author Dave Syer
 */
public interface MvcEndpoint {

	public static final ResponseEntity<Map<String, String>> DISABLED_RESPONSE = new ResponseEntity<Map<String, String>>(
			Collections.singletonMap("message", "This endpoint is disabled"),
			HttpStatus.NOT_FOUND);

	/**
	 * Return the MVC path of the endpoint.
	 * @return the endpoint path
	 */
	String getPath();

	/**
	 * Return if the endpoint exposes sensitive information.
	 * @return if the endpoint is sensitive
	 */
	boolean isSensitive();

	/**
	 * Return the type of {@link Endpoint} exposed, or {@code null} if this
	 * {@link MvcEndpoint} exposes information that cannot be represented as a traditional
	 * {@link Endpoint}.
	 * @return the endpoint type
	 */
	@SuppressWarnings("rawtypes")
	Class<? extends Endpoint> getEndpointType();

}
