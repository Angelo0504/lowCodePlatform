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
package com.bstek.ureport.build.aggregate;

import com.bstek.ureport.build.BindData;
import com.bstek.ureport.build.Context;
import com.bstek.ureport.expression.model.expr.dataset.DatasetExpression;
import com.bstek.ureport.model.Cell;

import java.util.List;

/**
 * @author
 * @since 1月20日
 */
public class ReselectAggregate extends SelectAggregate {
	@Override
	public List<BindData> aggregate(DatasetExpression expr, Cell cell,Context context) {
		List<?> objList=context.getDatasetData(expr.getDatasetName());
		return doAggregate(expr, cell, context,objList);
	}
}
