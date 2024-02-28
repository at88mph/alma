-- Recreate the SDO_GEOMETRY objects in the SPATIAL_BOUNDS column.  One could simply run a single UPDATE command,
-- but this is an attempt to speed it up.  This takes hours in our database.
declare
  cursor member_ouss_cursor is
  select distinct member_ouss_id as mous_id from asa_science where footprint is not null and footprint <> ' ';
  mousid varchar2(32);
begin
  for i in member_ouss_cursor loop
    mousid := i.mous_id;
    update asa_science set spatial_bounds = to_geometric_object(footprint) where member_ouss_id = mousid;
  end loop;
end;
/
