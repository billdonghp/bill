function CheckForm(a) {		
		
		if (document.sf.DISTINCT_FIELD.value == "PERSON_ID" && document.sf.DISTINCT_FIELD_2ND.value != "") 	{
		//"\n 已选择工号为区分项目，不能选择其它区分项目！"
			alert ('已选择工号为区分项目，不能选择其它区分项目');
			document.sf.DISTINCT_FIELD_2ND.options[0].selected = true ;
			return false;
		}
		
		return true;
}
