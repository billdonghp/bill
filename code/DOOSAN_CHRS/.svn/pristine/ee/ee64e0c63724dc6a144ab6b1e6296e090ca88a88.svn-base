function CheckForm()
{
	var reg=/^\d{4}-\d{1,2}-\d{2}$/;
	//检查是否选择
	var flag=0;
	for(var i=0;i<save.elements.length;i++)
	{
		if(save.elements[i].type=="checkbox")
		{
			if(save.elements[i].checked)
			{
				flag=1;
			}
		}
	}
	if(flag==0)
	{
	alert("没有添加任何档案");
	return true;
	}
	//对选择的进行校验
	////
	var number;
	var textname;
	var checkname;
	var end;
	var star;
	var temp;
	for(var i=0;i<save.elements.length;i++)
	{
		if(save.elements[i].type=="checkbox"||save.elements[i].type=="text")
		{
			if(save.elements[i].checked&&save.elements[i].type=="checkbox")
			{
				checkname=save.elements[i].name;
				end=checkname.length;
				star=checkname.indexOf("checkbox");
				checkname=checkname.substring(star+8,end);
			}
			if(save.elements[i].type=="text")
			{
				
				textname=save.elements[i].name;
				temp="fileno"+checkname;
				if(textname.indexOf(temp)!=-1)//档案号
				{
					if(save.elements[i].value=="")
					{
						alert("档案号不能为空");
						save.elements[i].focus();
						return false;
					}
					if(save.elements[i].value.length>5)
					{
							alert("档案号在5个字符长度以内,当前:"+save.elements[i].value.length);
							save.elements[i].focus();
							return false;
					}
				}
				temp="filepositionid"+checkname;
				if(textname.indexOf(temp)!=-1)//位置
				{
					if(save.elements[i].value.length>15)
					{
							alert("档案位置在15个字符长度以内,当前:"+save.elements[i].value.length);
							save.elements[i].focus();
							return false;
					}
				}
				temp="filedate"+checkname;
				if(textname.indexOf(temp)!=-1)//日期
				{
						if(!save.elements[i].value.match(reg))
						{
							alert("日期格式不正确");
							save.elements[i].focus();
							return false;
						}
				}
				temp="filelocatedinstitute"+checkname;
				if(textname.indexOf(temp)!=-1)//存放机构
				{
					if(save.elements[i].value.length>25)
					{
							alert("存放机构在25个字符长度以内,当前:"+save.elements[i].value.length);
							save.elements[i].focus();
							return false;
					}
				}
				temp="comments"+checkname;
				if(textname.indexOf(temp)!=-1)//备注
				{
					if(save.elements[i].value.length>75)
					{
							alert("备注在75个字符长度以内,当前:"+save.elements[i].value.length);
							save.elements[i].focus();
							return false;
					}
				}
			}
		}
	}
	return true;
	
}
	