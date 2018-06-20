CREATE TABLE FORMATIE 
   (id_formatie NUMBER(5,0), 
	nume VARCHAR2(30 BYTE), 
    data_lansare DATE,
    data_retragere DATE,
	website VARCHAR2(50 BYTE), 
	tara_prov VARCHAR2(30 BYTE), 
	PRIMARY KEY (id_formatie)
    );

CREATE TABLE ALBUM 
   (id_album NUMBER(5,0), 
    id_formatie NUMBER(5,0),  
    gen VARCHAR2(25 BYTE), 
    nume VARCHAR2(30 BYTE), 
    data_l DATE,
    pret NUMBER(5,0),
    PRIMARY KEY (id_album),
    FOREIGN KEY (id_formatie) REFERENCES Formatie(id_formatie)
    );
     
CREATE TABLE PREMIU 
   (id_premiu NUMBER(5,0), 
    concurs VARCHAR2(30 BYTE),
    sectiune VARCHAR2(30 BYTE),
    frecventa VARCHAR2(30 BYTE),
	tara_prov VARCHAR2(30 BYTE), 
	PRIMARY KEY (id_premiu)
    );
     
CREATE TABLE CASTIGA 
   (id_premiu NUMBER(5,0), 
	id_formatie NUMBER(5,0), 
    data_d DATE,
	loc_ocupat NUMBER(5,0),
    recompensa NUMBER(12,0),
	PRIMARY KEY (id_premiu, id_formatie),
    FOREIGN KEY (id_premiu) REFERENCES PREMIU(id_premiu),
    FOREIGN KEY (id_formatie) REFERENCES FORMATIE(id_formatie)
    );

INSERT INTO "GRUPA43"."FORMATIE" (id_formatie, nume, data_lansare, website, tara_prov) VALUES ('100', 'Beatels', TO_DATE('10-JUN-10', 'DD-MON-RR'), 'www.beatels.com', 'UK');
INSERT INTO "GRUPA43"."FORMATIE" (id_formatie, nume, data_lansare, website, tara_prov) VALUES ('101', 'ZsiD', TO_DATE('05-JUN-11', 'DD-MON-RR'), 'www.zsid.com', 'MD');
INSERT INTO "GRUPA43"."FORMATIE" (id_formatie, nume, data_lansare, website, tara_prov) VALUES ('102', 'Gandul', TO_DATE('05-JUL-12', 'DD-MON-RR'), 'www.gandul.com', 'RO');
INSERT INTO "GRUPA43"."FORMATIE" (id_formatie, nume, data_lansare, website, tara_prov) VALUES ('103', 'Cabus', TO_DATE('05-JUN-14', 'DD-MON-RR'), 'www.cabus.com', 'CZ');
INSERT INTO "GRUPA43"."FORMATIE" (id_formatie, nume, data_lansare, website, tara_prov) VALUES ('104', 'Florin', TO_DATE('05-JUN-13', 'DD-MON-RR'), 'www.florin.com', 'BG');
INSERT INTO "GRUPA43"."FORMATIE" (ID_FORMATIE, NUME, DATA_LANSARE, WEBSITE, TARA_PROV) VALUES ('99', 'Bumtzi', TO_DATE('06-JUN-09', 'DD-MON-RR'), 'www.bumtzi.com', 'RU');

INSERT INTO "GRUPA43"."ALBUM" (id_album, id_formatie, gen, nume, data_l, pret) VALUES ('25', '100', 'Rock', 'Forever 5', TO_DATE('01-JUN-18', 'DD-MON-RR'), '100');
INSERT INTO "GRUPA43"."ALBUM" (id_album, id_formatie, gen, nume, data_l, pret) VALUES ('26', '100', 'Rap', 'OMG', TO_DATE('02-JUN-18', 'DD-MON-RR'), '110');
INSERT INTO "GRUPA43"."ALBUM" (id_album, id_formatie, gen, nume, data_l, pret) VALUES ('27', '101', 'Folk', 'Yes', TO_DATE('03-JUN-18', 'DD-MON-RR'), '90');
INSERT INTO "GRUPA43"."ALBUM" (id_album, id_formatie, gen, nume, data_l, pret) VALUES ('28', '102', 'Pop', 'Number 1', TO_DATE('04-JUN-18', 'DD-MON-RR'), '80');

INSERT INTO "GRUPA43"."PREMIU" (ID_PREMIU, CONCURS, SECTIUNE, FRECVENTA, TARA_PROV) VALUES ('1', 'Cea mai tare', 'Sectiune 1', 'Anual', 'RO');
INSERT INTO "GRUPA43"."PREMIU" (ID_PREMIU, CONCURS, SECTIUNE, FRECVENTA, TARA_PROV) VALUES ('2', 'The best uk', 'Sec 2', 'Monthly', 'UK');
INSERT INTO "GRUPA43"."PREMIU" (ID_PREMIU, CONCURS, SECTIUNE, FRECVENTA, TARA_PROV) VALUES ('3', 'Milion', 'Sec 3', 'Daily', 'BG');
INSERT INTO "GRUPA43"."PREMIU" (ID_PREMIU, CONCURS, SECTIUNE, FRECVENTA, TARA_PROV) VALUES ('0', 'Cel Mai national', 'Sec 0', 'Anual', 'RU');
INSERT INTO "GRUPA43"."PREMIU" (ID_PREMIU, CONCURS, SECTIUNE, FRECVENTA, TARA_PROV) VALUES ('4', 'Mod Ru', 'Sec ru3', 'Anual', 'RU');
INSERT INTO "GRUPA43"."PREMIU" (ID_PREMIU, CONCURS, SECTIUNE, FRECVENTA, TARA_PROV) VALUES ('5', 'Cehia', 'Sec 22', 'Lunar', 'CZ');

