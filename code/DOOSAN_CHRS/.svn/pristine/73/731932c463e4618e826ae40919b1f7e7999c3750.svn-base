
var xaOptionTable = {

  /**** General Options *******************************************************************************************/
  "X_ACTIVATE"               : true,
  "X_SHOW_ELAPSED"           : true,
  "X_SUPPORT_HIDDEN_TYPE"    : true,
  "X_COLOR_SET_USE_CSS"      : false,
  "X_ALLOW_MULTI_CREATE"     : false,
  "X_DISABLE_ON_HIDE"        : true,
  "X_ERASE_VALUE_ON_DISABLE" : true,

  /**** Submit Process Options ************************************************************************************/
  "X_HOOK_SUBMIT_EVENT_ACTIVATE" : true,
  "X_VALIDATION_ACTIVATE"        : true,
  "X_DYNAMIC_SEND_VALUE"         : true,
  "X_DYNAMIC_ACTION_URL"         : true,
  "X_DISABLE_SUBMIT_ON_SUBMIT"   : true,
  "X_DISABLE_SUBMIT_TIMEOUT"     : 3000,
  "X_DISABLE_SUBMIT_TYPE"        : "submit,button",

  /**** Performance Options ***************************************************************************************/
  "X_RESTRICT_APPLY"       : false,
  "X_BINDING_RULE_APPLY"   : true,

  /**** Validation Options ****************************************************************************************/
  "X_VALIDATE_ON_MASK"         : true,
  "X_VALIDATE_ON_HIDE"         : true,
  "X_VALIDATE_ON_READONLY"     : true,
  "X_VALIDATE_ON_DISABLED"     : true,
  "X_VALIDATE_DO_ALERT"        : true,
  "X_VALIDATE_DO_ERROR_ACTION" : true,
  "X_VALIDATE_DO_TRIM_VALUE"   : true, 

  /**** Keyword Options *******************************************************************************************/
  "X_REQUIRED_IGNORE_WHITESPACE" : true,
  "X_FOCUS_THIS_USE_TIMEOUT"     : 30, //use -1, for focus_this not use timeout
  "X_DAYS_IN_MONTH"              : [31,28,31,30,31,30,31,31,30,31,30,31],
  "X_CSN_MULTIPLIERS"            : [1,3,7,1,3,7,1,3,5],
  "X_FSN_MULTIPLIERS"            : [2,3,4,5,6,7,8,9,2,3,4,5],
  "X_AUTO_FOCUS_FKEY"            : [229,16,17,18,21,25,9,8,37,38,39,40,33,34,35,36,45,91,93,19,20,144,145], //0,8,46
  "X_STRIP_WHITE_SPACE_CHAR"     : /\s/g,
  "X_STRIP_SPECIAL_CHAR"         : /(\(|\)|\[|\]|\{|\}|\<|\>|\"|\'|\`|\~|\$|\!|\#|\%|\^|\&|\@|\,|\.|\;|\:|\\|\/|\||\*|\=|\-|\?)*/g, // ( ) [ ] { } < > " ' ` ~  $ ! # % ^ & @  , . ; :  \ / |  * = - ?
  "X_HPD"                        : ["011", "016", "017", "018", "019", "0130"],
  "X_DDD"                        : ["02", "031", "032", "033", "041", "042", "043", "051", "052", "053", "054", "055", "061", "062", "063", "064", "080", "0502", "0505"],

  /**** Literal Definition ****************************************************************************************/
  "X_MASK_DATE"          : "9999/99/99" ,
  "X_MASK_PSN"           : "999999-9999999" ,
  "X_MASK_CSN"           : "999-99-99999" ,
  "X_FILTER_ALPHA"       : "[a-zA-Z]" ,
  "X_FILTER_NUM"         : "[0-9]" ,
  "X_FILTER_ALNUM"       : "[A-Za-z0-9]" ,
  "X_FILTER_INTEGER"     : "[0-9\\-\\+]" ,
  "X_FILTER_FLOAT"       : "[0-9\\.\\-\\+]" ,
  "X_FILTER_HEXA"        : "[a-fA-F0-9\\-\\+]" ,
  "X_REGEXP_ALPHA"       : /^[a-zA-Z]+$/ ,
  "X_REGEXP_NUM"         : /^[0-9]+$/ ,
  "X_REGEXP_ALNUM"       : /^[A-Za-z0-9]+$/ ,
  "X_REGEXP_INTEGER"     : /^(\+|\-|\d*)\d+$/ ,
  "X_REGEXP_FLOAT"       : /^(\-|\+|\d*)\d+(\.|\d)\d*$/ ,
  "X_REGEXP_HEXA"        : /^(\+|\-|[a-fA-F0-9]*)[a-fA-F0-9]+$/ ,
  "X_DELIMITER_NUMBER"   : /([^0-9\.\-])/g ,
  "X_DELIMITER_CHAR"     : /(\,|\.|\/|\$|\^|\*|\(|\)|\+|\?|\\|\{|\}|\||\[|\]|-|:)/g  // ,./$^*()+?\{}|[]-:

};

var xaSupportMatrix = {
  "required"           : [3,  "xo_required",           "text,password,textarea,radio,checkbox,select-one,select-multiple,file"],
  "reg_exp"            : [2,  "xo_reg_exp",            "text,password"],
  "filter"             : [2,  "xo_filter",             "text,password,textarea"],
  "mask"               : [2,  "xo_mask",               "text"],
  "alphabetic"         : [1,  "xo_alphabetic",         "text,password"],
  "numeric"            : [1,  "xo_numeric",            "text,password"],
  "alpha_numeric"      : [1,  "xo_alpha_numeric",      "text,password"],
  "integer"            : [1,  "xo_integer",            "text,password"],
  "float"              : [1,  "xo_float",              "text,password"],
  "hexa"               : [1,  "xo_hexa",               "text,password"],
  "maxlength"          : [2,  "xo_minlength",          "text,password,textarea,file"],
  "minlength"          : [2,  "xo_minlength",          "text,password,textarea,file"],
  "maxbyte"            : [2,  "xo_maxbyte",            "text,password,textarea,file"],
  "minbyte"            : [2,  "xo_minbyte",            "text,password,textarea,file"],
  "maxvalue"           : [2,  "xo_maxvalue",           "text,password"],
  "minvalue"           : [2,  "xo_minvalue",           "text,password"],
  "money"              : [1,  "xo_money",              "text"],
  "dollar"             : [1,  "xo_dollar",             "text"],
  "date"               : [1,  "xo_date",               "text"],
  "credit_card"        : [1,  "xo_credit_card",        "text,password"],
  "email"              : [1,  "xo_email",              "text"],
  "domain"             : [1,  "xo_domain",             "text"],
  "psn"                : [1,  "xo_psn",                "text,password"],
  "csn"                : [1,  "xo_csn",                "text,password"],
  "lsn"                : [1,  "xo_lsn",                "text,password"],
  "fsn"                : [1,  "xo_fsn",                "text,password"],
  "plain_box"          : [1,  "xo_plain_box",          "text,password,textarea,file,submit,button,reset"],
  "edit_align"         : [2,  "xo_edit_align",         "text,password,textarea"],
  "ime"                : [2,  "xo_ime",                "text,password,textarea"],
  "lable"              : [1,  "xo_lable",              "text"],
  "auto_focus"         : [1,  "xo_auto_focus",         "text,password"],
  "enter_move_focus"   : [1,  "xo_enter_move_focus",   "text,password,radio,checkbox,select-one,select-multiple,file,button,reset"],
  "focus_this"         : [1,  "xo_focus_this",         "text,password,textarea,radio,checkbox,select-one,select-multiple,file,submit,button,reset"],
  "show_this"          : [1,  "xo_show_this",          "text,password,textarea,radio,checkbox,select-one,select-multiple,file,submit,button,reset"],
  "hide_this"          : [1,  "xo_hide_this",          "text,password,textarea,radio,checkbox,select-one,select-multiple,file,submit,button,reset"],
  "disable_this"       : [1,  "xo_disable_this",       "text,password,textarea,radio,checkbox,select-one,select-multiple,file,submit,button,reset"],
  "readonly_this"      : [1,  "xo_readonly_this",      "text,password,textarea"],
  "selected_duih"      : [1,  "xo_selected_duih",      "select-one,select-multiple"],
  "show_fields"        : [2,  "xo_show_fields",        "text,password,textarea,radio,checkbox,select-one,select-multiple,file"],
  "hide_fields"        : [2,  "xo_hide_fields",        "text,password,textarea,radio,checkbox,select-one,select-multiple,file"],
  "enable_fields"      : [2,  "xo_enable_fields",      "text,password,textarea,radio,checkbox,select-one,select-multiple,file"],
  "disable_fields"     : [2,  "xo_disable_fields",     "text,password,textarea,radio,checkbox,select-one,select-multiple,file"],
  "trim_this"          : [3,  "xo_trim_this",          "text,password,textarea,file"],
  "strip_special_char" : [1,  "xo_strip_special_char", "text,password,textarea"],
  "strip_white_space"  : [1,  "xo_strip_white_space",  "text,password,textarea"],
  "ddd"                : [1,  "xo_ddd",                "text,select-one"],
  "hpd"                : [1,  "xo_hpd",                "text,select-one"],
  "case"               : [2,  "xo_case",               "text,password,textarea"],
  "send_value"         : [2,  "xo_send_value",         "submit"],
  "dyna_action"        : [2,  "xo_dyna_action",        "submit"],
  "status_bar"         : [2,  "xo_status_bar",         "text,password,textarea,radio,checkbox,select-one,select-multiple,file,submit,button,reset"],
  "sync"               : [2,  "xo_sync",               "text,password,radio,checkbox,select-one"],
  "is_value"           : [2,  "xo_is_value",           "text,password"],
  "filesize_limit"     : [2,  "xo_filesize_limit",     "file"]
};

var xaMessagesTable = {
  "required"       : [ "required input." ],
  "reg_exp"        : [ "not conform to Regular Expression. \n@ shoould conform to it." ],
  "mask"           : [ "input is invalid format.\ninput like [@]" ],
  "alphabetic"     : [ "not an alphabetic" ],
  "numeric"        : [ "not a numeric" ],
  "alpha_numeric"  : [ "not an alphabetic or numeric" ],
  "integer"        : [ "not an integer." ],
  "float"          : [ "not a real number format" ],
  "hexa"           : [ "not a hexa-digit" ],
  "maxlength"      : [ "to long. \nits length should shorter than @" ],
  "minlength"      : [ "to short. \nits length should more than @" ],
  "maxbyte"        : [ "to long. \nits length should shorter than @. \n" ],
  "minbyte"        : [ "to short. \nits length should more than @" ],
  "maxvalue"       : [ "to big. \nnow it is @\nit should less than @" ],
  "minvalue"       : [ "to small. \nnow its is @\nit should bigger than @" ],
  "money"          : [ "it's not a valid Won." ],
  "dollar"         : [ "it's not a valid $." ],
  "date"           : [ "not a date." ],
  "credit_card"    : [ "not a credit card format." ],
  "email"          : [ "not an email address." ],
  "domain"         : [ "invalid domain." ],
  "psn"            : [ "invalid resident registration number." ],
  "csn"            : [ "invalid business registration number." ],
  "lsn"            : [ "invalid company number." ],
  "fsn"            : [ "invalid foreigner number." ],
  "hpd"            : [ "@ is not valid cellphone number." ],
  "ddd"            : [ "@ is not valid DDD number." ],
  "is_value"       : [ "input for [@] is not equal to input for [@].", "xJos Says :\nat the [@],the use of is_value is incorrect. \nthe input field object for [@] is not exist or \ntoo many." ],
  "filesize_limit" : [ "file size is to big. it should below [@]." ],
  "itemname"       : [ "input error : itm [@]\n\n" ],
  "disable_submit" : [ "duplicated request :\nplease retry later."]
};

function sm_trim()  { return this.replace(/^\s+/ ,"").replace(/\s+$/ ,"");  }

function sm_left_trim()  { return this.replace(/^\s*/,""); }

function sm_right_trim() { return this.replace(/\s*$/,""); }

function sm_is_num () { return /\d/.test(this); }

function sm_get_byte()  {
  var len = 0;
  var itill = this.length;
  for (var idx=0 ; idx < itill ; idx++, len++)   {
    if ( (this.charCodeAt(idx)<0) || (this.charCodeAt(idx)>127) ) len ++;
  }
  return len;
}

function sm_hack(mainsep, subsep)  {
  var rArray = new Array;
  var aArray = this.split(mainsep);
  var tArray;

  for(var idx=0; idx < aArray.length ; idx++)  {
    tArray = aArray[idx].split(subsep);
    if (typeof(tArray[0]) == "undefined") continue;
    rArray[length] = [ tArray[0], (typeof(tArray[1]) == "undefined" ) ? null : tArray[1] ];
  }

  return rArray;
}

function sm_substitute_at()  {
  var target = this;
  var itill = arguments.length;
  for (var idx=0; idx < itill; idx++) {
    target = target.replace(/@/i, arguments[idx]);
  }
  return target;
}

function sm_filtering(filter)  {
  var result = "";
  var re = new RegExp(filter);
  for (var idx=0; idx < this.length; idx++) result += re.test(this.charAt(idx)) ? this.charAt(idx) : "";
  delete re;
  return result;
}

function sm_contain(key)  {
  return (this == key) ? true : false;
}


function am_compact()  {
  var nArray = new Array;
  var params = xuGetArray(arguments);
  for (key in this) {
    if (typeof(this[key]) == "function" ) continue;
    if (typeof(this[key]) == "undefined") continue;
    if (params.contain(this[key])) continue;
    if (key == parseInt(key,10) ) nArray[nArray.length] = this[key];
    else nArray[key] = this[key];
  }
  return nArray;
}

function am_hack(mainsep, subsep)  {
  var nArray = new Array;
  var node = null;

  for (key in this) {
    node = this[key];
    if (typeof(node) == "function" ) continue;
    if (typeof(node) == "undefined") continue;
    if (node instanceof Array) nArray[nArray.length] = node.join(subsep);
    else if(typeof(node.name) && node.getValue && node.getValue() != null ) nArray[nArray.length] = node.name + subsep + node.getValue() ;
  }
  return nArray.join(mainsep);
}

function am_contain(k)  {
  for (key in this) {
    if (typeof(key) == "function" ) continue;
    if (this[key] == k) return true;
  }
  return false;
}

function pm_set_css(attr)  {
  var css = this.getAttribute("className");
  if (!css) {
    this.setAttribute("className", attr);
    return;
  }
  if (!css.split(" ").contain(attr)) {
    this.setAttribute("className", css + " " + attr);
  }
}

function pm_unset_css(attr)  {
  if (!this.getAttribute("className")) return;
  var cArray = this.getAttribute("className").split(" ");
  cArray = cArray.compact(attr);
  this.setAttribute("className", cArray.join(" "));
}

function pm_contain_css(attr)  {
  if (!this.getAttribute("className"))  return false;
  var classArr = this.getAttribute("className").split(" ");
  return classArr.contain(attr);
}
function pm_toggle_css()  {

  if (!this.getAttribute("className")) {
    this.setAttribute("className", arguments[0]);
    return;
  }

  var params = xuGetArray(arguments);
  var cArray = this.getAttribute("className").split(" ");
  var nArray = new Array;

  var itill = cArray.length;
  for ( var idx=0; idx<itill; idx++) {
    if ( !params.contain(cArray[idx]) ) nArray[nArray.length] = cArray[idx];
  }
  nArray[nArray.length] = arguments[0];
  this.setAttribute("className", nArray.join(" "));
  delete nArray;
}

function pm_do_action(execCmd, type) {
  var tfunc  = new Function(execCmd);
  window.xJos.set(this, type);
  var flag = tfunc();
  delete tfunc;
  if (flag && xJos.cancel) flag = false;
  window.xJos.clear();
  return flag;
}

function em_initialize(parent)  {

  //attribute
  this.xjos      = true;       //tagged by xJos
  this.xBuffer   = new Array;  //detected xJos keywords buffering array
  this.xBuffered = false;      //buffer flag for window.xBuffer
  this.parent    = parent;     //represent parentNode/form/parentElement
  this.svalue    = null;       //stored value
  this.impotence = false;      //element.validate disabler flag
  this.bgColor   = this.style.backgroundColor;

  //Pseudo-inheritance Method definition
  this.setCss      = pm_set_css;
  this.unsetCss    = pm_unset_css;
  this.containCSS  = pm_contain_css;

  this.toggleCss   = pm_toggle_css;
  this.doAction    = pm_do_action;

  //methods definition
  this.get      = em_get;
  this.set      = em_set;
  this.clear    = em_clear;
  this.getValue = em_get_value;
  this.setValue = em_set_value;
  this.contain  = em_contain;
  this.store    = em_store;
  this.restore  = em_restore;

  this.setBgColor   = em_set_bg_color;
  this.unsetBgColor = em_unset_bg_color;

  this.disable       = em_disable;
  this.enable        = em_enable;
  this.show          = em_show;
  this.hide          = em_hide;
  this.setReadOnly   = em_set_readonly;
  this.unsetReadOnly = em_unset_readonly
  this.setCheck      = em_set_check;
  this.unsetCheck    = em_unset_check;

  this.trim  = em_trim;
  this.lTrim = em_left_trim;
  this.rTrim = em_right_trim;

  this.call  = em_call;

  this.amIhide             = em_am_i_hide;
  this.getNextFocus        = em_get_next_focus;
  this.getPrevFocus        = em_get_prev_focus;
  this.preventFocusHandler = ee_prevent_focus_handler;

  //binding events (delegate to xJos keyword)
  this.eventHandler = em_event_handler;

  this.detachEvent("onkeypress",  this.eventHandler);
  this.detachEvent("onkeyup",     this.eventHandler);
  this.detachEvent("onkeydown",   this.eventHandler);
  this.detachEvent("onfocus",     this.eventHandler);
  this.detachEvent("onblur",      this.eventHandler);
  this.detachEvent("onchange",    this.eventHandler);
  this.detachEvent("onclick",     this.eventHandler);
  this.detachEvent("onmouseover", this.eventHandler);
  this.detachEvent("onmouseout",  this.eventHandler);
  this.detachEvent("onpaste",     this.eventHandler);

  this.attachEvent("onkeypress",  this.eventHandler);
  this.attachEvent("onkeyup",     this.eventHandler);
  this.attachEvent("onkeydown",   this.eventHandler);
  this.attachEvent("onfocus",     this.eventHandler);
  this.attachEvent("onblur",      this.eventHandler);
  this.attachEvent("onchange",    this.eventHandler);
  this.attachEvent("onclick",     this.eventHandler);
  this.attachEvent("onmouseover", this.eventHandler);
  this.attachEvent("onmouseout",  this.eventHandler);
  this.attachEvent("onpaste",     this.eventHandler);

  //xjos engines method
  this.xFactory = em_x_factory;
  this.attachX  = em_attach_x;
  this.detachX  = em_detach_x;
  this.clearX   = em_clear_x;

  this.getMessage = em_get_message;
  this.alertX     = em_alert_x;
  this.validate   = em_validate;

  this.masking          = em_masking;
  this.unmasking        = em_unmasking;
  this.getMaskedValue   = em_get_masked_value;
  this.getUnmaskedValue = em_get_unmasked_value;

  this.getMaskedValueHandler = null;
  this.getUnmaskedValueHandler = null;
  this.setMaskHandler = em_set_mask_handler;

  //initialize keyword
  this.xFactory();
}

function em_get() {

  switch (this.type) {
    case "text" :   case "password" :    case "textarea" :
    case "file" :   case "hidden" :
      return this.value;
    break;

    case "select-one" :
      return [this.selectedIndex];
    break;

    case "select-multiple" :
      var sArray = new Array;
      var itill = this.options.length;
      for (var idx=0; idx<itill ; idx++) {
        if (this.options[idx].selected) {
            sArray[sArray.length] = idx;
        }
      }
      return sArray;
    break;

    case "radio" :  case "checkbox" :
      return this.checked;
    break;
  }
  return null;
}

function em_set(sval) {
   switch (this.type) {
    case "text" :   case "password" :   case "textarea" :
    case "file" :   case "hidden" :
      this.value = sval;
    break;

    case "select-one" :
      this.selectedIndex = sval[0];
    break;

    case "select-multiple" :
      var itill = sval.length;
      for (var idx=0; idx< itill ; idx++) {
        this.options[sval[idx]].selected = true;
      }
    break;

    case "radio" :  case "checkbox" :
      this.checked = sval;
    break;
   }
}

function em_clear()  {

   switch (this.type) {
    case "text" :   case "password" :    case "textarea" :
    case "file" :   case "hidden" :
      this.value = "";
    break;

    case "select-one" : case "select-multiple" :
      this.selectedIndex = -1;
    break;

    case "radio" :  case "checkbox" :
      this.checked = false;
    break;
  }
}

function em_get_value() {

  switch (this.type) {
    case "text" :   case "password" :    case "textarea" :
    case "file" :   case "hidden" :
      return this.value;
    break;

    case "select-one" :
      return this.options[this.selectedIndex].value;
    break;

    case "select-multiple" :
      var sArray = new Array;
      var itill = this.options.length;
      for (var idx=0; idx<itill; idx++) {
        if (this.options[idx].selected) {
            sArray[sArray.length] = this.options[idx].value;
        }
      }
      return sArray;
    break;

    case "radio" :  case "checkbox" :
      if (this.checked) return this.value;
    break;
  }

  return "";

}

function em_set_value(val) {

  switch (this.type) {
    case "text" :   case "password" :    case "textarea" :
    case "file" :   case "hidden"   :    case "select-one" :
      this.value = val;
    break;

    case "select-multiple" :
      var itill = this.options.length;
      for (var idx=0; idx<itill; idx++) {
        if (val.contain(this.options[idx].value))
          this.options[idx].selected = true;
      }
    break;

    case "radio" :  case "checkbox" :
      this.checked = (this.value == val) ? true : false;
    break;
  }
}

function em_contain() {

  var params = arguments[0] instanceof Array ? arguments[0] : xuGetArray(arguments);

  switch (this.type) {
    case "text" :   case "password" :    case "textarea" :
    case "file" :   case "hidden"   :
      return (arguments.legnth == 0 && this.value.length) ? true : params.contain(this.value);
    break;

    case "select-one" :
      if (this.selectedIndex == -1) return false;
      if (arguments.legnth == 0 && this.options[this.selectedIndex].value.length) return true;
      if (arguments.legnth > 0 && params.contain(this.value) ) return true;
    break;

    case "select-multiple" :
      if (this.selectedIndex == -1) return false;
      var itill = this.options.length;
      for (var idx=0; idx<itill; idx++) {
        if (!this.options[idx].selected) continue;
        if (arguments.legnth == 0 && this.options[idx].value.length) return true;
        if (arguments.legnth > 0 && params.contain(this.options[idx].value) ) return true;
      }
    break;

    case "radio" :  case "checkbox" :
      if (!this.checked) return false;
      if (arguments.legnth == 0) return this.checked;
      if (arguments.legnth == 0 && params.contain(this.value)) return true;
    break;
  }
  return false;
}
function em_store()  {
  this.svalue = this.get();
}
function em_restore()  {
  if (this.svalue == null) return;
  this.set(this.svalue);
}

function em_set_bg_color(nColor, nCss)  {
  window.xGetOption("X_COLOR_SET_USE_CSS") ? this.setCss(nCss) : this.style.backgroundColor = nColor;
}
function em_unset_bg_color(oCss)  {
  window.xGetOption("X_COLOR_SET_USE_CSS") ? this.unsetCss(oCss) : this.style.backgroundColor = this.bgColor;
}
function em_disable()  {
  if (this.getAttribute("disabled")) return;
  this.setAttribute("disabled",true);
  this.setBgColor("#cccccc", "xjs_disable");
  if (window.xGetOption("X_VALIDATE_ON_DISABLED")) this.impotence = true;

  if (window.xGetOption("X_ERASE_VALUE_ON_DISABLE")) {

    switch (this.type) {
      case "text" :   case "password" :   case "textarea" :
      case "file" :   case "image" :
      case "select-one" :  case "select-multiple" :
      case "radio" :  case "checkbox" :
        this.store(); this.clear();
      break;
    }

  }
}
function em_enable()  {
  if (!this.getAttribute("disabled")) return;
  this.removeAttribute("disabled");
  this.unsetBgColor("xjs_disable");
  if(window.xGetOption("X_VALIDATE_ON_DISABLED")) this.impotence = false;

  if (window.xGetOption("X_ERASE_VALUE_ON_DISABLE")) {
    switch (this.type) {
      case "text" :   case "password" :   case "textarea" :
      case "file" :   case "image" :
      case "select-one" :  case "select-multiple" :
      case "radio" :  case "checkbox" :
       this.restore();
      break;
    }
  }
}
function em_show()  {
   if (this.containCSS("xjs_show")) return;
   if (window.xGetOption("X_DISABLE_ON_HIDE")) this.enable();
   this.toggleCss("xjs_show", "xjs_hide");
}
function em_hide()  {
  if (this.containCSS("xjs_hide")) return;
  this.toggleCss("xjs_hide", "xjs_show");
  if (  window.xGetOption("X_DISABLE_ON_HIDE")) this.disable();
}
function em_set_readonly()  {
  if ( this.readOnly || this.getAttribute("disabled")) return;
  switch (this.type) {
    case "text" :
    case "password" :
    case "textarea" :
      this.readOnly = true;
      this.setBgColor("#FFFFCC", "xjs_readonly");
      this.detachEvent("onfocus", this.preventFocusHandler);
      this.attachEvent("onfocus", this.preventFocusHandler);
    break;
 }
}
function em_unset_readonly()  {
  if ( !this.readOnly ) return;
  switch (this.type) {
    case "text" :
    case "password" :
    case "textarea" :
      this.readOnly = false;
      this.unsetBgColor("xjs_readonly");
      this.detachEvent("onfocus", this.preventFocusHandler);
    break;
 }
}
function em_set_check()  {
  switch (this.type) {
    case "checkbox" :
    case "radio" :
      this.checked  = true;
    break;
 }
}
function em_unset_check()  {
  switch (this.type) {
    case "checkbox" :
    case "radio" :
      this.checked  = false;
    break;
 }
}
function em_trim()  { if (typeof(this.value) == "string") this.value =  this.value.trim(); }
function em_left_trim() { if (typeof(this.value) == "string") this.value =  this.value.lTrim(); }
function em_right_trim() { if (typeof(this.value) == "string") this.value =  this.value.rTrim(); }
function em_call(func)  {
  var ecmd = "this." + func + "()";
  try { return eval(ecmd); } catch(e) {return ;}
}
function em_am_i_hide()  {
  var oCraft = this;
  do {
    if ( ( oCraft.containCSS && oCraft.containCSS("xjs_hide")) ||
         ( oCraft.style && ( oCraft.style.display =='none' || oCraft.style.visibility =='hidden') ) ||
         ( oCraft.currentStyle && ( oCraft.currentStyle.display =='none' || oCraft.currentStyle.visibility =='hidden' ) )
       ) return true;
    oCraft = oCraft.parentElement;
  } while (oCraft && oCraft.tagName != "BODY");

  return false;
}
function em_get_next_focus(objname)  {
  if (this.parent.elements.length == 1 && this==this.parent.elements[0]) return this;

  var selfIndex = 0;
  var itill = this.parent.elements.length;
  var oCraft = null;
  for (var idx=0; idx < itill; idx++) {
    if (this.parent.elements[idx] == this) selfIndex = idx;
  }

  do {
    selfIndex ++;
    if (selfIndex >= itill ) selfIndex = 0;
    oCraft = this.parent.elements[selfIndex];
    if (oCraft == this) break;
    if (objname && oCraft.name && oCraft.name == objname) break;
  } while ( typeof(oCraft) == 'undefined' || oCraft.tagName == "OBJECT" || oCraft.readOnly ||
            ( oCraft.type && oCraft.type=='hidden') ||
            ( oCraft.amIhide && oCraft.amIhide() ) ||
            ( oCraft.getAttribute && oCraft.getAttribute("disabled")) ||
            ( objname && oCraft.name && oCraft.name != objname )
           );

  return oCraft;
}
function em_get_prev_focus(objname)  {

  if (this.parent.elements.length == 1 && this==this.parent.elements[0]) return this;

  var selfIndex = 0;
  var itill = this.parent.elements.length;
  var oCraft = null;

  for (var idx=0; idx < itill; idx++) {
    if (this.parent.elements[idx] == this) selfIndex = idx;
  }

  do {
    selfIndex --;
    if (selfIndex < 0 ) selfIndex = itill - 1 ;
    oCraft = this.parent.elements[selfIndex];
    if (oCraft == this) break;
    if (objname && oCraft.name && oCraft.name == objname) break;
  } while ( typeof(oCraft) == 'undefined' || oCraft.tagName == "OBJECT" || oCraft.readOnly ||
            ( oCraft.type && oCraft.type=='hidden') ||
            ( oCraft.amIhide && oCraft.amIhide() ) ||
            ( oCraft.getAttribute && oCraft.getAttribute("disabled") ) ||
            ( objname && oCraft.name && oCraft.name != objname )
           );

  return oCraft;
}
function ee_prevent_focus_handler()  {
  var sCraft = window.event.srcElement;
  var fCraft = null;
  try {
    fCraft = sCraft.getNextFocus();
    if (fCraft != sCraft) fCraft.focus();
  } catch (e) {
    if (sCraft.blur) sCraft.blur();
  }
}
function em_event_handler()  {
  var oSrc = window.event.srcElement;
  var type = window.event.type;
  if (!oSrc.xjos || !oSrc.xBuffer.length ) return;
  var itill = oSrc.xBuffer.length ;
  var ecmd = "";

  for (var idx=0; idx < itill; idx++) {
    ecmd = "window." + oSrc.xBuffer[idx] + "_on" + type ;
    try {
      if ( eval(ecmd) && eval(ecmd + "(oSrc, window.event)") == false) { window.event.returnValue = false; return false; }
    } catch(e) {}
  }

  if ( type == 'focus' && ( oSrc.type == "text" || oSrc.type == "password" || oSrc.type == "file" ) && oSrc.select)  {
    oSrc.select();
  }

}
function em_x_factory()  {

  for (key in xaSupportMatrix) {
    try {
     if (this.getAttribute(key) != null) this.attachX(key);
    } catch (e) {}
  }
}
function em_attach_x(key, val) {
  var config = xaSupportMatrix[key];
  if (!config) return false; //key is not in xaSupportMatrix

  val = ( typeof(val) == "undefined" ) ? this.getAttribute(key) : val ;

  try {
     if (window.xGetOption("X_BINDING_RULE_APPLY")) {
      if ( !config[0] || ( config[0] == 1 && val)) return false;
      if ( config[0] == 2 && !val) return false;
      if (!config[2].split(",").contain(this.type)) return false; //not support type
     }

    if (this.xBuffer.contain(key)) return false; //already bound

    this.xBuffer[this.xBuffer.length] = config[1];

    if (!this.xBuffered) {
      this.xBuffered=true;
      var buffer = window.xBuffer[this.form.uniqueID];
      buffer[buffer.length] = this;
    }

    this.setAttribute(key, typeof(val) == "undefined" ? "" : val);

    var cbFunc = "window." + config[1] + "_attach";
    try { if (eval(cbFunc)) eval(cbFunc + "(this)"); } catch (e) {}

  } catch(e) { return false; }

  return true;
}
function em_detach_x(key)  {
  if (!this.xBuffer.length ) return false;
  var config = xaSupportMatrix[key];
  if (!config) return false;

  var flag = false;
  var nBuffer = new Array;
  var itill = this.xBuffer.length;
  for (var idx=0; idx < itill; idx++) {
    if (this.xBuffer[idx] == config[1]) flag = true;
    else nBuffer[nBuffer.length] = this.xBuffer[idx];
  }

  if (flag) {
    var cbFunc = "window." + config[1] +"_detach";
    try { if (eval(cbFunc)) eval(cbFunc +"(this)"); } catch (e) {}
    this.removeAttribute(key);
    this.unsetBgColor("xjs_error");
    this.xBuffer = nBuffer;
  }

  return true;
}
function em_clear_x(deep)  {
  if (!this.xBuffer.length ) return false;

  var itill = this.xBuffer.length;
  for (var idx=0; idx < itill; idx++) {
    try {
      var cbFunc = "window." + this.xBuffer[idx] +"_detach";
      try { if (eval(cbFunc)) eval(cbFunc +"(this)"); } catch (e) {}
      if (deep) this.removeAttribute(this.xBuffer[idx].replace(/^xo_/ig, ""));
      this.unsetBgColor("xjs_error");
      delete this.xBuffer[idx];
    } catch (e) {return false;}
  }

  this.xBuffer = new Array;
  window.xBuffer[this.form.uniqueID] = window.xBuffer[this.form.uniqueID].compact(this);
  this.xBuffered = false;

  return true;
}
function em_get_message(key, index) {
  var imsg = "";
  var result = "";
  var itemname = this.getAttribute("itemname");
  var _msg = this.getAttribute(key + "_msg");
  if (itemname) {
    imsg  = xaMessagesTable["itemname"][0];
    imsg  = (imsg) ? imsg.substituteAt(itemname) : "";
  }
  try {
    index = index ? index : 0;
    result = imsg + ( _msg ? _msg : xaMessagesTable[key][index] );
  } catch (e) { result = "xJos (" + key + ", " + index + ") 메세지가 정의되어 있지 않습니다.!!"; }
  return result.toString();
}
function em_alert_x(message)  {
  var execCmd = null;

  execCmd = this.getAttribute("onActionBegin");
  if (execCmd && tihs.doAction(execCmd, "ActionBegin") == false) return false;

  if (window.xGetOption("X_VALIDATE_DO_ALERT")) window.alert(message);
  if (window.xGetOption("X_VALIDATE_DO_ERROR_ACTION")) {
    if (this.type == 'hidden' && window.xGetOption("X_SUPPORT_HIDDEN_TYPE") ) {
      if (this.getNextFocus && this.getNextFocus()) this.getNextFocus().focus();
      } else {
        if ( !this.amIhide()) this.focus();
        this.setBgColor("#EEFFB6", "xjs_error");
      }
  }

  execCmd = this.getAttribute("onActionEnd");
  if (execCmd && this.doAction(execCmd, "ActionEnd") == false) return false;

  return true;
}
function em_validate()  {

  var flag = true;
  var execCmd = null;

  if ( !this.xBuffer || !this.xBuffer.length ) return true;
  if ( this.impotence || this.getAttribute("disabled")) return true;

  if (!window.xGetOption("X_VALIDATE_ON_HIDE") && this.amIhide()) return true;
  if (this.readOnly && !window.xGetOption("X_VALIDATE_ON_READONLY")) return true;

  if (window.xGetOption("X_VALIDATE_DO_TRIM_VALUE") ) this.value = this.value.trim();

  execCmd = this.getAttribute("onValidateBegin");
  if (execCmd && this.doAction(execCmd, "ValidateBegin") == false) flag = false;

  var itill = this.xBuffer.length;
  for ( var idx=0; flag && idx < itill ; idx++ ) {
      var cbFunc = "window." + this.xBuffer[idx] + "_validate";
    try {
      if (eval(cbFunc) && eval(cbFunc + "(this)") == false) flag = false;
    } catch (e) { ;}
  }

  execCmd = this.getAttribute("onValidateEnd");
  if (flag && execCmd && this.doAction(execCmd, "ValidateEnd") == false) flag = false;

  if (flag) {
    this.unsetBgColor("xjs_error");
  }

  return flag;

}
function em_masking()  {
  this.value = this.getMaskedValue();
}
function em_unmasking()  {
  this.value = this.getUnmaskedValue();
}
function em_get_masked_value()  {
  return ( this.getMaskedValueHandler) ?  this.getMaskedValueHandler(this) : this.value;
}
function em_get_unmasked_value()  {
  return ( this.getUnmaskedValueHandler) ?  this.getUnmaskedValueHandler(this) : this.value;
}
function em_set_mask_handler(getMaskedValueHandler, getUnmaskedValueHandler)  {
  if (this.getMaskedValueHandler == null) this.getMaskedValueHandler = getMaskedValueHandler;
  if (this.getUnmaskedValueHandler == null) this.getUnmaskedValueHandler = getUnmaskedValueHandler;
}
function fm_initialize() {

  //attribute
  this.xjos      = true;       //tagged by xJos
  this.impotence = false;      //make form useless attribute
  window.xBuffer[this.uniqueID] = new Array; //detected xJos-binding elements buffering array

  //Pseudo-inheritance Method definition
  this.setCss      = pm_set_css;
  this.unsetCss    = pm_unset_css;
  this.containCSS  = pm_contain_css;

  this.toggleCss   = pm_toggle_css;
  this.doAction    = pm_do_action;

  //methods definition
  this.count         = fm_count;
  this.clear         = fm_clear;

  this.disable       = fm_disable;
  this.enable        = fm_enable;
  this.show          = fm_show;
  this.hide          = fm_hide;
  this.setReadOnly   = fm_set_readonly;
  this.unsetReadOnly = fm_unset_readonly
  this.setCheck      = fm_set_check;
  this.unsetCheck    = fm_unset_check;

  this.trim  = fm_trim;
  this.lTrim = fm_left_trim;
  this.rTrim = fm_right_trim;

  this.call       = fm_call;
  this.callByType = fm_call_by_type;
  this.setSendValue = fm_set_send_value;
  this.setDynaAction = fm_set_dyna_action;

  this.getElementsByNames = fm_get_elements_by_names;
  this.getxJosElementsByNames = fm_get_xjos_elements_by_names;

  this.appendHiddenElementsByURI = fm_append_hidden_elements_by_uri;
  this.appendHiddenElement       = fm_append_hidden_element;
  this.appendElementByTag        = fm_append_element_by_tag;

  this.copyToArray     = fm_copy_to_array;

  //xjos engines method
  this.xFactory = fm_x_factory;
  this.attachX  = fm_attach_x;
  this.detachX  = fm_detach_x;
  this.clearX   = fm_clear_x;
  this.finishAttachX = fm_finish_attach_x;

  this.validate  = fm_validate;
  this.masking   = fm_masking;
  this.unmasking = fm_unmasking;

  //xjos submit-framework method
  if (this.onsubmit) this.userOnSubmitHandler = this.onsubmit;
  this.onsubmit = fe_onsubmit;
  this.fireSubmit = fm_fire_submit;
  this.serveSubmit = fm_serve_submit;

  //initialize element
  this.xFactory();
}
function fm_count(names)  {
  var eArray = this.getElementsByNames(names);
  var mCnt = 0;
  var flag = null;
  var itill = eArray.length;
  for (var idx=0; idx < itill; idx++) {
    if (eArray[idx].tagName == "OBJECT" ) continue;
    flag = arguments.length > 1 ? eArray[idx].contain(xuGetArray(arguments, 1)) : eArray[idx].contain();
    if (flag) mCnt ++;
  }

  return mCnt;
}
function fm_clear(names) { this.call('clear', names); }
function fm_disable(names) { this.call('disable', names); }
function fm_enable(names) { this.call('enable', names); }
function fm_show(names) { this.call('show', names); }
function fm_hide(names) { this.call('hide', names); }
function fm_set_readonly(names) { this.call('setReadOnly', names); }
function fm_unset_readonly(names) { this.call('unsetReadOnly', names); }
function fm_set_check(names) { this.call('setCheck', names); }
function fm_unset_check(names) { this.call('unsetCheck', names); }
function fm_trim(names) { this.call('trim', names); }
function fm_left_trim(names) { this.call('lTrim', names); }
function fm_right_trim(names) { this.call('rTrim', names); }
function fm_call(func, names, limitor) {
  var eArray = (limitor) ? this.getxJosElementsByNames(names) : this.getElementsByNames(names) ;
  var itill = eArray.length;
  for (var idx=0; idx < itill ; idx++) {
    if (eArray[idx].tagName == "OBJECT" || !eArray[idx].xjos ) continue;
    try { eArray[idx].call(func) } catch (e) {};
  }
  return ;
}
function fm_call_by_type(func, types)  {
  var tArray = types.split(",");
  var itill = this.elements.length;
  for (var idx=0; idx < itill; idx++) {
    if (this.elements[idx].tagName == "OBJECT" ) continue;
    if (tArray.contain(this.elements[idx].type)) {
    try { this.elements[idx].call(func) } catch (e) {};
    }
  }
}
function fm_set_send_value(param)  {
  this.setAttribute("send_value", val);
}
function fm_set_dyna_action(param) {
	alert(param);
  this.setAttribute("dyna_action", param);
}
function fm_get_elements_by_names(names)  {
  if (!names) return xuGetArray(this.elements);
  var rArray = new Array;
  var nArray = names.split(",");
  for (var idx=0; idx < nArray.length; idx++) {
    try { rArray = rArray.concat(xuGetArray(this.elements(nArray[idx]))) } catch(e) {};
  }
  return rArray;
}
function fm_get_xjos_elements_by_names(names)  {
  var xBuffer = window.xBuffer[this.uniqueID];
  if (!names) return xBuffer;
  var rArray = new Array;
  var nArray = names.split(",");
  var itill = xBuffer.length;
  for (var idx=0; idx < itill; idx++) {
    if (nArray.contain(xBuffer[idx].name)) rArray[rArray.length] = xBuffer[idx];
  }
  return rArray;
}
function fm_append_hidden_elements_by_uri(uRi){
  var rArray = new Array;
  var uArray = uRi.hack("&","=");
  var itill = uArray.length;
  var name, value, key;

  for (var idx=0; idx<uArray.length; idx++) {
    try { rArray = rArray.concat(this.appendHiddenElement(uArray[idx][0], uArray[idx][1] )); } catch (e) {}
  }

  return rArray;
}
function fm_append_hidden_element(eName, eValue){

  var eArray = this.getElementsByNames(eName);
  var rArray = new Array;
  eValue = ( typeof(eValue) == "undefined") ? "" : eValue ;
  if (eArray.length > 0 && !window.xGetOption("X_ALLOW_MULTI_CREATE")) {
    for (var idx=0; idx < eArray.length; idx++) {
      try { eArray[idx].setValue(eValue); } catch(e) {}
    }
    return eArray;
  }

  var oElement = document.createElement("input");
  oElement.setAttribute("type", "hidden");
  oElement.setAttribute("value", eValue);
  oElement.setAttribute("xcreated", true);
  this.insertBefore(oElement);
  rArray[0] = oElement;
  return rArray;
}
function fm_append_element_by_tag(eTag, eParam, oSibling){

  var pArray = cParam.hack("&","=");
  var oElement = document.createElement(eTag);
  for (var idx=0; idx < pArray.length; idx++ ) {
    oElement.setAttribute(pArray[idx][0], pArray[idx][1]);
  }

  var eName = oElement.getAttribute("name");
  if (eName && !window.xGetOption("X_ALLOW_MULTI_CREATE" )  ) {
    var eArray = this.getElementsByNames(eName);
    var eValue = oElement.getAttribute("value");
    for (var idx=0; idx < eArray.length; idx++) {
      try { eArray[idx].setValue(eValue); } catch(e) {}
    }
    delete oElement;
    return eArray;
  }

  oElement.setAttribute("xcreated", true);

  (oSibling) ? this.insertBefore(oElement, oSibling) : this.insertBefore(oElement);

  rArray[0] = oElement;
  return rArray;
}
function fm_copy_to_array(names)  {
  var eArray = this.getElementsByNames(names);
  var rArray = new Array;
  var itill = eArray.length;
  var value = null;

  for (var idx=0; idx < itill ; idx++) {
    if (eArray[idx].tagName == "OBJECT" ) continue;
    if (eArray[idx].type == "button" || eArray[idx].type == "submit" || eArray[idx].type == "reset" ) continue;
    if (!window.xGetOption("X_SUPPORT_HIDDEN_TYPE") && eArray[idx].type == 'hidden') continue;
    if (!eArray[idx].xjos ) continue;
    value = eArray[idx].getValue();
    if ( value == null ) continue;
    if ( value instanceof Array) { //when select-multiple
      for (var jdx=0; jdx < value.length; jdx++) {
        rtnArr[rtnArr.length] = [ eArray[idx].name, value[jdx] ];
      }
    } else {
      rtnArr[rtnArr.length] = [ eArray[idx].name, value ];
    }
  }

  return rtnArr;
}
function fm_x_factory() {
  var itill = this.elements.length;
  var optH = window.xGetOption("X_SUPPORT_HIDDEN_TYPE");
  var optR = window.xGetOption("X_RESTRICT_APPLY");
  var oInput = null;
  for(var idx=0; idx < itill ; idx++) {
    oInput = this.elements[idx];
    if (oInput.tagName == "OBJECT" ) continue;
    if (optR && oInput.getAttribute("xjos") == null) continue;
    if (!optH && oInput.type == 'hidden') continue;
    oInput.initialize = em_initialize;
    oInput.initialize(this);
    //!attach all binding keywords
    //if (window.xGetOption("X_CSS_PLAINED")) this.attahX("plain_box");
    //if (window.xGetOption("X_NEXT_FOCUS_ON_ENTER")) this.addXObj("enter_move_focus", "xo_enter_move_focus");
  }
}
function fm_attach_x(names, key, val) {
  var eArray = this.getElementsByNames(names);
  var itill = eArray.length;
  for (var idx=0; idx < itill ; idx++) {
    if (eArray[idx].tagName == "OBJECT" ) continue;
    if (!window.xGetOption("X_SUPPORT_HIDDEN_TYPE") && eArray[idx].type == 'hidden') continue;
    eArray[idx].attachX(key, val);
  }
}
function fm_detach_x(names, key) {
  var eArray = this.getElementsByNames(names);
  var itill = eArray.length;
  for (var idx=0; idx < itill ; idx++) {
    if (eArray[idx].tagName == "OBJECT" ) continue;
    if (!window.xGetOption("X_SUPPORT_HIDDEN_TYPE") && eArray[idx].type == 'hidden') continue;
    eArray[idx].detachX(key);
  }
}
function fm_clear_x(deep) {
  var eArray = this.getElementsByNames(names);
  var itill = eArray.length;
  for (var idx=0; idx < itill ; idx++) {
    if (eArray[idx].tagName == "OBJECT" ) continue;
    if (!window.xGetOption("X_SUPPORT_HIDDEN_TYPE") && eArray[idx].type == 'hidden') continue;
    eArray[idx].clearX(deep);
  }
}
function fm_finish_attach_x() {
  var target = window.xBuffer[this.uniqueID];
  var start = new Date();
  target.sort(xuSortFunction);
  var end = new Date();
  if (window.xGetOption("X_SHOW_ELAPSED")) window.status = " xJos xBuffer refresh elasped = " + (end - start) + " m/s";
}
function fm_validate(names) {
  var execCmd = "";
  var flag = true;
  var eArray = this.getxJosElementsByNames(names);

  execCmd = this.getAttribute("onValidateBegin");
  if (execCmd && this.doAction(execCmd, "ValidateBegin") == false) flag = false;

  var start = new Date();

  var itill = eArray.length;
  for(var idx=0; flag && idx < itill; idx++){
    if (eArray[idx].validate && eArray[idx].validate() == false ) flag = false;
  }

  var end = new Date();
  if (flag && window.xGetOption("X_SHOW_ELAPSED")) window.status = " xJos validation elasped = " + (end - start) + " m/s";

  execCmd = this.getAttribute("onValidateEnd");
  if (flag && execCmd && this.doAction(execCmd, "ValidateEnd") == false) flag = false;

  return flag;
}
function fm_masking(names) { this.call('masking', names, true); }
function fm_unmasking(names) { this.call('unmasking', names, true); }

function fe_onsubmit()  {
  if ( !window.xGetOption("X_HOOK_SUBMIT_EVENT_ACTIVATE")) return;

  /* not user defined function but onSubmit ananoymouse function should return false if wants cancle event */
  if (this.userOnSubmitHandler && this.userOnSubmitHandler() == false) { window.event.returnValue = false; return false; }

  var oform = (window.event && window.event.type=='submit' && window.event.returnValue != false ) ? window.event.srcElement : null;

  if ( oform && oform.serveSubmit(window.event) == false) {
    window.event.returnValue = false;
    return false;
  }

}
function fm_fire_submit(evnt)  {
  var oform = (evnt && evnt.type=='submit') ? evnt.srcElement : this;
  execCmd = oform.getAttribute("onSubmitBegin");
  if (execCmd && oform.doAction(execCmd, "SubmitBegin") == false) flag = false;

  if ( this.serveSubmit() ) this.submit();
  else return false;

  execCmd = this.getAttribute("onSubmitEnd");
  if (execCmd && this.doAction(execCmd, "SubmitEnd") == false) flag = false;

  if (this.target && this.target.toLowerCase != "_self") this.masking();
}
function fm_serve_submit(evnt)  {
  var flag = true;
  var execCmd = "";

  var oform = (evnt && evnt.type=='submit') ? evnt.srcElement : this;

  if ( flag && oform.impotence ) {
    alert(xaMessagesTable["disable_submit"][0]);
    flag = false;
  }

  if ( flag && window.xGetOption("X_VALIDATION_ACTIVATE") && oform.validate ) {
    flag = oform.validate();
  }

  var con = oform.getAttribute("confirm");
  if ( flag && con != null && !confirm(con) ) flag = false;

  if ( flag && oform.unmasking) oform.unmasking();

  var dynaAction = oform.getAttribute("dyna_action");

  if ( flag && window.xGetOption("X_DYNAMIC_ACTION_URL") && dynaAction ) {
    oform.action = dynaAction;
    oform.removeAttribute("dyna_action");
  }

  var sendvalue = oform.getAttribute("send_value");
  if ( flag && window.xGetOption("X_DYNAMIC_SEND_VALUE") && sendvalue  ) {
    window.xCreatedBuffer = oform.appendHiddenElementsByURI(sendvalue);
    oform.removeAttribute("send_value");
  }

  if ( flag && window.xGetOption("X_DISABLE_SUBMIT_ON_SUBMIT")) {
    oform.impotence = true;
    var timeout = window.xGetOption("X_DISABLE_SUBMIT_TIMEOUT");
    this.callByType("disable", window.xGetOption("X_DISABLE_SUBMIT_TYPE"));
    if (oform.name) setTimeout("document."+ oform.name + ".impotence = false", timeout);
    if (oform.name) setTimeout("document."+ oform.name + ".callByType('enable', window.xGetOption('X_DISABLE_SUBMIT_TYPE'))", timeout);
  }

  return flag;
}
function xo_required_attach(oInput) {
  if (oInput.getAttribute("required") != "mute") {
    oInput.setCss("xjs_required");
  }
}
function xo_required_detach(oInput) {
  if (oInput.getAttribute("required") != "mute") {
    oInput.unsetCss("xjs_required");
  }
}
function xo_required_validate(oInput) {
  var flag = true;

  if (!oInput.value) flag = false;

  if(flag && window.xGetOption("X_REQUIRED_IGNORE_WHITESPACE") && /^\s*$/.test(oInput.value)) flag = false;

  if (flag && oInput.type == "checkbox" && !oInput.checked ) flag = false;
  if (flag && oInput.type == "radio" && !oInput.checked ) flag = false;
  if (flag && ( oInput.type == "select-one" || oInput.type == "select-multiple" )  && !oInput.selectedIndex == -1 ) flag = false;

  if (!flag) {
    oInput.alertX(oInput.getMessage("required"));
  }

  return flag;
}

function xo_reg_exp_validate(oInput) {
  var flag = true;

  if (!oInput.value) return true;

  var val = oInput.getAttribute("reg_exp");
  var regExp = val instanceof RegExp ? val : new RegExp(val);

  if (!regExp.test(oInput.value)) {
    oInput.alertX(oInput.getMessage("reg_exp").substituteAt(regExp));
    flag = false;
  }

  delete regExp;
  return flag;

}

function xo_filter_attach(oInput) {
   var filter = oInput.getAttribute("filter");
   if (filter && !oInput.getAttribute("mask") && oInput.value) oInput.value = oInput.value.filtering(filter);
}
function xo_filter_onkeypress(oInput, oEvent) {
  var filter = oInput.getAttribute("filter");
  if (filter) {
    var sKey = String.fromCharCode(oEvent.keyCode);
    var re = new RegExp(filter);
    if(sKey!="\r" && !re.test(sKey)) oEvent.returnValue=false;
    delete re;
  }
}
function xo_filter_onpaste(oInput, oEvent) {
  try {
    var val = window.clipboardData.getData("Text");
    window.clipboardData.setData("Text", val.filtering(oInput.getAttribute("filter")));
  } catch (e) {}
}

function xo_mask_attach(oInput) {
  oInput.setMaskHandler(xo_mask_get_masked_value, xo_mask_get_unmasked_value);
  oInput.masking();
}
function xo_mask_detach(oInput) {
  oInput.unmasking();
  oInput.setMaskHandler(null, null);
}
function xo_mask_onblur(oInput, oEvent)  { oInput.masking(); }
function xo_mask_onfocus(oInput, oEvent) { oInput.unmasking(); }
function xo_mask_get_masked_value(oInput)  {
  if (!oInput.value) return oInput.value;
  var value = oInput.value.replace(window.xGetOption("X_DELIMITER_CHAR"),"");

  var mask = oInput.getAttribute("mask");
  var result = "";

  var itill = value.length;
  var jtill = mask.length;;
  for(var idx=0, jdx=0; idx< itill && jdx < jtill; idx++, jdx++){
     if ( mask.charAt(jdx) != "9") result += mask.charAt(jdx++);
     result += value.charAt(idx);
  }

  return result;
}

function xo_mask_get_unmasked_value(oInput) {
  if (!oInput.value) return oInput.value;
  return oInput.value.replace(window.xGetOption("X_DELIMITER_CHAR"),"");
}

function xo_mask_validate(oInput)  {
  if ( !window.xGetOption("X_VALIDATE_ON_MASK")) { return true; }
  var flag = true;
  if (!oInput.value) return flag;

  var mask = oInput.getAttribute("mask");
  oInput.masking();

  var pattern=oInput.mask.replace(window.xGetOption("X_DELIMITER_CHAR"),"\\$1");
      pattern=pattern.replace(/9/g ,"\\d");

  var regExp = new RegExp("^"+pattern+"$");
  if(!regExp.test(oInput.value))  {
    flag = false;
    oInput.alertX(oInput.getMessage("mask").substituteAt(mask));
  }

  delete regExp;
  return flag;
}

function xo_alphabetic_attach(oInput) {
  if (!oInput.getAttribute("filter")) oInput.attachX("filter", window.xGetOption("X_FILTER_ALPHA"));
}
function xo_alphabetic_detach(oInput) {
  if (oInput.getAttribute("filter") == window.xGetOption("X_FILTER_ALPHA")) oInput.detachX("filter");
}
function xo_alphabetic_validate(oInput) {
  var flag = true;

  if (!oInput.value) return true;

  if (!window.xGetOption("X_REGEXP_ALPHA").test(oInput.value)) {
    oInput.alertX(oInput.getMessage("alphabetic"));
    flag = false;
  }

  return flag;
}

function xo_numeric_attach(oInput) {
  if (!oInput.getAttribute("filter")) oInput.attachX("filter", window.xGetOption("X_FILTER_NUM"));
}
function xo_numeric_detach(oInput) {
  if (oInput.getAttribute("filter") == window.xGetOption("X_FILTER_NUM")) oInput.detachX("filter");
}
function xo_numeric_validate(oInput) {
  var flag = true;

  if (!oInput.value) return true;

  if (!window.xGetOption("X_REGEXP_NUM").test(oInput.value)) {
    oInput.alertX(oInput.getMessage("numeric"));
    flag = false;
  }

  return flag;
}

function xo_alpha_numeric_attach(oInput) {
  if (!oInput.getAttribute("filter")) oInput.attachX("filter", window.xGetOption("X_FILTER_ALNUM"));
}
function xo_alpha_numeric_detach(oInput) {
  if (oInput.getAttribute("filter") == window.xGetOption("X_FILTER_ALNUM")) oInput.detachX("filter");
}
function xo_alpha_numeric_validate(oInput) {
  var flag = true;

  if (!oInput.value) return true;

  if (!window.xGetOption("X_REGEXP_ALNUM").test(oInput.value)) {
    oInput.alertX(oInput.getMessage("alpha_numeric"));
    flag = false;
  }

  return flag;
}

function xo_integer_attach(oInput) {
  if (!oInput.getAttribute("filter")) oInput.attachX("filter", window.xGetOption("X_FILTER_INTEGER"));
}
function xo_integer_detach(oInput) {
  if (oInput.getAttribute("filter") == window.xGetOption("X_FILTER_INTEGER")) oInput.detachX("filter");
}
function xo_integer_validate(oInput) {
  var flag = true;

  if (!oInput.value) return true;

  if (!window.xGetOption("X_REGEXP_INTEGER").test(oInput.value)) {
    oInput.alertX(oInput.getMessage("integer"));
    flag = false;
  }

  // if (parseInt(oInput.value, 10) == "NaN") oInput.value = "";
  return flag;
}

function xo_float_attach(oInput) {
  if (!oInput.getAttribute("filter")) oInput.attachX("filter", window.xGetOption("X_FILTER_FLOAT"));
}
function xo_float_detach(oInput) {
  if (oInput.getAttribute("filter") == window.xGetOption("X_FILTER_FLOAT")) oInput.detachX("filter");
}
function xo_float_validate(oInput) {
  var flag = true;

  if (!oInput.value) return true;
  if (!window.xGetOption("X_REGEXP_INTEGER").test(oInput.value) && !window.xGetOption("X_REGEXP_FLOAT").test(oInput.value) ) {
    oInput.alertX(oInput.getMessage("float"));
    flag = false;
  }

  return flag;
}

function xo_hexa_attach(oInput) {
  if (!oInput.getAttribute("filter")) oInput.attachX("filter", window.xGetOption("X_FILTER_HEXA"));
}
function xo_hexa_detach(oInput) {
  if (oInput.getAttribute("filter") == window.xGetOption("X_FILTER_HEXA")) oInput.detachX("filter");
}
function xo_hexa_validate(oInput) {
  var flag = true;

  if (!oInput.value) return true;

  if (!window.xGetOption("X_REGEXP_HEXA").test(oInput.value) ) {
    oInput.alertX(oInput.getMessage("hexa"));
    flag = false;
  }

  // if (parseInt(oInput.value, 16).toString(16) == "NaN") oInput.value = "";
  return flag;
}

function xo_maxlength_validate(oInput)  {
  var flag = true;
  if (!oInput.value) return flag;

  var val = oInput.getAttribute("minlength");

  if (oInput.value.length > val) {
    oInput.alertX(oInput.getMessage("maxlength").substituteAt(val));
    flag = false;
  }

  return flag;
}

function xo_minlength_validate(oInput)  {
  var flag = true;
  if (!oInput.value) return flag;

  var val = oInput.getAttribute("minlength");

  if (oInput.value.length < val) {
    oInput.alertX(oInput.getMessage("minlength").substituteAt(val));
    flag = false;
  }

  return flag;
}

function xo_maxbyte_validate(oInput)  {
  var flag = true;
  if (!oInput.value) return flag;

  var val = oInput.getAttribute("maxbyte");

  if (oInput.value.getByte() > val) {
    oInput.alertX(oInput.getMessage("maxbyte").substituteAt(val));
    flag = false;
  }

  return flag;
}

function xo_minbyte_validate(oInput)  {
  var flag = true;
  if (!oInput.value) return flag;

  var val = oInput.getAttribute("minbyte");

  if (oInput.value.getByte() < val) {
    oInput.alertX(oInput.getMessage("minbyte").substituteAt(val));
    flag = false;
  }

  return flag;
}

function xo_maxvalue_validate(oInput)  {
  var flag = true;
  if (!oInput.value) return flag;

  var tval = parseFloat(oInput.value);
  var val  = oInput.getAttribute("maxvalue");

  if (tval > val) {
    oInput.alertX(oInput.getMessage("maxvalue").substituteAt(tval, val));
    flag = false;
  }

  return flag;
}

function xo_minvalue_validate(oInput)  {
  var flag = true;
  if (!oInput.value) return flag;

  var tval = parseFloat(oInput.value);
  var val  = oInput.getAttribute("maxvalue");

  if (tval < val) {
    oInput.alertX(oInput.getMessage("minvalue").substituteAt(tval, val));
    flag = false;
  }

  return flag;
}

function xo_money_attach(oInput) {
  if (!oInput.getAttribute("filter")) oInput.attachX("filter", window.xGetOption("X_FILTER_FLOAT"));
  oInput.setMaskHandler(xo_money_get_masked_value, xo_money_get_unmasked_value);
  oInput.masking();
}
function xo_money_detach(oInput) {
  oInput.unmasking();
  oInput.setMaskHandler(null, null);
  if (oInput.getAttribute("filter") == window.xGetOption("X_FILTER_FLOAT")) oInput.detachX("filter");
}
function xo_money_onblur(oInput, oEvent)  {  oInput.masking(); }
function xo_money_onfocus(oInput, oEvent) { oInput.unmasking(); }

function xo_money_get_masked_value(oInput) {
  if (!oInput.value) return oInput.value;

  var value = oInput.value.replace(window.xGetOption("X_DELIMITER_NUMBER"),"");

  value = parseFloat(value, 10).toString();
  if (value == "NaN") return "0";

  var result = "";
  var mArray = value.split(".");
  var iPart = mArray[0];
  var fPart = mArray[1];
  var itill = iPart.length;

  for( var idx=0; idx < itill; idx++){
    if (idx != 0 && ( idx % 3 == itill % 3) && iPart.charAt(idx - 1) !="-") result += ",";
    if (idx < itill ) result += iPart.charAt(idx);
  }

  if ( fPart ) result += "." + fPart;

  return result;
}

function xo_money_get_unmasked_value(oInput) {
  if (!oInput.value) return oInput.value;
  return oInput.value.replace(window.xGetOption("X_DELIMITER_NUMBER"),"");
}

function xo_money_validate(oInput) {
  if ( !window.xGetOption("X_VALIDATE_ON_MASK")) { return true; }
  var flag = true;
  if (!oInput.value) return true;

  var val = this.parent.value.replace(window.xGetOption("X_DELIMITER_NUMBER"),"");

  if (!window.xGetOption("X_REGEXP_INTEGER").test(val) && !window.xGetOption("X_REGEXP_FLOAT").test(val) ) {
    oInput.alertX(oInput.getMessage("money"));
    flag = false;
  }

  return flag;
}


function xo_dollar_attach(oInput) {
  if (!oInput.getAttribute("filter")) oInput.attachX("filter", window.xGetOption("X_FILTER_FLOAT"));
  oInput.setMaskHandler(xo_dollar_get_masked_value, xo_dollar_get_unmasked_value);
  oInput.masking();
}

function xo_dollar_detach(oInput) {
  oInput.unmasking();
  oInput.setMaskHandler(null, null);
  if (oInput.getAttribute("filter") == window.xGetOption("X_FILTER_FLOAT")) oInput.detachX("filter");
}

function xo_dollar_onblur(oInput, oEvent)  { oInput.masking(); }

function xo_dollar_onfocus(oInput, oEvent) { oInput.unmasking(); }

function xo_dollar_get_masked_value(oInput) {
  if (!oInput.value) return oInput.value;

  var value = oInput.value.replace(window.xGetOption("X_DELIMITER_NUMBER"),"");

  value = parseFloat(value, 10).toString();
  if (value == "NaN") return "0";

  var result = "";
  var mArray = value.split(".");
  var iPart = mArray[0];
  var fPart = mArray[1];
  var itill = iPart.length;

  for( var idx=0; idx < itill; idx++){
    if (idx != 0 && ( idx % 3 == itill % 3) && iPart.charAt(idx - 1) !="-") result += ",";
    if (idx < itill ) result += iPart.charAt(idx);
  }

  if ( !fPart ) result += ".00";
  else if (fPart.length == 1 ) result += "." + fPart +"0";
  else if (fPart.length == 2 ) result += "." + fPart;
  else if (fPart.length >  2 ) result += "." + fPart.substr(0, 2);

  return result;
}

function xo_dollar_get_unmasked_value(oInput) {
  if (!oInput.value) return oInput.value;
  return oInput.value.replace(window.xGetOption("X_DELIMITER_NUMBER"),"");
}

function xo_dollar_validate(oInput) {
  if ( !window.xGetOption("X_VALIDATE_ON_MASK")) { return true; }
  var flag = true;
  if (!oInput.value) return true;

  var val = oInput.value.replace(window.xGetOption("X_DELIMITER_NUMBER"),"");

  if (!window.xGetOption("X_REGEXP_INTEGER").test(val) && !window.xGetOption("X_REGEXP_FLOAT").test(val) ) {
    oInput.alertX(oInput.getMessage("dollar"));
    flag = false;
  }

  return flag;
}

function xo_date_attach(oInput) {
  if (!oInput.getAttribute("filter")) oInput.attachX("filter", window.xGetOption("X_FILTER_NUM"));
  if (!oInput.getAttribute("mask"))   oInput.attachX("mask",   window.xGetOption("X_MASK_DATE"));
}
function xo_date_detach(oInput) {
  if (oInput.getAttribute("mask") == window.xGetOption("X_MASK_DATE")) oInput.detachX("mask");
  if (oInput.getAttribute("filter") == window.xGetOption("X_FILTER_NUM")) oInput.detachX("filter");
}

function xo_date_validate(oInput) {
  var flag = true;
  if (!oInput.value) return true;

  var iYear, iMonth, iDay;
  var val = oInput.value.replace(window.xGetOption("X_DELIMITER_CHAR"),"");

  if ( val.length != 8 ) flag = false;

  if (flag) {
    try {
      iYear = val.substr(0,4); iMonth = val.substr(4,2); iDay = val.substr(6,2);
      if ( iYear == null  || iMonth == null || iDay == null ||
           !iYear.isNum() || !iMonth.isNum() || !iDay.isNum() ||
           iMonth > 12 || iMonth < 1 || iDay < 1 ) flag = false ;
    } catch (e) { flag = false; }
  }

  if (flag) {
     var dArray = window.xGetOption("X_DAYS_IN_MONTH");
     var iDaysInMonth = (iMonth != 2) ? dArray[iMonth-1] : (( iYear%4 == 0 && iYear%100 != 0 || iYear % 400==0 ) ? 29 : 28 );
     if( iDay > iDaysInMonth ) flag = false ;
  }

  if(!flag) {
    oInput.alertX(oInput.getMessage("date"));
  }

  return flag;

}

function xo_credit_card_attach(oInput) {
  if (!oInput.getAttribute("filter")) oInput.attachX("filter", window.xGetOption("X_FILTER_NUM"));
}
function xo_credit_card_detach(oInput) {
  if (oInput.getAttribute("filter") == window.xGetOption("X_FILTER_NUM")) oInput.detachX("filter");
}
function xo_credit_card_validate(oInput) {
  var flag = true;
  if (!oInput.value) return true;

  var sum = 0;
  var mul = 1;
  var tproduct;
  var val = oInput.value.replace(window.xGetOption("X_DELIMITER_CHAR"),"");
  var len = val.length;
  if (len > 19) flag = false;

  for (var idx = 0; flag && idx < len; idx++) {
    tproduct = parseInt(val.substring(len-idx-1,len-idx) ,10) * mul;
    sum += (tproduct >= 10) ? (tproduct % 10) + 1 : tproduct;
    mul = (mul == 1) ? mul + 1 : mul - 1;
  }

  if ( flag && (sum % 10) != 0) flag = false;

  if (!flag)  {
    oInput.alertX(oInput.getMessage("credit_card"));
    flag = false;
  }

  return flag;
}

function xo_email_validate(oInput) {
  var flag = true;
  if (!oInput.value) return true;

  var re = /^\w+((-|\.)\w+)*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]{2,4}$/;

  if (!re.test(this.parent.value)) {
    oInput.alertX(oInput.getMessage("email"));
    flag = false;
  }

  return flag;
}

