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
package com.bstek.ureport.expression.parse.builder;

import com.bstek.ureport.dsl.ReportParserParser.UnitContext;
import com.bstek.ureport.expression.model.expr.BaseExpression;
import com.bstek.ureport.expression.model.expr.CurrentCellValueExpression;

/**
 * @author
 * @since 7月11日
 */
public class CurrentCellValueExpressionBuilder implements ExpressionBuilder {

	@Override
	public BaseExpression build(UnitContext unitContext) {
		return new CurrentCellValueExpression();
	}

	@Override
	public boolean support(UnitContext unitContext) {
		return unitContext.currentCellValue()!=null;
	}

}
