/*
 *
 * Copyright 2019 Wei-Ming Wu
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 */
package com.github.wnameless.json;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Because {@link JsonPopulatedKey} can NOT fulfill all the needs of Java bean
 * data populating, {@link JsonPopulatedValue} is introduced to solve this
 * problem. {@link JsonPopulatedValue} allows user to provide a
 * {@link JsonPopulatedValueCustomizer} which can process any input JSON data
 * and produce a final result used to set on the annotated field.
 * 
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonPopulatedValue {

  Class<? extends JsonPopulatedValueCustomizer> value();

}
