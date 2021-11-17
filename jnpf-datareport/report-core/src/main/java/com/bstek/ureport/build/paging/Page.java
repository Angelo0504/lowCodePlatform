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
package com.bstek.ureport.build.paging;

import com.bstek.ureport.model.Column;
import com.bstek.ureport.model.Row;

import java.util.List;

/**
 * @author
 * @since 1月17日
 */
public class Page {
	private List<Row> rows;
	private List<Column> columns;
	private HeaderFooter header;
	private HeaderFooter footer;

	public Page(List<Row> rows,List<Column> columns) {
		this.rows = rows;
		this.columns=columns;
	}
	public List<Row> getRows() {
		return rows;
	}
	public List<Column> getColumns() {
		return columns;
	}
	public HeaderFooter getHeader() {
		return header;
	}
	public void setHeader(HeaderFooter header) {
		this.header = header;
	}
	public HeaderFooter getFooter() {
		return footer;
	}
	public void setFooter(HeaderFooter footer) {
		this.footer = footer;
	}
}
