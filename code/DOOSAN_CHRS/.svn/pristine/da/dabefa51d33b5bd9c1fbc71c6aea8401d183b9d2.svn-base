var DoubleCombo = Class.create();   
DoubleCombo.prototype = {       
  initialize: function(source, target, ignore, url, options, excute,targetSelected,callFunction,all) {   
    this.source = $(source);   
    this.target = $(target);   
    this.ignore = $A(ignore);   
    this.callFunction=callFunction;
    this.targetSelected=targetSelected;
    this.url = url;   
    this.options = $H(options);
    this.all=all;
    this.source.onchange = this.doChange.bindAsEventListener(this);   
    if(excute) {
        this.doChange();   
    }
  },   

  doChange: function() {
    if(this.source.value != '') {
        // first clear the ignore ones
        this.ignore.each(
            function(value) {
                $(value).options.length = 1;   
                $(value).options[0].selected = 'selected';   
            }
        );
        // create parameter for ajax
        var query = $H({ id: this.source.value });
        var parameters = {
            method: 'post',    
            parameters: $H(this.options).merge(query).toQueryString(),    
            onComplete: this.getResponse.bindAsEventListener(this)   
        }
        var locationRequest = new Ajax.Request( this.url, parameters );
    }
  },
  getResponse: function(request) {
  	var index=0;
  	this.target.options.length = 0;
  		this.target.options[0] = new Option('请选择','');  
    var response = $A(request.responseText.strip().split(';'));
    for(var i = 0; i < response.length; i++) {
        var optionParam = response[i];
        this.target.options[this.target.options.length] = new Option(optionParam,optionParam);  
         if(this.targetSelected==optionParam){
            index=i+1;
         }
    }
    this.target.options[index].selected = 'selected';
	if(this.callFunction){
    	this.callFunction();
	}
  }
}