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
 * {@link JsonPopulatedKey} can annotate on fields of any class which implements
 * {@link JsonPopulatable}. The annotated field will be populated when
 * {@link JsonPopulatable#setPopulatedJson} is called. The value of this
 * annotation is used as the JSON key to retrieve value form given JSON
 * data.<br>
 * <br>
 * Nested JSON data is also supported. Open the following link to know the
 * format of data accessing key of nested JSON.
 * 
 * @see <a href="https://github.com/wnameless/json-flattener">Json Flattener</a>
 *
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonPopulatedKey {

  String value();

}
