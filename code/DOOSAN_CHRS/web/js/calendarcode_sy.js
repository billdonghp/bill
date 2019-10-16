
var topInPx_Y  = 0;
var leftInPx_X = 0;

var scroll_Top  = 0;
var scroll_Left = 0;

var IsCalendarVisible = false;
var calfrmName;
var maxYearList;
var minYearList;
var todayDate = new Date;
var curDate   = new Date;
var curDateBoxDD;
var curDateBoxMM;
var curDateBoxYY;
var curDateBox;
var minDate = new Date;
var maxDate = new Date;
var hideDropDowns;
var IsUsingMinMax;
var FuncsToRun;

var DivID  = "popupcalendar";
var PreVal = "";

minYearList=todayDate.getFullYear()-10;
maxYearList=todayDate.getFullYear()+10;

function showCalendar(frmName, dteBox, hideDrops, MnDt, MnMo, MnYr, MxDt, MxMo, MxYr,runFuncs) {

    NexVal      = frmName+""+dteBox;

    if ( ( PreVal != NexVal )&&( PreVal != "" ) ) {
      IsCalendarVisible = false;
      }

    PreVal      = frmName+""+dteBox;

  	topInPx_Y   = parseInt(window.event.y);
	  leftInPx_X  = parseInt(window.event.x);

    scroll_Top  = parseInt(document.body.scrollTop);
    scroll_Left = parseInt(document.body.scrollLeft);

    hideDropDowns = hideDrops;
    FuncsToRun = runFuncs;
    calfrmName = frmName;
    if (IsCalendarVisible) {
        hideCalendar();
    }
    else {

        if (hideDropDowns) {toggleDropDowns('hidden');}
        if ((MnDt!=null) && (MnMo!=null) && (MnYr!=null) && (MxDt!=null) && (MxMo!=null) && (MxYr!=null)) {
            IsUsingMinMax = true;
            minDate.setDate(MnDt);
            minDate.setMonth(MnMo-1);
            minDate.setFullYear(MnYr);
            maxDate.setDate(MxDt);
            maxDate.setMonth(MxMo-1);
            maxDate.setFullYear(MxYr);
        }
        else {
            IsUsingMinMax = false;
        }

        curDateBox = dteBox;

        domlay(1,Calendar(todayDate.getMonth(),todayDate.getFullYear()));

        IsCalendarVisible = true;
    }
}

function toggleDropDowns(showHow){
    var i; var j;
    for (i=0;i<document.forms.length;i++) {
        for (j=0;j<document.forms[i].elements.length;j++) {
            if (document.forms[i].elements[j].tagName == "SELECT") {
                if (document.forms[i].name != "Cal")
                    document.forms[i].elements[j].style.visibility=showHow;
            }
        }
    }
}

function hideCalendar(){
    domlay(0);
    IsCalendarVisible = false;
    if (hideDropDowns) {toggleDropDowns('visible');}
}

function domlay(trigger,content) {

    // Layer visible
    if (trigger=="1"){
        document.all[''+DivID+''].style.visibility = "visible";
        }
    // Layer hidden
    else if (trigger=="0"){
        document.all[''+DivID+''].style.visibility = "hidden";
        }

    if (content){
		document.all[''+DivID+''].innerHTML = content;
		document.all[''+DivID+''].style.top=(topInPx_Y+scroll_Top+8)+"px";
		document.all[''+DivID+''].style.left=(leftInPx_X+scroll_Left-100)+"px";
	}

}

