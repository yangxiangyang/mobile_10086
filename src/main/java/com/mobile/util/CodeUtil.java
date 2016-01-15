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
     * ���ɶ�ά��(QRCode)ͼƬ 
     * @param content ��ά��ͼƬ������
     * @param imgPath ���ɶ�ά��ͼƬ������·��
     * @param ccbpath  ��ά��ͼƬ�м��logo·��
     */  
    public static int createQRCode(String content, String imgPath,String ccbPath) {  
        try {  
            Qrcode qrcodeHandler = new Qrcode();  
            
//             ��һ��������M��������Ϣ������Ϊ15%��������Ϣͬ���洢�ڶ�ά���У�������Խ�ߣ�������Ϣռ�õĿռ�Խ�࣬
//            ��ѡL(7%)��M(15%)��Q(25%)��H(30%) 	
            //��ô�ܴ洢������ѶϢ��Խ�٣������ļ�����M���ǵڶ������ǱȽϵ͵ģ�
//		            �ڶ���������B������һ�ֱ��뷽ʽ��
//		            ������������7���Ƕ�ά��İ汾�ţ�Ҳ�����Ŷ�ά�����Ϣ��������ά����Կ���һ���ڰ׷�����󣬰汾��ͬ��
//		            ���󳤿��򷽸���������ֱ�ͬ���汾1Ϊ21*21���󣬰汾ÿ��1����ά��������߳�����4�����԰汾7Ϊ45*45�ľ�����߰汾Ϊ��40��
//		            ��177*177�ľ���
            
            
            qrcodeHandler.setQrcodeErrorCorrect('M');  
            qrcodeHandler.setQrcodeEncodeMode('B');  
            qrcodeHandler.setQrcodeVersion(7);  
  
            // System.out.println(content);  
            byte[] contentBytes = content.getBytes("gb2312");
            //ͼƬ������
            BufferedImage bufImg = new BufferedImage(150, 150, BufferedImage.TYPE_INT_RGB);  
            Graphics2D gs = bufImg.createGraphics();  
            	//����
            gs.setBackground(Color.WHITE);  
            gs.clearRect(0, 0, 200, 200);  
  
          // �趨ͼ����ɫ > BLACK  
            gs.setColor(Color.black);  
            // ����ƫ���� �����ÿ��ܵ��½�������  
            int pixoff = 2;  
            // ������� > ��ά��  
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
            /*  //ʵ����һ��Image����
            gs.drawImage(img, 55, 55, null);
            gs.dispose();  
            bufImg.flush();  
  */
           //ʵ����һ��Image����
        
            gs.drawImage(img, 44, 44, 30, 30, null);
           gs.dispose();  
          bufImg.flush();
            // ���ɶ�ά��QRCodeͼƬ  
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
	//ͼƬ���ɴ洢·��
	 String imgPath = "E:\\AppFiles\\code\\code.jpg";
	 //String content="http://mm.10086.cn/";
	 String content="�������ڱ���";
	 //����ͼƬ����Դ
	 String ccbPath="E:\\AppFiles\\icons\\code.jpg";
	 CodeUtil qrcodeimg =new CodeUtil();
	 qrcodeimg.createQRCode(content, imgPath,ccbPath);
	 System.out.println("�������");
	 
}   
    
}
