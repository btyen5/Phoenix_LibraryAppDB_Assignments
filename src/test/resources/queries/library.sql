-- US01
select count(id) from users; -- actual

select count(distinct id) from users; -- expected

-- US02
select count(*) from book_borrow where is_returned=0;

-- US03
select name from book_categories;

-- US04
select * from books where name='Clean Code';

-- US05
select bc.name,count(*) from book_borrow bb
inner join books b on bb.book_id = b.id
inner join book_categories bc on b.book_category_id=bc.id
group by name
order by 2 desc;

-- US06
select id,name,author from books
where name = 'Clean Code' and author='Robert C.Martin'
order by id desc;

-- US07
select full_name,b.name,bb.borrowed_date from users u
inner join book_borrow bb on u.id = bb.user_id
inner join books b on bb.book_id = b.id
where full_name='Test Student 1' and name='Head First Java'
order by 3 desc;

-- US08
select count(*) from users where status;