package api.com.chj.core.barcode;
 

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Binarizer;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import api.com.chj.core.PathUtil;
import api.com.chj.core.StackTraceUtil;
import api.com.chj.core.StringUtil;

/**
 * 
 * 二维码的生成和解析
 * @author Chen Huajun
 * 20160821
 * 测试通过,功能完整 
 */
public class QRCodeUtil {

	private static final Logger logger = Logger.getLogger(QRCodeUtil.class);
	private static final Map<EncodeHintType, Object> defalutEncodeHints = 
			new HashMap<EncodeHintType, Object>();
	static {
		defalutEncodeHints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		defalutEncodeHints.put(EncodeHintType.MARGIN, 0);  
	}
	private static final Map<DecodeHintType, Object> defalutDecodeHints = 
			new HashMap<DecodeHintType, Object>();
	static {
		defalutDecodeHints.put(DecodeHintType.CHARACTER_SET, "utf-8"); 
	}
	private static final GraphicSize defaultSize = new GraphicSize(300,300); 
	private static final GraphicColor defaultColors = new GraphicColor(0xFF000000,0xFFFFFFFF); // FFCCDDEE
	
	/**
	 * 获取加密之后的二维码,后三个参数可以不指定
	 * @param contents 二维码的内容,为空则不会生成对象
	 * @param sz 二维码像素大小,为空则默认为 300 * 300
	 * @param infoColor 二维码前景色和背景色,不指定则默认前景色黑色,背景色白色
	 * @param hints 二维码的编码参数,不指定则默认utf8,无margin
	 * @return
	 */
	public static BufferedImage getBarCodeOfContents(String contents,
			GraphicSize sz,
			GraphicColor infoColor,
			Map<EncodeHintType, Object> hints) throws Exception{
		if(StringUtil.emptyString(contents)){
			throw new IllegalArgumentException("contents of bar code cannot be null");
		}
		MultiFormatWriter cdFormater = null;
		BitMatrix bm = null;
		BufferedImage bfImg = null;
		try {
			cdFormater = new MultiFormatWriter();
			Map<EncodeHintType, Object> actHints = null != hints && hints.isEmpty() ? hints : defalutEncodeHints;
			GraphicSize actSize = null != sz ? sz : defaultSize; 
			GraphicColor actColor = null != infoColor ? infoColor : defaultColors;  
			bm =  cdFormater.encode(contents, BarcodeFormat.QR_CODE, 
					actSize.getHeight(), actSize.getHeight(), 
					actHints);
			bfImg = new BufferedImage(bm.getHeight(), bm.getWidth(), BufferedImage.TYPE_INT_RGB);
			for(int i=0;i<bm.getWidth();i++){
				for(int j=0;j<bm.getWidth();j++){
					if(bm.get(i, j)){						
						bfImg.setRGB(i, j, actColor.getFgValue());
					}
					else{
						bfImg.setRGB(i, j,actColor.getBgValue());						
					} 
				}
			}
		} catch (Exception e) {
			logger.info(StackTraceUtil.getStackTrace(e)); 
		}
		
		return bfImg;
	}
	/**
	 * 根据contents编码成二维码图片,图片全路径名fileName
	 * @param fileName
	 * @param contents
	 * @throws Exception
	 */
	public static void produceBarCode(String fileName,String contents) throws Exception{
		String[] fNms = PathUtil.getFileNameOfFile(fileName);
		if(null == fNms || fNms.length < 2){
			throw new IllegalArgumentException("文件不正确");
		}		  
		BufferedImage bfImg = getBarCodeOfContents(contents, null, null, null);
		if(null == bfImg){
			logger.info("cannot create barcode");
			return ;
		}  
		ImageIO.write(bfImg, fNms[1], new File(fileName));		
	}
	public static String getContentsOfBarCode(String fileName) throws Exception{
		File f = new File(fileName);
		if(!f.exists()){
			throw new IllegalArgumentException("file is not exists!");
		}		 
		return getContentsOfBarCode(f);
	}
	public static String getContentsOfBarCode(File file) throws Exception{
		if(!file.exists()){
			throw new IllegalArgumentException("file is not exists!");
		} 
		BufferedImage bfImg = ImageIO.read(file);		
		return getContentsOfBarCode(bfImg,null);
	}
	
	/**
	 * 二维码解码
	 * @param bfImg
	 * @param hints
	 * @return
	 * @throws Exception
	 */
	private static String getContentsOfBarCode(BufferedImage bfImg,
			Map<DecodeHintType, Object> hints) throws Exception{	
		if(null == bfImg){
			throw new IllegalArgumentException("BufferedImage cannot be null!");
		}
		
		MultiFormatReader codeReader = null; 
		String decodeContext = null;
		try {
			codeReader = new MultiFormatReader();  
			LuminanceSource source = new BufferedImageLuminanceSource(bfImg); 
			Binarizer binarizer = new HybridBinarizer(source);  
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);  
			Map<DecodeHintType, Object> actHints = null != hints && hints.isEmpty() ? hints : defalutDecodeHints;
			Result result = codeReader.decode(binaryBitmap, actHints); 
			decodeContext = result.getText(); 
		} catch (Exception e) {
			logger.info(StackTraceUtil.getStackTrace(e)); 
		}
		return decodeContext;
	}
}
