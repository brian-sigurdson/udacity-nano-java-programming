-- These queries are associated with the questions presented in the REGIONAL OUTLOOK section.

-- Create a table that shows the Regions and their percent forest area
--      (sum of forest area divided by sum of land area) in 1990 and 2016. (Note that 1 sq mi = 2.59 sq km).
--      Based on the table you created, answer the following questions.
with sqkm_1990 as
    (
        select region,
               sum(forest_area_sqkm) as sum_forest_area_sqkm_1990,
               sum(total_area_sqkm) as sum_land_area_sqkm_1990,
               round((sum(forest_area_sqkm) / sum(total_area_sqkm))::numeric * 100,2) as percentage_forest_area_1990
        from forestation
        where year = 1990
        group by 1
    ),
    sqkm_2016 as
    (
        select region,
               sum(forest_area_sqkm) as sum_forest_area_sqkm_2016,
               sum(total_area_sqkm) as sum_land_area_sqkm_2016,
               round((sum(forest_area_sqkm) / sum(total_area_sqkm))::numeric * 100,2) as percentage_forest_area_2016
        from forestation
        where year = 2016
        group by 1
    )
select sqkm_1990.region,
       percentage_forest_area_1990,
       percentage_forest_area_2016,
       percentage_forest_area_2016 - percentage_forest_area_1990 as percentage_forest_area_change
from sqkm_1990
join sqkm_2016 on sqkm_1990.region = sqkm_2016.region
-- order by sqkm_forest_area_change
order by percentage_forest_area_1990


-- a. What was the percent forest of the entire world in 2016?
-- 31.38
-- Which region had the HIGHEST percent forest in 2016, and which had the LOWEST, to 2 decimal places?
-- Highest = Latin America & Caribbean, Lowest = Middle East & North Africa

-- b. What was the percent forest of the entire world in 1990?
-- 32.42
-- Which region had the HIGHEST percent forest in 1990, and which had the LOWEST, to 2 decimal places?
-- Highest = Latin America & Caribbean, Lowest = Middle East & North Africa

-- c. Based on the table you created, which regions of the world DECREASED in forest area from 1990 to 2016?
-- Latin America & Caribbean, Sub-Saharan Africa