function xo_domain_validate(oInput) {
  var flag = true;
  if (!oInput.value) return true;

  var re = /^[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]{2,4}$/;

  if (!re.test(this.parent.value)) {
    oInput.alertX(oInput.getMessage("domain"));
    flag = false;
  }

  return flag;
}

function xo_psn_attach(oInput) {
  if (!oInput.getAttribute("filter")) oInput.attachX("filter", window.xGetOption("X_FILTER_NUM"));
  if (!oInput.getAttribute("mask"))   oInput.attachX("mask",   window.xGetOption("X_MASK_PSN"));
}
function xo_psn_detach(oInput) {
  if (oInput.getAttribute("mask") == window.xGetOption("X_MASK_PSN")) oInput.detachX("mask");
  if (oInput.getAttribute("filter") == window.xGetOption("X_FILTER_NUM")) oInput.detachX("filter");
}
function xo_psn_validate(oInput) {
  var flag = true;
  if (!oInput.value) return true;

  var sum = 0;
  var val = oInput.value.replace(window.xGetOption("X_DELIMITER_CHAR"),"");

  for (idx = 0, jdx = 2; jdx < 10; idx++, jdx++) sum += val.charAt(idx) * jdx ;
  for (idx = 8, jdx = 2; jdx <  6; idx++, jdx++) sum += val.charAt(idx) * jdx ;

  var nam = sum % 11;
  var cDigit = 11 - nam ;
  cDigit = (cDigit >= 10 ) ? cDigit - 10 : cDigit;
  if ( !val.toString().isNum() || val.charAt(12) != cDigit)  {
    oInput.alertX(oInput.getMessage("psn"));
    flag = false;
  }

  return flag;
}

