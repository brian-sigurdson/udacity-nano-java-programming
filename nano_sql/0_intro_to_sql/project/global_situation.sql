-- These queries are associated with the questions presented in the Global Situation section.

-- a. What was the total forest area (in sq km) of the world in 1990?
-- Please keep in mind that you can use the country record denoted as “World" in the region table.
select forest_area_sqkm as forest_area_sqkm_1990
from forestation
where year = 1990 and country_code = 'WLD';

-- b. What was the total forest area (in sq km) of the world in 2016?
-- Please keep in mind that you can use the country record in the table is denoted as “World.”
select forest_area_sqkm as forest_area_sqkm_2016
from forestation
where year = 2016 and country_code = 'WLD';

-- c. What was the change (in sq km) in the forest area of the world from 1990 to 2016?
with sqkm_1990 as
    (
        select country_code, forest_area_sqkm as forest_area_sqkm_1990
        from forestation
        where year = 1990 and country_code = 'WLD'
    ),
    sqkm_2016 as
    (
        select country_code, forest_area_sqkm as forest_area_sqkm_2016
        from forestation
        where year = 2016 and country_code = 'WLD'
    )
select sqkm_1990.country_code,
       forest_area_sqkm_1990,
       forest_area_sqkm_2016,
       forest_area_sqkm_2016 - forest_area_sqkm_1990 as sqkm_change
from sqkm_1990
join sqkm_2016 on sqkm_1990.country_code = sqkm_2016.country_code

-- d. What was the percent change in forest area of the world between 1990 and 2016?
with sqkm_1990 as
    (
        select country_code, forest_area_sqkm as forest_area_sqkm_1990
        from forestation
        where year = 1990 and country_code = 'WLD'
    ),
    sqkm_2016 as
    (
        select country_code, forest_area_sqkm as forest_area_sqkm_2016
        from forestation
        where year = 2016 and country_code = 'WLD'
    )
select sqkm_1990.country_code,
       forest_area_sqkm_1990,
       forest_area_sqkm_2016,
       forest_area_sqkm_2016 - forest_area_sqkm_1990 as sqkm_change,
       round((((forest_area_sqkm_2016 - forest_area_sqkm_1990) / forest_area_sqkm_1990)::numeric * 100),2) as percentage_change
from sqkm_1990
join sqkm_2016 on sqkm_1990.country_code = sqkm_2016.country_code

-- e. If you compare the amount of forest area lost between 1990 and 2016, to which country's total area in 2016 is it closest to?
-- I manually looked this up. It is Australia.
select country_name, forest_area_sqkm
from forestation
where year = 2016 and country_code = 'AUS'