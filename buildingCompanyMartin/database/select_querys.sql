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
FROM COUNTRIES country
INNER JOIN CITIES city ON (city.country_id = country.id)
INNER JOIN LOCATIONS location ON (city.id = location.city_id)
INNER JOIN PROJECTS proj ON (proj.location_id = location.id)
INNER JOIN CONTRACTS contract ON (contract.project_id=proj.id)
INNER JOIN ASSETS asset ON (asset.project_id = proj.id)
INNER JOIN MACHINES machine ON (asset.id= machine.asset_id)
INNER JOIN COMPANIES company ON (company.id = machine.company_id)
INNER JOIN MACHINE_TYPES mt ON (machine.type_id = mt.id);

#Select all projects with its contracts, full locations AND the 
#amount of machines each company have working on it.
SELECT company.id, company.name, count(*)
FROM COUNTRIES country
INNER JOIN CITIES city ON (city.country_id = country.id)
INNER JOIN LOCATIONS location ON (city.id = location.city_id)
INNER JOIN PROJECTS proj ON (proj.location_id = location.id)
INNER JOIN CONTRACTS contract ON (contract.project_id=proj.id)
INNER JOIN ASSETS asset ON (asset.project_id = proj.id)
INNER JOIN MACHINES machine ON (asset.id= machine.asset_id)
INNER JOIN COMPANIES company ON (company.id = machine.company_id)
INNER JOIN MACHINE_TYPES mt ON (machine.type_id = mt.id)
GROUP BY company.id;

#AVG salary per project

SELECT proj.id, AVG(employee.salary)
FROM PROJECTS proj
LEFT JOIN ASSETS asset ON (asset.project_id = proj.id)
INNER JOIN EMPLOYEES employee ON (asset.id = employee.asset_id)
GROUP BY proj.id;

#Contracts with the amount it have been paid
SELECT contract.id, contract.prize, SUM(payment.amount)
FROM CONTRACTS contract 
INNER JOIN PAYMENTS payment ON (payment.contract_id = contract.id)
GROUP BY contract.id;

#Countries with the count of locations
SELECT country.id, count(*)
FROM COUNTRIES country
INNER JOIN CITIES city ON (country.id= city.country_id)
INNER JOIN LOCATIONS location ON (city.id = location.city_id)
GROUP BY country.id;


#With having
SELECT proj.id, AVG(employee.salary)
FROM PROJECTS proj
LEFT JOIN ASSETS asset ON (asset.project_id = proj.id)
INNER JOIN EMPLOYEES employee ON (asset.id = employee.asset_id)
GROUP BY proj.id
HAVING AVG(employee.salary) > 1000;

#Sum of contracts prices of those clients that have more than 1 contract
SELECT client.id, client.name, SUM(contract.prize)
FROM CLIENTS client 
INNER JOIN CONTRACTS contract ON (client.id = contract.client_id)
GROUP BY client.id
HAVING COUNT(*) > 1;

SELECT proj.id, AVG(asset.weekly_hours)
FROM PROJECTS proj
INNER JOIN ASSETS asset ON (asset.project_id = proj.id)
GROUP BY proj.id
HAVING AVG(asset.weekly_hours) > 30; 