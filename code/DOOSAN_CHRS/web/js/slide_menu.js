	function PreImgLoad(Title, imgNum)
	{
		imgNum++;
		TotalNum = imgNum;
		rollOn = new Array(imgNum);
		rollOff = new Array(imgNum);
		for (i = 1; i <= imgNum; i++) {
			if (i < 10) {
				tmp = "0";
			} else {
				tmp ="";
			}
			rollOn[i] = new Image();
			rollOn[i].src = Title + tmp + i + "_on.gif";
			rollOff[i] = new Image();
			rollOff[i].src = Title + tmp + i + "_off.gif";

		}
	}

	function show_layer(lname,flag,parentcode)

	{
          var layer = (navigator.appName == 'Netscape') ? document.layers[lname] : document.all[lname];

        if (lname == '')

	    return;

              if (parentcode == '')

	    return;

         layer.style.visibility = (flag == 0) ? 'visible' : 'hidden';

}

	function imgOn(Num) {
		if (rollOn[Num] != null) {
			document["Image"+Num].src = rollOn[Num].src;
		}
	}

	function imgOff(Num) {
		if (rollOn[Num] != null) {
			document["Image"+Num].src = rollOff[Num].src;
		}
	}
