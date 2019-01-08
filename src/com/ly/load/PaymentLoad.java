package com.ly.load;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayOpenPublicTemplateMessageIndustryModifyRequest;
import com.alipay.api.response.AlipayOpenPublicTemplateMessageIndustryModifyResponse;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ly.bean.PlaceOrder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaymentLoad extends BaseLoad {


    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String APP_ID = "2016092500593296";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String APP_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCZ+NMKRVt6VTDHvvBbNinv+v11laeZ3CRgZ7Fp8GJaHEXez0Fm0VSH4IwXzzwr5EFO5Z16pZfuyVU9lIob9Zhl93oe34aCK7iA415Re1Th/g0J3bE8U2bT9FK3lSbHcPWDi4NCbs//yX1avb1irjTs7w9BGckUvKjYkbzoh+Za6LbATrbMJqmxLUw2ERY4PAo1mEAsLMLiltxkDx214jYe1JQJW+hs2ENEKgt43IRxzlfdWCl5NZGroO4GbRHnpHctySWAc3RwzyqV0ZkboksdwflPXqgZTWkuUrUcLpuFCkBlpkVu4TynYuHsOQ1eNUUw7g7YDdrJvabslrLrcLMFAgMBAAECggEAQpwisI7QxFuWftNbqBoGiotKm1Pi7e0Oz6YMWohkBBe3Im0HHjPxHddGxAYjQdm9ZyRuvGBnGiZ/eeSI2w+knngxnKmWxzmi2B+E7jssvZfqjBocUrzB/2/zWPhtjlqP3Bi+8D6APWgtktQC2m4C9310W/WLyCzV3tstnmssfKcAqXZtmtU8DcMTHzHdJbfTOh2Kp9HbRq10K8+0TFLMpSbkhQxeA/YjLjksZ9TCKwLs9b+6aACT2wwbpzmkT88qhZ8Fxl2fweZOIEzRwC59CUEQR+t1WZQA1dCHVOvJIuqSWy771XKSF83i5HKor0cvRgRMwhp+++k9pVx5hSVxAQKBgQD2B5umtPyulywKPL+ZvGsgQTQ3RIg0TsiSoVHum5OgHIlWZjVXB0xCJHVrijUDEiK+iE866c0ho+nIcr6d1DGVYfopIpEnzopRsKA4E+No9G3fHWHDdvOedOPysYpy5hX53rgrG1pzg8eKlW85FLn3XqLJQ8LRgnB8790sGp2huwKBgQCgNi3xTp1Zjr6BOybBnbi06rRRF1lno1RnAR1mU3PsL3seCP5gtRyyyDCgyjzL7Ax3TbJkqp0P2loxP7Jk9z7slo69ClmTbaCVzUGvgRkOu4yA+fjfY0cPsS+B7xd2gHAJzENrxF8iCeQzoku7tbDFUrH27hZwhDF76f8ryQhSPwKBgANMwNKwlL8Gugl9HRjDxTAOj9O58JW7BMeqsD2iCo3YNCNbhZWgPG6arJR/qwxafurNSzrADgdIeoc8ur/FK9EUGlXY59d/1q0X0F5QLfmnZZ3beAc2Hjhe2vvTU8cMvPi3+wJhAIqSFugN7wr18zSVKVvHs+I5fKbAVNgVl02nAoGAegoLYR9jWQ8VKtfmI+KDCr0mcgb5Qar25ifBP2seiLF6dVygeQY9QCqicDjdqvC6Zj63P1t/nM3VmmJNAAAe3JWrUXEK3vTJOJNMD+alMcXHt4+y9mdngBqWd8Gq+8fGkJAaY4MIfKNQQ8zDJ8+1QfpGOjUOvtLT5vJOQAo43n0CgYAH6+hV+E3NzrnflwnxSx8ST73udis9O9/t8i4Q0xf30s5Goi/Mz8KEr7a8YteZeTRvwV6NZo0mf4ii3jZCz7S1goD8+Z3WRtP36TeURz+A32lcFkL33ccZLrPpk/r32iYU7joTLFFFTSClqX0rZ6ErLN0lfPtx9EpEFFBbuuWOQg==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoapjGk9t8ZYgp8Z93e74pjol1XDyvs/NImlql0mEQo9n8FCjASnASpE4qUss11QDeAe8ABRneTjZKmN+IRIKgRr1ivYseEF9T2WhoXQZvvGKtC15IdbXWzkH1IZi0HU163DZ3MWPeXBd7i8PFdnUw5nmfSyn1SXmNjqtHTpiheN8vwViP+diq5tBCwh/I9xF7gqvhBy+CV2lm4wdZ3X66sP97hHPQ3z7xJe7wQDlDJl5WFa2h14iEQ9h/jPsgSV5lWPLMGcLTo/yA47hRW1Dn3TLzDsY6IHbG1pmtSCgGSjEfOgVng5F0yGBYz588+nU+77DgEDlYKr99IFfofn58QIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/ttsx/zfb/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/ttsx/zfb/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String CHARSET = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";

    public PaymentLoad(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }


    public void getSettlementCommodityINfo(){

        Gson gson = new Gson();
        PlaceOrder placeOrder = new PlaceOrder();
        //接受数据
        String data = request.getParameter("cids");

        ArrayList<PlaceOrder>  placeOrderArrayList;

        Type type = new TypeToken<ArrayList<PlaceOrder>>() {
        }.getType();

        placeOrderArrayList = gson.fromJson(data, type);

        try {
            pay();
        } catch (AlipayApiException e) {
            System.out.println();
            e.printStackTrace();
        }


    }

    public void pay() throws AlipayApiException {
        //实例化客户端
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, APP_ID, APP_PRIVATE_KEY, "json", CHARSET, ALIPAY_PUBLIC_KEY, "RSA2");
        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.open.public.template.message.industry.modify
        AlipayOpenPublicTemplateMessageIndustryModifyRequest request = new AlipayOpenPublicTemplateMessageIndustryModifyRequest();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数
        //此次只是参数展示，未进行字符串转义，实际情况下请转义
        request.setBizContent("  {" +
                "    \"primary_industry_name\":\"IT科技/IT软件与服务\"," +
                "    \"primary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_code\":\"10001/20102\"," +
                "    \"secondary_industry_name\":\"IT科技/IT软件与服务\"" +
                " }");
        AlipayOpenPublicTemplateMessageIndustryModifyResponse response = alipayClient.execute(request);
        //调用成功，则处理业务逻辑
        if(response.isSuccess()){
            System.out.println("支付宝接口调用成功");
        }
    }


}
