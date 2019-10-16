// JavaScript Document
function getintervalDay(from_y,from_m,from_d,to_y,to_m,to_d)
{
	if(parseInt(from_d)==parseInt(to_d))
	{
		d_count=0;
	}
	if(parseInt(from_d)<parseInt(to_d))
	{
		d_count=parseInt(to_d)-parseInt(from_d);
	}
	if(parseInt(from_d)>parseInt(to_d))
	{
		alert("结束日期小于开始日期");
		return 0;
	}
	return parseInt(d_count)+parseInt(1);
}
function getintervalMonth(from_y,from_m,from_d,to_y,to_m,to_d)
{
	if(parseInt(from_m)<parseInt(to_m))
	{
			//2002-10-05   2002-12-12
		var m_count=0;
		
		//中间间隔月总天数
		for(var i=parseInt(from_m)+1;i<parseInt(to_m);i++)
		{	
			if(i==2)
			{
				if(from_y%4==0&&from_y%100!=0||from_y%400==0)
				{	
					m_count+=29;
						
				}
				else
				{
					m_count+=28;
				}
			}
			if(i==1||i==3||i==5||i==7||i==8||i==10||i==12)//31天
			{	
				m_count+=31;
			}
			if(i==4||i==6||i==9||i==11)//30天
			{
				m_count+=30;
			}
		}
		////
		if(from_m==2)//2月判断瑞年平年
		{	
			if(from_y%4==0&&from_y%100!=0||from_y%400==0)
			{	
				m_count+=parseInt(29-parseInt(from_d));
						
			}
			else
			{
				m_count+=parseInt(28-parseInt(from_d));
			}
		}
		if(from_m==1||from_m==3||from_m==5||from_m==7||from_m==8||from_m==10||from_m==12)//31天
		{	
			m_count+=parseInt(31-parseInt(from_d));
		}
		if(from_m==4||from_m==6||from_m==9||from_m==11)//30天
		{	
			m_count+=parseInt(30-parseInt(from_d));
		}
		m_count+=parseInt(to_d);
		d_count=m_count;
		return parseInt(d_count)+parseInt(1);
	}
	if(parseInt(from_m)>parseInt(to_m))
	{
		alert("结束日期小于开始日期1");
		return 0;
	}
}
function getintervalYear(from_y,from_m,from_d,to_y,to_m,to_d)
{
	if(parseInt(from_y)>parseInt(to_y))
	{
			alert("结束日期小于开始日期");
			return 0;
	}
	if(parseInt(to_y)-parseInt(from_y)>1)
	{
			alert("日期最大间隔为1年");
			return 0;
	}
		var m_count=0;
		var temp_count=0;
		var temp=from_m;

		//////////////
		for(var i=parseInt(from_m)+1;i<=12;i++)//检测中间间隔几月总天数
		{	
			if(i==2)
			{
				if(from_y%4==0&&from_y%100!=0||from_y%400==0)
				{	
					m_count+=29;
						
				}
				else
				{
					m_count+=28;
				}
			}
			if(i==1||i==3||i==5||i==7||i==8||i==10||i==12)//31天
			{	
				m_count+=31;
			}
			if(i==4||i==6||i==9||i==11)//30天
			{
				m_count+=30;
			}
		}
		if(from_m==2)//2月判断瑞年平年
		{	
			if(from_y%4==0&&from_y%100!=0||from_y%400==0)
			{	
				m_count+=parseInt(29-parseInt(from_d));
						
			}
			else
			{
				m_count+=parseInt(28-parseInt(from_d));
			}
		}
		if(from_m==1||from_m==3||from_m==5||from_m==7||from_m==8||from_m==10||from_m==12)//31天
		{	
			m_count+=parseInt(31-parseInt(from_d));
		}
		if(from_m==4||from_m==6||from_m==9||from_m==11)//30天
		{	
			m_count+=parseInt(30-parseInt(from_d));
		}
		////////////////
		var temp1_count=0;
		var temp1=1;
		
		if(to_m==1)//一月
		{
			d_count=parseInt(to_d)+parseInt(m_count);
			return parseInt(d_count)+parseInt(1);
		}
		if(parseInt(to_m)>1)
		{
			for(var j=1;j<parseInt(to_m);j++)
			{
				if(j==2)
				{
					if(from_y%4==0&&from_y%100!=0||from_y%400==0)
					{
						m_count+=29;
					}
					else
					{
						m_count+=28;
					}
				}
				if(j==1||j==3||j==5||j==7||j==8||j==10||j==12)//31天
				{
					m_count+=31;
				}
				if(j==4||j==6||j==9||j==11)//30天
				{
					m_count+=30;
				}
			}
			d_count=parseInt(to_d)+parseInt(m_count);
			return parseInt(d_count)+parseInt(1);
		}
		/*for(var j=1;j=parseInt(to_m)-1;j++)
		{
			temp1+=1;
			temp1_count=temp1;
			if(temp1_count==2)
			{
				if(from_y%4==0&&from_y%100!=0||from_y%400==0)
				{
					m_count+=29;
						
				}
				else
				{
					m_count+=28;
				}
			}
			if(temp1_count==1||temp1_count==3||temp1_count==5||temp1_count==7||temp1_count==8||temp1_count==10||temp1_count==12)//31天
			{
				m_count+=31;
			}
			if(temp1_count==4||temp1_count==6||temp1_count==9||temp1_count==11)//30天
			{
				m_count+=30;
			}
		}*/
}