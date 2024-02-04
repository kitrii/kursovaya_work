DROP TABLE IF EXISTS bonds CASCADE;

CREATE TABLE bonds (
    bondid int,
    bondname varchar(255),
    nominalcost int,
    repaymentperiod int,
    couponfrequency int,
    couponrate int,
    yieldtomaturity int,
    ownerid int,
    owner varchar(255));


insert into bonds values(111, 'RZD-25 CHF', 20000, 12, 10, 5, 1, 7122003, 'Морозов Евгений');
insert into bonds values(324, 'ВТБ', 50000, 36, 10, 5, 1, 7122003, 'Морозов Евгений');
insert into bonds values(712, 'VTB-24 CHF', 30000, 24, 10, 5, 1, 7122003, 'Морозов Евгений');
insert into bonds values(777, 'ВТБ', 50000, 36, 10, 5, 1, 7122003, 'Морозов Евгений');
insert into bonds values(111, 'Raiff BIAG', 76000, 12, 10, 5, 1, 12102323, 'Криушина Мария');
insert into bonds values(111, 'ГазпромБ19', 1200, 13, 10, 5, 1, 8912120, 'Ящевская Ольга');
insert into bonds values(222, 'Тинькофф', 29900, 48, 10, 5, 1, 123987, 'Подолинов Олег');
insert into bonds values(111, 'RZD-25 CHF', 77000, 18, 10, 5, 1, 123987, 'Подолинов Олег');
insert into bonds values(872, 'Сбербанк', 13000, 20, 10, 5, 1, 123987, 'Подолинов Олег');
