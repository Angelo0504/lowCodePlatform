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
package com.bstek.ureport.expression.function.date;

import com.bstek.ureport.build.Context;
import com.bstek.ureport.expression.model.data.ExpressionData;
import com.bstek.ureport.model.Cell;

import java.util.Calendar;
import java.util.List;

/**
 * @author
 * @since 1月22日
 */
public class MonthFunction extends CalendarFunction {

	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context,Cell currentCell) {
		Calendar c = buildCalendar(dataList);
		int month=c.get(Calendar.MONTH)+1;
		return month+1;
	}

	@Override
	public String name() {
		return "month";
	}
}
