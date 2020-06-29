CREATE OR REPLACE VIEW public.web_vdgo_bypass
 AS
 SELECT vdg_obj_work.id,
    vdg_obj_work.id_object,
    vdg_obj_work.id_exec,
    vdg_obj_work.type_dog,
    vdg_obj_work.date_action,
    vdg_obj_work.exec_vdgo,
        CASE
            WHEN web_vdgo_buff_data.exec_vdgo IS NULL THEN 0
            ELSE web_vdgo_buff_data.exec_vdgo::integer
        END AS web_exec,
        CASE
            WHEN web_vdgo_buff_data.undone_reason IS NULL THEN 0
            ELSE web_vdgo_buff_data.undone_reason::integer
        END AS undone_reason,
        CASE
            WHEN web_vdgo_buff_data.fixed IS NULL THEN false
            ELSE web_vdgo_buff_data.fixed
        END AS fixed,
        CASE
            WHEN web_vdgo_buff_data.id IS NULL THEN false
            ELSE true
        END AS is_created
   FROM vdg_obj_work
     LEFT JOIN web_vdgo_buff_data ON vdg_obj_work.id = web_vdgo_buff_data.id_bypass
  WHERE vdg_obj_work.type_action = 0 AND vdg_obj_work.date_action = '2020-06-22 00:00:00+03'::timestamp with time zone;

ALTER TABLE public.web_vdgo_bypass
    OWNER TO postgres;