function Calendar(whatMonth,whatYear) {
    var output = '';
    var datecolwidth;
    var startMonth;
    var startYear;
    startMonth=whatMonth;
    startYear=whatYear;

    curDate.setMonth(whatMonth);
    curDate.setFullYear(whatYear);
    curDate.setDate(todayDate.getDate());

        output += '<table width="185" border="1" class="cal-Table" cellspacing="0" cellpadding="0"><form name="Cal"><tr>';


    output += '<td class="cal-HeadCell" align="center"  width="100%"><a href="javascript:clearDay();"><img name="calbtn1" src="/jsp/images/calendar_sy/btn_del_small.gif" border="0" width="12" height="13"></a>&nbsp;&nbsp;<a href="javascript:scrollMonth(-1);" class="cal-DayLink"><img src="/jsp/images/calendar_sy/back.gif" align=absmiddle border=0></a>&nbsp;<SELECT class="cal-TextBox" NAME="cboMonth" onChange="changeMonth();">';
    for (month=0; month<12; month++) {
        if (month == whatMonth) output += '<OPTION VALUE="' + month + '" SELECTED>' + names[month] + '<\/OPTION>';
        else                output += '<OPTION VALUE="' + month + '">'          + names[month] + '<\/OPTION>';
    }

    output += '<\/SELECT><SELECT class="cal-TextBox" NAME="cboYear" onChange="changeYear();">';

    for (year=minYearList; year<maxYearList; year++) {
        if (year == whatYear) output += '<OPTION VALUE="' + year + '" SELECTED>' + year + '<\/OPTION>';
        else              output += '<OPTION VALUE="' + year + '">'          + year + '<\/OPTION>';
    }

    output += '<\/SELECT>&nbsp;<a href="javascript:scrollMonth(1);" class="cal-DayLink"><img src="/jsp/images/calendar_sy/front.gif" align=absmiddle border=0></a>&nbsp;&nbsp;<a href="javascript:hideCalendar();"><img src="/jsp/images/calendar_sy/close.gif" align=absmiddle border=0></a><\/td><\/tr><tr><td width="100%" align="center">';

    firstDay = new Date(whatYear,whatMonth,1);
    startDay = firstDay.getDay();

    if (((whatYear % 4 == 0) && (whatYear % 100 != 0)) || (whatYear % 400 == 0))
         days[1] = 29;
    else
         days[1] = 28;

    output += '<table width="185" cellspacing="1" cellpadding="2" border="0"><tr>';

    for (i=0; i<7; i++) {
        if (i==0 || i==6) {
            datecolwidth="15%"
        }
        else
        {
            datecolwidth="14%"
        }
        output += '<td class="cal-HeadCell" width="' + datecolwidth + '" align="center" valign="middle"><font color=#000080>'+ dow[i] +'<\/td>';
    }

    output += '<\/tr><tr>';

    var column = 0;
    var lastMonth = whatMonth - 1;
    var lastYear = whatYear;
    if (lastMonth == -1) { lastMonth = 11; lastYear=lastYear-1;}

    for (i=0; i<startDay; i++, column++) {
        output += getDayLink((days[lastMonth]-startDay+i+1),true,lastMonth,lastYear);
    }

    for (i=1; i<=days[whatMonth]; i++, column++) {
        output += getDayLink(i,false,whatMonth,whatYear);
        if (column == 6) {
            output += '<\/tr><tr>';
            column = -1;
        }
    }

    var nextMonth = whatMonth+1;
    var nextYear = whatYear;
    if (nextMonth==12) { nextMonth=0; nextYear=nextYear+1;}

    if (column > 0) {
        for (i=1; column<7; i++, column++) {
            output +=  getDayLink(i,true,nextMonth,nextYear);
        }
        output += '<\/tr><\/table><\/td><\/tr>';
    }
    else {
        output = output.substr(0,output.length-4); // remove the <tr> from the end if there's no last row
        output += '<\/table><\/td><\/tr>';
    }
        output += '<\/form><\/table>';

    curDate.setDate(1);
    curDate.setMonth(startMonth);
    curDate.setFullYear(startYear);
	return output;
}

