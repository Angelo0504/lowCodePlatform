package jnpf.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jnpf.util.DownUtil;
import jnpf.util.ServletUtil;
import jnpf.util.ZxingCodeUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;

/**
 * 生成条码
 *
 * @author JNPF开发平台组
 * @version V3.1.0
 * @copyright 引迈信息技术有限公司（https://www.jnpfsoft.com）
 * @date 2019年9月26日 上午9:18
 */
@RestController
@Api(tags = "（待定）生成条码", value = "BarCode")
@RequestMapping("/BarCode")
public class BarCodeController {

    /**
     * 生成二维码
     *
     * @return
     */
    @ApiOperation("生成二维码")
    @GetMapping("/BuildQRCode")
    public void buildQrCode() {
        BufferedImage image = ZxingCodeUtil.createCode(ServletUtil.getHeader("F_QRCodeContent"), 400, 400);
        DownUtil.write(image);
    }

    /**
     * 生成条形码
     *
     * @return
     */
    @ApiOperation("生成条形码")
    @GetMapping("/BuildBarCode")
    public void buildBarCode() {
        BufferedImage image = ZxingCodeUtil.getBarcode(ServletUtil.getHeader("F_BarCodeContent"), 265, 50);
        DownUtil.write(image);
    }
}
