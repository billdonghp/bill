/**
打开searchname_win
*/
function openSearch(winname,classname) {
    api.openWin({
        name: 'searchname_win',
        url: './searchname_win.html',
        slidBackEnabled:false,
        pageParam: {
            winname: winname,
            classname: classname
        }
    });
}

function openBaseWin(obj){

  var winname = obj.getAttribute("win");
  api.openWin({
      name: winname,
      url: './'+winname+'.html',
      reload: true,
      slidBackEnabled:false,
  });
}
/*refresh:'UIPullRefresh'*/
/*{refresh,isReload,pageParam}*/
function fnOpenFrame(params){
  //alert(!!isReload);
  var frameName = api.winName.replace('_win','_frm');
  api.openFrame({
      name: frameName,
      url: './'+frameName+'.html',
      rect: {
          x: 0,
          y: headerHeight,
          w: 'auto',
          h: api.winHeight - headerHeight
      },
      pageParam: params.pageParam ?  params.pageParam : api.pageParam,
      customRefreshHeader: params.refresh,
      bounces: true,
      reload: !!params.isReload,
      bgColor: 'rgba(0,0,0,0)',
      vScrollBarEnabled: true,
      hScrollBarEnabled: true
  });

}

function fnHeader(){
  header = document.querySelector('#aui-header');
  $api.fixStatusBar(header);
  headerHeight = $api.offset(header).h;
}

function getClient(classname){
  var client = new Resource('A6018795405001', 'AF5CCDB7-6C2C-1EEE-61BF-B27D00B05042');
  return client.Factory(classname);
}


function apiToast(msg, duration, location) {
    api.toast({
        msg: msg,
        duration: duration ? duration : 2000,
        location: location ? location : 'bottom'
    });
}


function getNo(type) {
    //var thisDate = new Date();
    return type +new Date().format("yyyyMMddHHmm");
}



function closeWin() {
    api.closeWin();
}




Date.prototype.format = function(format){
    var o = {
        "M+" : this.getMonth()+1, //month
        "d+" : this.getDate(), //day
        "H+" : this.getHours(), //hour
        "m+" : this.getMinutes(), //minute
        "s+" : this.getSeconds(), //second
        "q+" : Math.floor((this.getMonth()+3)/3), //quarter
        "S" : this.getMilliseconds() //millisecond
    }

    if(/(y+)/.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));
    }

    for(var k in o) {
        if(new RegExp("("+ k +")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length==1 ? o[k] : ("00"+ o[k]).substr((""+ o[k]).length));
        }
    }
    return format;
}


var actMenu = function(title, data, callback, back) {
    var UIMultiSelector = api.require('UIMultiSelector');
    UIMultiSelector.open({
        text: {
            title: title,
            leftBtn: '取消',
            rightBtn: '确定'
        },
        singleSelection: true,
        styles: {
            mask: !back ? 'rgba(100,100,100,0.3)' : 'rgba(100,100,100,0)',
            title: {
                bg: '#f5f5f5',
                color: 'rgb(0,0,0)',
                size: 16,
                h: 44
            },
            leftButton: {
                w: 80,
                h: 35,
                marginT: 5,
                marginL: 8,
                color: 'rgb(0,0,0)',
                bg: 'rgb(200,200,200)',
                size: 14,
            },
            rightButton: {
                w: 80,
                h: 35,
                marginT: 5,
                marginR: 8,
                color: '#ffffff',
                bg: '#7885f8',
                size: 14,
            },
            item: {
                h: 35,
                bg: '#fff',
                bgActive: '#7885f8',
                bgHighlight: '#7885f8',
                color: 'rgb(0,0,0)',
                active: 'rgb(255,255,255)',
                highlight: 'rgb(255,255,255)',
                lineColor: 'rgb(200,200,200)',
                size: 14,
                textAlign: 'center',
            }
        },
        animation: false,
        items: data,
        maskClose: !back
    }, function(ret, err) {
        if (ret.eventType == 'clickLeft' || ret.eventType == 'clickRight') {
            UIMultiSelector.close();
            if (ret.eventType == 'clickRight') {
                callback(ret, err);
            }
        }

    });
};

/*EventListener*/
function listReload(){
  window.location.reload();
}
function sendEventReload(params) {
    api.sendEvent({
        name: 'listReload',
        extra: {
            type: params.type ? params.type : 'single',
            winName: params.winName
        }
    });
}

function addEventListReload(params){
  api.addEventListener({
      name: 'listReload'
  }, function(ret, err){
      if( ret.value.type == params.type || ret.value.winName == params.winName){
        listReload();
      }else{
       //alert( JSON.stringify( err ) );
      }
  });
}

function addEventKeyback(){
  var flag = false;
  api.addEventListener({
      name: 'keyback'
  }, function(ret, err){
      if(flag == false){
        apiToast('再试一次退出多元海洋!');
        flag = true;
        setTimeout(function(){
          flag = false;
        },2000)

      }else{
        api.closeWidget({
            slient: true
        });

      }
  });

}


function upPull(fn){
  api.addEventListener({
      name:'scrolltobottom',
      extra:{
          threshold:10
      }
  }, fn);

}


function getsell(index){
  switch (index) {
    case '1':
      return '微信'
      break;
    case '2':
      return '支付宝'
      break;
    case '3':
      return '银行卡'
      break;
    case '4':
      return '现金'
      break;
    default:
    return index;

  }
}

// 价格，小数点后几位，替换$，千分位显示什么符号默认'，'  ，  小数点'.'
function formatMoney(number, places, symbol, thousand, decimal) {
    number = number || 0;
    places = !isNaN(places = Math.abs(places)) ? places : 2;
    symbol = symbol !== undefined ? symbol : "$";
    thousand = thousand || ",";
    decimal = decimal || ".";
    var negative = number < 0 ? "-" : "",
        i = parseInt(number = Math.abs(+number || 0).toFixed(places), 10) + "",
        j = (a = i.length) > 3 ? a % 3 : 0;
    return symbol + negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : "");
}

//formatMoney(12345.67) // $12,345.67
//formatMoney(12345.67 , 3 , "￥") // ￥12,345.670
//再把'$12,345.67' 变成纯数字 12345.67
//parseFloat('$12,345.67'.replace(/[^0-9-.]/g, ''))
