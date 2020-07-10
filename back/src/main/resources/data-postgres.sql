----------------------------------vehicle----------------------------------------

-- insert into vehicle (mileage, mileage_limit, collision_protection, children_seats, start_date, end_date, fuel_type_id, make_id, model_id, style_id, transmission_id, user_id, location_id)
-- values (67324, 12000, false, 0, DATE('2020-06-08'), DATE('2020-08-23'), 1, 1, 1, 1, 1, 2, 4);
-- insert into vehicle (mileage, mileage_limit, collision_protection, children_seats, start_date, end_date, fuel_type_id, make_id, model_id, style_id, transmission_id, user_id, location_id)
-- values (67324, 12000, false, 0, DATE('2020-06-29'), DATE('2020-10-23'), 1, 1, 2, 2, 1, 2, 4);
--
-- insert into vehicle (mileage, mileage_limit, collision_protection, children_seats, start_date, end_date, fuel_type_id, make_id, model_id, style_id, transmission_id, user_id, location_id)
-- values (50000, 10000, true, 2, DATE('2020-06-23'), DATE('2020-12-23'), 1, 2, 3, 1, 1, 2, 1);
-- insert into vehicle (mileage, mileage_limit, collision_protection, children_seats, start_date, end_date, fuel_type_id, make_id, model_id, style_id, transmission_id, user_id, location_id)
-- values (69420, 8000, false, 0, DATE('2020-06-08'), DATE('2020-08-23'), 1, 2, 4, 5, 2, 2, 4);
--
-- insert into vehicle (mileage, mileage_limit, collision_protection, children_seats, start_date, end_date, fuel_type_id, make_id, model_id, style_id, transmission_id, user_id, location_id)
-- values (89212, 20000, false, 0, DATE('2020-07-11'), DATE('2020-12-12'), 2, 3, 5, 2, 2, 2, 4);
-- insert into vehicle (mileage, mileage_limit, collision_protection, children_seats, start_date, end_date, fuel_type_id, make_id, model_id, style_id, transmission_id, user_id, location_id)
-- values (47902, 5000, true, 0, DATE('2020-06-08'), DATE('2020-08-08'), 2, 3, 6, 5, 1, 2, 4);
--
-- insert into vehicle (mileage, mileage_limit, collision_protection, children_seats, start_date, end_date, fuel_type_id, make_id, model_id, style_id, transmission_id, user_id, location_id)
-- values (113200, 8000, true, 1, DATE('2020-06-23'), DATE('2020-12-23'), 3, 4, 7, 4, 1, 2, 1);
-- insert into vehicle (mileage, mileage_limit, collision_protection, children_seats, start_date, end_date, fuel_type_id, make_id, model_id, style_id, transmission_id, user_id, location_id)
-- values (72410, 8000, false, 1, DATE('2020-07-10'), DATE('2020-11-11'), 6, 4, 8, 5, 2, 2, 1);

--------------------------------user----------------------------------------


insert into user_details(full_name, address, vehicle_num, user_type) values ('Ivana Brkic', 'Adresa 1', 3, 0);
insert into user_details(full_name, address, business_num, vehicle_num, user_type) values ('Tamara Lazarevic', 'Adresa 2', '12A', 1, 1);
insert into user_details(full_name, address, business_num, vehicle_num, user_type) values ('Vladimir Popovic', 'Adresa 3', '12B', 0, 1);
insert into user_details(full_name, address, vehicle_num, user_type) values ('Milan Lukic', 'Adresa 4', 2, 2);

insert into sys_user(username, password, user_details_id, verified) values ('admin', 'admin', 1, true);
insert into sys_user(username, password, user_details_id, verified) values ('agent1', 'agent1', 2, true);
insert into sys_user(username, password, user_details_id, verified) values ('agent2', 'agent2', 3, true);
insert into sys_user(username, password, user_details_id, verified) values ('user', 'user', 4, true);

insert into user_privilege(user_id, privilege) values (1, 0);
insert into user_privilege(user_id, privilege) values (1, 1);
insert into user_privilege(user_id, privilege) values (1, 2);
insert into user_privilege(user_id, privilege) values (1, 3);

