package api.com.chj.core.barcode;

/**
 * 代表长和宽的矩形大小
 * @author Chen Huajun
 *
 */
public class GraphicSize {

	private int width;
	private int height;
	public GraphicSize(){
		
	}
	public GraphicSize(int width,int height){
		this.width = width;
		this.height = height;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
