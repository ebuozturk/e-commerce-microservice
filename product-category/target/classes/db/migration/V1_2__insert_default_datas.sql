-- insert into category values((SELECT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring)),'Smart Phone');
-- insert into category values((SELECT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring)),'Notebook');
-- insert into category values((SELECT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring)),'Electronics');
-- insert into category values((SELECT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring)),'Men Fashion');
-- insert into category values((SELECT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring)),'Women Fashion');
-- insert into category values((SELECT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring)),'Sports');
--
-- insert into product values((SELECT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring)),
--                            'Lorem ipsum dolor sit amet,',
--                            'Iphone 13',
--                            1,
--                            1100,
--                            55,
--                            (select id from category where name = 'Smart Phone')
--                            );
--
-- insert into product values((SELECT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring)),
--     'Lorem ipsum dolor sit amet,',
--     'Iphone 12',
--     1,
--     1000,
--     45,
--     (select id from category where name = 'Smart Phone')
--     );
--
-- insert into product values((SELECT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring)),
--     'Lorem ipsum dolor sit amet,',
--     'Macbook Pro',
--     1,
--     5000,
--     35,
--     (select id from category where name = 'Notebook')
--     );
-- insert into product values((SELECT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring)),
--     'Lorem ipsum dolor sit amet,',
--     'MacBook Air',
--     1,
--     4000,
--     35,
--     (select id from category where name = 'Notebook')
--     );
--
-- insert into product values((SELECT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring)),
--     'Lorem ipsum dolor sit amet,',
--     'Airpods Pro',
--     1,
--     500,
--     35,
--     (select id from category where name = 'Electronics')
--     );
-- insert into product values((SELECT uuid_in(md5(random()::text || clock_timestamp()::text)::cstring)),
--     'Lorem ipsum dolor sit amet,',
--     'Airpods 2',
--     1,
--     600,
--     35,
--     (select id from category where name = 'Electronics')
--     );
