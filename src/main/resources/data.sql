insert into student
values(10001,'Ranga', 'E1234567');

insert into student
values(10002,'Ravi', 'A1234568');

insert into Application
values(1, 'TD', 'Toronto Dominion', true);

insert into Application
values(2, 'RBC', 'Royal Bank Of Canada', true);

insert into Application
values(3, 'BMO', 'Bank of Montreal', true);

insert into Application
values(4, 'CIBC', 'Canadian Imperial Bank of Commerce', true);

insert into Feature (id,abbreviation,description,purged,dateoverlapmode,featurecontrol,featurelocked,applicationid)
values(1, 'Abb1', 'Abbrevation1',true, 'CONTINUOUS',true,true,18);

insert into Feature (id,abbreviation,description,purged,dateoverlapmode,featurecontrol,featurelocked,applicationid)
values(2, 'Abb2', 'Abbrevation2',true, 'CONTINUOUS',true,true,19);

insert into Feature (id,abbreviation,description,purged,dateoverlapmode,featurecontrol,featurelocked,applicationid)
values(3, 'Abb3', 'Abbrevation3',true, 'CONTINUOUS',true,true,20);

insert into Feature (id,abbreviation,description,purged,dateoverlapmode,featurecontrol,featurelocked,applicationid)
values(4, 'Abb4', 'Abbrevation4',true, 'NCONTINUOUS',true,true,21);

insert into Control (id,entity,val,startdate,enddate,purged,featureid)
values(1, 'Ent1','val','20120101','20120102',true,1);

insert into Control (id,entity,val,startdate,enddate,purged,featureid)
values(3, 'Ent3', 'C', '20230103','20230304',true,15);

insert into Control (id,entity,val,startdate,enddate,purged,featureid)
values(4, 'Ent4', 'D', '20230104','20230404',true,16);

insert into Control (id,entity,val,startdate,enddate,purged,featureid)
values(5, 'Ent5', 'E', '20230105','20230504',true,17);

insert into Control (id,entity,val,startdate,enddate,purged,featureid)
values(2, 'Ent2', 'B', '20230102','20230204',true,14);






		  

