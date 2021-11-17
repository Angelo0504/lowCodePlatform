/*******************************************************************************
 * Copyright 2017 Bstek
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.bstek.ureport.definition.datasource;

import java.sql.Connection;

/**
 * @author
 * @since 2月9日
 */
public interface BuildinDatasource {
	/**
	 * @return 返回数据源名称
	 */
	String name();
	/**
	 * @return 返回当前采用数据源的一个连接
	 */
	Connection getConnection();
}
