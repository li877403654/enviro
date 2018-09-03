package envir.web.file.action;

import envir.sys.entity.SysFile;
import envir.sys.service.SysFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import os.SysCommand;
import pub.functions.FsFuncs;
import pub.functions.ReqFuncs;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;

/**
 * Created by zzl on 2015/11/1.
 */
@Controller
public class GetAction {

    private final String _thumbFileName = "__thumb.jpg";

    @RequestMapping
    public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Integer id = ReqFuncs.getIntegerParam(request, "id");
        boolean download = request.getParameter("download") != null;
        boolean thumb = request.getParameter("thumb") != null;

        ServletContext sc = request.getSession().getServletContext();
        String sysFileDir = sc.getRealPath("/sysfile");
        String SEP = File.separator;
        //
        File fileDir = new File(sysFileDir + SEP + id);
        String[] fileNames = fileDir.list();
        String fileName = null;
        if (fileNames == null || fileNames.length == 0) {
            fileDir.mkdirs();
            SysFile sysFile = sysFileService.get(id);
            if (sysFile == null) {
                return;
            }
            fileName = FsFuncs.getFileName(sysFile.getName());

            String localFileName = "file";
            int lastDotPos = fileName.lastIndexOf('.');
            if(lastDotPos != -1) {
                localFileName += fileName.substring(lastDotPos);
            }
            //
            fileName = localFileName;

            File file = new File(fileDir.getAbsolutePath() + SEP + fileName);
            OutputStream os = new BufferedOutputStream(new FileOutputStream(file));
            os.write(sysFile.getContent());
            os.close();
        }
        else {
            //fileName = fileNames[0];
            for(String tFileName: fileNames) {
                if(!tFileName.startsWith("__thumb")) {
                    fileName = tFileName;
                    break;
                }
            }
            if(fileName == null) {
                throw new RuntimeException("??");
            }
        }

        if(thumb) {
            response.setHeader("Content-disposition", "inline; filename=__thumb.jpg");
            response.setContentType("image/jpeg");
            generateThumb(sc, fileDir, fileName);
            fileName = _thumbFileName;
        }
        else if (download) {
            String attachmentName = new String(fileName.getBytes("GB2312"), "iso8859-1");
            response.setHeader("Content-disposition", "attachment; filename=" + attachmentName);
            response.setContentType("application/x-download");
        }
        else {
            String attachmentName = new String(fileName.getBytes("GB2312"), "iso8859-1");
            response.setHeader("Content-disposition", "inline; filename=" + attachmentName);
//			response.setContentType("application/octet-stream");

            File file = new File(fileDir, fileName);
            String contentType = null;
            try {
                contentType = Files.probeContentType(file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
            response.setContentType(contentType);
        }
        String filePath = "/sysfile/" + id + "/" + fileName;
        sc.getRequestDispatcher(filePath).forward(request, response);
//        RequestDispatcher rd = request.getServletContext().getNamedDispatcher("default");
    }

    private void generateThumb(ServletContext sc, File fileDir,
                               String fileName) throws IOException {
        File thumbFile = new File(fileDir, _thumbFileName);
        if(thumbFile.exists()) {
            return;
        }
        String ext = FsFuncs.getExt(fileName);
        if(ext.equals("jpg") || ext.equals("png") ||
                ext.equals("gif") || ext.equals("bmp") || ext.equals("jpeg")) {
            generateImageThumb(fileDir, fileName);
        }
        else if(ext.equals("mp4")) {
            generateVideoThumb(fileDir, fileName);
        }
        else {
            String fallbackThumbFilePath = sc.getRealPath("/image/filetype-blank.jpg");
            Files.copy(new File(fallbackThumbFilePath).toPath(),
                    thumbFile.toPath());
        }
    }

    private void generateVideoThumb(File fileDir, String fileName) throws IOException {
        File inputFile = new File(fileDir, fileName);
        String inputFilePath = inputFile.getAbsolutePath();

        File outputPngFile = new File(fileDir, "__thumb.png");
        String outputPngFilePath = outputPngFile.getAbsolutePath();

        SysCommand sc = new SysCommand("ffmpeg", "-y", "-i", inputFilePath,
                "-frames:v", "1", "-ss", "0", outputPngFilePath);
        sc.run(true);
        long outputSize = outputPngFile.length();
        if(outputSize < 1024*4) { //lt 4k
            String testOutputFile = new File(fileDir, "__thumb_test.png").getAbsolutePath();
            for(int n = 1; n <= 4; n++) {
                sc = new SysCommand("ffmpeg", "-y", "-i", inputFilePath,
                        "-frames:v", "1", "-ss", "" + n, testOutputFile);
                sc.run(true);
                long testOutputSize = new File(testOutputFile).length();
                if(1.0 * testOutputSize / outputSize > 1.1) {
                    outputSize = testOutputSize;
                    outputPngFile.delete();
                    new File(testOutputFile).renameTo(outputPngFile);
                }
                else {
                    new File(testOutputFile).delete();
                }
            }
        }
        BufferedImage bufImg = ImageIO.read(outputPngFile);
        int width = bufImg.getWidth();
        int height = bufImg.getHeight();
        int newWidth = Math.min(width, 720);
        int newHeight = newWidth * height / width;
        BufferedImage newBufImg = new BufferedImage(newWidth,
                newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = newBufImg.createGraphics();
        g.drawImage(bufImg, 0, 0, newWidth, newHeight, null);
        File outputJpgFile = new File(fileDir, _thumbFileName);
        ImageIO.write(newBufImg, "jpg", outputJpgFile);
        outputPngFile.delete();
    }

    private void generateImageThumb(File fileDir, String fileName) throws IOException {
        File file = new File(fileDir, fileName);
        File thumbFile = new File(fileDir, _thumbFileName);
        BufferedImage bufImg = ImageIO.read(file);
        int width = bufImg.getWidth();
        int height = bufImg.getHeight();
        if(fileName.endsWith(".jpg") && width < 720) {
            Files.copy(file.toPath(), thumbFile.toPath());
            return;
        }
        else {
            int newWidth = Math.min(width, 720);
            int newHeight = newWidth * height / width;
            BufferedImage newBufImg = new BufferedImage(newWidth,
                    newHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = newBufImg.createGraphics();
            g.drawImage(bufImg, 0, 0, newWidth, newHeight, null);
            ImageIO.write(newBufImg, "jpg", thumbFile);
        }
    }

    @Autowired
    private SysFileService sysFileService;
}
