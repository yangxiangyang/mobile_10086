package com.mobile.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import com.swetake.util.Qrcode;

public class CodeUtil {
	
	/** 
     * 生成二维码(QRCode)图片 
     * @param content 二维码图片的内容
     * @param imgPath 生成二维码图片完整的路径
     * @param ccbpath  二维码图片中间的logo路径
     */  
    public static int createQRCode(String content, String imgPath,String ccbPath) {  
        try {  
            Qrcode qrcodeHandler = new Qrcode();  
            
//             第一个参数“M”代表信息纠错级别为15%；纠错信息同样存储在二维码中，纠错级别越高，纠错信息占用的空间越多，
//            可选L(7%)、M(15%)、Q(25%)、H(30%) 	
            //那么能存储的有用讯息就越少；共有四级，“M”是第二级，是比较低的；
//		            第二个参数“B”代表一种编码方式；
//		            第三个参数“7”是二维码的版本号，也象征着二维码的信息容量；二维码可以看成一个黑白方格矩阵，版本不同，
//		            矩阵长宽方向方格的总数量分别不同，版本1为21*21矩阵，版本每增1，二维码的两个边长都增4；所以版本7为45*45的矩阵；最高版本为是40，
//		            是177*177的矩阵；
            
            
            qrcodeHandler.setQrcodeErrorCorrect('M');  
            qrcodeHandler.setQrcodeEncodeMode('B');  
            qrcodeHandler.setQrcodeVersion(7);  
  
            // System.out.println(content);  
            byte[] contentBytes = content.getBytes("gb2312");
            //图片缓存区
            BufferedImage bufImg = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);  
            Graphics2D gs = bufImg.createGraphics();  
            	//背景
            gs.setBackground(Color.WHITE);  
            gs.clearRect(0, 0, 200, 200);  
  
          // 设定图像颜色 > BLACK  
            gs.setColor(Color.black);  
            // 设置偏移量 不设置可能导致解析出错  
            int pixoff = 2;  
            // 输出内容 > 二维码  
            if (contentBytes.length > 0 && contentBytes.length < 120) {  
                boolean[][]  codeOut =  qrcodeHandler.calQrcode(contentBytes);  
                for (int i = 0; i < codeOut.length; i++) {  
                    for (int j = 0; j < codeOut.length; j++) {  
                        if (codeOut[j][i]) {  
                            gs.fillRect(j * 3 + pixoff, i * 3 + pixoff, 3, 3);  
                        }  
                    }  
                }  
            } else {  
                System.err.println("QRCode content bytes length = "  
                        + contentBytes.length + " not in [ 0,120 ]. ");  
                return -1;
            }  
        Image img = ImageIO.read(new File(ccbPath));
            /*  //实例化一个Image对象。
            gs.drawImage(img, 55, 55, null);
            gs.dispose();  
            bufImg.flush();  
  */
           //实例化一个Image对象。
        
            gs.drawImage(img, 44, 44, 30, 30, null);
           gs.dispose();  
          bufImg.flush();
            // 生成二维码QRCode图片  
            File imgFile = new File(imgPath);  
            ImageIO.write(bufImg, "png", imgFile);  
        } catch (Exception e) 
        {  
            e.printStackTrace();  
            return -100;
        }    
        return 0;
}
    
 public static void main(String[] args) {
	//图片生成存储路径
	 String imgPath = "E:\\AppFiles\\code\\code.jpg";
	 //String content="http://mm.10086.cn/";
	 String content="杨向阳在北京";
	 //插入图片的来源
	 String ccbPath="E:\\AppFiles\\icons\\code.jpg";
	 CodeUtil qrcodeimg =new CodeUtil();
	 qrcodeimg.createQRCode(content, imgPath,ccbPath);
	 System.out.println("创建完成");
	 
}   
    
}
