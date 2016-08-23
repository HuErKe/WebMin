package api.com.chj.core.barcode;

import java.awt.Color;

/**
 * 代表前景色和背景色,用rgb值表示,或者用Color类表示
 * @author Chen Huajun
 *
 */
public class GraphicColor {

	private int 	fgValue = 0xFF000000;
	private int 	bgValue = 0xFFFFFFFF;
	private Color	fgColor = Color.BLACK;
	private Color	bgColor = Color.WHITE; 
	
	public GraphicColor(int fgValue,int bgValue){
		this.fgValue = fgValue;
		this.bgValue = bgValue;
	}
	public GraphicColor(Color fgColor,Color bgColor){
		this.fgColor = fgColor;
		this.bgColor = bgColor;
	}
	public int getFgValue() {
		return fgValue;
	}
	public void setFgValue(int fgValue) {
		if(fgValue == this.fgValue){
			return ;
		}
		synchronized(this){
			this.fgValue = fgValue;
			this.fgColor = new Color(fgValue);
		}
	}
	public int getBgValue() {
		return bgValue;
	}
	public void setBgValue(int bgValue) {
		if(bgValue == this.bgValue){
			return ;
		}
		synchronized(this){
			this.bgValue = bgValue;
			this.bgColor = new Color(bgValue);
		}
	}
	public Color getFgColor() {
		return fgColor;
	}
	public void setFgColor(Color fgColor) {
		if(fgColor == this.fgColor || fgColor.equals(fgColor)){
			return ;
		}
		synchronized(this){
			this.fgColor = fgColor;
			this.fgValue = fgColor.getRGB();
		} 
	}
	public Color getBgColor() {
		return bgColor;
	}
	public void setBgColor(Color bgColor) {
		if(bgColor == this.bgColor || bgColor.equals(bgColor)){
			return ;
		}
		synchronized(this){
			this.bgColor = bgColor;
			this.fgValue = bgColor.getRGB();
		} 
	}
	
}
