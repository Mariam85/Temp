CREATE OR REPLACE TRIGGER dummy_on_insert 
BEFORE INSERT ON dummy 
FOR EACH ROW 
BEGIN 
SELECT dummy_sequence_id.nextval 
INTO :new.id 
FROM dual; 
END;