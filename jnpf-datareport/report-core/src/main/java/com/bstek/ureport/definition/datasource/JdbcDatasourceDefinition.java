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

import com.bstek.ureport.build.Dataset;
import com.bstek.ureport.definition.dataset.DatasetDefinition;
import com.bstek.ureport.definition.dataset.SqlDatasetDefinition;
import com.bstek.ureport.exception.ReportComputeException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author
 * @since 2016年12月27日
 */
public class JdbcDatasourceDefinition implements DatasourceDefinition {
	private String name;
	private String driver;
	private String url;
	private String username;
	private String password;
	private List<DatasetDefinition> datasets;

	public List<Dataset> buildDatasets(Connection conn,Map<String,Object> parameters){
		if(datasets==null || datasets.size()==0){
			return null;
		}
		if(conn==null)conn=getConnection();
		List<Dataset> list=new ArrayList<Dataset>();
		try{

			for(DatasetDefinition dsDef:datasets){
				SqlDatasetDefinition sqlDataset=(SqlDatasetDefinition)dsDef;
				Dataset ds=sqlDataset.buildDataset(parameters, conn);
				list.add(ds);
			}
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				throw new ReportComputeException(e);
			}
		}
		return list;
	}

	private Connection getConnection() {
		try {
			Class.forName(driver);
			Connection conn=DriverManager.getConnection(url, username, password);
			return conn;
		} catch (Exception e) {
			throw new ReportComputeException(e);
		}
	}

	@Override
	public DatasourceType getType() {
		return DatasourceType.jdbc;
	}

	@Override
	public List<DatasetDefinition> getDatasets() {
		return datasets;
	}
	public void setDatasets(List<DatasetDefinition> datasets) {
		this.datasets = datasets;
	}
	@Override
	public String getName() {
		return name;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setName(String name) {
		this.name = name;
	}
}
