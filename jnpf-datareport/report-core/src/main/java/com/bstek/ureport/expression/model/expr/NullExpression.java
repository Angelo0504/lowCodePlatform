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
package com.bstek.ureport.expression.model.expr;

import com.bstek.ureport.build.Context;
import com.bstek.ureport.expression.model.data.ExpressionData;
import com.bstek.ureport.expression.model.data.NoneExpressionData;
import com.bstek.ureport.model.Cell;

/**
 * @author
 * @since 2016年12月23日
 */
public class NullExpression extends BaseExpression {
	private static final long serialVersionUID = -5448531052217619991L;

	@Override
	public ExpressionData<?> compute(Cell cell, Cell currentCell,Context context) {
		return new NoneExpressionData();
	}
}