insert into user_privilege(user_id, privilege) values (2, 0);
insert into user_privilege(user_id, privilege) values (2, 1);
insert into user_privilege(user_id, privilege) values (2, 2);
insert into user_privilege(user_id, privilege) values (2, 3);

insert into user_privilege(user_id, privilege) values (3, 0);
insert into user_privilege(user_id, privilege) values (3, 1);
insert into user_privilege(user_id, privilege) values (3, 2);
insert into user_privilege(user_id, privilege) values (3, 3);

-- insert into user_privilege(user_id, privilege) values (3, 4);

insert into user_privilege(user_id, privilege) values (4, 0);
insert into user_privilege(user_id, privilege) values (4, 3);

--------------------------------pricelist----------------------------------------

-- insert into vehicle_discount(num_days, discount) values (20, 10);
-- insert into vehicle_discount(num_days, discount) values (15, 8);
-- insert into vehicle_discount(num_days, discount) values (30, 20);
-- --vehicle 1
-- insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
-- values (DATE('2020-06-08'), DATE('2020-07-23'), 70, 2, 0, 1, 2);
-- insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
-- values (DATE('2020-07-24'), DATE('2020-08-23'), 80, 3, 0, 1, 2);
-- --vehicle 2
-- insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
-- values (DATE('2020-06-29'), DATE('2020-08-23'), 60, 3, 0, 2, 2);
-- insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
-- values (DATE('2020-08-24'), DATE('2020-10-23'), 55, 3, 0, 2, 2);
-- --vehicle 3
-- insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
-- values (DATE('2020-06-23'), DATE('2020-09-23'), 90, 2, 120, 3, 3);
-- insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
-- values (DATE('2020-09-24'), DATE('2020-12-23'), 110, 3, 120, 3, 3);
-- --vehicle 4
-- insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
-- values (DATE('2020-06-08'), DATE('2020-06-29'), 100, 3, 0, 4, 2);
-- insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
-- values (DATE('2020-06-30'), DATE('2020-08-23'), 130, 3, 0, 4, 1);
-- --vehicle 5
-- insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
-- values (DATE('2020-07-11'), DATE('2020-07-28'), 100, 3, 0, 5, 1);
-- insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
-- values (DATE('2020-07-29'), DATE('2020-09-23'), 130, 3, 0, 5, 1);
-- insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
-- values (DATE('2020-09-24'), DATE('2020-12-12'), 130, 3, 0, 5, 1);
-- --vehicle 6
-- insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
-- values (DATE('2020-06-08'), DATE('2020-08-08'), 140, 4, 150, 6, 1);
-- --vehicle 7
-- insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
-- values (DATE('2020-06-23'), DATE('2020-09-13'), 60, 3, 80, 7, 1);
-- insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
-- values (DATE('2020-09-14'), DATE('2020-12-23'), 65, 3, 80, 7, 2);
-- --vehicle 8
-- insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id, discount_id)
-- values (DATE('2020-07-10'), DATE('2020-08-26'), 80, 2, 0, 8, 3);
-- insert into pricelist(start_date, end_date, price, price_by_mile, price_collision, vehicle_id)
-- values (DATE('2020-08-27'), DATE('2020-11-11'), 90, 2, 0, 8);

--------------------------------location----------------------------------------

-- insert into state(state) values ('Srbija');
insert into state(state) values ('Norveska');

insert into city(city) values ('Backa Palanka');
insert into city(city) values ('Beograd');
insert into city(city) values ('Indjija');
insert into city(city) values ('Novi Sad');

insert into street(street) values ('Save Kovacevica 21');
insert into street(street) values ('Alekse Santica 31');
insert into street(street) values ('Mise Dimitrijevica 25');
insert into street(street) values ('Zeleznicka 24');
insert into street(street) values ('Masarikova 12');

