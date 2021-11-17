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
package com.bstek.ureport.expression.function;

import com.bstek.ureport.build.Context;
import com.bstek.ureport.expression.model.data.ExpressionData;
import com.bstek.ureport.expression.model.data.ObjectExpressionData;
import com.bstek.ureport.expression.model.data.ObjectListExpressionData;
import com.bstek.ureport.model.Cell;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * @since 1月3日
 */
public class ListFunction implements Function {

	@Override
	public Object execute(List<ExpressionData<?>> dataList, Context context,Cell currentCell) {
		List<Object> list= new ArrayList<Object>();
		for(ExpressionData<?> d:dataList){
			if(d instanceof ObjectExpressionData){
				ObjectExpressionData exprData=(ObjectExpressionData)d;
				list.add(exprData.getData());
			}else if(d instanceof ObjectListExpressionData){
				ObjectListExpressionData listData=(ObjectListExpressionData)d;
				list.addAll(listData.getData());
			}
		}
		return list;
	}

	@Override
	public String name() {
		return "list";
	}
}
