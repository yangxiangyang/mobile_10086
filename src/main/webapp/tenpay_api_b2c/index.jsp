<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>

<%@ page import="com.tenpay.util.TenpayUtil" %>
<%@ include file = "config.jsp" %>    

<%
//��ǰʱ�� yyyyMMddHHmmss
String currTime = TenpayUtil.getCurrTime();

//6λʱ��
String strTime = currTime.substring(8, currTime.length());

//��λ�����
String strRandom = TenpayUtil.buildRandom(4) + "";

//10λ���к�,�������е�����
String strReq = strTime + strRandom;
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML><HEAD><TITLE>�Ƹ�ͨ����ͨ��</TITLE>
<META http-equiv=Content-Type content="text/html; charset=GBK">
<META 
content=���Ϲ���/����֧��/��ȫ֧��/��ȫ����/�����ȫ/֧��,��ȫ/�Ƹ�ͨ/��ȫ,֧��/��ȫ������/֧��,����/����,�տ�/����,ó��/����ó��. 
name=description>
<META 
content=���Ϲ���/����֧��/��ȫ֧��/��ȫ����/�����ȫ/֧��,��ȫ/�Ƹ�ͨ/��ȫ,֧��/��ȫ������/֧��,����/����,�տ�/����,ó��/����ó��. 
name=keywords>
<META content="MSHTML 6.00.3790.2577" name=GENERATOR>

<style type="text/css">
<!--
a:link {
	color: #003399;
}
.px12 {
	font-size: 12px;
}
a:visited {
	color: #003399;
}
a:hover {
	color: #FF6600;
}
.px14 {
	font-size: 14px;
}
.px12hui {
	font-size: 12px;
	color: #999999;
}
.STYLE2 {font-size: 14px; font-weight: bold; }
-->
</style>
</HEAD>
<BODY topMargin=0>


<div align="center">
  <table width="760" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="381" align="left" valign="middle"><a href="https://www.tenpay.com/" target="_blank"><img src="image/logo.jpg" width="537" height="145" border="0"></a></td>
      <td width="379" align="right" valign="middle"><font style="color:#000000;font-size:12px;">���ã��� <A 
      href="https://www.tenpay.com/" target="_blank">ע��</A> �� <A 
      href="https://www.tenpay.com/" target="_blank">��¼</A> | <A 
      href="https://www.tenpay.com/" target="_blank">�Ƹ�ͨ��ҳ</A></font></td>
    </tr>
  </table>
  <table width="760" height="25" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td></td>
    </tr>
  </table>
  <table width="760" height="406" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td align="center" valign="top"><table width="760" border="0" cellspacing="0" cellpadding="3" align="center">
        <tr>
          <td height="30" width="5" bgcolor="#666666"></td>
          <td width="743" height="30" bgcolor="#FF6600"><font style="color:#FFFFFF;font-size:14px;"><B> ���Ƹ�ͨ���ٸ���ͨ�� ���� ��� ��ȫ</B></font></td>
        </tr>
      </table>
          <table width="760" height="42" border="0" align="center" cellpadding="0" cellspacing="0">
               <tr> <td height="30" ><span class="STYLE2"><img src="image/arrow_02_01.gif"> ��д������Ϣ</span></td>
            </tr>
        </table>
        <table width="760" border="0" cellspacing="0" cellpadding="0" align="center" height="1">
            <tr>
              <td width="740" bgcolor="#CCCCCC"></td>
              <td width="20"></td>
            </tr>
        </table>
        <table width="760" border="0" align="center" cellpadding="0" cellspacing="0">
                        <script language="javascript">
	function payFrm()
	{
		if (directFrm.order_no.value=="")
		{
			alert("���ѣ�����д������ţ�������ض��Ķ�����ţ������Ĭ�ϱ�ţ���ˢ��һ��ҳ��Ϳ����ˣ�");
			directFrm.order_no.focus();
			return false;
		}
		if (directFrm.product_name.value=="")
		{
			alert("���ѣ�����д��Ʒ����(������Ŀ)��");
			directFrm.product_name.focus();
			return false;
		}
		if (directFrm.order_price.value=="")
		{
			alert("���ѣ�����д�����Ľ��׽�");
			directFrm.order_price.focus();
			return false;
		}else if(directFrm.order_price.value.split(".")[1].length>2){
     	    alert("���׽���С����ֻ������λ��");
		   return false;
	    }
		
		if (directFrm.remarkexplain.value=="")
		{
			alert("���ѣ�����д���ļ�Ҫ˵����");
			directFrm.remarkexplain.focus();
			return false;
		}
		if (directFrm.remarkexplain.value.length>31)
		{
			alert("���ѣ������涨������,����������");
  			return   false;   
		}
		return true;
	}
  </script>
  
