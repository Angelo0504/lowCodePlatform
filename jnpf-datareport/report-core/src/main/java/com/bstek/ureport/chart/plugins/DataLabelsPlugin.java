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
package com.bstek.ureport.chart.plugins;
/**
 * @author
 * @since 2018年7月6日
 */
public class DataLabelsPlugin implements Plugin {
	private boolean display;
	@Override
	public String getName() {
		return "data-labels";
	}
	@Override
	public String toJson(String type) {
		StringBuilder sb=new StringBuilder();
		sb.append("\"datalabels\":{\"display\":"+display+",");
		sb.append("\"font\":{");
		sb.append("\"size\":14,");
		sb.append("\"weight\":\"bold\"");
		sb.append("}");
		sb.append("}");
		return sb.toString();
	}
	public boolean isDisplay() {
		return display;
	}
	public void setDisplay(boolean display) {
		this.display = display;
	}
}
