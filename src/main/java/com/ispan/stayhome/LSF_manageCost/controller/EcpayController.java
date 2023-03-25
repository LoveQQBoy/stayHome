package com.ispan.stayhome.LSF_manageCost.controller;

import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ispan.stayhome.LSF_manageCost.service.EcpayService;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;

@Controller
public class EcpayController {
	
	@Autowired
	private EcpayService ecpayService;
	
	private static AllInOne all = new AllInOne("");
	
	//http://localhost:8080/ecpay/ECPayServer
	@RequestMapping(value="/ECPayServer", produces="text/html;charset=utf-8") //預設response的字元編碼為ISO-8859-1
	@ResponseBody
	public String processPayment(HttpServletRequest request, HttpServletResponse response) {		
		String form = genAioCheckOutALL(request);		
		System.out.printf("【ECPayServer.java】產生了讓消費者付款的表單：\n%s\n",form);	
		//輸出內容請參考最下面區段。
		
		//測試：「接下來將重新導向到綠界(使用者進行付款)，然後再回到我們應用程式(Post+/ecpay/ECPayServer3)」時，HttpSession是否還保持原有者?
		request.getSession().setAttribute("mcId", request.getParameter("mcId"));	
		
		System.out.printf("【MyController.java】response.getHeaders('Set-Cookie')=%s\n", 
				response.getHeaders("Set-Cookie").toString());
		//【MyController.java】response.getHeaders('Set-Cookie')=[JSESSIONID=112C952450225B1545AD1D541E451513; Path=/ecpay; Secure; HttpOnly; SameSite=None]

		return form; 		
	}
	
	private String genAioCheckOutALL(HttpServletRequest request ){	
		AioCheckOutALL obj = new AioCheckOutALL();
		//下列三項設定於payment_conf.xml
		//<MerchantID>2000132</MerchantID>
        //<HashKey>5294y06JbISpM5x9</HashKey>
        //<HashIV>v77hoKGq4kWxNNIS</HashIV>
		obj.setMerchantTradeNo(String.format("III%d", new Date().getTime()));	//特店交易編號均為唯一值，不可重複使用。英數字大小寫混合。
		obj.setMerchantTradeDate(String.format("%tY/%<tm/%<td %<tH:%<tM:%<tS", new Date() ) );	//特店交易時間。格式為：yyyy/MM/dd HH:mm:ss。
		
		obj.setTotalAmount( request.getParameter("TotalAmount") );	//交易金額。請帶整數，不可有小數點。僅限新台幣。
		obj.setTradeDesc( request.getParameter("TradeDesc") );		//交易描述。請勿帶入特殊字元。
		obj.setItemName( request.getParameter("ItemName") );		//商品名稱 
																	//1. 如果商品名稱有多筆，需在金流選擇頁一行一行顯示商品名稱的話，商品名稱請以符號#分隔。
																	//2. 商品名稱字數限制為中英數 400 字內，超過此限制系統將自動截斷。		
		obj.setNeedExtraPaidInfo("N");
		
		//***付款結果通知我們伺服端的方式(可二選一)***//
		//A.以Server端(綠界)方式直接回傳付款結果通知
		obj.setReturnURL("https://220.133.103.95/ecpay/ECPayServer2");
			//當消費者付款完成後，綠界會將付款結果參數以幕後(Server POST)回傳到該網址(該網址須是一個固定IP且使用https協定)。
			//必設欄位。但我們可以忽略相關的後續處理作業。
		//B.以Client端(消費者)方式回傳付款結果通知
		obj.setOrderResultURL("http://localhost:8081/stayHome/ECPayServer3"); 
			//當消費者付款完成後，綠界會將付款結果參數以幕前(Client POST)回傳到該網址。			
			//若與下面[ClientBackURL]同時設定，將會以此參數為主。		
		//********************************//
		
		//obj.setClientBackURL("http://localhost:8080/ecpay/ECPayServer3");
			//綠界付款成功畫面會增加「返回商店」按鈕，消費者點選此按鈕後，會將頁面導回到此設定的網址
			//(注意：導回時不會帶付款結果到此網址，只是將頁面(以GET方法)導回而已。)	
		
		String form = all.aioCheckOut(obj, null);
		return form;
	}
	
	//【ECPayServer.java】obj.setClientBackURL("http://localhost:8080/ecpay/ECPayServer3");
		//綠界付款成功畫面會增加「返回商店」按鈕，消費者點選此按鈕後，會將頁面導回到此設定的網址
		//(注意：導回時不會帶付款結果到此網址，只是將頁面(以GET方法)導回而已。)	
		@GetMapping(value="/ECPayServer3", produces="text/html;charset=utf-8")	  
		@ResponseBody public String processPaymentResult() { 
			return  "<h1>恭喜您付款成功。</h1>"; 
		}	 
		