INSERT INTO "GRUPA43"."CASTIGA" (ID_PREMIU, ID_FORMATIE, DATA_D, LOC_OCUPAT, RECOMPENSA) VALUES ('1', '102', TO_DATE('03-JUN-18', 'DD-MON-RR'), '1', '100');
INSERT INTO "GRUPA43"."CASTIGA" (ID_PREMIU, ID_FORMATIE, DATA_D, LOC_OCUPAT, RECOMPENSA) VALUES ('2', '102', TO_DATE('04-JUN-18', 'DD-MON-RR'), '2', '50');
INSERT INTO "GRUPA43"."CASTIGA" (ID_PREMIU, ID_FORMATIE, DATA_D, LOC_OCUPAT, RECOMPENSA) VALUES ('3', '101', TO_DATE('01-JUN-18', 'DD-MON-RR'), '3', '20');
INSERT INTO "GRUPA43"."CASTIGA" (ID_PREMIU, ID_FORMATIE, DATA_D, LOC_OCUPAT, RECOMPENSA) VALUES ('1', '100', TO_DATE('05-MAY-18', 'DD-MON-RR'), '1', '100');
INSERT INTO "GRUPA43"."CASTIGA" (ID_PREMIU, ID_FORMATIE, DATA_D, LOC_OCUPAT, RECOMPENSA) VALUES ('0', '99', TO_DATE('06-APR-18', 'DD-MON-RR'), '1', '1000');
INSERT INTO "GRUPA43"."CASTIGA" (ID_PREMIU, ID_FORMATIE, DATA_D, LOC_OCUPAT, RECOMPENSA) VALUES ('4', '99', TO_DATE('06-JUN-17', 'DD-MON-RR'), '2', '500');
INSERT INTO "GRUPA43"."CASTIGA" (ID_PREMIU, ID_FORMATIE, DATA_D, LOC_OCUPAT, RECOMPENSA) VALUES ('5', '103', TO_DATE('06-JUN-18', 'DD-MON-RR'), '3', '200');

set serveroutput on;

--ex1
create or replace procedure ex1 (cod formatie.id_formatie%type) is
v_gen varchar2(25);
v_nume varchar2(30);
v_nume_album varchar2(30);
begin
    select  nume into v_nume
    from formatie
    where id_formatie = cod;
    dbms_output.put_line('Formatia: ' || v_nume || ' are urmatoarele albume: ');
    for i in (select nume, gen
              from album 
              where id_formatie=cod) 
    loop
    dbms_output.put_line('        Nume: '||i.nume||' Genul: '||i.gen);
    end loop;
end;

execute ex1(100);

--ex2
set define off;
create or replace trigger ex2
  before insert on castiga
  for each row
  declare 
  v_id_1 number;
  v_id_2 number;
  begin
      select count (id_formatie) into v_id_1 
      from formatie
      where id_formatie = :NEW.id_formatie;
      
      select count (id_premiu) into v_id_2 
      from premiu
      where id_premiu = :NEW.id_premiu;
      if (v_id_1 = 0) then 
        RAISE_APPLICATION_ERROR (-20222, '!eroare fk formatie!');
      end if;
      if (v_id_2 = 0) then 
        RAISE_APPLICATION_ERROR (-20222, '!eroare fk premiu!');
      end if;
  end;
  
--ex3
create or replace procedure ex3 is
type type_nume is table of formatie.nume%type;
v_nume type_nume := type_nume (); --initializam nested table
v_nume2 type_nume := type_nume ();
begin
  --slectam formatiile care au premiu doar in tara lor
  for i in (select fo.id_formatie idf
            from formatie fo
            join castiga ca on (fo.id_formatie = ca.id_formatie)
            where (select count (id_formatie) -- calculam numarul premiilor externe pentru fiecare formatie
                    from castiga
                    join premiu on (premiu.id_premiu = castiga.id_premiu) 
                    where ca.id_formatie = id_formatie and fo.tara_prov != tara_prov ) <= 0)
  loop
    --adaugam numele formatiilor in nested table
    v_nume.extend;
    select nume into v_nume(v_nume.last)
    from formatie
    where i.idf = id_formatie;
  end loop;
  --eliminam dublicatele din nested table cu multiset intersect distinct
  v_nume2 := v_nume;
  v_nume := v_nume multiset intersect distinct v_nume2;
  for j in v_nume.first..v_nume.last loop
    dbms_output.put_line ('Nume formatie cu premiu doar din tara sa: ' || v_nume(j));
  end loop;
end;

execute ex3;