insert into location(state, city, street) values (1,1,1);
insert into location(state, city, street) values (1,2,2);
insert into location(state, city, street) values (1,2,3);
insert into location(state, city, street) values (1,3,3);
insert into location(state, city, street) values (1,4,2);

--------------------------------catalogue----------------------------------------

-- insert into vehicle_fuel_type(fuel_type) values ('Diesel');
-- insert into vehicle_fuel_type(fuel_type) values ('Gasoline');
-- insert into vehicle_fuel_type(fuel_type) values ('Liquified Petroleum');
-- insert into vehicle_fuel_type(fuel_type) values ('Compressed Natural Gas');
-- insert into vehicle_fuel_type(fuel_type) values ('Ethanol');
-- insert into vehicle_fuel_type(fuel_type) values ('Bio-diesel');
insert into vehicle_fuel_type(fuel_type) values ('Ethanol with gasoline');

-- insert into vehicle_make(make) values ('Alfa Romeo');
-- insert into vehicle_make(make) values ('Audi');
-- insert into vehicle_make(make) values ('BMW');
-- insert into vehicle_make(make) values ('Citroen');
-- insert into vehicle_make(make) values ('Fiat');
-- insert into vehicle_make(make) values ('Ford');
-- insert into vehicle_make(make) values ('Honda');
-- insert into vehicle_make(make) values ('Hyundai');
-- insert into vehicle_make(make) values ('Kia');
-- insert into vehicle_make(make) values ('Mazda');
-- insert into vehicle_make(make) values ('Mercedes Benz');
-- insert into vehicle_make(make) values ('Nissan');
insert into vehicle_make(make) values ('Tesla');

-- insert into vehicle_model(make_id, model) values (1,'156');
-- insert into vehicle_model(make_id, model) values (1,'Giulietta');
-- insert into vehicle_model(make_id, model) values (2,'A3');
-- insert into vehicle_model(make_id, model) values (2,'Q3');
-- insert into vehicle_model(make_id, model) values (3,'116');
-- insert into vehicle_model(make_id, model) values (3,'X6');
-- insert into vehicle_model(make_id, model) values (4,'Berlingo');
-- insert into vehicle_model(make_id, model) values (4,'C3');
-- insert into vehicle_model(make_id, model) values (5,'500L');
-- insert into vehicle_model(make_id, model) values (5,'Multipla');
-- insert into vehicle_model(make_id, model) values (6,'Escort');
-- insert into vehicle_model(make_id, model) values (6,'Focus');
-- insert into vehicle_model(make_id, model) values (7,'Accord');
-- insert into vehicle_model(make_id, model) values (7,'Civic');
-- insert into vehicle_model(make_id, model) values (8,'i30');
-- insert into vehicle_model(make_id, model) values (8,'Tucson');
-- insert into vehicle_model(make_id, model) values (9,'Picanto');
-- insert into vehicle_model(make_id, model) values (9,'Spectra');
-- insert into vehicle_model(make_id, model) values (10,'CX-3');
-- insert into vehicle_model(make_id, model) values (10,'MX-5');
-- insert into vehicle_model(make_id, model) values (11,'E 320');
-- insert into vehicle_model(make_id, model) values (11,'C 250');
-- insert into vehicle_model(make_id, model) values (12,'Qashqai');
-- insert into vehicle_model(make_id, model) values (12,'Murano');
insert into vehicle_model(make_id, model) values (1,'XXX');

-- insert into vehicle_style(style) values ('Sedan');
-- insert into vehicle_style(style) values ('Hatchback');
-- insert into vehicle_style(style) values ('Coupe');
-- insert into vehicle_style(style) values ('Minivan');
-- insert into vehicle_style(style) values ('SUV');
-- insert into vehicle_style(style) values ('Pickup');
-- insert into vehicle_style(style) values ('Cabriolet');
insert into vehicle_style(style) values ('Cute Minivan');

-- insert into vehicle_transmission(transmission) values ('Manual');
-- insert into vehicle_transmission(transmission) values ('Automatic');
-- insert into vehicle_transmission(transmission) values ('CVT');
insert into vehicle_transmission(transmission) values ('Manual/Automatic');









