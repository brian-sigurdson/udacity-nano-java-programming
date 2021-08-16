-- view for project

create or replace view forestation as

select r.country_name,
       r.country_code,
       r.region,
       r.income_group,
       f.year,
       f.forest_area_sqkm,
       f.forest_area_sqkm / 2.59 as forest_area_sq_mi,
       la.total_area_sq_mi,
       la.total_area_sq_mi * 2.59 as total_area_sqkm,
       round( (f.forest_area_sqkm / (la.total_area_sq_mi * 2.59) * 100)::numeric, 2) as percent_forest
from regions r
join forest_area f on r.country_code = f.country_code
join land_area la on f.country_code = la.country_code and f.year = la.year
order by year, country_name