function xo_csn_attach(oInput) {
  if (!oInput.getAttribute("filter")) oInput.attachX("filter", window.xGetOption("X_FILTER_NUM"));
  if (!oInput.getAttribute("mask"))   oInput.attachX("mask",   window.xGetOption("X_MASK_CSN"));
}
function xo_csn_detach(oInput) {
  if (oInput.getAttribute("mask") == window.xGetOption("X_MASK_CSN")) oInput.detachX("mask");
  if (oInput.getAttribute("filter") == window.xGetOption("X_FILTER_NUM")) oInput.detachX("filter");
}
function xo_csn_validate(oInput) {
  var flag = true;
  if (!oInput.value) return true;

  var sum = 0;
  var val = oInput.value.replace(window.xGetOption("X_DELIMITER_CHAR"),"");
  var mArray = window.xGetOption("X_CSN_MULTIPLIERS");

  for(idx=0 ; idx < 9 ; idx++) sum += val.charAt(idx) * mArray[idx];

  sum = sum + ((val.charAt(8) * 5 ) / 10);
  var nam = Math.floor(sum) % 10;
  var cDigit = ( nam == 0 ) ? 0 : 10 - nam;
  if ( !val.toString().isNum() || val.charAt(9) != cDigit) {
    oInput.alertX(oInput.getMessage("csn"));
    flag = false;
  }

  return flag;
}

