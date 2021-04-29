package com.solvd.buildingCompany.dao.projects;

import java.util.List;

import com.solvd.buildingCompany.model.projects.Asset;

public interface IAssetDAO {

	public List<Asset> getAssetsByProjectId(long id);
	public List<Asset> getHistoricAssetsByProjectId(long id);
}
