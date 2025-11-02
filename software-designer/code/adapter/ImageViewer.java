//@+leo-ver=5-thin
//@+node:swot.20251102090025.1: * @file code/adapter/ImageViewer.java
//@@language java
// Target 接口 - 客户端期望的图片阅读接口
interface ImageReader {
    void displayImage(String fileName);
}

// Adaptee - 已存在的JPEG图片阅读器（不兼容的接口）
class JpegReader {
    public void readJpegFile(String file) {
        System.out.println("正在显示JPEG图片: " + file);
        // 实际的JPEG显示逻辑
    }
}

// Adaptee - 已存在的PNG图片阅读器（不兼容的接口）  
class PngReader {
    public void renderPngImage(String filename) {
        System.out.println("正在渲染PNG图像: " + filename);
        // 实际的PNG渲染逻辑
    }
}

// Adapter - JPEG适配器
class JpegAdapter implements ImageReader {
    private JpegReader jpegReader;  // 组合关系：持有Adaptee实例
    
    public JpegAdapter() {
        this.jpegReader = new JpegReader();
    }
    
    @Override
    public void displayImage(String fileName) {
        // 适配：将 displayImage 调用转发给 readJpegFile
        jpegReader.readJpegFile(fileName);
    }
}

// Adapter - PNG适配器
class PngAdapter implements ImageReader {
    private PngReader pngReader;  // 组合关系：持有Adaptee实例
    
    public PngAdapter() {
        this.pngReader = new PngReader();
    }
    
    @Override
    public void displayImage(String fileName) {
        // 适配：将 displayImage 调用转发给 renderPngImage
        pngReader.renderPngImage(fileName);
    }
}

// Client - 客户端代码
public class ImageViewer {
    public void viewImage(ImageReader reader, String file) {
        System.out.println("图片查看器启动...");
        reader.displayImage(file);  // 只依赖Target接口
        System.out.println("图片显示完成");
        System.out.println();  // Add a blank line to show better
    }
    
    public static void main(String[] args) {
        ImageViewer viewer = new ImageViewer();
        
        // 使用适配器来兼容不同的图片格式
        ImageReader jpegReader = new JpegAdapter();
        ImageReader pngReader = new PngAdapter();
        
        viewer.viewImage(jpegReader, "photo.jpg");
        viewer.viewImage(pngReader, "diagram.png");
    }
}
//@-leo