function getDayLink(linkDay,isGreyDate,linkMonth,linkYear) {
    var templink;
    if (!(IsUsingMinMax)) {
        if (isGreyDate) {
            templink='<td align="center" class="cal-GreyDate">' + linkDay + '<\/td>';
        }
        else {
            if (isDayToday(linkDay)) {
                templink='<td align="center" class="cal-DayCell">' + '<a class="cal-TodayLink" onmouseover="self.status=\' \';return true" href="javascript:changeDay(' + linkDay + ');">' + linkDay + '<\/a>' +'<\/td>';
            }
            else {
                templink='<td align="center" class="cal-DayCell">' + '<a class="cal-DayLink" onmouseover="self.status=\' \';return true" href="javascript:changeDay(' + linkDay + ');">' + linkDay + '<\/a>' +'<\/td>';
            }
        }
    }
    else {
        if (isDayValid(linkDay,linkMonth,linkYear)) {

            if (isGreyDate){
                templink='<td align="center" class="cal-GreyDate">' + linkDay + '<\/td>';
            }
            else {
                if (isDayToday(linkDay)) {
                    templink='<td align="center" class="cal-DayCell">' + '<a class="cal-TodayLink" onmouseover="self.status=\' \';return true" href="javascript:changeDay(' + linkDay + ');">' + linkDay + '<\/a>' +'<\/td>';
                }
                else {
                    templink='<td align="center" class="cal-DayCell">' + '<a class="cal-DayLink" onmouseover="self.status=\' \';return true" href="javascript:changeDay(' + linkDay + ');">' + linkDay + '<\/a>' +'<\/td>';
                }
            }
        }
        else {
            templink='<td align="center" class="cal-GreyInvalidDate">'+ linkDay + '<\/td>';
        }
    }
    return templink;
}

function isDayToday(isDay) {
    if ((curDate.getFullYear() == todayDate.getFullYear()) && (curDate.getMonth() == todayDate.getMonth()) && (isDay == todayDate.getDate())) {
        return true;
    }
    else {
        return false;
    }
}

function isDayValid(validDay, validMonth, validYear){

    curDate.setDate(validDay);
    curDate.setMonth(validMonth);
    curDate.setFullYear(validYear);

    if ((curDate>=minDate) && (curDate<=maxDate)) {
        return true;
    }
    else {
        return false;
    }
}

function padout(number) { return (number < 10) ? '0' + number : number; }

function clearDay() {
    eval('document.' + calfrmName + '.' + curDateBox + '.value = \'\'');
    hideCalendar();
    if (FuncsToRun!=null)
        eval(FuncsToRun);
}

function changeDay(whatDay) {
    curDate.setDate(whatDay);
    eval('document.' + calfrmName + '.' + curDateBox + '.value = "'+
                            curDate.getFullYear() +
                            (((curDate.getMonth()+1) <10)? "0"+(curDate.getMonth()+1):(curDate.getMonth()+1) ) +
                            ((curDate.getDate()<10) ? "0"+curDate.getDate():padout(curDate.getDate())) + '"');

    hideCalendar();
    if (FuncsToRun!=null)
        eval(FuncsToRun);
}

function scrollMonth(amount) {
    var monthCheck;
    var yearCheck;

        monthCheck = document.forms["Cal"].cboMonth.selectedIndex + amount;

    if (monthCheck < 0) {
        yearCheck = curDate.getFullYear() - 1;
        if ( yearCheck < minYearList ) {
            yearCheck = minYearList;
            monthCheck = 0;
        }
        else {
            monthCheck = 11;
        }
        curDate.setFullYear(yearCheck);
    }
    else if (monthCheck >11) {
        yearCheck = curDate.getFullYear() + 1;
        if ( yearCheck > maxYearList-1 ) {
            yearCheck = maxYearList-1;
            monthCheck = 11;
        }
        else {
            monthCheck = 0;
        }
        curDate.setFullYear(yearCheck);
    }

    curDate.setMonth(document.forms["Cal"].cboMonth.options[monthCheck].value);

    domlay(1,Calendar(curDate.getMonth(),curDate.getFullYear()));
}

function changeMonth() {
  curDate.setMonth(document.forms["Cal"].cboMonth.options[document.forms["Cal"].cboMonth.selectedIndex].value);
  domlay(1,Calendar(curDate.getMonth(),curDate.getFullYear()));
  }

function changeYear() {
  curDate.setFullYear(document.forms["Cal"].cboYear.options[document.forms["Cal"].cboYear.selectedIndex].value);
  domlay(1,Calendar(curDate.getMonth(),curDate.getFullYear()));
  }

function makeArray0() {
    for (i = 0; i<makeArray0.arguments.length; i++)
        this[i] = makeArray0.arguments[i];
}

var names     = new makeArray0('Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec');
var days      = new makeArray0(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
var dow       = new makeArray0('Su','Mo','Tu','We','Th','Fr','Sa');
