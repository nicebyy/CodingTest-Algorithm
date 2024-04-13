-- 코드를 작성해주세요

select 
result.route, 
concat(result.TOTAL_DISTANCE,"km") as TOTAL_DISTANCE,
concat(result.AVERAGE_DISTANCE,"km") as AVERAGE_DISTANCE

from(select 
s.route,
round(sum(s.D_BETWEEN_DIST),2) as TOTAL_DISTANCE	,
round(avg(s.D_BETWEEN_DIST),2) as AVERAGE_DISTANCE
from SUBWAY_DISTANCE s
group by route
order by TOTAL_DISTANCE desc) as result