<form action="payRequest.jsp" method="post" name="directFrm" onSubmit="return payFrm();">
            <tr>
              <td align="left"><table width="760" height="30" border="0" align="left" cellpadding="0" cellspacing="1" bgcolor="#FFCC00">
                  <tr>
                    <td align="center" valign="top" bgcolor="#FFFFEE"><table width="760" height="351" border="0" cellpadding="6" cellspacing="0" class="px14">
                        <tr>
                          <td height="26" align="right" valign="top">&nbsp;</td>
                          <td valign="top"> �� </td>
                          <td width="269" rowspan="8" valign="top"><table width="100%" border="0" align="left" cellpadding="0" cellspacing="5">
                            <tr>
                              <td height="10" align="center" valign="middle"><img src="image/cft.gif" width="180" height="81"></td>
                              </tr>
                            <tr>
                              <td height="24"><font style="color:#000000;font-size:12px;"><B>˵��1��</B></font></td>
                              </tr>
                            <tr>
                              <td height="24"><font style="color:#000000;font-size:12px;">��ͨ��Ϊ���òƸ�ͨ�������֧��ǰ�뱾��վ���һ�¡�</font></td>
                              </tr>
                            <tr>
                              <td height="24"><font style="color:#000000;font-size:12px;"><B>˵��2��</B></font></td>
                              </tr>
                            <tr>
                              <td height="24"><font style="color:#000000;font-size:12px;">�����ȷ�Ϻö����ͻ�����ٸ�������ڿ��ٸ���ͨ����ġ������Ҫ���͡���������������Ӧ�Ķ�����Ϣ��</font></td>
                              </tr>
                            
                          </table></td>
                        </tr>
                        <tr>
                        <td width="102" height="26" align="right" valign="top">�տ��</td>
                        <td width="353" valign="top"><%=spname %></td>
                        </tr> 
                      <tr>
                        <td align="right" valign="top">������ţ�</td>
                        <td valign="top">
                             <%=strReq%>
			              <input type="hidden" name="order_no" maxlength="50" size="18"  value="<%=strReq%>"></td>
                      </tr>
                      <tr>
                        <td align="right" valign="top">������Ŀ��</td>
                        <td valign="top"><span style="color:#000000;font-size:12px;">
                          <input name="product_name" type="text" size="18" maxlength="50" value="" >
                        </span></td>
                      </tr>
                      <tr>
                        <td align="right" valign="top">�����</td>
                        <td valign="top"><input type="text" name="order_price" maxlength="50" size="18" onkeyup="if(isNaN(value))execCommand('undo')" onafterpaste="if(isNaN(value))execCommand('undo')" value=""> 
                        Ԫ����ʽ��500.01��</td>
                      </tr>
                       <tr>
                        <td align="right" valign="top">֧����ʽ��</td>
                        <td valign="top"><span style="color:#000000;font-size:12px;">
                          <input type="radio" name="trade_mode" value="1"  checked="true">��ʱ����&nbsp;
                           <input type="radio" name="trade_mode" value="2">�н鵣��&nbsp;
                            <input type="radio" name="trade_mode" value="3">��̨ѡ��
                        </span></td>
                      </tr>
                      <tr>
                        <td height="99" align="right" valign="top">��Ҫ˵����</td>
                        <td valign="top"><textarea name="remarkexplain" cols="48" rows="5" id="remark2" ></textarea>
                          <br>
                          ����д�������ļ�Ҫ˵����30�����ڣ�</td></tr>
                      <tr>
                        <td align="right" valign="top">&nbsp;</td>
                        <td valign="top"><b>
                          <input name="submit" type="image" src="image/next.gif" alt="ʹ�òƸ�ͨ��ȫ֧��" width="103" height="27">
                        </b></td>
                        <td valign="top">&nbsp;</td>
                      </tr>
                    </table></td>
                  </tr></form>
              </table></td>
            </tr> 
        </table>
        <table width="760" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td>&nbsp;</td>
            </tr>
        </table></td>
    </tr>
  </table>