function xo_lsn_attach(oInput) {
  if (!oInput.getAttribute("filter")) oInput.attachX("filter", window.xGetOption("X_FILTER_NUM"));
  if (!oInput.getAttribute("mask"))   oInput.attachX("mask",   window.xGetOption("X_MASK_PSN"));
}
function xo_lsn_detach(oInput) {
  if (oInput.getAttribute("mask") == window.xGetOption("X_MASK_PSN")) oInput.detachX("mask");
  if (oInput.getAttribute("filter") == window.xGetOption("X_FILTER_NUM")) oInput.detachX("filter");
}
function xo_lsn_validate(oInput) {
  var flag = true;
  if (!oInput.value) return true;

  var sum = 0;
  var val = oInput.value.replace(window.xGetOption("X_DELIMITER_CHAR"),"");
  for (idx = 0; idx < 12; idx++) sum += val.charAt(idx) * (idx % 2 + 1);

  var nam = sum % 10;
  var cDigit = 10 - nam ;
  cDigit = (cDigit >= 10 ) ? cDigit-10:cDigit;
  if ( !val.toString().isNum() || val.charAt(12) != cDigit) {
    oInput.alertX(oInput.getMessage("lsn"));
    flag = false;
  }

  return flag;
}

function xo_fsn_attach(oInput) {
  if (!oInput.getAttribute("filter")) oInput.attachX("filter", window.xGetOption("X_FILTER_NUM"));
  if (!oInput.getAttribute("mask"))   oInput.attachX("mask",   window.xGetOption("X_MASK_PSN"));
}
function xo_fsn_detach(oInput) {
  if (oInput.getAttribute("mask") == window.xGetOption("X_MASK_PSN")) oInput.detachX("mask");
  if (oInput.getAttribute("filter") == window.xGetOption("X_FILTER_NUM")) oInput.detachX("filter");
}
function xo_fsn_validate(oInput) {
  var flag = true;
  if (!oInput.value) return true;

  var sum = 0;
  var val = oInput.value.replace(window.xGetOption("X_DELIMITER_CHAR"),"");
  var vArray = val.match(/[0-9]/g);
  var odd = vArray[7]*10 + vArray[8];
  if (odd % 2 != 0) flag = false;
  if ( flag && vArray[11] < 6 || vArray[11] > 9 ) flag = false;

  if (flag) {
    var mArray = window.xGetOption("X_FSN_MULTIPLIERS");
    for (var idx=0 ; idx < 12 ; idx++) sum += (vArray[idx] *= mArray[idx]);
    sum = 11 - (sum % 11);
    sum = (sum >= 10) ? sum - 10 : sum;
    sum += 2;
    sum = (sum >= 10) ? sum - 10 : sum;
    if (sum != vArray[12]) flag = false;
  }

  if (!flag) {
    oInput.alertX(oInput.getMessage("fsn"));
  }

  return flag;
}

