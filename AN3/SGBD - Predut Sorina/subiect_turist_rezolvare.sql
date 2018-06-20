CREATE TABLE turist
  (
    id_turist     NUMBER(5) PRIMARY KEY,
    nume          VARCHAR2(64),
    prenume       VARCHAR2(64),
    data_nasterii DATE,
    oras          VARCHAR2(64)
  );
CREATE TABLE agentie
  (
    id_agentie NUMBER(5) PRIMARY KEY,
    denumire   VARCHAR2(64),
    adresa     VARCHAR2(64)
  );
CREATE TABLE excursie
  (
    id_excursie  NUMBER(5) PRIMARY KEY,
    denumire     VARCHAR2(64),
    pret         NUMBER(10,2),
    data_inceput DATE,
    data_sfarsit DATE,
    numar_locuri NUMBER(3),
    cod_agentie  NUMBER(5),
    CONSTRAINT fk_cod FOREIGN KEY (cod_agentie) REFERENCES agentie(id_agentie)
  );
CREATE TABLE achizitioneaza
  (
    cod_turist     NUMBER(5) ,
    cod_excursie   NUMBER(5),
    data_achizitie DATE,
    PRIMARY KEY (cod_turist, cod_excursie),
    FOREIGN KEY (cod_turist) REFERENCES turist(id_turist),
    FOREIGN KEY (cod_excursie) REFERENCES excursie(id_excursie)
  );
/

-- exercitiu 1
-- teorie

-- exercitiu 2
CREATE type tzile
AS object (id_exc NUMBER(4),numar_zile NUMBER(4)); -- cate zile are fiecare exursie
  
CREATE type lista_tzile AS TABLE OF tzile;

ALTER TABLE turist ADD lista lista_tzile nested TABLE lista store AS turist_lista;
  /
  DECLARE
    v lista_tzile;
  BEGIN
    FOR i IN (SELECT * FROM turist) LOOP
      v := lista_tzile();
      FOR j IN (SELECT * FROM achizitioneaza WHERE cod_turist=i.id_turist) LOOP
        v.extend();
        v(v.last()):=tzile(j.cod_excursie,0);
        SELECT data_sfarsit-data_inceput
        INTO v(v.last()).numar_zile
        FROM excursie
        WHERE id_excursie=j.cod_excursie;
      END LOOP;
      UPDATE turist
      SET lista=v
      WHERE id_turist=i.id_turist;
    END LOOP;
  END;
  /
  SET serveroutput ON;
  DECLARE
    v NUMBER(4);
  BEGIN
    FOR i IN (SELECT * FROM turist) LOOP
      v:=0;
      FOR j IN 1..i.lista.count()
      LOOP
        v:=v+i.lista(j).numar_zile;
      END LOOP;
      dbms_output.put_line(i.nume||' '||i.lista.count()||' '||v);
    END LOOP;
  END;
  
--exercitiu 3
CREATE PROCEDURE nume_proc(nume_oras VARCHAR2)
IS 
variabila NUMBER(4);
BEGIN
  FOR i IN (SELECT * FROM turist WHERE nume_oras=oras) LOOP
    dbms_output.put_line(i.nume);
    FOR j IN
		(SELECT e.denumire d
		FROM achizitioneaza a
		JOIN excursie e
		ON (a.cod_excursie=e.id_excursie)
		WHERE cod_turist  =i.id_turist)
    LOOP
		dbms_output.put_line(j.d);
    END LOOP;
	
    SELECT MIN(data_inceput-sysdate) INTO variabila
    FROM achizitioneaza a
    JOIN excursie e
    ON (a.cod_excursie=e.id_excursie)
    WHERE cod_turist  =i.id_turist AND sysdate < data_inceput;
	dbms_output.put_line(variabila);
	
  END LOOP;
END;

--exercitiu 4
CREATE OR REPLACE
  FUNCTION nume_fct( denumire_agentie VARCHAR2 )
    RETURN NUMBER
  IS
    varsta_cmtt NUMBER(4);
  BEGIN
    SELECT id_excursie INTO varsta_cmtt
    FROM
      (SELECT id_excursie
      FROM excursie e
      JOIN agentie a
      ON e.cod_agentie=a.id_agentie
      WHERE a.denumire=denumire_agentie
      ORDER BY pret DESC)
    WHERE rownum<=1;
	
    SELECT floor(months_between(sysdate,data_n) /12) INTO varsta_cmtt
    FROM
      (SELECT data_nasterii data_n
      FROM turist t
      JOIN achizitioneaza a
      ON t.id_turist       =a.cod_turist
      WHERE a.cod_excursie = varsta_cmtt
      ORDER BY 1 DESC)
    WHERE rownum <=1;
	
    RETURN varsta_cmtt;
  EXCEPTION
  WHEN no_data_found THEN
    raise_application_error (-22000,'N-avem') ;
  END;
  
--exercitiu 5
CREATE OR REPLACE PACKAGE pk
IS
  type texc IS TABLE OF NUMBER INDEX BY pls_integer;
  exc texc;
END;

CREATE TRIGGER tr_calc_count 
before INSERT ON achizitioneaza 
BEGIN 
  FOR i IN
    (SELECT id_excursie, COUNT(cod_turist) turisti
      FROM achizitioneaza a
      RIGHT JOIN excursie e
      ON a.cod_excursie=e.id_excursie
      GROUP BY id_excursie)
    LOOP 
	pk.exc(i.id_excursie) := i.turisti;
	END LOOP;
END;

CREATE OR REPLACE TRIGGER tr_liv_linie 
before INSERT ON achizitioneaza 
for each row 
DECLARE 
nr_locuri NUMBER;
BEGIN
  SELECT numar_locuri
  INTO nr_locuri
  FROM excursie
  WHERE id_excursie =: new.cod_excursie;
  
  IF pk.exc(:new.cod_excursie)=nr_locuri THEN
    raise_application_error(-22213, 'locuri ocupate');
  END IF;
	pk.exc(:new.cod_excursie):=pk.exc(:new.cod_excursie)+1;
END;