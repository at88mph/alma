CREATE OR REPLACE PROCEDURE ALMA.GEN_DATA
IS
  MIN_RA NUMBER;
  MAX_RA NUMBER;
  MIN_DEC NUMBER;
  MAX_DEC NUMBER;
  NEW_RA NUMBER;
  NEW_DEC NUMBER;
BEGIN
  SELECT MIN(s_ra) INTO MIN_RA FROM tap_upload.irisblast_h5xrkuev2p9shglh;
  SELECT MAX(s_ra) INTO MAX_RA FROM tap_upload.irisblast_h5xrkuev2p9shglh;
  SELECT MIN(s_dec) INTO MIN_DEC FROM tap_upload.irisblast_h5xrkuev2p9shglh;
  SELECT MAX(s_dec) INTO MAX_DEC FROM tap_upload.irisblast_h5xrkuev2p9shglh;

  FOR NUM IN 0..50
  LOOP
    SELECT trunc(dbms_random.value(MIN_RA, MAX_RA), 6) INTO NEW_RA FROM DUAL;
    SELECT trunc(dbms_random.value(MIN_DEC, MAX_DEC), 8) INTO NEW_DEC FROM DUAL;

    INSERT INTO tap_upload.irisblast_h5xrkuev2p9shglh
    (s_ra, s_dec)
    VALUES
    (NEW_RA, NEW_DEC);
  END LOOP;
END;
/