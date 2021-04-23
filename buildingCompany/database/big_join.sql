#Select all projects with its contracts, full abbreviation of the locations AND the machines
#that are working on it.
SELECT proj.id AS ProjectId, country.abbreviation AS Country,
		city.abbreviation AS City,
        location.name AS location,
        contract.id AS ContractId,
        contract.prize AS ContractPrize,
        asset.id AS AssetId,
        machine.id AS MachineId,
        company.name AS CompanyName,
        mt.name AS MachineType
FROM COUNTRY country
INNER JOIN CITIES city ON (city.country_id = contry.id)
INNER JOIN LOCATION location ON (city.id = location.city_id)
INNER JOIN PROJECT proj ON (proj.location_id = location.id)
INNER JOIN CONTRACTS contract ON (contract.project_id=proj.id)
INNER JOIN ASSETS asset ON (asset.project_id = proj.id)
INNER JOIN MACHINES machine ON (asset.id= machines.asset_id)
INNER JOIN COMPANY company ON (company.id = machines.company_id)
INNER JOIN MACHINES_TYPE mt ON (machine.type_id = mt.id)

#Select all projects with its contracts, full locations AND the 
#amount of machines each company have working on it.