		//【ECPayServer.java】obj.setOrderResultURL("http://localhost:8080/ecpay/ECPayServer3"); 
		//當消費者付款完成後，綠界會將付款結果參數以幕前(Client POST)回傳到該網址。			
		//若與[ClientBackURL]同時設定，將會以此參數為主。
		@PostMapping(value="/ECPayServer3",  produces="text/html;charset=utf-8") //預設response的字元編碼為ISO-8859-1
		public String processPaymentResult2(HttpServletRequest request) {	
			
			Hashtable<String, String> dict = new Hashtable<String, String>();
			Enumeration<String> enumeration = request.getParameterNames();
			while(enumeration.hasMoreElements()) {
				String paramName = enumeration.nextElement();
				String paramValue = request.getParameter(paramName);
				dict.put(paramName, paramValue);			
			}
			System.out.printf("【ECPayServer3.java】用戶端付款成功後回傳「付款結果」通知給伺服端的參數們：\n%s\n", dict.toString());
			//輸出範例：
			//【ECPayServer3.java】用戶端付款成功後回傳「付款結果」通知給伺服端的參數們：
			//{CheckMacValue=028D288F5CB566EB1FA5E204FA46B6FC68AB3ED68EC12AE17E561A6A9AF885F5, 
			//TradeDate=2021/08/31 11:09:08, TradeNo=2108311109087900, MerchantID=2000132, 
			//PaymentTypeChargeFee=21, PaymentType=Credit_CreditCard, TradeAmt=1050, RtnMsg=Succeeded, StoreID=, CustomField4=, 
			//MerchantTradeNo=III1630379348465, CustomField3=, 
			//PaymentDate=2021/08/31 11:10:23, SimulatePaid=0, CustomField2=, CustomField1=, RtnCode=1}

			boolean checkStatus = all.compareCheckMacValue(dict); //true：表示資料未被竄改
			//消費者付款成功且檢查碼正確的時候：	(RtnCode:交易狀態(1:成功，其餘為失敗))	
			if ("1".equals(dict.get("RtnCode")) && checkStatus==true ){
				//---------------------------//
				Integer manageCostId =  Integer.parseInt((String) request.getSession().getAttribute("mcId")); 
				ecpayService.createPayTimeWhenPaySuccess(manageCostId);
				request.getSession().removeAttribute("mcId");
				//---------------------------//
				//回應用戶端(付款者)
				return "redirect:/manageCost/manageCostPageFront";			 
			}
			else
				return "<h1>伺服端已接收到從用戶端(付款者)送出的付款通知(但付款資料有誤！)。</h1>";	
		}

}

//ECPayServer.java 產生了讓消費者付款的表單：
//<form id="allPayAPIForm" action="https://payment-stage.ecPay.com.tw/Cashier/AioCheckOut/V5" method="post">
//<input type="hidden" name="NeedExtraPaidInfo" value="N"><input type="hidden" name="ReturnURL" value="https://220.133.103.95/ecpay/ECPayServer2"><input type="hidden" name="BidingCard" value="">
//<input type="hidden" name="CheckMacValue" value="20CF70D1E6DE43ACF024DF8F579E02EA08EAF1C56D9D54B3A41211D1AAE8CE0D"><input type="hidden" name="StoreExpireDate" value=""><input type="hidden" name="PeriodAmount" value=""><input type="hidden" name="PaymentInfoURL" value=""><input type="hidden" name="ClientRedirectURL" value=""><input type="hidden" name="StoreID" value=""><input type="hidden" name="EncryptType" value="1"><input type="hidden" name="IgnorePayment" value=""><input type="hidden" name="CreditInstallment" value=""><input type="hidden" name="PaymentType" value="aio">
//<input type="hidden" name="OrderResultURL" value="http://220.133.103.95:8080/ecpay/ECPayServer3"><input type="hidden" name="PeriodReturnURL" value=""><input type="hidden" name="PlatformID" value=""><input type="hidden" name="MerchantMemberID" value=""><input type="hidden" name="Frequency" value=""><input type="hidden" name="ExpireDate" value=""><input type="hidden" name="ItemName" value="馬桶刷850元x1#消毒水200元"><input type="hidden" name="InvoiceMark" value="N"><input type="hidden" name="ExecTimes" value=""><input type="hidden" name="ChoosePayment" value="Credit"><input type="hidden" name="MerchantID" value="2000132"><input type="hidden" name="TradeDesc" value="刷兩三下就光亮如新"><input type="hidden" name="ClientBackURL" value=""><input type="hidden" name="PeriodType" value=""><input type="hidden" name="CustomField4" value=""><input type="hidden" name="Desc_4" value=""><input type="hidden" name="TotalAmount" value="1050"><input type="hidden" name="CustomField3" value=""><input type="hidden" name="Desc_3" value=""><input type="hidden" name="CustomField2" value=""><input type="hidden" name="Desc_2" value=""><input type="hidden" name="MerchantTradeDate" value="2021/08/31 11:09:08"><input type="hidden" name="CustomField1" value=""><input type="hidden" name="Desc_1" value=""><input type="hidden" name="ChooseSubPayment" value=""><input type="hidden" name="UnionPay" value=""><input type="hidden" name="InstallmentAmount" value=""><input type="hidden" name="MerchantTradeNo" value="III1630379348465"><input type="hidden" name="ItemURL" value=""><input type="hidden" name="Remark" value=""><input type="hidden" name="DeviceSource" value=""><input type="hidden" name="Language" value=""><input type="hidden" name="Redeem" value="">
//<script language="JavaScript">allPayAPIForm.submit()</script>
//</form>
/*
綠界要求「付款表單」需遵守下列規則：
1.action="https://payment-stage.ecpay.com.tw/Cashier/AioCheckOut/V5" (設定於~.config.EcpayPayment.xml)
2.enctype="application/x-www-form-urlencoded"
3.method="POST"
*/