function xo_plain_box_attach(oInput) {
  var css = "";
  switch(oInput.type)    {
      case "text" : case "password" :
      case "file" : case "textarea" :
        css = "xjs_flat_box";
      break;
      case "button" : case "reset" : case "submit" :
        css = "xjs_flat_button";
      break;
  }
  oInput.setCss(css);
}
function xo_plain_box_detach(oInput) {
  var css = "";
  switch(this.parent.type)    {
      case "text" : case "password" :
      case "file" : case "textarea" :
        css = "xjs_flat_box";
      break;

      case "button" : case "reset" : case "submit" :
        css = "xjs_flat_button";
      break;
  }
  oInput.unsetCss(css);
}

function xo_edit_align_attach(oInput) {
  var css = "xjs_left";
  var type = oInput.getAttribute("edit_align").toLowerCase();
  switch(type) {
      case "right"  : css = "xjs_right";  break;
      case "center" : css = "xjs_center"; break;
      case "left"   : css = "xjs_left";   break;
  }
  oInput.setCss(css);
}
function xo_edit_align_detach(oInput) {
  var css = "xjs_left";
  var type = oInput.getAttribute("edit_align").toLowerCase();
  switch(type) {
      case "right"  : css = "xjs_right";  break;
      case "center" : css = "xjs_center"; break;
      case "left"   : css = "xjs_left";   break;
  }
  oInput.unsetCss(css);
}

