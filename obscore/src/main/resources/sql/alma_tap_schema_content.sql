INSERT INTO TAP_SCHEMA.schemas11 (schema_name, utype, description, schema_index) VALUES ('ivoa', null, 'IVOA tables', 10);

INSERT INTO TAP_SCHEMA.tables11 (schema_name, table_name, table_type, utype, description, table_index) VALUES ('ivoa', 'ivoa.obscore', 'view', null, 'ObsCore-1.1 observation table', 10);

INSERT ALL
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'calib_level', 'obscore:ObsDataset.calibLevel', 'meta.code;obs.calib', null, 'calibration level (2 or 3). 2 if product_type = MOUS, 3 if product_type = GOUS', 'int', null, null, null, 1, 0, 1, 7, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 's_ra', 'obscore:Char.SpatialAxis.Coverage.Location.Coord.Position2D.Value2.C1', 'pos.eq.ra', 'deg', 'RA of central coordinates', 'double', NULL, 'adql:DOUBLE', null, 1, 0, 1, 10, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 's_dec', 'obscore:Char.SpatialAxis.Coverage.Location.Coord.Position2D.Value2.C2', 'pos.eq.dec', 'deg', 'DEC of central coordinates', 'double', NULL, 'adql:DOUBLE', null, 1, 0, 1, 11, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 's_fov', 'obscore:Char.SpatialAxis.Coverage.Bounds.Extent.diameter', 'phys.angSize;instr.fov', 'deg', 'size of the region covered (~diameter of minimum bounding circle)', 'double', NULL, 'adql:DOUBLE', null, 1, 0, 1, 12, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 's_region', 'obscore:Char.SpatialAxis.Coverage.Support.Area', 'phys.outline;obs.field', 'deg', 'region bounded by observation', 'char', '*', 'adql:REGION', null, 1, 1, 1, 13, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 's_resolution', 'obscore:Char.SpatialAxis.Resolution.refval.value', 'pos.angResolution', 'arcsec', 'typical spatial resolution', 'double', NULL, 'adql:DOUBLE', null, 1, 0, 1, 14, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 't_min', 'obscore:Char.TimeAxis.Coverage.Bounds.Limits.StartTime', 'time.start;obs.exposure', 'd', 'start time of observation (MJD)', 'double', NULL, 'adql:DOUBLE', null, 1, 1, 1, 17, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 't_max', 'obscore:Char.TimeAxis.Coverage.Bounds.Limits.StopTime', 'time.end;obs.exposure', 'd', 'end time of observation (MJD)', 'double', NULL, 'adql:DOUBLE', null, 1, 1, 1, 18, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 't_exptime', 'obscore:Char.TimeAxis.Coverage.Support.Extent', 'time.duration;obs.exposure', 's', 'exposure time of observation', 'double', NULL, 'adql:DOUBLE', null, 1, 1, 1, 19, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 't_resolution', 'obscore:Char.TimeAxis.Resolution.refval.value', 'time.resolution', 's', 'typical temporal resolution', 'double', NULL, 'adql:DOUBLE', null, 1, 0, 1, 20, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'em_min', 'obscore:Char.SpectralAxis.Coverage.Bounds.Limits.LoLimit', 'em.wl;stat.min', 'm', 'start spectral coordinate value', 'double', NULL, 'adql:DOUBLE', null, 1, 1, 1, 22, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'em_max', 'obscore:Char.SpectralAxis.Coverage.Bounds.Limits.HiLimit', 'em.wl;stat.max', 'm', 'stop spectral coordinate value', 'double', NULL, 'adql:DOUBLE', null, 1, 1, 1, 23, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'em_res_power', 'obscore:Char.SpectralAxis.Resolution.ResolPower.refval', 'spect.resolution', null, 'typical spectral resolution', 'double', NULL, 'adql:DOUBLE', null, 1, 0, 1, 24, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'access_url', 'obscore:Access.Reference', 'meta.ref.url', null, 'URL to download the data', 'char', '*', 'clob', null, 1, 0, 1, 30, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'access_format', 'obscore:Access.Format', 'meta.code.mime', null, 'Content format of the data', 'char', '128*', null, 128, 1, 0, 1, 31, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'access_estsize', 'obscore:Access.Size', 'phys.size;meta.file', 'kbyte', 'estimated size of the download', 'long', NULL, 'adql:DOUBLE', null, 1, 0, 1, 32, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'obs_publisher_did', 'obscore:Curation.PublisherDID', 'meta.ref.url;meta.curation', null, 'publisher dataset identifier', 'char', '256*', null, 256, 1, 1, 1, 1, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'obs_collection', 'obscore:DataID.Collection', 'meta.id', null, 'short name for the data collection', 'char', '128*', null, 128, 1, 0, 1, 2, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'facility_name', 'obscore:Provenance.ObsConfig.Facility.name', 'meta.id;instr.tel', null, 'telescope name', 'char', '128*', null, 128, 1, 0, 1, 3, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'instrument_name', 'obscore:Provenance.ObsConfig.Instrument.name', 'meta.id;instr', null, 'instrument name', 'char', '128*', null, 128, 1, 0, 1, 4, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'obs_id', 'obscore:DataID.observationID', 'meta.id', null, 'internal dataset identifier', 'char', '128*', null, 128, 1, 0, 1, 5, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'dataproduct_type', 'obscore:ObsDataset.dataProductType', 'meta.id', null, 'type of product', 'char', '128*', null, 128, 1, 0, 1, 6, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'target_name', 'obscore:Target.Name', 'meta.id;src', null, 'name of intended target', 'char', '32*', null, 32, 1, 0, 1, 9, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'pol_states', 'obscore:Char.PolarizationAxis.stateList', 'meta.code;phys.polarization', null, 'polarization states present in the data', 'char', '32*', null, 32, 1, 0, 1, 27, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'o_ucd', 'obscore:Char.ObservableAxis.ucd', 'meta.ucd', null, 'UCD describing the observable axis (pixel values)', 'char', '32*', null, 32, 1, 0, 1, 29, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'footprint', 'obscore:Characterisation.SpatialAxis.Coverage.Support.Area', 'pos', 'deg', 'Footprint of the field in STC-S form. Corresponds to IVOA.ObsCore.s_region', 'char', NULL, 'adql:REGION', 1024, 0, 0, 1, 33, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'data_rights', NULL, NULL, NULL, 'OUS Project Code', 'char', NULL, 'adql:VARCHAR', 64, 0, 0, 0, 34, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'gal_longitude', NULL, 'pos.galactic.lon', 'deg', 'Galactic longitude of the observation for RA/Dec. Estimated using PyEphem and RA/Dec.', 'double', NULL, 'adql:DOUBLE', NULL, 0, 0, 0, 35, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'gal_latitude', NULL, 'pos.galactic.lat', 'deg', 'Galactic latitude of the observation for RA/Dec. Estimated using PyEphem and RA/Dec.', 'double', NULL, 'adql:DOUBLE', NULL, 0, 0, 0, 36, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'band_list', NULL, NULL, NULL, 'Space delimited list of bands', 'char', NULL, 'adql:VARCHAR', 30, 0, 0, 0, 37, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'em_resolution', 'obscore:Char.SpectralAxis.Resolution.refval', 'spect.resolution', 'kHz', 'Estimated frequency resolution from all the spectral windows, using median values of channel widths.', 'double', NULL, 'adql:DOUBLE', NULL, 0, 0, 0, 38, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'antenna_arrays', NULL, 'meta.code.member;instr.setup', NULL, 'Blank-separated list of Pad:Antenna pairs, i.e., A109:DV09 J504:DV02 J505:DV05 for antennas DV09, DV02 and DV05 sitting on pads A109, J504, and J505, respectively.', 'char', NULL, 'adql:VARCHAR', 512, 0, 0, 0, 39, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'is_mosaic', NULL, NULL, NULL, 'Flag to indicate if this ASDM represents a mosaic or not.', 'char', NULL, 'adql:CHAR', 1, 0, 0, 0, 40, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'obs_release_date', 'obscore:Curation.releaseDate', 'time.release_date', NULL, 'timestamp of date the data becomes publicly available', 'char', '*', 'adql:TIMESTAMP', NULL, 1, 0, 1, 41, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'frequency_support', 'obscore:Char.SpectralAxis.Coverage.Location.support', 'em.freq;obs;meta.main', 'GHz', 'All frequency ranges used by the field', 'char', NULL, 'adql:VARCHAR', 2700, 0, 0, 0, 42, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'velocity_resolution', 'obscore:Char.VelocityAxis.Resolution.refval', 'phys.veloc;spect.resolution', 'm/s', 'Estimated velocity resolution from all the spectral windows, from frequency resolution.', 'double', NULL, 'adql:DOUBLE', NULL, 0, 0, 0, 43, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'obs_creator_name', NULL, 'meta.id.PI', NULL, 'case-insensitive partial match over the full PI name. Wildcards can be used', 'char', NULL, 'adql:VARCHAR', 256, 1, 1, 0, 44, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'schedblock_name', NULL, 'meta.code;obs;instr.setup', NULL, 'Name of the Scheduling Block used as a template for executing the ASDM containing this Field.', 'char', NULL, 'adql:VARCHAR', 128, 0, 0, 0, 45, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'coi_name', NULL, 'meta.id.CoI', NULL, 'Full name of CoIs .', 'char', NULL, 'adql:VARCHAR', 512, 0, 1, 0, 46, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'sensitivity_10kms', NULL, NULL, 'mJy/beam', 'Estimated noise in an nominal 10km/s bandwidth. Note this is an indication only, it does not include the effects of flagging or Hanning smoothing, and a 10km/s bandwidth may not be achievable with the data as taken.', 'double', NULL, 'adql:DOUBLE', NULL, 0, 0, 0, 47, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'cont_sensitivity_bandwidth', NULL, NULL, 'mJy/beam', 'Estimated noise in the aggregated continuum bandwidth. Note this is an indication only, it does not include the effects of flagging or dynamic range limitations.', 'double', NULL, 'adql:DOUBLE', NULL, 0, 0, 0, 48, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'pwv', NULL, 'obs.atmos.extinction', 'mm', 'Estimated precipitable water vapour from the XML_CALWVR_ENTITIES table.', 'float', NULL, 'adql:FLOAT', NULL, 0, 0, 0, 49, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'group_ous_uid', NULL, NULL, NULL, 'Group OUS ID', 'char', NULL, 'adql:VARCHAR', 64, 0, 0, 0, 50, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'member_ous_uid', NULL, NULL, NULL, 'Member OUS ID', 'char', NULL, 'adql:VARCHAR', 64, 0, 0, 0, 51, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'asdm_uid', NULL, 'meta.id', NULL, 'UID of the ASDM containing this Field.', 'char', NULL, 'adql:VARCHAR', 33, 1, 1, 0, 52, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'obs_title', NULL, 'meta.title;obs.proposal', NULL, 'Case-insensitive search over the project title', 'char', NULL, 'adql:VARCHAR', 256, 1, 1, 0, 53, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'scan_intent', NULL, 'meta.code.class;obs', NULL, 'Scan intent list for the observed field.', 'char', NULL, 'adql:VARCHAR', 256, 0, 0, 0, 54, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'spatial_scale_max', 'obscore:Characterisation.SpatialAxis.Coverage.Bounds.limits.Interval.HiLim', 'pos.angResolution;stat.max', 'arcsec', 'Due to the fact that radio antennas can not be placed infinitely close, measurements do have a smallest separation which translates into a maximal angular distance beyond which features can not be resolved reliably any more. Adding observations with the ALMA Total Power array can add those missing largest scales.', 'double', NULL, 'adql:DOUBLE', NULL, 0, 0, 0, 55, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'qa2_passed', NULL, 'meta.flag', NULL, 'Quality Assessment 2 status: does the Member / Group OUS fulfil the PI''s requirements?', 'char', NULL, 'adql:CHAR', 1, 0, 1, 0, 56, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'bib_reference', NULL, NULL, NULL, 'Bibliography code', 'char', NULL, 'adql:VARCHAR', 30, 0, 0, 0, 57, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'science_keyword', NULL, NULL, NULL, NULL, 'char', NULL, 'adql:VARCHAR', 200, 0, 0, 0, 58, null)
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'scientific_category', NULL, NULL, NULL, NULL, 'char', NULL, 'adql:VARCHAR', 200, 0, 0, 0, 59, null)      
    INTO TAP_SCHEMA.columns11 (table_name, column_name, utype, ucd, unit, description, datatype, arraysize, xtype, "size", principal, indexed, std, column_index, id) VALUES ('ivoa.obscore', 'lastModified', null, null, null, 'Time stamp of last modification of the metadata', 'char', '*', 'adql:TIMESTAMP', 32, 1, 0, 1, 60, null)  
SELECT 1 FROM DUAL;