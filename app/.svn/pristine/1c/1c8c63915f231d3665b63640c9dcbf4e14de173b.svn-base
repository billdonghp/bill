 var comUrl = "http://testpmapp.doosaninfracore.cn:8999/mobile/api/";

 var lisType = {
     leaveAffirm: 'leaveAffirm',
     otAffirm: 'otAffirm',
     arModifyAffirm: 'arModifyAffirm',
     presentAffirm: 'presentAffirm',
     expressAffirm: 'expressAffirm',
     sealAffirm: 'sealAffirm',
     tempCardAffirm: 'tempCardAffirm',
     visiterAffirm: 'visiterAffirm',
     visaAffirm: 'visaAffirm',
     evsAffirm: 'evsAffirm',
     evsResultAffirm: 'evsResultAffirm'
 };

 function fnPost(path, data, isLogin, callback, showloading, ispostlogin) {
     var headers = {
         "Content-Type": 'application/json'
     }
     if (isLogin) {
         var accessToken = api.getPrefs({
             sync: true,
             key: 'tokenKey'
         });
         if ($api.trimAll(accessToken) == "") {
             locatDouApp("post登录验证");
             return;
         }

         headers["authorization"] = accessToken;
     }
     if (!showloading) {
         api.showProgress({
             title: '数据请求中',
             modal: false
         });
     }
      //console.log(JSON.stringify(headers));
      //console.log(JSON.stringify(data));
     api.ajax({
         url: comUrl + path,
         method: 'post',
         timeout: 15,
         dataType: 'json',
         returnAll: false,
         headers: headers,
         data: data
     }, function(ret, err) {
      //console.log(JSON.stringify(ret));
        if(!ispostlogin)
         api.hideProgress();
         if (err && err.statusCode == 401 && !ispostlogin) {
             api.toast({
                 msg: '登录过期，请重新登录',
                 duration: 2000,
                 location: 'bottom'
             });
             api.removePrefs({
                 key: 'tokenKey'
             });
             locatDouApp(comUrl + path + "---post401验证");
             return;
         }
         callback(ret, err);
     })
 };
