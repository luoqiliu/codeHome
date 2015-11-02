package com.holydota.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

@Controller
@RequestMapping("/tools")
public class QrCodeController {

    private final static Integer DEFAULT_WEIGHT = 300;

    private final static Integer DEFAULT_HEIGHT = 300;

    //二维码颜色  
    private static final int     BLACK          = 0xFF000000;
    //二维码背景色  
    private static final int     WHITE          = 0xFFFFFFFF;

    @RequestMapping(value = "/qrcode", method = RequestMethod.GET)
    public void qr(HttpServletRequest request, HttpServletResponse response) {
        if (request == null) {
            return;
        }
        String url = request.getParameter("u");
        if (StringUtils.isBlank(url)) {
            return;
        }
        int weight = NumberUtils.toInt(request.getParameter("w"));
        weight = weight != 0 ? weight : DEFAULT_WEIGHT;
        int height = NumberUtils.toInt(request.getParameter("h"));
        height = height != 0 ? height : DEFAULT_HEIGHT;
        ServletOutputStream stream = null;
        try {
            stream = response.getOutputStream();
            QRCodeWriter writer = new QRCodeWriter();
            BitMatrix m = writer.encode(url, BarcodeFormat.QR_CODE, weight, height, getDecodeHintType());
            decodeQR_CODE2OutputStream(m, "JPEG", stream);
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 构建初始化二维码
     * 
     * @param bm
     * @return
     */
    public BufferedImage fileToBufferedImage(BitMatrix bm) {
        BufferedImage image = null;
        try {
            int w = bm.getWidth(), h = bm.getHeight();
            image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

            for (int x = 0; x < w; x++) {
                for (int y = 0; y < h; y++) {
                    image.setRGB(x, y, bm.get(x, y) ? BLACK : WHITE);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    /**
     * 将二维码生成为输出流
     * 
     * @param content
     * @param imageFormat
     * @param os
     */
    public void decodeQR_CODE2OutputStream(BitMatrix bm, String imageFormat, OutputStream os) {
        try {
            BufferedImage image = fileToBufferedImage(bm);

            ImageIO.write(image, imageFormat, os);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置二维码的格式参数
     * 
     * @return
     */
    public Map<EncodeHintType, Object> getDecodeHintType() {
        // 用于设置QR二维码参数
        Map<EncodeHintType, Object> hints = new HashMap<EncodeHintType, Object>();
        // 设置QR二维码的纠错级别（H为最高级别）具体级别信息
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        // 设置边界大小
        hints.put(EncodeHintType.MARGIN, 1);
        // 设置编码方式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.MAX_SIZE, 600);
        hints.put(EncodeHintType.MIN_SIZE, 600);

        return hints;
    }
}