<TABLE width=760 border=0 cellPadding=0 cellSpacing=4 class="px12">
  <TBODY>
  <TR>
    <TD width="71" rowSpan=2 align="center" noWrap bgcolor="#CCCCCC" class=box-note><FONT 
      class=note-help>֧��<FONT class=note-help>���� </FONT></FONT></TD>
    <TD width="14" rowSpan=2></TD>
    <TD width="120"><IMG alt=�й��������� 
      src="image/icon_zggsyh_s.gif" border=0> </TD>
    <TD width="142"><IMG alt=�й��������� src="image/icon_ccb_s.gif" border=0> </TD>
    <TD width="108"><IMG alt=�Ϻ��ֶ���չ���� src="image/icon_spdb_s.gif" 
      border=0> </TD>
    <TD width="142"><IMG alt=�������� src="image/icon_zsyh_s.gif" border=0>    </TD>
    <TD width="141"><IMG alt=�й��������� src="image/icon_cmbc_s.gif" 
      border=0></TD>
  </TR>
  <TR>
    <TD>
      <DIV align=left><IMG alt=�й�ũҵ���� 
      src="image/icon_abc_s.gif" border=0> </DIV></TD>
    <TD><IMG alt=�㶫��չ���� src="image/icon_gdb_s.gif" border=0> </TD>
    <TD><IMG alt=��ҵ���� src="image/index_38.gif" 
      border=0> </TD>
    <TD>
      <DIV align=left><IMG alt=���ڷ�չ���� 
      src="image/icon_sdb_s.gif" border=0> </DIV></TD>
  <TD><IMG alt=VISA src="image/icon_visa_s.gif" border=0> </TD>
  </TR></TBODY></TABLE></div><HR width=760 SIZE=1>

<TABLE width=760 border=0 align="center" cellSpacing=1 class="px12hui">
  <TBODY>
  <TR>
    <TD>����������ȷ�ϡ���ť������ʾ���Ѿ����ܡ��Ƹ�ͨ����Э�顱��ͬ�������ҹ��� ����Ʒ�� 
      <BR>�������β�����������Ʒ��¼���ϣ��������ҵ�˵���ͽ��ܵĸ��ʽ�����ұ���е���Ʒ��Ϣ��ȷ��¼�� ���Σ� 
</TD>
  </TR></TBODY></TABLE>
<TABLE width=760 border=0 align=center cellPadding=0 cellSpacing=0 class="px12">
  <TBODY>
  <TR align="center">
    <TD class="txt12 lh15"><div align="center"><A href="http://www.tencent.com/" 
      target=_blank>��Ѷ���¹�˾</A> | �Ƹ�ͨ ��Ȩ���� 2008 </div></TD></TR>
  <TR align="center">
    <TD class="txt12 lh15"><div align="center"><IMG alt="�Ƹ�ͨͨ��������Ȩ����ȫ��֤�� " 
      src="image/logo_vbvv.gif" border=0><BR>
      �Ƹ�ͨͨ��������Ȩ����ȫ 
      ��֤��</div></TD></TR></TBODY></TABLE></body>
</html>
