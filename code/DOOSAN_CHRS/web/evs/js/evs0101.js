
function CheckForm() {
ev_year=document.LGFORM.ev_year.value; 
	if(ev_year=='2004')
	{
		alert("???????");
		document.LGFORM.ev_year.focus();
		return false;
	}
	return true;
}
