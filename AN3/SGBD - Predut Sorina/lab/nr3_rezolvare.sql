create table prezentare 
    (
    cod_pr number(5) primary key,
    data date,
    oras varchar2(25),
    nume varchar2(30)
    );

create table sponsor(
cod_sponsor number(5) primary key,
nume varchar2(30),
info varchar2(50),
tara_origine varchar2(30));

create table sustine(
cod_pr number(5) references prezentare(cod_pr),
cod_sp number(5) references sponsor(cod_sponsor),
suma number(12),
primary key(cod_pr, cod_sp));

create table VESTIMENTATIE
    (
    cod_vestimentatie number(6) primary key, 
    denumire varchar2(30), 
    valoare number(5), 
    cod_prezentare number(5),
    FOREIGN KEY (cod_prezentare) REFERENCES prezentare(cod_pr)
);


insert into prezentare values (4500, TO_DATE('12-05-2002', 'DD-MM-YYYY'), 'Milan', 'Spring 2002');
insert into prezentare values (4501, TO_DATE('2-09-2002', 'DD-MM-YYYY'), 'Paris', 'Automne 2002');
insert into prezentare values (4502, TO_DATE('28-04-2002', 'DD-MM-YYYY'), 'New York', 'Spring 2003');
insert into prezentare values (4503, TO_DATE('2-05-2004', 'DD-MM-YYYY'), 'Rome', 'Spring 2004');
insert into prezentare values (4504, TO_DATE('5-04-2002', 'DD-MM-YYYY'), 'Paris', 'Printemps 2002');
insert into prezentare values (4505, TO_DATE('19-08-2002', 'DD-MM-YYYY'), 'Milan', 'Fall 20025');
insert into prezentare values (4506, TO_DATE('17-04-2005', 'DD-MM-YYYY'), 'Milan', 'Spring 2005');
insert into prezentare values (4507, TO_DATE('2-02-2006', 'DD-MM-YYYY'), 'Milan', 'Spring 2006');

insert into sponsor values(5200, 'Saphire Inc.', 'cosmetics company', 'US');
insert into sponsor values(5201, 'World''s tissues', 'textiles company', 'France');
insert into sponsor values(5202, 'Exotic scents', 'cosmetics company', 'Italy');
insert into sponsor values(5203, 'Natural', 'cosmetics company', 'France');
insert into sponsor values(5204, 'Cover', 'textiles company', 'Italy');
insert into sponsor values(5205, null, 'textiles company', 'Italy');
insert into sponsor values(5206, null, 'textiles company', 'France');

insert into sustine values(4500, 5203, 20000);  
insert into sustine values(4500, 5204, 25000);  
insert into sustine values(4501, 5206, 10000);  
insert into sustine values(4501, 5203, 20000);  
insert into sustine values(4501, 5202, 30000);  
insert into sustine values(4506, 5203, 12000);  
insert into sustine values(4507, 5204, 25000);  
insert into sustine values(4505, 5201, 22000);  
insert into sustine values(4502, 5203, 15000);  
insert into sustine values(4503, 5203, 18000);  
insert into sustine values(4500, 5205, 16000);  

insert into vestimentatie values(6300, 'black dress', 4000, 4504); 
insert into vestimentatie values(6301, 'rain coat', 2200, 4504); 
insert into vestimentatie values(6302, 'red shoes', 1750, 4502); 
insert into vestimentatie values(6303, 'silk scarf', 400, 4507); 
insert into vestimentatie values(6304, 'bride dress', 5000, 4507); 
insert into vestimentatie values(6305, 'black skirt', 2310, 4504); 
insert into vestimentatie values(6306, 'yellow dress', 350, 4507); 
insert into vestimentatie values(6307, 'orange shirt', 1000, 4505); 
insert into vestimentatie values(6308, 'brown boots', 670, 4503); 
insert into vestimentatie values(6309, 'white scarf', 460, 4502); 
insert into vestimentatie values(6310, 'black hat', 1300, 4501);
insert into vestimentatie values(6311, 'blue dress', 1250, 4502); 
insert into vestimentatie values(6312, 'pink skirt', 2100, 4505); 
insert into vestimentatie values(6313, 'bride dress', 4300, 4504); 
insert into vestimentatie values(6314, 'coat', 3000, 4506);
insert into vestimentatie values(6315, 'white dress', 3000, null);  
insert into vestimentatie values(6316, 'RED DRESS', 1300, 4504);
insert into vestimentatie values(6317, 'coat', 560, 4500);  

commit;

set serveroutput on;

--ex1
create or replace trigger ex1
  before insert on vestimentatie
  for each row
  declare 
  v_id_1 number;
  begin
      select count (cod_pr) into v_id_1 
      from prezentare
      where cod_pr = :NEW.cod_prezentare;
      
      if (v_id_1 = 0) then 
        RAISE_APPLICATION_ERROR (-20222, 'Erroare FK vestimentatie');
      end if;
  end;
  
--ex2
create or replace procedure ex2_pr1 (cod_sponsor sustine.cod_sp%type, x number:=3)
is
v_media number;
begin
  for i in (select avg(suma) m
            from sustine
            where cod_sp = cod_sponsor
            and (select count(cod_vestimentatie)
                from VESTIMENTATIE
                where cod_prezentare = cod_pr) >= x)
  loop
      dbms_output.put_line(' Media: ' || i.m);
  end loop;
end;

execute ex2_pr1(5203, 3);

--ex3
create or replace trigger ex3_pr1
before insert on VESTIMENTATIE
for each row
declare
v_suma number:=0;
v_valori number:=0;
begin
  select sum(suma) into v_suma
  from sustine
  where cod_pr = :new.cod_prezentare;
  select sum (valoare) into v_valori
  from vestimentatie
  where cod_prezentare = :new.cod_prezentare;
  v_valori := v_valori + :new.valoare;
  if (v_valori >= v_suma) then
    RAISE_APPLICATION_ERROR(-20222, 'Eroare valoare vestimentatie!!!! Valori' || v_valori || ' Suma: ' || v_suma);
  end if;
end;
