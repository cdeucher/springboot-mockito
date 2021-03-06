package com.app.product.controller;


import com.app.product.dao.ImageDaoImpl;
import com.app.product.entity.Image;
import com.app.product.service.ImageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.app.product.common.Common.generateRandomId;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/images")
public class ImagesController {

    private static ImageService imgService;

    public ImagesController(ImageDaoImpl dao) {
        imgService = new ImageService(dao);
    }

    @RequestMapping(value ="/{productId}", method = RequestMethod.GET)
    public String list(Model model, @PathVariable int productId){
        List imageList = imgService.list(productId);
        model.addAttribute("imageList", imageList);
        model.addAttribute("productId", productId);
        return "prod/image";
    }

    @ResponseBody
    @RequestMapping(value ="/{productId}", method = RequestMethod.POST)
    public List imgListJson(@PathVariable int productId){
        return imgService.list(productId);
    }

    @ResponseBody
    @RequestMapping("/{productId}/{imageId}")
    public Image imgList(@PathVariable int productId, @PathVariable int imageId){
        return imgService.getImage(imageId);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addImage(Model model, @RequestParam String name,@RequestParam String url,@RequestParam int productId){
        int id = generateRandomId();
        imgService.insertNewImage(new Image(name, url, productId, id));
        model.addAttribute("imageList", imgService.list(productId));
        model.addAttribute("productId", productId);
        return "prod/image";
    }

    @RequestMapping("/del/{imageId}")
    public String removeProduct(Model model, @PathVariable int imageId){
        imgService.removeImageById(imageId);
        return "prod/image";
    }
}
