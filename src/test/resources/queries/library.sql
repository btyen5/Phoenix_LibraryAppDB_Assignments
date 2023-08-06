select count(id) from users; -- actual

select count(distinct id) from users; -- expected