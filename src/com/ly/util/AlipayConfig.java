package com.ly.util;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016092500593296";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCZ+NMKRVt6VTDHvvBbNinv+v11laeZ3CRgZ7Fp8GJaHEXez0Fm0VSH4IwXzzwr5EFO5Z16pZfuyVU9lIob9Zhl93oe34aCK7iA415Re1Th/g0J3bE8U2bT9FK3lSbHcPWDi4NCbs//yX1avb1irjTs7w9BGckUvKjYkbzoh+Za6LbATrbMJqmxLUw2ERY4PAo1mEAsLMLiltxkDx214jYe1JQJW+hs2ENEKgt43IRxzlfdWCl5NZGroO4GbRHnpHctySWAc3RwzyqV0ZkboksdwflPXqgZTWkuUrUcLpuFCkBlpkVu4TynYuHsOQ1eNUUw7g7YDdrJvabslrLrcLMFAgMBAAECggEAQpwisI7QxFuWftNbqBoGiotKm1Pi7e0Oz6YMWohkBBe3Im0HHjPxHddGxAYjQdm9ZyRuvGBnGiZ/eeSI2w+knngxnKmWxzmi2B+E7jssvZfqjBocUrzB/2/zWPhtjlqP3Bi+8D6APWgtktQC2m4C9310W/WLyCzV3tstnmssfKcAqXZtmtU8DcMTHzHdJbfTOh2Kp9HbRq10K8+0TFLMpSbkhQxeA/YjLjksZ9TCKwLs9b+6aACT2wwbpzmkT88qhZ8Fxl2fweZOIEzRwC59CUEQR+t1WZQA1dCHVOvJIuqSWy771XKSF83i5HKor0cvRgRMwhp+++k9pVx5hSVxAQKBgQD2B5umtPyulywKPL+ZvGsgQTQ3RIg0TsiSoVHum5OgHIlWZjVXB0xCJHVrijUDEiK+iE866c0ho+nIcr6d1DGVYfopIpEnzopRsKA4E+No9G3fHWHDdvOedOPysYpy5hX53rgrG1pzg8eKlW85FLn3XqLJQ8LRgnB8790sGp2huwKBgQCgNi3xTp1Zjr6BOybBnbi06rRRF1lno1RnAR1mU3PsL3seCP5gtRyyyDCgyjzL7Ax3TbJkqp0P2loxP7Jk9z7slo69ClmTbaCVzUGvgRkOu4yA+fjfY0cPsS+B7xd2gHAJzENrxF8iCeQzoku7tbDFUrH27hZwhDF76f8ryQhSPwKBgANMwNKwlL8Gugl9HRjDxTAOj9O58JW7BMeqsD2iCo3YNCNbhZWgPG6arJR/qwxafurNSzrADgdIeoc8ur/FK9EUGlXY59d/1q0X0F5QLfmnZZ3beAc2Hjhe2vvTU8cMvPi3+wJhAIqSFugN7wr18zSVKVvHs+I5fKbAVNgVl02nAoGAegoLYR9jWQ8VKtfmI+KDCr0mcgb5Qar25ifBP2seiLF6dVygeQY9QCqicDjdqvC6Zj63P1t/nM3VmmJNAAAe3JWrUXEK3vTJOJNMD+alMcXHt4+y9mdngBqWd8Gq+8fGkJAaY4MIfKNQQ8zDJ8+1QfpGOjUOvtLT5vJOQAo43n0CgYAH6+hV+E3NzrnflwnxSx8ST73udis9O9/t8i4Q0xf30s5Goi/Mz8KEr7a8YteZeTRvwV6NZo0mf4ii3jZCz7S1goD8+Z3WRtP36TeURz+A32lcFkL33ccZLrPpk/r32iYU7joTLFFFTSClqX0rZ6ErLN0lfPtx9EpEFFBbuuWOQg==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAoapjGk9t8ZYgp8Z93e74pjol1XDyvs/NImlql0mEQo9n8FCjASnASpE4qUss11QDeAe8ABRneTjZKmN+IRIKgRr1ivYseEF9T2WhoXQZvvGKtC15IdbXWzkH1IZi0HU163DZ3MWPeXBd7i8PFdnUw5nmfSyn1SXmNjqtHTpiheN8vwViP+diq5tBCwh/I9xF7gqvhBy+CV2lm4wdZ3X66sP97hHPQ3z7xJe7wQDlDJl5WFa2h14iEQ9h/jPsgSV5lWPLMGcLTo/yA47hRW1Dn3TLzDsY6IHbG1pmtSCgGSjEfOgVng5F0yGBYz588+nU+77DgEDlYKr99IFfofn58QIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/zfb/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/zfb/return_url.jsp";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