function xo_ime_attach(oInput) {
  var css = "xjs_imeEng";
  var type = oInput.getAttribute("ime").toLowerCase();
  switch(type) {
      case "kor"     : css = "xjs_imeKor"; break;
      case "eng"     : css = "xjs_imeEng"; break;
      case "engonly" : css = "xjs_imeDis"; break;
  }
  oInput.setCss(css);
}
function xo_ime_detach(oInput) {
  var css = "xjs_imeEng";
  var type = oInput.getAttribute("ime").toLowerCase();
  switch(type) {
      case "kor"     : css = "xjs_imeKor"; break;
      case "eng"     : css = "xjs_imeEng"; break;
      case "engonly" : css = "xjs_imeDis"; break;
  }
  oInput.unsetCss(css);
}

function xo_lable_attach(oInput) {
  window.xo_lable_onfocus = oInput.preventFocusHandler;
  oInput.setCss("xjs_lable");
}
function xo_lable_detach(oInput) {
  oInput.unsetCss("xjs_lable");
  window.xo_lable_onfocus = null;
}

function xo_auto_focus_onkeyup(oInput, oEvent) {
  var kArray = window.xGetOption("X_AUTO_FOCUS_FKEY");
  if ( kArray.contain(oEvent.keyCode)) return;
  if ( oInput.value.length >= oInput.getAttribute("maxlength") ) try { oInput.getNextFocus().focus(); } catch (e) {}
}

