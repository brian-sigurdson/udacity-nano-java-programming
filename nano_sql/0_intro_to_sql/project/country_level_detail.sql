-- These queries are associated with the RECOMMENDATIONS section.

-- Income group: count, forest area, land area, percent forest
with tbl_1990 as
    (
        select income_group,
            count(*) as count,
            round(sum(forest_area_sqkm)::numeric,2) as ttl_forest_area,
            round(sum(total_area_sqkm)::numeric,2) as ttl_land_area,
            round((sum(forest_area_sqkm) / sum(total_area_sqkm))::numeric * 100, 2) as pct_forest
        from forestation
        where year = 1990 and country_code != 'WLD'
        group by 1
    ),
  tbl_2016 as
      (
          select income_group,
                 count(*) as count,
                 round(sum(forest_area_sqkm)::numeric,2) as ttl_forest_area,
                 round(sum(total_area_sqkm)::numeric,2) as ttl_land_area,
                 round((sum(forest_area_sqkm) / sum(total_area_sqkm))::numeric * 100, 2) as pct_forest
          from forestation
          where year = 2016
            and country_code != 'WLD'
          group by 1
      )
select tbl_1990.income_group, tbl_2016.count,
       tbl_1990.ttl_forest_area as ttl_forest_area_1990,
       tbl_2016.ttl_forest_area as ttl_forest_area_2016,
       tbl_2016.ttl_forest_area - tbl_1990.ttl_forest_area as ttl_forest_area_change,
       tbl_1990.pct_forest as pct_forest_1990,
       tbl_2016.pct_forest as pct_forest_2016,
       tbl_2016.pct_forest - tbl_1990.pct_forest as pct_change
from tbl_1990
join tbl_2016 on tbl_1990.income_group = tbl_2016.income_group