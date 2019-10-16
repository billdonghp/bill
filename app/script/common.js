var cjxType = ['C_12005_93775', 'C_12005_43', 'C_12005_1330060'];

function fnReady() {
    fnReadyKeyback();
    fnReadyOpenWin();
    fnReadyHeader();
    fnReadyNav();
    fnReadyFooter();
};


function locatDouApp(fr) {
    api.openWin({
        name: 'login',
        url: 'widget://html/login.html'
    });
    // api.openApp({
    //     iosUrl: "wbelight://",
    //     androidPkg: "com.doosan.elight",
    //     mimeType: 'text/html',
    //     uri: "http://www.baidu.com"
    // }, function(ret, err) {
    //     if (ret) {} else {
    //         toast("未安装斗掌天下");
    //     }
    // });
};

function resumeApp() {
    api.addEventListener({
        name: 'resume'
    }, function(ret, err) {
        console.log("resume");
        loginState();
    });
};

function loginState(txt) {
    asyCheckToken(function(islogin) {
        if (!islogin) {
            locatDouApp("登录验证state" + txt + ":" + islogin);
        }
    }, txt);
};

// 请先登录提示
async function loginToast(func, txt) {
    var isLogin = false;
    //alert("await-post11" + txt);
    var data = await checkToken();
    if (data && data.resultCode == 200) {
        //alert("await-post22" + txt);
        isLogin = true;
    }
    func(isLogin);
};

var checkToken = () => new Promise((resolve, reject) => {
    fnPost('checkToken', {}, true, function(ret, err) {
        if (ret && ret.resultCode == 200) {
            resolve(ret);
        } else {
            resolve(err);
        }
    }, true, true);
});

var asyCheckToken = function(resolve)
{
  fnPost('checkToken', {}, true, function(ret, err) {
      if (ret && ret.resultCode == 200) {
          resolve(true);
      } else {
          resolve(false);
      }
  }, true, true);
}

function pageError() {
    toast('数据错误，请返回后刷新数据');
    setTimeout(function() {
        api.sendEvent({
            name: 'headerLoading'
        });
        api.closeWin();
    }, 1500);
};


function fnReadyKeyback() {
    var keybacks = $api.domAll('.event-back');
    for (var i = 0; i < keybacks.length; i++) {
        $api.attr(keybacks[i], 'tapmode', 'highlight');
        keybacks[i].onclick = function() {
            api.closeWin();
        };
    }

    api.parseTapmode();
};

function fnReadyOpenWin() {
    var buttons = $api.domAll('.open-win');
    for (var i = 0; i < buttons.length; i++) {
        $api.attr(buttons[i], 'tapmode', 'highlight');
        buttons[i].onclick = function() {
            var target = $api.closest(event.target, '.open-win');
            var winName = $api.attr(target, 'win'),
                isNeedLogin = $api.attr(target, 'login'),
                param = $api.attr(target, 'param');

            if (param) {
                param = JSON.parse(param);
            }

            api.openWin({
                name: winName.replace('html/', ''),
                url: './' + winName + '.html',
                pageParam: param
            });
        };
    }
    api.parseTapmode();
};

var header, headerHeight = 0;

function fnReadyHeader() {
    header = $api.byId('header');
    if (header) {
        $api.fixIos7Bar(header);
        headerHeight = $api.offset(header).h;
    }
};

var nav, navHeight = 0;

function fnReadyNav() {
    nav = $api.byId('nav');
    if (nav) {
        navHeight = $api.offset(nav).h;
    }
};

var footer, footerHeight = 0;

function fnReadyFooter() {
    footer = $api.byId('footer');
    if (footer) {
        footerHeight = $api.offset(footer).h;
    }
};

function fnReadyFrame(isreload) {
    var frameName = api.winName + '_frame';
    api.openFrame({
        name: frameName,
        url: './' + frameName + '.html',
        bounces: true,
        bgColor: '#f0f0f0',
        customRefreshHeader: 'UIPullRefresh',
        rect: {
            x: 0,
            y: headerHeight + navHeight,
            w: 'auto',
            h: api.winHeight - headerHeight - footerHeight - navHeight
        },
        reload: !!isreload,
        pageParam: api.pageParam
    });
};




var actMenu = function(title, data, callback,back) {
    var UIMultiSelector = api.require('UIMultiSelector');
    UIMultiSelector.open({
        text: {
            title: title,
            leftBtn: '取消',
            rightBtn: '确定'
        },
        singleSelection: true,
        styles: {
            mask:  !back ? 'rgba(100,100,100,0.3)' : 'rgba(100,100,100,0)',
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
        maskClose : !back
    }, function(ret, err) {
        if (ret.eventType == 'clickLeft' || ret.eventType == 'clickRight') {
            UIMultiSelector.close();
            if (ret.eventType == 'clickRight') {
                callback(ret, err);
            }
        }

    });
};

var toast = function(txt) {
    api.toast({
        msg: txt,
        duration: 2000,
        location: 'bottom'
    });
};

function addMenu(callback) {
    var UIActionSelector = api.require('UIActionSelector');
    UIActionSelector.open({
        datas: 'widget://res/city.json',
        layout: {
            row: 5,
            col: 3,
            height: 30,
            size: 14,
            sizeActive: 16,
            rowSpacing: 5,
            colSpacing: 10,
            maskBg: 'rgba(0,0,0,0.2)',
            bg: '#fff',
            color: '#333333',
            colorActive: '#8992F1',
            colorSelected: '#8992F1'
        },
        iPhoneXBottomHeight: 0,
        animation: true,
        cancel: {
            text: '取消',
            size: 12,
            w: 90,
            h: 35,
            bg: '#8992F1',
            bgActive: '#ccc',
            color: '#FEFEFE',
            colorActive: '#fff'
        },
        ok: {
            text: '确定',
            size: 12,
            w: 90,
            h: 35,
            bg: '#8992F1',
            bgActive: '#ccc',
            color: '#FEFEFE',
            colorActive: '#fff'
        },
        title: {
            text: '',
            size: 12,
            h: 44,
            bg: '#eee',
            color: '#888'
        },
        fixedOn: api.frameName
    }, function(ret, err) {
        callback(ret, err);
    });

}

Date.prototype.format = function(format) {
    var date = {
        "M+": this.getMonth() + 1,
        "d+": this.getDate(),
        "h+": this.getHours(),
        "m+": this.getMinutes(),
        "s+": this.getSeconds(),
        "q+": Math.floor((this.getMonth() + 3) / 3),
        "S+": this.getMilliseconds()
    };
    if (/(y+)/i.test(format)) {
        format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
    }
    for (var k in date) {
        if (new RegExp("(" + k + ")").test(format)) {
            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ?
                date[k] : ("00" + date[k]).substr(("" + date[k]).length));
        }
    }
    return format;
};