function xo_enter_move_focus_onkeydown(oInput, oEvent) {
  if ( oEvent.keyCode == 13 ) try {
     //oInput.getNextFocus().focus(); oEvent.returnValue = false;
     oEvent.keyCode = 9;
     } catch (e) {}
}

function xo_focus_this_attach(oInput) {
  if (!oInput.form.name || window.xGetOption("X_FOCUS_THIS_USE_TIMEOUT") < 0) oInput.focus();
  else window.setTimeout("document.all["+oInput.sourceIndex+"].focus()", window.xGetOption("X_FOCUS_THIS_USE_TIMEOUT"));
}

function xo_show_this_attach(oInput) { oInput.show(); }

function xo_hide_this_attach(oInput) { oInput.hide(); }

function xo_disable_this_attach(oInput) { oInput.disable(); }

function xo_readonly_this_attach(oInput) { oInput.setReadOnly(); }

function xo_selected_duih_onchange(oInput, oEvent) {
  var iArray = oInput.get();
  var itill =iArray.length;
  var oCraft = null;
  for (var idx=0; idx <itill; idx++) {
    if (iArray[idx] == -1 ) continue;
    if (oCraft.getAttribute(selected_show)          ) oInput.show(oCraft.getAttribute(selected_show));
    if (oCraft.getAttribute(selected_hide)          ) oInput.hide(oCraft.getAttribute(selected_hide));
    if (oCraft.getAttribute(selected_enable)        ) oInput.enable(oCraft.getAttribute(selected_enable)) ;
    if (oCraft.getAttribute(selected_disable)       ) oInput.disable(oCraft.getAttribute(selected_disable));
    if (oCraft.getAttribute(selected_set_readonly)  ) oInput.setReadOnly(oCraft.getAttribute(selected_set_readonly));
    if (oCraft.getAttribute(selected_unset_readonly)) oInput.unsetReadOnly(oCraft.getAttribute(selected_unset_readonly));
  }
}

function xo_show_fields_attach(oInput) {
  switch (oInput.type) {
    case "text" :  case "password" :  case "textarea" :  case "file" :
      xo_show_fields_onblur = xo_show_fields_duiHandler   ; break;
    case "select-one" : case "select-multiple" :
      xo_show_fields_onchange = xo_show_fields_duiHandler ; break;
    case "radio" :  case "checkbox" :
      xo_show_fields_onclick = xo_show_fields_duiHandler  ; break;
  }
}
function xo_show_fields_detach(oInput) {
  xo_show_fields_onblur   = null ;
  xo_show_fields_onchange = null ;
  xo_show_fields_onclick  = null ;
}
function xo_show_fields_duiHandler(oInput, oEvent) {
  var names = oInput.getAttribute("show_fields");
  var condition = oInput.getAttribute("show_values");
  var flag = condition ? oInput.contain(condition.split(",")) : oInput.contain();
  flag ? oInput.form.show(names) : oInput.form.hide(names);
}

function xo_hide_fields_attach(oInput) {
  switch (oInput.type) {
    case "text" :  case "password" :  case "textarea" :  case "file" :
      xo_hide_fields_onblur = xo_hide_fields_duiHandler   ; break;
    case "select-one" : case "select-multiple" :
      xo_hide_fields_onchange = xo_hide_fields_duiHandler ; break;
    case "radio" :  case "checkbox" :
      xo_hide_fields_onclick = xo_hide_fields_duiHandler  ; break;
  }
}
function xo_hide_fields_detach(oInput) {
  xo_hide_fields_onblur   = null ;
  xo_hide_fields_onchange = null ;
  xo_hide_fields_onclick  = null ;
}
function xo_hide_fields_duiHandler(oInput, oEvent) {
  var names = oInput.getAttribute("hide_fields");
  var condition = oInput.getAttribute("hide_values");
  var flag = condition ? oInput.contain(condition.split(",")) : oInput.contain();
  flag ? oInput.form.hide(names) : oInput.form.show(names);
}

function xo_enable_fields_attach(oInput) {
  switch (oInput.type) {
    case "text" :  case "password" :  case "textarea" :  case "file" :
      xo_enable_fields_onblur = xo_enable_fields_duiHandler   ; break;
    case "select-one" : case "select-multiple" :
      xo_enable_fields_onchange = xo_enable_fields_duiHandler ; break;
    case "radio" :  case "checkbox" :
      xo_enable_fields_onclick = xo_enable_fields_duiHandler  ; break;
  }
}
function xo_enable_fields_detach(oInput) {
  xo_enable_fields_onblur   = null ;
  xo_enable_fields_onchange = null ;
  xo_enable_fields_onclick  = null ;
}
function xo_enable_fields_duiHandler(oInput, oEvent) {
  var names = oInput.getAttribute("enable_fields");
  var condition = oInput.getAttribute("enable_values");
  var flag = condition ? oInput.contain(condition.split(",")) : oInput.contain();
  flag ? oInput.form.enable(names) : oInput.form.disable(names);
}

