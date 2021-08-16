-- These queries are associated with the questions presented in the COUNTRY-LEVEL DETAIL section.

-- The first paragraph of part 3 of the document, COUNTRY-LEVEL DETAIL, states information about success stories,
-- but were not included in the preparation questions below, so I'm creating it now.

-- success stories
-- Which country increased forest area from 1990 to 2016?
-- What was the percentage change?
-- Which is the country with the next largest increase in forest area from 1990 to 2016?
-- What was the percentage change?
-- China,527229.06,33.55
-- United States,79200,2.62
with sqkm_1990 as
    (
        select country_name, forest_area_sqkm as forest_area_sqkm_1990
        from forestation
        where year = 1990 and country_name != 'World'
    ),
    sqkm_2016 as
    (
        select country_name, forest_area_sqkm as forest_area_sqkm_2016
        from forestation
        where year = 2016 and country_name != 'World'
    )
select sqkm_1990.country_name,
       round((forest_area_sqkm_2016 - forest_area_sqkm_1990)::numeric,2) as sqkm_change,
       round(((forest_area_sqkm_2016 - forest_area_sqkm_1990)/(forest_area_sqkm_1990))::numeric * 100,2) as percent_change
from sqkm_1990
join sqkm_2016 on sqkm_1990.country_name = sqkm_2016.country_name
where (forest_area_sqkm_2016 - forest_area_sqkm_1990) is not null
order by percent_change desc
limit 2

-- a. Which 5 countries saw the largest amount decrease in forest area from 1990 to 2016?
-- What was the difference in forest area for each?
-- Brazil,-541510
-- Indonesia,-282193.98
-- Myanmar,-107234
-- Nigeria,-106506
-- Tanzania,-102320
with sqkm_1990 as
    (
        select country_name, forest_area_sqkm as forest_area_sqkm_1990
        from forestation
        where year = 1990 and country_name != 'World'
    ),
    sqkm_2016 as
    (
        select country_name, forest_area_sqkm as forest_area_sqkm_2016
        from forestation
        where year = 2016 and country_name != 'World'
    )
select sqkm_1990.country_name, round((forest_area_sqkm_2016 - forest_area_sqkm_1990)::numeric,2) as sqkm_change
from sqkm_1990
join sqkm_2016 on sqkm_1990.country_name = sqkm_2016.country_name
order by sqkm_change
limit 5

-- b. Which 5 countries saw the largest percent decrease in forest area from 1990 to 2016?
-- What was the percent change to 2 decimal places for each?

with sqkm_1990 as
    (
        select country_name, region, forest_area_sqkm as forest_area_sqkm_1990
        from forestation
        where year = 1990 and country_name != 'World'
    ),
    sqkm_2016 as
    (
        select country_name, region, forest_area_sqkm as forest_area_sqkm_2016
        from forestation
        where year = 2016 and country_name != 'World'
    )
select sqkm_1990.country_name, sqkm_1990.region,
       round((forest_area_sqkm_2016 - forest_area_sqkm_1990)::numeric,2) as sqkm_change,
       round(((forest_area_sqkm_2016 - forest_area_sqkm_1990)/(forest_area_sqkm_1990))::numeric * 100,2) as percent_change
from sqkm_1990
join sqkm_2016 on sqkm_1990.country_name = sqkm_2016.country_name
where (forest_area_sqkm_2016 - forest_area_sqkm_1990) is not null
-- order by sqkm_change
order by percent_change
limit 5

-- c. If countries were grouped by percent forestation in quartiles, which group had the most countries in it in 2016?
-- NOTE:  Per the grading rubric, we need to implement a case statement for this solution.
-- NOTE:  Quartiles should be 25% each, only one group may exceed, if the result set is not divisible by 4
-- ***  I think the wording of the question is not actually looking for the quartile, but is actually looking for the
--      results ordered by percent and grouped by <25%, <50%, <75%, <100%
-- with quartile_sums_2016 as
--     (
--         select
--             case when ntile(4) over (order by percent_forest) = 1 then 1 else 0 end as q1,
--             case when ntile(4) over (order by percent_forest) = 2 then 1 else 0 end as q2,
--             case when ntile(4) over (order by percent_forest) = 3 then 1 else 0 end as q3,
--             case when ntile(4) over (order by percent_forest) = 4 then 1 else 0 end as q4
--         from forestation
--         where year = 2016
--     )
-- select sum(q1) as sum_quartile_1, sum(q2) as sum_quartile_2, sum(q3) as sum_quartile_3, sum(q4) as sum_quartile_4
-- from quartile_sums_2016

-- *** I believe that this is the requested query.
with quartile_sums_2016 as
    (
        select
            case when percent_forest <= 25.00 then 1 else 0 end as q1,
            case when percent_forest < 25.00 and percent_forest <= 50.00 then 1 else 0 end as q2,
            case when percent_forest < 50.00 and percent_forest <= 75.00 then 1 else 0 end as q3,
            case when percent_forest < 75.00 and percent_forest <= 100.00 then 1 else 0 end as q4
        from forestation
        where year = 2016
    )
select sum(q1) as sum_quartile_1, sum(q2) as sum_quartile_2, sum(q3) as sum_quartile_3, sum(q4) as sum_quartile_4
from quartile_sums_2016

-- d. List all of the countries that were in the 4th quartile (percent forest > 75%) in 2016.
select country_name
from forestation
where year = 2016 and percent_forest > 75.00


-- e. How many countries had a percent forestation higher than the United States in 2016?
-- 94
-- NOTE:  This is a self-join query.
select count(*)
from forestation a
join forestation b on a.year = b.year and a.country_code = b.country_code
where a.year = 2016 and a.percent_forest >
                        (
                            select percent_forest
                            from forestation
                            where year = 2016 and country_code = 'USA'
                        )