function xo_disable_fields_attach(oInput) {
  switch (oInput.type) {
    case "text" :  case "password" :  case "textarea" :  case "file" :
      xo_disable_fields_onblur = xo_disable_fields_duiHandler   ; break;
    case "select-one" : case "select-multiple" :
      xo_disable_fields_onchange = xo_disable_fields_duiHandler ; break;
    case "radio" :  case "checkbox" :
      xo_disable_fields_onclick = xo_disable_fields_duiHandler  ; break;
  }
}
function xo_disable_fields_detach(oInput) {
  xo_disable_fields_onblur   = null ;
  xo_disable_fields_onchange = null ;
  xo_disable_fields_onclick  = null ;
}
function xo_disable_fields_duiHandler(oInput, oEvent) {
  var names = oInput.getAttribute("disable_fields");
  var condition = oInput.getAttribute("disable_values");
  var flag = condition ? oInput.contain(condition.split(",")) : oInput.contain();
  flag ? oInput.form.disable(names) : oInput.form.enable(names);
}

function xo_trim_this_attach(oInput) {
  if (!oInput.value) return;
  switch (parent.getAttribute("trim")) {
    case "left"  : oInput.value = this.parent.value.lTrim(); break;
    case "right" : oInput.value = this.parent.value.rTrim(); break;
    default      : oInput.value = this.parent.value.trim() ; break;
  }
}
function xo_trim_this_onblur(oInput, oEvent) {
  if (!oInput.value) return;
  switch (parent.getAttribute("trim")) {
    case "left"  : oInput.value = this.parent.value.lTrim(); break;
    case "right" : oInput.value = this.parent.value.rTrim(); break;
    default      : oInput.value = this.parent.value.trim() ; break;
  }
}

function xo_strip_white_space_attach(oInput) {
  if (!oInput.value) return;
  oInput.value = oInput.value.replace(window.xGetOption("X_STRIP_WHITE_SPACE_CHAR"), "");
}
function xo_strip_white_space_onblur(oInput, oEvent) {
  if (!oInput.value) return;
  oInput.value = oInput.value.replace(window.xGetOption("X_STRIP_WHITE_SPACE_CHAR"), "");
}

function xo_strip_special_char_attach(oInput) {
  if (!oInput.value) return;
  oInput.value = oInput.value.replace(window.xGetOption("X_STRIP_SPECIAL_CHAR"), "");
}
function xo_strip_special_char_onblur(oInput, oEvent) {
  if (!oInput.value) return;
  oInput.value = oInput.value.replace(window.xGetOption("X_STRIP_SPECIAL_CHAR"), "");
}

function xo_hpd_attach(oInput) {
  if (!oInput.getAttribute("filter")) oInput.attachX("filter", window.xGetOption("X_FILTER_NUM"));
}
function xo_hpd_detach(oInput) {
  if (oInput.getAttribute("filter") == window.xGetOption("X_FILTER_NUM")) oInput.detachX("filter");
}
function xo_hpd_validate(oInput) {
  var flag = true;
  if (!oInput.value) return true;

  if (!window.xGetOption("X_HPD").contain(oInput.value)) {
    oInput.alertX(oInput.getMessage("hpd").substituteAt(oInput.value));
    flag = false;
  }

  return flag;
}

function xo_ddd_attach(oInput) {
  if (!oInput.getAttribute("filter")) oInput.attachX("filter", window.xGetOption("X_FILTER_NUM"));
}
function xo_ddd_detach(oInput) {
  if (oInput.getAttribute("filter") == window.xGetOption("X_FILTER_NUM")) oInput.detachX("filter");
}
function xo_ddd_validate(oInput) {
  var flag = true;
  if (!oInput.value) return true;

  if (!window.xGetOption("X_DDD").contain(oInput.value)) {
    oInput.alertX(oInput.getMessage("ddd").substituteAt(oInput.value));
    flag = false;
  }

  return flag;
}

function xo_case_attach(oInput) {
  if (!oInput.value) return;
  switch(oInput.getAttribute("case")) {
   case "lower" :  oInput.value = oInput.value.toLowerCase(); break;
   case "upper" :  oInput.value = oInput.value.toUpperCase(); break;
  }
}
function xo_case_onkeypress(oInput, oEvent) {
  var code = oEvent.keyCode;
  switch(oInput.getAttribute("case")) {
   case "lower" :  if (code >= 65 && code <= 90)  oEvent.keyCode += 32; break;
   case "upper" :  if (code >= 97 && code <= 122) oEvent.keyCode -= 32; break;
  }
}
function xo_case_onpaste(oInput, oEvent) {
  if (!oInput.value) return;
  switch(oInput.getAttribute("case")) {
   case "lower" :  window.clipboardData.setData("Text", window.clipboardData.getData("Text").toLowerCase()); break;
   case "upper" :  window.clipboardData.setData("Text", window.clipboardData.getData("Text").toUpperCase()); break;
  }
}

function xo_send_value_detach(oInput) {
  if (oInput.getAttribute("send_value") == oInput.form.getAttribute("send_value")) oInput.form.removeAttribute("send_value");
}

function xo_dyna_action_detach(oInput) {
  if (oInput.getAttribute("dyna_action") == oInput.form.getAttribute("dyna_action")) oInput.form.removeAttribute("dyna_action");
}
function xo_send_value_onclick(oInput, oEvent) {
  oInput.form.setAttribute("send_value", oInput.getAttribute("send_value"));
}

function xo_dyna_action_onclick(oInput, oEvent) {
  oInput.form.setAttribute("dyna_action", oInput.getAttribute("dyna_action"));
}
function xo_status_bar_attach(oInput) {
  xo_status_bar_onfocus     = xo_status_bar_show;
  xo_status_bar_onblur      = xo_status_bar_kill;
  xo_status_bar_onmouseover = xo_status_bar_show;
  xo_status_bar_onmouseout  = xo_status_bar_kill;
}
function xo_status_bar_detach(oInput) {
  xo_status_bar_onfocus     = null;
  xo_status_bar_onblur      = null;
  xo_status_bar_onmouseover = null;
  xo_status_bar_onmouseout  = null;
}
function xo_status_bar_show(oInput, oEvent) {
  window.status = oInput.getAttribute("status_bar");
}

function xo_status_bar_kill(oInput, oEvent) {
  window.status = "" ;
}


function xo_sync_attach(oInput) {
  switch (this.parent.type) {
    case "text"   : case "password"   : xo_sync_onkeyup  = xo_sync_handler; break;
    case "hidden" : case "select-one" : xo_sync_onchange = xo_sync_handler; break;
    case "radio"  : case "checkbox"   : xo_sync_onclick  = xo_sync_handler; break;
  }
}

function xo_sync_detach(oInput) {
  xo_sync_onkeyup  = null;
  xo_sync_onchange = null;
  xo_sync_onclick  = nnll;
}

function xo_sync_handler(oInput, oEvent) {
  var tArray = oInput.form.getElementsByNames(oInput.getAttribute("sync"));
  var itill = tArray.length;
  for (var idx=0; idx < itill; idx++) {
    tArray.setValue(oInput.getValue());
  }
}


function xo_is_value_validate(oInput) {
  if (!oInput.value) return true;
  var flag = true;
  var tInputs = oInput.form.getElementsByNames(oInput.getAttribute("is_value"));

  if (tInputs.length != 1) {
    oInput.alertX(oInput.getMessage("is_value", 1).substituteAt(oInput.name, oInput.getAttribute("is_value")));
    flag = false;
  }

  if (tInputs[0].getValue() != oInput.getValue()) {
    var item1 = oInput.isAttrValue("itemname") ? oInput.getAttribute("itemname") : oInput.name;
    var item2 = tInputs[0].isAttrValue("itemname") ? tInputs[0].getAttribute("itemname") : tInputs[0].name;
    oInput.alertX(oInput.getMessage("is_value").substituteAt(item1, item2) );
    flag = false;

  }
  return flag;
}
function xo_filesize_limit_validate(oInput) {
	if (xJScriptEngineVersion < 5.5) return;
  if (!oInput.value) return true;
  var flag = true;
  var limit = oInput.getAttribute("filesize_limit").toUpperCase();
  if (limit.indexOf("KB") == limit.length -2) limit = parseInt(limit.substring(0, limit.length-2), 10) * 1024;
  else if (limit.indexOf("MB") == limit.length -2) limit = parseInt(limit.substring(0, limit.length-2), 10) * 1048576;
  limit = parseInt(limit, 10);
 	var oIMG = document.createElement("IMG");
  oIMG.dynsrc = oInput.value;
  if (oIMG.fileSize > limit) {
    oInput.alertX(oInput.getMessage("filesize_limit").substituteAt(oInput.getAttribute("filesize_limit")));
    flag = false;
  }
  //oIMG.removeNode(true);
  //document.removeChild(oIMG);

  return flag;
}


function xJosEventClass() {
  this.type = null;
  this.srcElement = null;
  this.cancel = false;
  this.set = xjos_event_set;
  this.clear = xjos_event_clear;
}

function xjos_event_clear() {
  this.xkey = null;
  this.type = null;
  this.srcElement = null;
  this.cancel = false;
}
function xjos_event_set(o, type){
  this.srcElement = o;
  this.type = type;
  this.cancel = false;
}

function wm_get_option(optStr) {
  return xaOptionTable[optStr];
}

function wm_set_option(optStr, attr) {
  xaOptionTable[optStr]=attr;
}

function xuSortFunction(one, two) {
  if ( one.sourceIndex > two.sourceIndex ) return +1;
  else if ( one.sourceIndex < two.sourceIndex )return -1;
  else return 0;
}


function xuGetArray(oCollection, start, end) {
  var vArray = new Array;
  if (!oCollection) return vArray;
  if (!oCollection.length) { vArray[0] = oCollection; return vArray; }
  var itill = oCollection.length;
  var is = arguments.length > 1 ? start : 0;
  var ie = arguments.length > 2 ? end   : oCollection.length;
  for (var idx=is; idx < ie;  idx++) {
    vArray[vArray.length] = oCollection[idx];
  }
  return vArray;
}

function extendPrototypeX()  {
  String.prototype.trim         = sm_trim;
  String.prototype.lTrim        = sm_left_trim;
  String.prototype.rTrim        = sm_right_trim;
  String.prototype.isNum        = sm_is_num;
  String.prototype.getByte      = sm_get_byte;
  String.prototype.hack         = sm_hack;
  String.prototype.substituteAt = sm_substitute_at;
  String.prototype.filtering    = sm_filtering;
  String.prototype.contain      = sm_contain;
  Array.prototype.compact       = am_compact;
  Array.prototype.hack          = am_hack;
  Array.prototype.contain       = am_contain;
}

function initializeX()  {
  if (!xaOptionTable["X_ACTIVATE"]) return ;
  /*@cc_on @*/
  /*@ xJScriptEngineVersion = @_jscript_version;  @*/
  this.xGetOption = wm_get_option;
  this.xSetOption = wm_set_option;
  extendPrototypeX();
  this.xJos = new xJosEventClass();
  this.xBuffer = new Array;
  var start_x = new Date();
  var itill = document.forms.length;
  var tinput = 0;
  var opt = window.xGetOption("X_RESTRICT_APPLY");
  for ( var idx = 0 ; idx < itill ; idx++)  {
    if (opt && document.forms[idx].getAttribute("xjos") == null) continue;
    document.forms[idx].initialize = fm_initialize;
    document.forms[idx].initialize();
    tinput += document.forms[idx].length;
  }
  var end_x = new Date();
  if (window.xGetOption("X_SHOW_ELAPSED"))  window.status = " xJos init elasped = " + (end_x - start_x) + " m/s for " + tinput + " elements";
  delete start_x; delete end_x;
}

initializeX();